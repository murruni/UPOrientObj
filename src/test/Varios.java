package test;

import dao.AutoDAOJdbcImpl;
import dao.AutoparteDAOJdbcImpl;
import entities.Auto;
import entities.Autoparte;
import exception.PersistenceException;

public class Varios {
	public static void main(String[] args) throws PersistenceException {
		AutoparteDAOJdbcImpl dao = new AutoparteDAOJdbcImpl();
		AutoDAOJdbcImpl daoAuto = new AutoDAOJdbcImpl();
		Autoparte autoparte = new Autoparte();
		
		autoparte.setNombre("654");
		autoparte.setDescripcion("Filtro de aceite");
		
		System.out.println(autoparte);
		try {
			autoparte = dao.create(autoparte);
			System.out.println(autoparte);			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		for (Autoparte a : dao.read()) {
			System.out.println(a);
		}
		
		for (Auto a : daoAuto.read()){
			System.out.println(a);
		}
	}
}
