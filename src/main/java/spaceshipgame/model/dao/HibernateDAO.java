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
	 *@param pw Adatbázishoz szükséges jelszó.
	 *@return HibernateDAO objektum.
	 */
	public static HibernateDAO getInstance(String pw){
		
		if (instance == null) {
			instance = new HibernateDAO();
		}
		if (emf == null) {
			Map<String, Object> tmp = new HashMap<>();
			tmp.put("hibernate.connection.password", pw);
			emf = Persistence.createEntityManagerFactory("unidebdb",tmp);
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
