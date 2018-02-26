/*
 * Actor.java
 * 
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import domain.DomainEntity;
import domain.Location;

@Entity
@Access(AccessType.PROPERTY)
public class RendezvousForm extends DomainEntity {

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

}
