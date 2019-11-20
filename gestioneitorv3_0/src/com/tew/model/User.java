package com.tew.model;

import java.io.Serializable;

public class User implements Serializable {
	private int serialVersionUID=25;
	
	private String name;
	private String passwd;
	
	public User(String n,String p){
		name=n;
		passwd=p;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setLogin1(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
