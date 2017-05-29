package spaceshipgame.model.dao;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;

import spaceshipgame.model.Player;
/**
 *Játékos osztályhoz DAO osztály.
 */
public class PlayerDAOImpl implements PlayerDAO{
	
	private EntityManager em;
	/**
	 *Entitymanager beállítása.
	 *@param em EntityManager.
	 */
	public PlayerDAOImpl(EntityManager em) {
		this.em = em;
	}
	/**
	 *Játékos mentése adatbázisba.
	 *@param player Adott játékos.
	 */
	@Override
	public void createPlayer(Player player) {
		em.getTransaction().begin();
		em.persist(player);
		em.getTransaction().commit();
		
	}
	/**
	 *Játékos lekérése adatbázisból.
	 *@param name Játékos neve.
	 *@return Adott játékos név alapján.
	 */
	@Override
	public Player getPlayer(String name){
		return em.createNamedQuery("player.find", Player.class).setParameter("name", name).getSingleResult();
	}
	/**
	 *Összes játékos lekérése adatbázisból.
	 *@return Összes játékos.
	 */
	@Override
	public List<Player> getPlayers() {
		return em.createNamedQuery("player.findAll", Player.class).getResultList();
	}
	/**
	 *Játékos adatok frissítése az adatbázisban.
	 *@param p Adott játékos.
	 */
	@Override
	public void updatePlayerTime(Player p) {
		em.getTransaction().begin();
		em.createNamedQuery("player.update.gameTime")
			.setParameter("time", LocalTime.ofSecondOfDay(p.getTime()).toString())
			.setParameter("name", p.getUserName()).executeUpdate();
		em.getTransaction().commit();
		
	}
	/**
	 *Játékos adatok frissítése az adatbázisban.
	 *@param p Adott játékos.
	 */
	@Override
	public void updatePlayerScore(Player p) {
		em.getTransaction().begin();
		em.createNamedQuery("player.update.highestScore")
			.setParameter("score", p.getScore())
			.setParameter("name", p.getUserName()).executeUpdate();
		em.getTransaction().commit();
	}
	/**
	 *Játékos adatok frissítése az adatbázisban.
	 *@param p Adott játékos.
	 */
	@Override
	public void updatePlayerStage(Player p) {
		em.getTransaction().begin();
		em.createNamedQuery("player.update.highestStage")
			.setParameter("stage", p.getStage())
			.setParameter("name", p.getUserName()).executeUpdate();
		em.getTransaction().commit();
		
	}

}
