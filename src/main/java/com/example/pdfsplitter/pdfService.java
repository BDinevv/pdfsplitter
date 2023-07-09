package com.example.pdfsplitter;

import jakarta.servlet.ServletOutputStream;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static java.nio.file.Paths.get;

@Service
public class pdfService {
    public void pdf(MultipartFile file, int from, int to) throws IOException {
            // Loading PDF
            InputStream data = new ByteArrayInputStream(file.getBytes());
            PDDocument document = PDDocument.load(data);

            // Splitter Class
            Splitter splitting = new Splitter();
            splitting.setStartPage(from);
            splitting.setEndPage(to);

            // Getting the subpages that we want
            List<PDDocument> splitPages =  splitting.split(document);

            PDDocument newDocument = new PDDocument();
            for (PDDocument page : splitPages){
            newDocument.addPage(page.getPage(0));
            }

            // Saving the subpages as PDF file
        newDocument.save("C:/Disc/splitPDF.pdf");
            System.out.println("Splitted Pdf Successfully.");
            document.close();
        //return newDocument;
    }

    public ResponseEntity<Resource> generateDownloadLink() throws IOException {
        Path file =  get("C:/Disc").toAbsolutePath().normalize().resolve("splitPDF.pdf");

        var fileName = "splitPDF.pdf";
        var resource = new UrlResource(file.toUri());

        System.out.println(file.toUri());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", fileName);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name="+fileName);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(file))).headers(httpHeaders).body(resource);
    }
}
