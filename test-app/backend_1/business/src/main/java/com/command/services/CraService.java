package com.command.services;

//import java.util.Date;
import java.sql.Timestamp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.command.model.Cra;
import com.command.repository.CraRepository;

@Service
public class CraService implements CraServiceInterface {
	@Autowired
	private CraRepository craRepository;

	public List<Cra> getbymonthbefore(int mymonth) {
		List<Cra> m = craRepository.findAll();
		List<Cra> n = new ArrayList<Cra>();
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getMonth() < mymonth)
				n.add(m.get(i));
		}

		return n;
	}

	public List<Cra> getbymonthafter(int mymonth) {
		List<Cra> m = craRepository.findAll();
		List<Cra> n = new ArrayList<Cra>();
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getMonth() > mymonth)
				n.add(m.get(i));
		}

		return n;
	}

	public List<Cra> getbymonth(int mymonth) {
		List<Cra> m = craRepository.findAll();
		List<Cra> n = new ArrayList<Cra>();
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getMonth() == mymonth)
				n.add(m.get(i));
		}

		return n;
	}

	public List<Cra> getAll() {
		return craRepository.findAll();
	}

	public Optional<Cra> get(Long id) {
		return craRepository.findById(id);

	}

	public void add(Cra cra) {

		Date date = new Date(System.currentTimeMillis());
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		cra.setCreatedAt(ts);
		cra.getDocument().setCreatedAt(ts);
		craRepository.save(cra);
		System.out.println("Cra ajouté !");

	}

	public void delete(Long id) {
		

		Optional<Cra> cra = craRepository.findById(id);
		cra.ifPresent(present -> {
			String docpath = present.getDocument().getDocument_location();
			Path path = Paths.get(docpath);
			try {
				Files.delete(path);
				System.out.println(docpath);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		craRepository.deleteById(id);
	//	System.out.println("cra supprimé ! ");

	}

	public void updatee(Long id, Cra cra) {
		Date date = new Date(System.currentTimeMillis());
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		cra.setUpdatedAt(ts);
		Optional<Cra> craupdate = craRepository.findById(id);
		craupdate.ifPresent(crapresent -> {
			
			if ((crapresent.getDocument().getDocument_name()!= null)&&(crapresent.getDocument().getDocument_name()!=cra.getDocument().getDocument_name())){
				
			
			String docpath = crapresent.getDocument().getDocument_location();
			Path path = Paths.get(docpath);
			try {
				Files.delete(path);
				System.out.println(docpath);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		});
		craRepository.save(cra);
		System.out.println("cra mis à jours ! ");

	}

}
