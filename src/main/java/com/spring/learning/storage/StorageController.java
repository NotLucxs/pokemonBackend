package com.spring.learning.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
public class StorageController {

    @Autowired
    private LocalStorageSystem localStorageSystem;

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
