package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import spaceshipgame.model.Game;
import spaceshipgame.model.MovingCircle;
import spaceshipgame.model.Player;

public class GameTest {
	private Game game;
	
	
	@Before
	public void setUp() throws Exception {
		game = new Game();
		game.window = new Point2D(600, 400);
		game.setPane(new Pane());
	}
	
	@Test
	public void makeBullet(){
		int old = game.bulletlist.size();
		game.makebullet();
		assertTrue(old<game.bulletlist.size());
	}
	@Test
	public void removetestBullet(){
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		b.setLayoutX(200);
		b.setLayoutY(200);
		assertFalse(game.removetestbullet(b));
		
		b.setLayoutX(200);
		b.setLayoutY(800);
		assertTrue(game.removetestbullet(b));
		
		b.setLayoutX(800);
		b.setLayoutY(400);
		assertTrue(game.removetestbullet(b));
	}
	@Test
	public void removeBullet(){
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		game.removelistb.add(b);
		int old = game.removelistb.size();
		game.removebullet();
		assertTrue(old>game.bulletlist.size());
	}
	
	@Test
	public void makeCircle(){
		game.setPlayer(new Player());
		
		int old = game.circlelist.size();
		game.makecircle();
		assertTrue(old<=game.circlelist.size());
		old = game.circlelist.size();
		game.makecircle();
		assertTrue(old<=game.circlelist.size());
		old = game.circlelist.size();
		game.makecircle();
		assertTrue(old<=game.circlelist.size());
	}
	@Test
	public void removetestCircle(){
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		b.setLayoutX(200);
		b.setLayoutY(200);
		assertFalse(game.removetestcircle(b));
		
		b.setLayoutX(200);
		b.setLayoutY(800);
		assertTrue(game.removetestcircle(b));
		
		b.setLayoutX(800);
		b.setLayoutY(400);
		assertTrue(game.removetestcircle(b));
	}
	@Test
	public void removeCircle(){
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		game.removelistc.add(b);
		int old = game.removelistc.size();
		game.removecircle();
		assertTrue(old>game.circlelist.size());
	}
	@Test
	public void collosionDetection(){
		MovingCircle a = new MovingCircle(200, 200, 5, Color.GREEN);
		a.setLayoutX(190);
		a.setLayoutY(200);
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		b.setLayoutX(200);
		b.setLayoutY(200);
		assertTrue(game.collosionDetection(a, b));
		a.setLayoutX(189);
		a.setLayoutY(200);
		b.setLayoutX(200);
		b.setLayoutY(200);
		assertFalse(game.collosionDetection(a, b));
	}

}
