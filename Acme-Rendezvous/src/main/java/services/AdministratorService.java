package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import forms.ActorForm;


@Service
@Transactional
public class AdministratorService {

	// Managed repository --------------------------------------------------------------------------------------
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	// Supporting service --------------------------------------------------------------------------------------
	
	@Autowired
	private Validator validator;
	
	// Constructor ---------------------------------------------------------------------------------------------
	
	public AdministratorService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------------------------------------------------------
	
	public Administrator create(){
		
		
		Administrator result = new Administrator();
		result.setName("name"+result.getId());
		result.setSurname("surname" + result.getId());
		result = new Administrator();
		return result;
	}
	
	public Administrator save(Administrator admin){
		
		return administratorRepository.save(admin);
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
	
	
//Extra methods
	
	public Actor reconstruct(ActorForm actor, BindingResult binding){
		Administrator result;
		
		result = administratorRepository.findOne(actor.getId());
		
		result.setName(actor.getName());
		result.setSurname(actor.getSurname());
		result.setEmail(actor.getEmail());
		result.setPhoneNumber(actor.getPhoneNumber());
		result.setPostalAddress(actor.getPostalAddress());
		
		validator.validate(result,binding);
		
		return result;
	}
	
	public Administrator findByUserAccount(UserAccount u){
		Administrator res = administratorRepository.FindByUserAccount(u);
		
		
		return res;
	}
	
}