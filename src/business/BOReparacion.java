package business;

import control.Handler;
import dao.ReparacionDAOJdbcImpl;
import entities.Reparacion;
import exception.BusinessException;

public class BOReparacion extends BOEntity<Reparacion, ReparacionDAOJdbcImpl> {

	public BOReparacion(Handler h) {
		super(h);
	}

	@Override
	protected boolean esEdicion(Reparacion reparacion) {
		if (reparacion.getId() != null && reparacion.getId() >= 0) {
			return true;
		}
		return false;
	}

	@Override
	protected void validarEdicion(Reparacion reparacion) throws BusinessException {
		// FIXME codear
		
	}

	@Override
	protected void validarNuevo(Reparacion reparacion) throws BusinessException {
		// FIXME codear
		
	}

	@Override
	protected void validarBorrado(Reparacion reparacion) throws BusinessException {
		// FIXME codear
		
	}

}
