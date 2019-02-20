package model;

import java.io.Serializable;

public class Valutazione implements Bean, Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private int rating;
	private String commento;
	private int Brano_idBrano;
	private String Utente_nomeUtente;
	private String date_ins;
	
	public String getDate_ins() {
		return date_ins;
	}
	public void setDate_ins(String date_ins) {
		this.date_ins = date_ins;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	public int getBrano_idBrano() {
		return Brano_idBrano;
	}
	public void setBrano_idBrano(int brano_idBrano) {
		Brano_idBrano = brano_idBrano;
	}
	public String getUtente_nomeUtente() {
		return Utente_nomeUtente;
	}
	public void setUtente_nomeUtente(String utente_nomeUtente) {
		Utente_nomeUtente = utente_nomeUtente;
	}

}

