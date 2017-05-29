package spaceshipgame.view;
//CHECKSTYLE:OFF
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import spaceshipgame.model.PlayerValidation;

public class LoginController {
	Main main;
	
	@FXML
	private TextField usernamefield;
	
	@FXML
	private PasswordField passwordfield;
	
	@FXML
	private Label errorField;
	
	@FXML
	private Button loginbtn;
	
	@FXML
	private Button registerbtn;
	
	PlayerValidation pv = new PlayerValidation();
	
	@FXML
	private void onClickLoginBtn(){
		errorField.setText("");
		if (pv.loginValidation(usernamefield, passwordfield,errorField)) {
			main.createMenu(Main.primaryStage);
		}
	}
	
	@FXML 
	private void onClickRegistrationBtn(){
		main.createRegistrationView(Main.primaryStage);
	}
	
	public void setMain(Main main){
		this.main=main;
	}
	
	

}
