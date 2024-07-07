package com.spring.learning.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

    String store(MultipartFile file);

    Resource serveImage(String filename);

    Path load(String filename);

    Stream<Path> loadAll();

    void deleteImage(Path path);
}
