package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import domain.User;

import repositories.UserRepository;


@Service
@Transactional
public class UserService {

	// Managed repository --------------------------------------------------------------------------------------
	
	@Autowired
	private UserRepository userRepository;
	
	// Supporting service --------------------------------------------------------------------------------------
	
	@Autowired
	
	
	// Constructor ---------------------------------------------------------------------------------------------
	
	public UserService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------------------------------------------------------
	
	public User create(){
		
		
		User result;
		result = new User();
		return result;
	}
	
	public User save(User result){
		User res = new User();
		userRepository.save(res);
		
		return res;
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
}