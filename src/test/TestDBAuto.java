package test;

import java.util.List;

import dao.AutoDAOJdbcImpl;
import entities.Auto;
import exception.PersistenceException;

public class TestDBAuto {
	public static void main(String[] args) {
		Auto auto = new Auto();
		auto.setDominio("AC912MP");
		auto.setDuenio("Romina");

		System.out.println("Objeto sin persistir");
		System.out.println(auto);

		AutoDAOJdbcImpl daoAuto = new AutoDAOJdbcImpl();
		try {
			auto = daoAuto.create(auto);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Error al crear un auto en la base");
		}

		System.out.println("Objeto desde la base");
		System.out.println(auto);

		System.out.println("LISTA DE AUTOS:");
		List<Auto> autos = null;
		try {
			autos = daoAuto.read();
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Error al listar");
		}
		for (Auto a : autos) {
			System.out.println(a);
		}
	}
}
