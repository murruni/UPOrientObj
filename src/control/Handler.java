package control;

import java.util.List;

import business.BOAuto;
import business.BOAutoparte;
import business.BOLogin;
import business.BOReparacion;
import business.BOUsuario;
import dao.AutoDAOJdbcImpl;
import dao.AutoparteDAOJdbcImpl;
import dao.ReparacionDAOJdbcImpl;
import dao.UsuarioDAOJdbcImpl;
import entities.Auto;
import entities.Autoparte;
import entities.Reparacion;
import entities.Usuario;
import exception.BusinessException;
import exception.UserException;
import ui.UI;
import ui_swing.UISwing;

public class Handler {

	private UI ui;
	private BOAuto boAuto;
	private BOAutoparte boAutoparte;
	private BOReparacion boReparacion;
	private BOLogin boLogin;
	private BOUsuario boUsuario;

	public Handler() {
		cargarObjetosDeNegocio();
	}

	private void cargarObjetosDeNegocio() {
		// Entidad Auto
		setBoAuto(new BOAuto(this));
		getBoAuto().setDAO(new AutoDAOJdbcImpl());
		// Entidad Autoparte
		setBoAutoparte(new BOAutoparte(this));
		getBoAutoparte().setDAO(new AutoparteDAOJdbcImpl());
		// Entidad Reoaracion
		setBoReparacion(new BOReparacion(this));
		getBoReparacion().setDAO(new ReparacionDAOJdbcImpl());
		// Entidad Usuario
		setBoUsuario(new BOUsuario(this));
		getBoUsuario().setDAO(new UsuarioDAOJdbcImpl());
		// Login de usuario
		setBoLogin(new BOLogin(this));
	}

	// -----------------------------------------------------------------------------
	// -- User Interface
	// -----------------------------------------------------------------------------

	// -- basicos ------------------------------------------------------------------
	public void iniciarAppDesktop() {
		// esto debería venir por configuracion o algo (web/desktop/etc.)
		// incio la app desktop
		setUi(new UISwing(this));
		getUi().pantallaLogin();
		getUi().initUI();
	}

	public void pantallaLogin() {
		getUi().pantallaLogin();
	}

	public void pantallaInicio() {
		getUi().pantallaInicio();
	}

	public void mostrarError(String msg) {
		getUi().mostrarError(msg);
	}

	public void mostrarExito(String msg) {
		getUi().mostrarExito(msg);
	}

	public void salir() {
		// aqui deberia cerrar los procesos, sesiones, etc
		getUi().salir();
	}

	// -- Auto ---------------------------------------------------------------------
	public void listarAutos() {
		getUi().mostrarListadoAutos();
	}

	public void mostrar(Auto a) {
		getUi().ver(a);
	}

	public void editar(Auto a) {
		getUi().editar(a);
	}

	// -- Autoparte ----------------------------------------------------------------
	public void listarAutopartes() {
		getUi().mostrarListadoAutopartes();
	}

	public void mostrar(Autoparte a) {
		getUi().ver(a);
	}

	public void editar(Autoparte a) {
		getUi().editar(a);
	}

	// -- Reparaciones -------------------------------------------------------------
	public void listarReparaciones() {
		getUi().mostrarListadoReparaciones();
	}

	public void mostrar(Reparacion a) {
		getUi().ver(a);
	}

	public void editar(Reparacion a) {
		getUi().editar(a);
	}

	// -----------------------------------------------------------------------------
	// -- BUSINESS
	// -----------------------------------------------------------------------------

	// -- Login --------------------------------------------------------------------
	public void validarLogin(Usuario user) throws UserException {
		try {
			getBoLogin().validarLogin(user);
		} catch (BusinessException e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	// -- Autos --------------------------------------------------------------------
	public List<Auto> getListaDeAutos() throws UserException {
		try {
			return getBoAuto().listar();
		} catch (BusinessException e) {
			String msg = "Error al obtener listado de autos";
			throw new UserException(msg, e);
		}
	}

	public void createUpdate(Auto a) {
		try {
			a = getBoAuto().createUpdate(a);
			mostrarExito("Éxito");
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
		}
	}

	public void eliminar(Auto a) {
		try {
			getBoAuto().borrar(a);
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
		}
	}

	// -- Autopartes ---------------------------------------------------------------
	public List<Autoparte> getListaDeAutopartes() throws UserException {
		try {
			return getBoAutoparte().listar();
		} catch (BusinessException e) {
			String msg = "Error al obtener listado de autopartes";
			throw new UserException(msg, e);
		}
	}

	public void createUpdate(Autoparte a) {
		try {
			a = getBoAutoparte().createUpdate(a);
			mostrarExito("Éxito");
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
		}
	}

	public void eliminar(Autoparte a) {
		try {
			getBoAutoparte().borrar(a);
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
		}
	}

	// -- Reparaciones -------------------------------------------------------------
	public List<Reparacion> getListaDeReparaciones() throws UserException {
		try {
			return getBoReparacion().listar();
		} catch (BusinessException e) {
			String msg = "Error al obtener listado de Reparaciones";
			throw new UserException(msg, e);
		}
	}

	public void createUpdate(Reparacion a) {
		try {
			a = getBoReparacion().createUpdate(a);
			mostrarExito("Éxito");
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
		}
	}

	public void eliminar(Reparacion a) {
		try {
			getBoReparacion().borrar(a);
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
		}
	}

	// -----------------------------------------------------------------------------
	// -- getters & setters
	// -----------------------------------------------------------------------------
	public BOAuto getBoAuto() {
		return boAuto;
	}

	public void setBoAuto(BOAuto boAuto) {
		this.boAuto = boAuto;
	}

	public BOAutoparte getBoAutoparte() {
		return boAutoparte;
	}

	public void setBoAutoparte(BOAutoparte boAutoparte) {
		this.boAutoparte = boAutoparte;
	}

	public BOUsuario getBoUsuario() {
		return boUsuario;
	}

	public void setBoUsuario(BOUsuario boUsuario) {
		this.boUsuario = boUsuario;
	}

	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	public BOLogin getBoLogin() {
		return boLogin;
	}

	public void setBoLogin(BOLogin boLogin) {
		this.boLogin = boLogin;
	}

	public BOReparacion getBoReparacion() {
		return boReparacion;
	}

	public void setBoReparacion(BOReparacion boReparacion) {
		this.boReparacion = boReparacion;
	}
}
