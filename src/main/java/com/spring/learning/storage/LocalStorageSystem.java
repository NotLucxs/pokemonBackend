package com.spring.learning.storage;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.spring.learning.pokemon.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class LocalStorageSystem implements IStorageService{

    private static final Logger log = LoggerFactory.getLogger(LocalStorageSystem.class);
    private final Path dir = Paths.get("pokemon");
    private final Gson gson = new Gson();

    @Override
    public String store(MultipartFile file) {
        if(file.isEmpty()){
            throw new StorageException("File cannot be empty!");
        }
        var destinationFile = this.dir.resolve(
                Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();
        System.out.println("Saving to: "+destinationFile.toString());
        if(!destinationFile.getParent().equals(this.dir.toAbsolutePath())) {
            throw new StorageException("Cannot store file outside current directory!");
        }

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return file.getOriginalFilename();
        } catch(IOException e) {
            throw new StorageException("Unable to store file!", e);
        }
    }

    @Override
    public Resource serveImage(String filename) {
        if(filename.isEmpty()){
            throw new StorageException("Empty filename!");
        }
        try {
            Path file = load(filename);
            System.out.println(file);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                log.error("Requested resource: "+filename+" does not exist");
                throw new StorageException("Unable to load image :(");
            }
        } catch(MalformedURLException e){
            throw new StorageException("Unable to load image :(", e);
        }
    }

    public Pokemon getPokemonDataById(String id) throws StorageException{
        try {
            var reader = new JsonReader(new FileReader(dir + "/pokedex.json"));
            Pokemon[] pokemons = gson.fromJson(reader, Pokemon[].class);
            return Arrays.stream(pokemons).filter(pokemon -> String.valueOf(pokemon.getId()).equals(id)).findFirst().get();
        } catch(FileNotFoundException e) {
            throw new StorageException("Unable to find pokedex.json!");
        } catch(Exception e) {
            throw new StorageException("Unable to find pokemon "+id);
        }
    }

    @Override
    public Path load(String filename) {
        return dir.resolve(filename);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(dir, 1)
                    .filter(path -> !path.equals(dir))
                    .map(dir::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public void deleteImage(Path path) {

    }
}
