package business;

import control.Handler;
import entities.Usuario;
import exception.BusinessException;

public class BOLogin extends BO {

	private BOUsuario boUsuario;

	public BOLogin(Handler h) {
		super(h);
		this.boUsuario = getHandler().getBoUsuario();
	}

	public void validarLogin(Usuario user) throws BusinessException {
		Usuario userFound = null;
		userFound = boUsuario.getUsuario(user);

		if (!user.getUsuario().equalsIgnoreCase(userFound.getUsuario())
				|| !user.getClave().equals(userFound.getClave())) {
			throw new BusinessException("Datos de acceso inválidos");
		}
	}

	// -- getters && setters
	public BOUsuario getBoUsuario() {
		return boUsuario;
	}

	public void setBoUsuario(BOUsuario boUsuario) {
		this.boUsuario = boUsuario;
	}

}
