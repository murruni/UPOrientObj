package business;

import java.util.ArrayList;
import java.util.List;

import control.Handler;
import dao.DAO;
import entities.MiEntity;
import exception.BusinessException;
import exception.PersistenceException;

public abstract class BOEntity<T extends MiEntity, D extends DAO<T>> extends BO{

	public BOEntity(Handler h) {
		super(h);
	}

	protected D dao;

	public List<T> listar() throws BusinessException {
		List<T> l = new ArrayList<T>();
		try {
			l = dao.read();
		} catch (Exception e) {
			throw new BusinessException("Error al listar", e);
		}
		return l;
	}

	public T createUpdate(T t) throws BusinessException {
		// Edicion
		if (esEdicion(t)) {
			return editar(t);
		}
		// Alta
		return alta(t);
	}

	public T alta(T t) throws BusinessException {
		try {
			validarNuevo(t);
			return dao.create(t);
		} catch (PersistenceException e) {
			throw new BusinessException("No se pudo crear en base de datos", e);
		}
	}

	public T editar(T t) throws BusinessException {
		try {
			validarEdicion(t);
			return dao.update(t);
		} catch (PersistenceException e) {
			throw new BusinessException("No se pudo editar en la base de datos", e);
		}
	}

	public void borrar(T t) throws BusinessException {
		try {
			validarBorrado(t);
			dao.delete(t);
		} catch (PersistenceException e) {
			throw new BusinessException("No se pudo eliminar en la base de datos", e);
		}
	}

	// ------------------------------------------------------------
	// -- Metodos a implementar --
	// ------------------------------------------------------------
	protected abstract boolean esEdicion(T t);

	protected abstract void validarEdicion(T t) throws BusinessException;

	protected abstract void validarNuevo(T t) throws BusinessException;

	protected abstract void validarBorrado(T t) throws BusinessException;

	// ------------------------------------------------------------
	// -- Getters & Setters --
	// ------------------------------------------------------------
	public void setDAO(D dao) {
		this.dao = dao;
	}

	public D getDAO() {
		return this.dao;
	}
}
