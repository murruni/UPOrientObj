package entities;

public class Usuario implements MiEntity {
	private Integer id;
	private String nombre;
	private String usuario;
	private String clave;

	public String toString() {
		return "||USUARIO||" + " id: " + id + " nombre: " + nombre + ", usuario: " + usuario + ", clave: " + clave;
	}

	// Getters & Setters
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
