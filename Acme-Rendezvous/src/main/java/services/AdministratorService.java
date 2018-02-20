package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import domain.Administrator;

import repositories.AdministratorRepository;


@Service
@Transactional
public class AdministratorService {

	// Managed repository --------------------------------------------------------------------------------------
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	// Supporting service --------------------------------------------------------------------------------------
	
	@Autowired
	//	private UserService userService;
	
	// Constructor ---------------------------------------------------------------------------------------------
	
	public AdministratorService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------------------------------------------------------
	
	public Administrator create(){
		
		
		Administrator result;
		result = new Administrator();
		return result;
	}
	
	public Administrator save(Administrator result){
		Administrator res = new Administrator();
		administratorRepository.save(res);
		
		return res;
	}
	
	public void delete(Administrator res){
	
		administratorRepository.delete(res);
	}
	
	public Collection<Administrator> findAll(){
		return administratorRepository.findAll();
	}
	
	public Administrator findOne(int id){
		return administratorRepository.findOne(id);
	}
}