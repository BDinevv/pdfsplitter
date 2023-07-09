package com.example.pdfsplitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@CrossOrigin (origins = {"http://localhost:4200", "https://brave-dawn-30133.pktriot.net"})
@RestController
public class Controller {

    @Autowired
    pdfService service;


    @PostMapping("/hi")
    public String first(@RequestParam("file") MultipartFile file, @RequestParam("to") String toPage, @RequestParam("from") String fromPage) throws IOException {
        int customFromPage = Integer.parseInt(fromPage);
        int customToPage = Integer.parseInt(toPage);
        service.pdf(file, customFromPage, customToPage);
        return "Download you file from here: ";
    }

    @GetMapping("/hi/download")
    public ResponseEntity<Resource> downloadOneFile() throws IOException {
        return service.generateDownloadLink();
    }

    @PostMapping("/feedback")
    public String feedback(@RequestBody FeedbackViewModel fb, BindingResult bindingResult){
        return "Feedback received! Thank you!";
    }

    @GetMapping("/healthcheck")
    public String feedback(){
        return "Available!";
    }

}
