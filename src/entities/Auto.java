package entities;

public class Auto implements MiEntity {
	private Integer id;
	private String dominio;
	private String duenio;

	public Auto() {

	}

	public Auto(String dominio) {
		this.setDominio(dominio);
	}

	public String toString() {
		return "||AUTO|| ID: " + id + ", dominio: " + dominio + ", duenio: " + duenio;
	}

	// ---------------------------------------------------------------------------------------
	// -- Getters & Setters --
	// ---------------------------------------------------------------------------------------
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getDuenio() {
		return duenio;
	}

	public void setDuenio(String duenio) {
		this.duenio = duenio;
	}
}
