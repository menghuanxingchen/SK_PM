package com.repast.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;


@Entity(name="exampleentity")
public class ExampleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ID
	private String id;
	private String name;
	private Date birthday;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
