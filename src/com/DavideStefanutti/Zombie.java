package com.DavideStefanutti;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table(name = "zombies")
public class Zombie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	int vita;
	String nome;
	String img; 
	String commento;
	
	
	
	public Zombie() {
		// TODO Auto-generated constructor stub
		vita = 100;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setup(int vita, String nome, String img) {
		this.vita = vita;
		this.nome = nome;
		this.img = img;
		this.setCommento(this.nome + " - " + "ha iniziato la sua avventura come zombie");
	}
	
	
	public String getName() {
		return this.nome;
	}
	
	public String getImg() {
		return this.img;
	}
	
	public int getVita() {
		return this.vita;
	}
	
	public boolean setVita(int vita) {
		this.vita = vita;
		if(vita<= 0) {
			return true;
		}
		return false;
	}
	
	public boolean decrementaVita(int dec) {
		this.vita -= dec;
		if(this.vita <=0) {
			this.vita = 0;
			return true;
		}
		return false;
	}
	
	public void incrementaVita(int dec) {
		this.vita += dec;
		if(vita > 100) {
			vita = 100;
		}
	}
	
	public void kill() {
		this.vita = 0;
	}
	
	
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	public String getCommento() {
		return this.commento;
	}
	

}
