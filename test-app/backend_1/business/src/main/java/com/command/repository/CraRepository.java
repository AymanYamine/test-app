package com.command.repository;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.*;

import com.command.model.Cra;

public interface CraRepository extends JpaRepository<Cra, Long>{

	 @Modifying
	   @Transactional
	public void deleteById(Long id);



	 
	
	
}