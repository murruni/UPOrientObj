package business;

import control.Handler;
import dao.AutoparteDAOJdbcImpl;
import entities.Autoparte;
import exception.BusinessException;

public class BOAutoparte extends BOEntity<Autoparte, AutoparteDAOJdbcImpl> {

	public BOAutoparte(Handler h) {
		super(h);
	}

	@Override
	protected boolean esEdicion(Autoparte autoparte) {
		if (autoparte.getId() != null && autoparte.getId() >= 0) {
			return true;
		}
		return false;
	}

	@Override
	protected void validarEdicion(Autoparte autoparte) throws BusinessException {
		// FIXME validar

	}

	@Override
	protected void validarNuevo(Autoparte autoparte) throws BusinessException {
		// FIXME validar

	}

	@Override
	protected void validarBorrado(Autoparte autoparte) throws BusinessException {
		// FIXME validar

	}
}
