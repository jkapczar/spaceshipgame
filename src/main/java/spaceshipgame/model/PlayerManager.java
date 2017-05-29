package spaceshipgame.model;

import java.time.LocalTime;
import java.util.List;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spaceshipgame.model.dao.HibernateDAO;
import spaceshipgame.model.dao.PlayerDAO;

/**
 *Játékos kezelő osztály mely megvalósítja az adatbázissal való műveletek nagy részét. 
 */
public class PlayerManager {
	private static Logger logger = LoggerFactory.getLogger(PlayerManager.class);
	private BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	private PlayerDAO pd;
	private Player player;
	private List<Player> players;
	/**
	 *Konstruktor.
	 */
	public PlayerManager(){}
	/**
	 *Játékos feltöltése adatbázisba.
	 *@param p Adott játékos.
	 */
	public void savePlayer(Player p){
		pd.createPlayer(p);
		logger.info("player saved");
	}
	/**
	 *Bejelentkezett játékos beálítása.
	 *@param p Adott játékos.
	 */
	public void setLoggedInPlayer(Player p){
		player = p;
		logger.info("logged in player set");
	}
	/**
	 *Adatbázis kapcsolat létrehozása.
	 *@param pw Adatbázishoz szükséges jelszó.
	 */
	public void createConnection(String pw){
		pd = HibernateDAO.getInstance(pw).createDAO();
	}
	/**
	 *Bejelentkezett játékos lekérése.
	 *@return Bejelentkezett játékos.
	 */
	public Player getLoggedInPlayer(){
		return this.player;
	}
	/**
	 *Egy adott játékos lekérése név alapján.
	 *@param name Adott játékos neve.
	 *@return Adott játékos amelynek neve a name paraméterrel egyezik.
	 */
	public Player getPlayerFromDB(String name){
		logger.info("get player from db");
		return pd.getPlayer(name);
	}
	/**
	 *Összes játékos lekérése.
	 *@return Összes regisztrált játékos.
	 */
	public List<Player> getPlayersFromDB(){
		players = pd.getPlayers();
		logger.info("get all player from db");
		return players;
	}
	/**
	 *Jelszóvisszafejtéshez használt objektum lekérdező metódusa.
	 *@return Jelszó visszafejtő.
	 */
	public BasicPasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}
	/**
	 *Játékos statisztikák frissítését végző metódus.
	 *@param player Adott játékos.
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
