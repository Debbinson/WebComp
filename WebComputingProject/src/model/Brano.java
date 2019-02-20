package model;

import java.io.Serializable;

public class Brano implements Bean, Serializable {

	@Override
	public String toString() {
		return "Brano [idBrano=" + idBrano + ", titolo=" + titolo + ", descrizione=" + descrizione + ", prezzo="
				+ prezzo + ", path=" + path + ", brano_categoria=" + brano_categoria + ", brano_utente=" + brano_utente
				+ ", disponibile=" + disponibile + ", date_ins=" + date_ins + ", pathImage=" + pathImage + ", stelle="
				+ stelle + "]";
	}

	private static final long serialVersionUID = 1L;

	private int idBrano;
	private String titolo;
	private String descrizione;
	private int prezzo;
	private String path;
	private int brano_categoria;
	private int brano_utente;
	private boolean disponibile;
	private String date_ins;
	private String pathImage;
	private int stelle;

	public int getStelle() {
		return stelle;
	}

	public void setStelle(int stelle) {
		this.stelle = stelle;
	}

	public Brano() {
	}

	public int getIdBrano() {
		return idBrano;
	}

	public void setIdBrano(int idBrano) {
		this.idBrano = idBrano;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getBrano_categoria() {
		return brano_categoria;
	}

	public void setBrano_categoria(int brano_categoria) {
		this.brano_categoria = brano_categoria;
	}

	public int getBrano_utente() {
		return brano_utente;
	}

	public void setBrano_utente(int brano_utente) {
		this.brano_utente = brano_utente;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(String date_ins) {
		this.date_ins = date_ins;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brano_categoria;
		result = prime * result + brano_utente;
		result = prime * result + ((date_ins == null) ? 0 : date_ins.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + (disponibile ? 1231 : 1237);
		result = prime * result + idBrano;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((pathImage == null) ? 0 : pathImage.hashCode());
		result = prime * result + Float.floatToIntBits(prezzo);
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brano other = (Brano) obj;
		if (brano_categoria != other.brano_categoria)
			return false;
		if (brano_utente != other.brano_utente)
			return false;
		if (date_ins == null) {
			if (other.date_ins != null)
				return false;
		} else if (!date_ins.equals(other.date_ins))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (disponibile != other.disponibile)
			return false;
		if (idBrano != other.idBrano)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (pathImage == null) {
			if (other.pathImage != null)
				return false;
		} else if (!pathImage.equals(other.pathImage))
			return false;
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
}