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
		game.setPlayer(new Player());
	}
	
	@Test
	public void makeBullet(){
		int old = game.bulletlist.size();
		game.makebullet();
		assertTrue(old<game.bulletlist.size());
	}
	@Test
	public void removetestBullet(){
		// 600 400
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
		
		b.setLayoutX(-10);
		b.setLayoutY(200);
		assertTrue(game.removetestbullet(b));
		
		b.setLayoutX(200);
		b.setLayoutY(-10);
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
		int old = game.circlelist.size();
		game.makecircle();
		assertTrue(old<=game.circlelist.size());
		old = game.circlelist.size();
		game.makecircle();
		assertTrue(old<=game.circlelist.size());
		old = game.circlelist.size();
		game.makecircle();
		assertTrue(old<=game.circlelist.size());
		old = game.circlelist.size();
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
		
		b.setLayoutX(-10);
		b.setLayoutY(200);
		assertTrue(game.removetestcircle(b));
		
		b.setLayoutX(200);
		b.setLayoutY(-10);
		assertTrue(game.removetestcircle(b));
	}
	@Test
	public void removeCircle(){
		MovingCircle b = new MovingCircle(200, 200, 5, Color.RED);
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
	
	@Test
	public void collosionResponse(){
		MovingCircle a = new MovingCircle(200, 200, 5, Color.GREEN);
		a.setLayoutX(300);
		a.setLayoutY(300);
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		b.setLayoutX(293);
		b.setLayoutY(300);
		MovingCircle c = new MovingCircle(200, 200, 5, Color.GREEN);
		c.setLayoutX(192);
		c.setLayoutY(200);
		MovingCircle d = new MovingCircle(200, 200, 5, Color.GREEN);
		d.setLayoutX(400);
		d.setLayoutY(200);
		MovingCircle e = new MovingCircle(200, 200, 5, Color.GREEN);
		e.setLayoutX(350);
		e.setLayoutY(200);
		
		game.circlelist.add(a);
		game.circlelist.add(c);
		game.circlelist.add(e);
		game.bulletlist.add(b);
		game.bulletlist.add(d);
		
		game.ship.shipc.setLayoutX(200);
		game.ship.shipc.setLayoutY(200);
		game.ship.shipc.setRadius(5);
		
		int oldb = game.removelistb.size();
		int oldc = game.removelistc.size();
		
		game.collosionResponse();
		
		assertTrue(oldc<game.removelistc.size());
		assertTrue(oldb<game.removelistb.size());
		
		
	}
	@Test
	public void moveBullet(){
		MovingCircle a = new MovingCircle(200, 200, 5, Color.GREEN);
		a.setLayoutX(800);
		a.setLayoutY(300);
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		b.setLayoutX(300);
		b.setLayoutY(300);
		
		game.bulletlist.add(a);
		game.bulletlist.add(b);
		int old = game.bulletlist.size();
		game.movebullet();
		assertTrue(old>game.bulletlist.size());
	}
	@Test
	public void moveCircle(){
		MovingCircle a = new MovingCircle(200, 200, 5, Color.GREEN);
		a.setLayoutX(800);
		a.setLayoutY(300);
		MovingCircle b = new MovingCircle(200, 200, 5, Color.GREEN);
		b.setLayoutX(300);
		b.setLayoutY(300);
		
		game.circlelist.add(a);
		game.circlelist.add(b);
		int old = game.circlelist.size();
		game.movecircle();
		assertTrue(old>game.circlelist.size());
	}
	
	

}
