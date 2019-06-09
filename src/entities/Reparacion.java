package entities;

import java.util.ArrayList;
import java.util.List;

public class Reparacion implements MiEntity {
	private Integer id;
	private String titulo;
	private Auto auto;
	private List<Autoparte> autopartes = new ArrayList<Autoparte>();
	private Integer precioManoObra;
	private boolean cobrado;
	private boolean terminado;

	// Getters & Setters
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public List<Autoparte> getAutopartes() {
		return autopartes;
	}

	public void setAutopartes(List<Autoparte> autopartes) {
		this.autopartes = autopartes;
	}

	public Integer getPrecioManoObra() {
		return precioManoObra;
	}

	public void setPrecioManoObra(Integer precioManoObra) {
		this.precioManoObra = precioManoObra;
	}

	public boolean isCobrado() {
		return cobrado;
	}

	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
