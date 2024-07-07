package com.spring.learning.storage;

import com.google.gson.Gson;
import com.spring.learning.pokemon.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*")
public class StorageController {

    @Autowired
    private LocalStorageSystem localStorageSystem;

    private Gson gson = new Gson();

    @GetMapping("/helloworld")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }

//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
//        var res = localStorageSystem.store(file);
//        redirectAttributes.addFlashAttribute("message", "You successfully uploaded +"+file.getOriginalFilename()+"!");
//        redirectAttributes.addAttribute("filename", res);
//
//        return "redirect:/files/{filename}";
//    }

    @GetMapping(value = "/pokemon/img/{filename:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        var image = localStorageSystem.serveImage(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ image.getFilename())
                .body(image);
    }

    @GetMapping(value = "/pokemon/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getPokemonData(@PathVariable String id) {
        try {
            return gson.toJson(localStorageSystem.getPokemonDataById(id), Pokemon.class);
        } catch(StorageException e){
            return String.format("Pokemon %s ID does not exist", id);
        }
    }

//    @GetMapping("/uploadForm")
//    public String uploadForm(Model model) {
//        model.addAttribute("files", localStorageSystem.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(StorageController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

//    @GetMapping("/files/all")
//    public String listUploadedFiles(Model model) throws IOException {
//        model.addAttribute("files", localStorageSystem.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(StorageController.class,
//                                "serveImage", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

}
