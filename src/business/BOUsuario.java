package business;

import control.Handler;
import dao.UsuarioDAO;
import entities.Usuario;
import exception.BusinessException;
import exception.PersistenceException;

public class BOUsuario extends BOEntity<Usuario, UsuarioDAO> {

	public BOUsuario(Handler h) {
		super(h);
	}

	@Override
	protected boolean esEdicion(Usuario t) {
		// FIXME validacion
		return false;
	}

	@Override
	protected void validarEdicion(Usuario t) throws BusinessException {
		// FIXME validacion

	}

	@Override
	protected void validarNuevo(Usuario t) throws BusinessException {
		// FIXME validacion

	}

	@Override
	protected void validarBorrado(Usuario t) throws BusinessException {
		// FIXME validacion

	}

	public void validarIngreso() {

	}

	public Usuario getUsuario(String nombreUsuario) throws BusinessException {
		Usuario usuario = null;
		Usuario usuarioFound = null;
		try {
			usuario = new Usuario();
			usuario.setUsuario(nombreUsuario);
			usuarioFound = dao.get(usuario);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al obtener un usuario desde la base", e);
		}
		return usuarioFound;
	}

	public Usuario getUsuario(Usuario usuario) throws BusinessException {
		Usuario usuarioFound = null;
		try {
			usuarioFound = dao.get(usuario);
			if (usuarioFound == null) {
				throw new BusinessException("El usuario no existe");
			}
		} catch (PersistenceException e) {
			String msg = "Error de aplicación";
			throw new BusinessException(msg, e);
		}
		return usuarioFound;
	}

}
