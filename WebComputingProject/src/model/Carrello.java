package model;

import java.io.Serializable;

public class Carrello implements Bean, Serializable {
	private int carrello_utente;
	private int carrello_brano;
	private String date_ins;

	public Carrello() {
		super();
	}

	public int getCarrello_utente() {
		return carrello_utente;
	}

	public void setCarrello_utente(int carrello_utente) {
		this.carrello_utente = carrello_utente;
	}

	public int getCarrello_brano() {
		return carrello_brano;
	}

	public void setCarrello_brano(int carrello_brano) {
		this.carrello_brano = carrello_brano;
	}

	public String getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(String date_ins) {
		this.date_ins = date_ins;
	}

}
