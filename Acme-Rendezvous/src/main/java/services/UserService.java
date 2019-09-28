package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.User;
import forms.ActorForm;


@Service
@Transactional
public class UserService {

	// Managed repository --------------------------------------------------------------------------------------
	
	@Autowired
	private UserRepository userRepository;
	
	// Supporting service --------------------------------------------------------------------------------------
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private Validator validator;
	
	// Constructor ---------------------------------------------------------------------------------------------
	
	public UserService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------------------------------------------------------
	
	public User create(){
		
		User result = new User();
	//	result.setName("name"+result.getId());
	//	result.setSurname("surname" + result.getId());
		
		UserAccount ua = userAccountService.create();
		Authority au = new Authority();
		au.setAuthority("USER");
		ua.getAuthorities().add(au);
		result.setUserAccount(userAccountService.save(ua));
		return result;
	}
	
	public User save(User result){

		return userRepository.save(result);

	}
	
	public void delete(User res){
	
		userRepository.delete(res);
	}
	
	public Collection<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(int id){
		return userRepository.findOne(id);
	}
	
	
	//extra methods
	
	//The ratio of users who have ever created a rendezvous 
	//versus the users who have never created any rendezvouses.
	
	public User reconstruct(ActorForm actor, BindingResult binding){
		User result;
		
		if(actor.getId()==0){
			result = this.create();
					
			result.setName(actor.getName());
			result.setSurname(actor.getSurname());
			result.setEmail(actor.getEmail());
			result.setPhoneNumber(actor.getPhoneNumber());
			result.setPostalAddress(actor.getPostalAddress());
		}else{
			result = userRepository.findOne(actor.getId());
			
			result.setName(actor.getName());
			result.setSurname(actor.getSurname());
			result.setEmail(actor.getEmail());
			result.setPhoneNumber(actor.getPhoneNumber());
			result.setPostalAddress(actor.getPostalAddress());
			
			validator.validate(result,binding);
		}
		return result;
	}
	
	public Double ratioUsersCreatedRendezvous(){
		Double res;
		Long uR = userRepository.NumberUsersCreatedRendezvouses();
		res = (double) uR/userRepository.findAll().size();
		return res;
	}
	
	public User findByUserAccount(UserAccount u){
		User res = userRepository.FindByUserAccount(u);
		return res;
	}
	
	
	
	
}