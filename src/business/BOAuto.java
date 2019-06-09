package business;

import control.Handler;
import dao.AutoDAO;
import entities.Auto;
import exception.BusinessException;
import exception.PersistenceException;

public class BOAuto extends BOEntity<Auto, AutoDAO> {

	public BOAuto(Handler h) {
		super(h);
	}

	@Override
	protected boolean esEdicion(Auto auto) {
		if (auto.getId() != null && auto.getId() >= 0) {
			return true;
		}
		return false;
	}

	@Override
	protected void validarEdicion(Auto auto) throws BusinessException {
		try {
			Auto autoDB = dao.get(auto);
			if (!auto.getDominio().equalsIgnoreCase(autoDB.getDominio())) {
				throw new BusinessException("No se puede modificar el dominio de un auto");
			}
		} catch (PersistenceException e) {
			throw new BusinessException("Error al obtener un auto desde la base", e);
		}
	}

	@Override
	protected void validarNuevo(Auto auto) throws BusinessException {
		try {
			Auto autoDB = dao.get(auto);
			if (autoDB != null && autoDB.getDominio().equalsIgnoreCase(auto.getDominio())) {
				throw new BusinessException("Ya existe un auto con ese dominio");
			}
			
			if(auto.getDominio() == null ) {
				throw new BusinessException("Debe tener dominio");
			}
			
		} catch (PersistenceException e) {
			throw new BusinessException("Error al obtener un auto desde la base", e);
		}
	}

	@Override
	protected void validarBorrado(Auto auto) throws BusinessException {
		// FIXME validar si existe en alguna relacion db (reparacion, etc.)
	}
}
