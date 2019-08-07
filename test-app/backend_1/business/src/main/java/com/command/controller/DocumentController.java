package com.command.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.command.model.Document;
import com.command.services.DocumentService;

@CrossOrigin(origins = "*")

@RestController

@RequestMapping(value = "/documents")

public class DocumentController {
	@Autowired
	private DocumentService documentService;
	
	@GetMapping(value = { "/" })
	public List<Document> getAlldoc() {
		return documentService.getDoc();

	}
}
