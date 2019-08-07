package com.command.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.command.model.Cra;
import com.command.model.Document;

public interface DocumentRepository  extends JpaRepository<Document, Long> {
	
	 @Modifying
	   @Transactional
	public void deleteById(Long id);
	
}
