package spaceshipgame.view;
//CHECKSTYLE:OFF


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import spaceshipgame.model.Player;

public class InGameMenuController {
	Main main;
	Player player;

	@FXML
	private Button continuebtn;
	
	@FXML
	private Button mainMenu;
	
	
	@FXML
	public void continuebtn(){
		Main.secondaryStage.close();
		GameController.loop.play();
		player.startTimer();
	}
	@FXML
	public void mainMenu(){
		Main.secondaryStage.close();
		main.createMenu(Main.primaryStage);
	}
	
	public void setMain(Main main){
		this.main=main;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	
	
}
