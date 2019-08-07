package com.command.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.command.model.Cra;

import com.command.services.CraService;

@CrossOrigin(origins = "*")

@RestController

@RequestMapping(value = "/cras")
public class Controller {
	@Autowired
	private CraService craService;

	@GetMapping(value = { "/", "" })
	public List<Cra> getAllCras() {
		return craService.getAll();
	}

	@PostMapping(value = { "/upload" })
	public void uploadCra(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
		File fileToSave = new File("C:\\Users\\LENOVO\\cra-app\\public\\docs\\" + file.getOriginalFilename());
		
		file.transferTo(fileToSave);
		
		System.out.println(file.getOriginalFilename());
	}

	@PostMapping(value = { "/", "" })
	public ResponseEntity<Cra> addCra(@RequestBody Cra cra) {

		craService.add(cra);
		 return new ResponseEntity<Cra>(cra, HttpStatus.CREATED);

	}

//	@GetMapping(value = { "/{id}" })
	// public Optional<Cra> getCra(@PathVariable Long id) {

	// return craService.get(id);
//	}

//}

	@PutMapping(value = { "/update/{id}" })
	public ResponseEntity<Cra> updateCra(@RequestBody Cra cra, @PathVariable Long id) {
		craService.updatee(id, cra);
		return new ResponseEntity<Cra>(cra, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public void deleteCra(@PathVariable Long id) {
		craService.delete(id);
		

	}

	

	@GetMapping(value = { "/month/{mo}" })
	public List<Cra> getCraMonth(@PathVariable int mo) {

		return craService.getbymonth(mo);
	}

	@GetMapping(value = { "/beforemonth/{mo}" })
	public List<Cra> getCrabeforeM(@PathVariable int mo) {

		return craService.getbymonthbefore(mo);
	}

	@GetMapping(value = { "/aftermonth/{mo}" })
	public List<Cra> getCraafterM(@PathVariable int mo) {

		return craService.getbymonthafter(mo);
	}

	

}