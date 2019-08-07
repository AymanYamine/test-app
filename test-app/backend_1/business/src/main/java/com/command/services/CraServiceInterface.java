package com.command.services;


import java.util.List;
import java.util.Optional;


import com.command.model.Cra;

public interface CraServiceInterface {

	public List<Cra> getbymonthbefore(int mymonth);

	public List<Cra> getbymonthafter(int mymonth);

	

	public List<Cra> getbymonth(int mymonth);

	public List<Cra> getAll();


	public Optional<Cra> get(Long id);

	public void add(Cra cra);

	public void delete(Long id);

	public void updatee(Long id, Cra cra);

}
