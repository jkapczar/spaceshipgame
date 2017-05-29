package spaceshipgame.view;

//CHECKSTYLE:OFF

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {
	Main main;
	
	@FXML 
	private Button start;
	
	@FXML
	private Button exit;
	
	@FXML
	private Button scoreboard;
	
	@FXML
	private Button logoutbtn;
	
	@FXML
	public void scoreboardOnClick(){
		main.createScoreBoardView();
	}
	@FXML
	public void logoutOnClick(){
		Main.PM.setLoggedInPlayer(null);
		main.createLoginView(Main.primaryStage);
	}
	
	@FXML
	public void startOnClick(){
		main.createGameView();
	}
	@FXML
	public void exitOnClick(){
		Main.primaryStage.close();
		System.exit(0);
	} 
	
	
	
	public void setMain(Main main){
		this.main = main;
	}

}
