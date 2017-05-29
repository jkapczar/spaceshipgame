package spaceshipgame.model.dao;
//CHECKSTYLE:OFF

import java.util.List;

import spaceshipgame.model.Player;
/**
 *Játékos osztályhoz DAO interface.
 */
public interface PlayerDAO {
	public void createPlayer(Player player);
	public Player getPlayer(String name);
	public void updatePlayerTime(Player player);
	public void updatePlayerScore(Player player);
	public void updatePlayerStage(Player name);
	public List<Player> getPlayers();
}
