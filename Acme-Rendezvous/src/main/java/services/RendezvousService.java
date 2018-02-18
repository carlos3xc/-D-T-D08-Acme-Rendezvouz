package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RendezvousService {

	// Managed repository --------------------------------------------------------------------------------------
	
	@Autowired
	private RendezvousRepository rendezvousRepository;
	
	// Supporting service --------------------------------------------------------------------------------------
	
	@Autowired
//	private UserService userService;
	
	// Constructor ---------------------------------------------------------------------------------------------
	
	public RendezvousService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------------------------------------------------------
	
	public Rendezvous create(){
		Assert.isTrue(checkUser());
		
		Rendezvous result;
		result = new Rendezvous();
		
		result.setListAttendants(new ArrayList<String>());
		result.setAnnouncements(new ArrayList<Announcement>());
		result.setComments(new ArrayList<Comment>());
		result.setFinalMode(false);
		result.setLinkedRendezvous(new ArrayList<Rendezvous>());
		result.setQuestions(new ArrayList<Question>());
		
		return result;
	}
	
	public Rendezvous save(Rendezvous result){
		Assert.isTrue(checkUser());
		Assert.notNull(result);
		
		return rendezvousRepository.save(result);
	}
	
	public void delete(Rendezvous result){
		Assert.isTrue(checkUser() || checkAdmin());
		Assert.isTrue(!result.getFinalMode());
		
		result.setFinalMode(true);
		result.setFlag("DELETED");
		
		rendezvousRepository.save(result);
	}
	
	public Collection<Rendezvous> findAll(){
		return rendezvousRepository.findAll();
	}
	
	public Rendezvous findOne(int id){
		return rendezvousRepository.findOne(id);
	}
	
	// Other business methods ----------------------------------------------------------------------------------
	
// Aqui falta un metodo en userService que se hara con queries, aunque este metodo tmbn se podria hacer con una query en
// UserRepository
	/*
	public Collection<Rendezvous> getRendezvousOwnedBy(UserAccount userAccount){
		Collection<Rendezvous> result = new ArrayList<Rendezvous>();
		User user = userService.findByUserAccount(userAccount);
		for(Rendezvous r:this.findAll()){
			if(r.getUser().equals(user)){
				result.add(r);
			}
		}
		return result;
	}*/
	
	public Double getAverageRendezvousPerUser(){
		return rendezvousRepository.getAverageRendezvousPerUser();
	}
	
	public Double getDeviatonRendezvousCreatedPerUser(){
		return rendezvousRepository.getDeviatonRendezvousCreatedPerUser();
	}
	
	public Double getAverageRendezvousRSVdPerUser(){
		return rendezvousRepository.getAverageRendezvousRSVdPerUser();
	}
	
	public Double getDeviatonRendezvousRSVdPerUser(){
		return rendezvousRepository.getDeviatonRendezvousRSVdPerUser();
	}
	
	public Double getAverageRendezvousPerAnnouncement(){
		return rendezvousRepository.getAverageRendezvousPerAnnouncement();
	}
	
	private Boolean checkUser(){
		Boolean result = false;
		UserAccount ua = LoginService.getPrincipal();
		for(Authority a:ua.getAuthorities()){
			if(a.equals(Authority.USER))
				result = true;
		}
		return result;
	}
	
	private Boolean checkAdmin(){
		Boolean result = false;
		UserAccount ua = LoginService.getPrincipal();
		for(Authority a:ua.getAuthorities()){
			if(a.equals(Authority.ADMIN))
				result = true;
		}
		return result;
	}
}