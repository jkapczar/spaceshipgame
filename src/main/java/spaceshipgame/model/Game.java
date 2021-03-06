package spaceshipgame.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import spaceshipgame.view.GameController;
import spaceshipgame.view.Main;

/**
 *A játék mechanikáját megvalósító osztály. 
 * 
 */
public class Game {
	private static Logger logger = LoggerFactory.getLogger(Game.class);
	
	private Player player;
	private Pane root;
	private Main main;
	
	public Ship ship = new Ship();
	public List<MovingCircle> circlelist = new ArrayList<>();
	public List<MovingCircle> bulletlist = new ArrayList<>();
	public List<MovingCircle> removelistc = new ArrayList<>();
	public List<MovingCircle> removelistb = new ArrayList<>();
	

	public static Point2D window;
	public static double centerx;
	public static double centery;
	public double mousex;
	public double mousey;
	public double shipx;
	public double shipy;

	/**
	 *Belső osztály a spaceship kirajzolásához, forgatásához. 
	 * 
	 */
	public class Ship extends ImageView{
		
		public Circle shipc = new Circle();
		private InputStream in = Game.class.getResourceAsStream("/png/ship.png");
		private Image img = new Image(in);
		public double angle;
		
		/**
		 *Ship kezdőértékeit beállító metódus.
		 */
		public void setShip(){
			logger.info("setting ship");
			ship.setImage(img);
			ship.setFitWidth(50);
			ship.setFitHeight(50);
			ship.shipc.setRadius(ship.getFitWidth()/2);
		}
		/**
		 *A ship forgatásához szükséges szöget számoló függvény.
		 */
		public void rotate(){
			
			root.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			    	mousex = mouseEvent.getSceneX();
			    	mousey = mouseEvent.getSceneY();
			    	Point2D v1 = new Point2D(0, -centery);
			    	Point2D v2 = new Point2D(mouseEvent.getSceneX()-centerx, mouseEvent.getSceneY()-centery);
			  
			    	if (mousex < centerx) {
			    		angle = 360 - v1.angle(v2);
					}
			    	else
			    		angle = v1.angle(v2);
			   
			   }
			});
			
			
			
		}
		
		/**
		 *A ship tüzelését megvalósító metódus. 
		 *A {@link makebullet()} metódust hívja kattintáskor.
		 */
		public void fire(){
			root.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			    	makebullet();
			   }
			});
		}
		
	}
	/**
	 *Töltényeket készítő metódus. 
	 */
	public void makebullet(){
		
		MovingCircle b = new MovingCircle(mousex, mousey, 5, Color.GREEN);
		bulletlist.add(b);
		root.getChildren().add(0,b);
		
	}
	/**
	 *Töltények mozgatását végző metódus.<br>
	 *Az új pozíció kiszámítása után leteszteli hogy nem e hagyta el az ablakot az adott<br>
	 *töltény. Ezt a {@link removetestbullet(MovingCircle)} metódus végzi.<br>
	 *Ha lenne olyan töltény ami elhagyta az ablakot az törlésre kerül.<br>
	 *Ezt a {@link removebullet()} végzi.<br>
	 */
	public void movebullet(){
	
		for (MovingCircle b : bulletlist) {
			b.setLayoutX(b.getLayoutX() + b.getDirection().getX() * b.getSpeed());
			b.setLayoutY(b.getLayoutY() + b.getDirection().getY() * b.getSpeed());	
			if (removetestbullet(b)) {
				removelistb.add(b);
			}
	}
	removebullet();	
	}
	/**
	 *Töltények törlését végző metódus. 
	 */
	public void removebullet(){
		for (MovingCircle b : removelistb) {
			
			bulletlist.remove(b);
			root.getChildren().remove(b);
			
		}
		removelistb.clear();
	}
	/**
	 *Megállapítja, hogy törlésre kerüljön e az adott töltény.<br>
	 *Vizsgálja hogy az adott töltény elhagyta e már az ablakot.<br>
	 *Ha igen törli.
	 *@param b Töltény objektum.
	 *@return Igaz ha elhagyta az ablakot, ellenkező esetben hamis.
	 */
	public boolean removetestbullet(MovingCircle b){
		if (b.getLayoutX() > window.getX() || b.getLayoutX() < 0) {
			return true;
		}
		if (b.getLayoutY() > window.getY() || b.getLayoutY() < 0) {
			return true;
		}
		return false;
	} 
	/**
	 *Ellenséges kör objektumokat készítő metódus.<br>
	 *Az ellenséges objektumok az ablak bármely oldaláról érkezhetnek.<br>
	 *Egyszerre mindig csak adott számú ellenséges objektum lehet.<br>
	 *Ez a játékos által elért stage szinttől függ.<br>
	 */
	public void makecircle(){
		
		if (circlelist.size()<player.getStage() + 3) {
			
			Random rand = new Random();
			int randnum = rand.nextInt(4);
			
			switch (randnum) {
			case 1: randnum = 0;
				MovingCircle c0 = new MovingCircle(rand.nextDouble()*window.getX(), 0, 5, Color.RED);
				root.getChildren().add(c0);
				circlelist.add(c0);
				break;
			case 2: randnum = 1;
				MovingCircle c1 = new MovingCircle(rand.nextDouble()*window.getX(), window.getY(), 5, Color.RED);
				root.getChildren().add(c1);
				circlelist.add(c1);
				break;
			case 3: randnum = 2;
				MovingCircle c2 = new MovingCircle(0,rand.nextDouble()*window.getY(), 5, Color.RED);
				root.getChildren().add(c2);
				circlelist.add(c2);
				break;
			case 4: randnum = 3;
				MovingCircle c3 = new MovingCircle(window.getX(), rand.nextDouble()*window.getY(), 5, Color.RED);
				root.getChildren().add(c3);
				circlelist.add(c3);
				break;
			
			}	
			
		}
		
	}
	/**
	 *Ellenséges objektumok mozgását megvalósító metódus. <br>
	 *Az új pozíció kiszámítása után leteszteli hogy nem e hagyta el az ablakot az adott<br>
	 *objektum. Ezt a {@link removetestcircle(MovingCircle)} metódus végzi.<br>
	 *Ha lenne olyan objektum ami elhagyta az ablakot az törlésre kerül.<br>
	 *Ezt a {@link removecircle()} végzi.<br>
	 */
	public void movecircle(){
		
		for (MovingCircle c : circlelist) {
			c.setLayoutX(c.getLayoutX() + c.getDirection().getX() * c.getSpeed());
			c.setLayoutY(c.getLayoutY() + c.getDirection().getY() * c.getSpeed());
			if (removetestcircle(c)) {
				removelistc.add(c);
			}
		}
		removecircle();
	}
	/**
	 *Ellenséges objektumok törlését megvalósító metódus. 
	 */
	public void removecircle(){
		for (MovingCircle c : removelistc) {
			circlelist.remove(c);
			root.getChildren().remove(c);
		}
		removelistc.clear();
	}
	/**
	 *Megállapítja, hogy törlésre kerüljön e az adott ellenséges objektum.<br>
	 *Vizsgálja hogy az adott objektum elhagyta e már az ablakot.<br>
	 *Ha igen törli.
	 *@param c Ellenséges objektum.
	 *@return Igaz ha elhagyta az ablakot, ellenkező esetben hamis.
	 */
	public boolean removetestcircle(MovingCircle c){
		if (c.getLayoutX() > window.getX() || c.getLayoutX() < 0) {
			return true;
		}
		if (c.getLayoutY() > window.getY() || c.getLayoutY() < 0) {
			return true;
		}
		return false;
	}
	/**
	 *Két kör objektum ütközésére reagál.<br>
	 *Megvizsgáljuk hogy ellenséges objektum ütközött e a hajóval.<br>
	 *Ha igen őt eltávolítjuk.<br>
	 *Vizsgálatra kerül az is ha töltény ütközik ellenséges objektummal.<br>
	 *Ebben az esetben mind a kettő eltávolításra kerül. <br>
	 */
	public void collosionResponse(){
		
		for (MovingCircle c : circlelist) {
			if (collosionDetection(c, ship.shipc)) {
				logger.info("collosion");
				player.setScore(player.getScore() - 10);
				player.setLifes(player.getLifes() - 1);
				removelistc.add(c);
				break;
			}
			for (MovingCircle b : bulletlist ) {
				if (collosionDetection(c, b)) {
					player.setScore(player.getScore()+10);
					removelistb.add(b);
					removelistc.add(c);
				}
				
			}
		}
		
	}
	/**
	 *Vizsgálja,hogy két kör ütközik e.
	 *@param c kör objektum.
	 *@param b kör objektum.
	 *@return Igaz ha a két kör ütközésben van, ellenkező esetben hamis.
	 */
	public boolean collosionDetection(Circle c, Circle b){
		return b.getBoundsInParent().intersects(c.getBoundsInParent());
	}
	/**
	 *10 másodpercenként növeli a stage változo értéket ami nehezíti a játékot.<br>
	 *A stage a játékos által elért szintet jelenti.
	 */
	public void updateGameStages(){
		player.setStage((int) player.getTime() / 10);
	}
	/**
	 *Vizsgálja h véget ért e az adott játékmenet.
	 *Ez akkor következik be ha a játékos életeinek száma 0 lesz. 
	 */
	public void checkEndGame(){
		if (player.getLifes() == 0) {
			logger.info("end game");
			GameController.loop.stop();
			player.stopTimer();
			Main.PM.updatePlayerStats(player);
			main.createGameOverView();
			
		}
	}
	/**
	 *Pane-t beáálító metódus.<br>
	 *Erre fogunk rajzolni.
	 *@param game Amire rajzolni fogunk.
	 */
	public void setPane(Pane game){
		this.root = game;
	}
	/**
	 *Main-t beállító metódus.
	 *@param main Main.
	 */
	public void setMain(Main main){
		this.main = main;
	}
	/**
	 *Játékos beállító metódus.
	 *@param player Adott játékos.
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	
	
}
