package com.command.services;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.command.model.Cra;
import com.command.model.Document;
import com.command.repository.CraRepository;
import com.command.repository.DocumentRepository;

@Service
public class DocumentService implements DocumentServiceInterface {

	@Autowired
	DocumentRepository documentRepository;
	

	public List<Document> getDoc() {
		return documentRepository.findAll();
	}

	public void addDoc(Document  document) {

		Date date = new Date(System.currentTimeMillis());
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		document.setCreatedAt(ts);
		documentRepository.save(document);
		System.out.println("Document ajouté !");

	}

	
	
	
/*	public ResponseEntity<Object> saveupload(Long id,MultipartFile file,String type) throws IOException {
	
		Document document = new Document(1, "test", "test", "texte", null, null) ;
		document.setType(type);
		int mount = 1;
		File convertFile = new File("C:\\Users\\LENOVO\\client"+mount+".txt");
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
	//	=document.c
		document.setDocument_name("client"+mount);
		document.setDocument_location("C:\\Users\\LENOVO\\client"+mount+".txt");
		Date date = new Date(System.currentTimeMillis());
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		document.setCreatedAt(ts);
		Optional<Cra> cra =craRepository.findById(id).;
		cra.ifPresent(present -> {
			document.setCra(present);    
		});
		
	
		
		documentRepository.save(document);
		mount++;
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);

	}*/

}
