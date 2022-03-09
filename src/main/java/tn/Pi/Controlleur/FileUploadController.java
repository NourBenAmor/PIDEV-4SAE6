package tn.Pi.Controlleur;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.Pi.Service.FileUploadService;

@RestController
public class FileUploadController {
	@Autowired
	FileUploadService fs;
	@PostMapping("/filefile")
	public void uploadfile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException
	{
	fs.uploadFile(file);	
	}

}
