package spaceshipgame.view;
//CHECKSTYLE:OFF
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spaceshipgame.model.Player;

public class GameOverController {
	Player player;
	Main main;
	
	@FXML
	private Label time;
	@FXML
	private Label score;
	@FXML
	private Label stage;
	@FXML
	private Label rtime;
	@FXML
	private Label rscore;
	@FXML
	private Label rstage;
	
	@FXML
	private Button newgame;
	@FXML
	private Button mainmenu;
	
	public void setPlayer(Player player){
		this.player=player;
	}
	public void setMain(Main main){
		this.main=main;
	}
	
	public void setText(){
		score.setText("Score: " + player.getScore());
		stage.setText("Stage: " + player.getStage());
		time.setText("Time: " + LocalTime.ofSecondOfDay(player.getTime()).toString());
		rscore.setText("Score: " + player.getHighestScore());
		rstage.setText("Stage: " + player.getHighestStage());
		rtime.setText("Time: " + player.getGameTime());
		
	}
	
	@FXML
	public void onClockNewGameBtn(){
		Main.secondaryStage.close();
		main.createGameView();
	}
	@FXML
	public void onClickMainMenuBtn(){
		Main.secondaryStage.close();
		main.createMenu(Main.primaryStage);
	}

}
