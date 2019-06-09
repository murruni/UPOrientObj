package test;

import business.BOUsuario;
import dao.UsuarioDAO;
import dao.UsuarioDAOJdbcImpl;
import entities.Usuario;
import exception.BusinessException;

public class TestUser {
	public static void main(String[] args) throws BusinessException {
		BOUsuario boUser = new BOUsuario(null);
		UsuarioDAO daoUser = new UsuarioDAOJdbcImpl();
		boUser.setDAO(daoUser);

		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
//		Usuario u3 = new Usuario();
//		Usuario u4 = new Usuario();
		u1.setUsuario("admin6546");

		System.out.println("u1:" + u1);

		try {
			u2 = boUser.getUsuario(u1);
		} catch (BusinessException e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}
		System.out.println("u1:" + u1);
		System.out.println("u2:" + u2);
	}
}
