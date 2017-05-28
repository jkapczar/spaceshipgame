package spaceshipgame.model;

import java.time.LocalTime;
import java.util.List;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spaceshipgame.model.dao.HibernateDAO;
import spaceshipgame.model.dao.PlayerDAO;

/**
 *Játékos kezelő osztály mely megvalósitja az adatbázissal való műveletek nagy részét. 
 */
public class PlayerManager {
	private static Logger logger = LoggerFactory.getLogger(PlayerManager.class);
	private BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	private PlayerDAO pd;
	private Player player;
	private List<Player> players;
	/**
	 *Adatbázis kapcsolat létrehozása.
	 */
	public PlayerManager(){
		pd = HibernateDAO.getInstance().createDAO();
	}
	/**
	 *Játékos feltöltése adatbázisba.
	 */
	public void savePlayer(Player p){
		pd.createPlayer(p);
	}
	/**
	 *Bejelentkezett játékos beálítása.
	 */
	public void setLoggedInPlayer(Player p){
		player = p;
	}
	/**
	 *Bejelentkezett játékos lekérése.
	 */
	public Player getLoggedInPlayer(){
		return this.player;
	}
	/**
	 *Egy adott játékos lekérése név alapján.
	 */
	public Player getPlayerFromDB(String name){
		return pd.getPlayer(name);
	}
	/**
	 *Összes játékos lekérése.
	 */
	public List<Player> getPlayersFromDB(){
		players = pd.getPlayers();
		return players;
	}
	/**
	 *Jelszóvisszafejtéshez használt objektum lekérdező metódusa.
	 */
	public BasicPasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}
	/**
	 *Játékos statisztikák frissítését végző metódus.
	 */
	public void updatePlayerStats(Player player){
		if (player.getScore()>player.getHighestScore()) {
			logger.info("player score updated");
			player.setHighestScore(player.getScore());
			pd.updatePlayerScore(player);
		}
		if (player.getStage()>player.getHighestStage()) {
			logger.info("player stage updated");
			player.setHighestStage(player.getStage());
			pd.updatePlayerStage(player);
		}
		if (LocalTime.parse(player.getGameTime()).toSecondOfDay()<player.getTime()) {
			logger.info("player time updated");
			player.setGameTime(LocalTime.ofSecondOfDay(player.getTime()).toString());
			pd.updatePlayerTime(player);
		}
		
	}
	
	

}
