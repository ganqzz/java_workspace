package com.example.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Name implements Serializable {
	
	private String firstName;
	private String lastName;

	public Name(){ }
	
	public void setFirstName(String fname){
		this.firstName = fname;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setLastName(String lname){
		this.lastName = lname;
	}
	
	public String getLastName(){
		return this.lastName;
	}
}
