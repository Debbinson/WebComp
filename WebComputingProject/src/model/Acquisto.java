package model;

public class Acquisto implements Bean {
	private int Acquisto_Utente;
	private int Acquisto_Brano;
	private String date_ins;

	public Acquisto() {
		super();
	}

	public int getAcquisto_Utente() {
		return Acquisto_Utente;
	}

	public void setAcquisto_Utente(int acquisto_Utente) {
		Acquisto_Utente = acquisto_Utente;
	}

	public int getAcquisto_Brano() {
		return Acquisto_Brano;
	}

	public void setAcquisto_Brano(int acquisto_Brano) {
		Acquisto_Brano = acquisto_Brano;
	}

	public String getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(String date_ins) {
		this.date_ins = date_ins;
	}
}
