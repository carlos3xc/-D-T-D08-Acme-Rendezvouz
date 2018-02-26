
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	private RendezvousRepository	rendezvousRepository;


	// Supporting service --------------------------------------------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserService userService;
	
	// Constructor ---------------------------------------------------------------------------------------------
	public RendezvousService() {
		super();
	}

	// Simple CRUD methods -------------------------------------------------------------------------------------

	public Rendezvous create() {
		Assert.isTrue(this.checkUser());

		Rendezvous result;
		result = new Rendezvous();
		
		//Se añade el usuario creador a la lista de antedidos por defecto.
		List<User> list = new ArrayList<>();
		list.add((User)actorService.findByPrincipal());
		result.setListAttendants(list);
		
		result.setAnnouncements(new ArrayList<Announcement>());
		result.setComments(new ArrayList<Comment>());
		result.setFinalMode(false);
		result.setLinkedRendezvous(new ArrayList<Rendezvous>());
		result.setQuestions(new ArrayList<Question>());
		result.setFlag("PASSED");

		return result;
	}

	public Rendezvous save(final Rendezvous result) {
		Assert.isTrue(this.checkUser());
		Assert.notNull(result);

		return this.rendezvousRepository.save(result);
	}

	public void delete(final Rendezvous result) {
		Assert.isTrue(this.checkUser() || this.checkAdmin());
		Assert.isTrue(!result.getFinalMode());

		result.setFinalMode(true);
		result.setFlag("DELETED");

		this.rendezvousRepository.save(result);
	}

	public Collection<Rendezvous> findAll() {
		return this.rendezvousRepository.findAll();
	}

	public Rendezvous findOne(final int id) {
		return this.rendezvousRepository.findOne(id);
	}

	// Other business methods ----------------------------------------------------------------------------------
	
	 public Collection<Rendezvous> getRendezvousOwnedBy(UserAccount userAccount){
		 Collection<Rendezvous> result = new ArrayList<Rendezvous>();
		 User user = userService.findByUserAccount(userAccount);
		 for(Rendezvous r:this.findAll()){
			 if(r.getUser().equals(user)){
				 result.add(r);
			 }
		 }
		 return result;
	 	}

	public Double getAverageRendezvousPerUser() {
		return (double) (this.findAll().size() / userService.findAll().size());
	}
/*
	public Double getDeviatonRendezvousCreatedPerUser() {
		return this.rendezvousRepository.getDeviatonRendezvousCreatedPerUser();
	}
*/
	public Double getAverageRendezvousRSVdPerUser() {
		return (double) (rendezvousRepository.getRendezvousRSVd().size()/userService.findAll().size());
	}
/*
	public Double getDeviatonRendezvousRSVdPerUser() {
		return this.rendezvousRepository.getDeviatonRendezvousRSVdPerUser();
	}

	public Double getAverageRendezvousPerAnnouncement() {
		return this.rendezvousRepository.getAverageRendezvousPerAnnouncement();
	}*/

	public Collection<Rendezvous> getRendezvousUser(User user){
		return rendezvousRepository.getRendezvousUser(user);
	}
	public Rendezvous getRendezvousById(int rendezvousId){
		return rendezvousRepository.getRendezvousById(rendezvousId);
	}
	private Boolean checkUser() {
		Boolean result = false;
		final UserAccount ua = LoginService.getPrincipal();
		for (final Authority a : ua.getAuthorities())
			if (a.equals(Authority.USER))
				result = true;
		return result;
	}

	private Boolean checkAdmin() {
		Boolean result = false;
		final UserAccount ua = LoginService.getPrincipal();
		for (final Authority a : ua.getAuthorities())
			if (a.equals(Authority.ADMIN))
				result = true;
		return result;
	}
}
