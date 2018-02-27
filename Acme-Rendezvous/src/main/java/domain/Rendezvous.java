/*
 * Actor.java
 * 
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Rendezvous extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String				name;
	private String				description;
	private Date				moment;
	private String				picture;
	private Location			gpsCoordinates;
	private Boolean 			finalMode;
	private Boolean 			isAdultContent;
	private String 				flag;

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public Location getGpsCoordinates() {
		return this.gpsCoordinates;
	}

	public void setGpsCoordinates(final Location gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}
	
	public Boolean getFinalMode(){
		return this.finalMode;
	}
	
	public void setFinalMode(final Boolean finalMode){
		this.finalMode = finalMode;
	}
	
	public Boolean getIsAdultContent(){
		return this.isAdultContent;
	}
	
	public void setIsAdultContent(final Boolean isAdult){
		this.isAdultContent = isAdult;
	}
	
	public String getFlag(){
		return this.flag;
	}
	@Pattern(regexp = "DELETED|PASSED")
	public void setFlag(final String flag){
		this.flag = flag;
	}


	// Relationships ----------------------------------------------------------

	private User 						user;
	private Collection<Comment> 		comments;
	private Collection<Rendezvous> 		linkedRendezvous;
	private Collection<Question> 		questions;
	private Collection<Announcement> 	announcements;
	private Collection<User> 			listAttendants;



	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Comment> getComments(){
		return this.comments;
	}
	
	public void setComments(final Collection<Comment> comments){
		this.comments = comments;
	}
	
	@ManyToMany 
	public Collection<User> getListAttendants(){
		return this.listAttendants;
	}
	
	public void setListAttendants(final Collection<User> listAttendants){
		this.listAttendants = listAttendants;
	}
	
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Question> getQuestions(){
		return this.questions;
	}
	
	public void setQuestions(final Collection<Question> questions){
		this.questions = questions;
	}
	
	@Valid
	@OneToMany()
	public Collection<Announcement> getAnnouncements(){
		return this.announcements;
	}
	
	public void setAnnouncements(final Collection<Announcement> announcements){
		this.announcements = announcements;
	}
	
	@Valid
	@OneToMany()
	public Collection<Rendezvous> getLinkedRendezvous(){
		return this.linkedRendezvous;
	}
	
	public void setLinkedRendezvous(final Collection<Rendezvous> linkedRendezvous){
		this.linkedRendezvous = linkedRendezvous;
	}
}
