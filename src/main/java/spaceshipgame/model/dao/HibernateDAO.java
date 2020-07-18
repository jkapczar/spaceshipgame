package spaceshipgame.model.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *Adatbázissal való kapcsolatteremtés.
 */
public class HibernateDAO{
	private static EntityManager em = null;
	private static EntityManagerFactory emf = null;
	private static HibernateDAO instance = null;
	
	private HibernateDAO(){}
	
	/**
	 *Globális hozzáférési pontot biztosít az adatbázishoz.
	 *@return HibernateDAO objektum.
	 */
	public static HibernateDAO getInstance(){
		
		if (instance == null) {
			instance = new HibernateDAO();
		}
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("db");
		}
		if (em == null) {
			em = emf.createEntityManager();
		}
		return instance;
	}
	/**
	 *DAO létrehozása.
	 *@return DAO implementáció.
	 */
	public PlayerDAO createDAO(){
		return new PlayerDAOImpl(em);
	}
	
	/**
	 *Adatbázis kapcsolat lezárása.
	 */
	public static void close(){
		em.close();
		emf.close();
	} 

}
