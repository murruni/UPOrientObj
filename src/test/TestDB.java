package test;

import java.util.ArrayList;
import java.util.List;

import dao.AutoDAO;
import dao.AutoDAOJdbcImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOJdbcImpl;
import entities.Auto;
import entities.Usuario;
import exception.PersistenceException;

public class TestDB {
	private static UsuarioDAO usuarioDao;
	private static AutoDAO autoDao;

	public static void main(String[] args) throws PersistenceException {
		usuarioDao = new UsuarioDAOJdbcImpl();
		autoDao = new AutoDAOJdbcImpl();
		crear();
		listar();
	}

	private static void crear() {
		Usuario user = new Usuario();
		Usuario user1 = new Usuario();

		user.setNombre("Administrador");
		user.setUsuario("admin");
		user.setClave("admin");
		
		user1.setNombre("Administrador1");
		user1.setUsuario("admin1");
		user1.setClave("admin1");

		try {
			usuarioDao.create(user);
			usuarioDao.create(user1);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Error al crear usuario");
		}

		Auto a = new Auto();
		Auto a2 = new Auto();
		a.setDominio("wer1");
		a.setDuenio("plope0");
		a2.setDominio("asdas66d");
		a2.setDuenio("654asd");
		try {
			autoDao.create(a);
			autoDao.create(a2);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	private static void listar() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Auto> listaAutos = new ArrayList<Auto>();

//		Usuario user = new Usuario();
//		Usuario userFound = null;
//		user.setUsuario("admin");

		try {
//			userFound = usuarioDao.get(user);
			listaUsuarios = usuarioDao.read();
			listaAutos = autoDao.read();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

//		System.out.println(userFound);

		
		System.out.println("LISTA DE USUARIOS");
		for (Usuario uu : listaUsuarios) {
			System.out.println(uu);
		}

		System.out.println("LISTA DE AUTOS");
		for (Auto aa : listaAutos) {
			System.out.println(aa);
		}
	}
}
