package spaceshipgame.view;

import java.time.LocalTime;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import spaceshipgame.model.Game;
import spaceshipgame.model.Player;

public class GameController {
	private Player player;
	private Main main;
	private Game game = new Game();
	private Pane root;
	public static Timeline loop;
	
	@FXML
	private Label score;
	@FXML
	private Label stage;
	@FXML
	private Label time;
	@FXML
	private Label lifes;
	
	public void setWindowSize(double x,double y){
		Game.window = new Point2D(x, y);
		Game.centerx = Game.window.getX()/2;
		Game.centery = Game.window.getY()/2;
		game.shipx = Game.window.getX()/2 - game.ship.getFitWidth()/2;
		game.shipy = Game.window.getY()/2 - game.ship.getFitHeight()/2;
		game.ship.shipc.setLayoutX(Game.centerx);
		game.ship.shipc.setLayoutY(Game.centery);
		game.ship.setLayoutX(game.shipx);
		game.ship.setLayoutY(game.shipy);
		setTextAlignment();
		
	}
	
	
	public void setKeyPresses(Scene scene){
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		
		@Override
		public void handle(KeyEvent event) {
			if (event.getCode()==KeyCode.ESCAPE) {
				System.out.println("pause");
				loop.pause();
				player.stopTimer();
				main.createInGameMenu();
			
			}
			
			
		}
		
	});
}
	

	public void setgame(){
		player.setDefault();
		
		game.ship.setShip();
		root.getChildren().add(game.ship);
		
		
		Game.window = new Point2D(root.getPrefWidth(), root.getPrefHeight());
        setWindowSize(Game.window.getX(), Game.window.getY());
        
        game.setMain(main);
        game.setPane(root);
        game.setPlayer(player);
        game.ship.rotate();
		game.ship.fire();
        
	}
	public void startGame(){
		setgame();
		
        loop = new Timeline(new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent t) {
            	
            	if (Game.window.getX() != root.getWidth() || Game.window.getY() != root.getHeight()) {
					setWindowSize(root.getWidth(), root.getHeight());
				
				}
     
        		game.ship.setRotate(game.ship.angle);
        		game.makecircle();
            	game.movecircle();
            	game.movebullet();
            	game.collosionResponse();
            	player.updateTimer();
            	updateText();
            	game.updateGameStages();
            	game.checkEndGame();
            	
			}
           
        }));

        loop.setCycleCount(Animation.INDEFINITE);
        loop.play();
        
	}
	public void setTextAlignment(){
		score.setLayoutX(Game.window.getX() - score.getPrefWidth());
		stage.setLayoutX(Game.centerx - stage.getPrefWidth() + 50);
		lifes.setLayoutY(Game.window.getY() - lifes.getPrefHeight());
	}
	
	public void updateText(){
		score.setText("Score: " + player.getScore());
		stage.setText("Stage: " + player.getStage());
		lifes.setText("Lifes: " + player.getLifes());
		time.setText("Time: " + LocalTime.ofSecondOfDay(player.getTime()).toString());
	}
	public void setMain(Main main){
		this.main=main;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	public void setPane(Pane game){
		this.root = game;
	}
	
}
