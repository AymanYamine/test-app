package com.command.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.command.model.Cra;
import com.command.model.Document;

public interface DocumentServiceInterface {
	
	//public ResponseEntity<Object>  saveupload(Long id,MultipartFile file, String type) throws IOException;

	public List<Document> getDoc();
}
