package entities;

public class Autoparte implements MiEntity {

	private Integer id;
	private String nombre;
	private String descripcion;

	public String toString() {
		return "||AUTOPARTE|| ID: " + id + " nombre: " + nombre + ", descripcion: " + descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
