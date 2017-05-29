package spaceshipgame.view;
//CHECKSTYLE:OFF
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import spaceshipgame.model.Player;
import spaceshipgame.model.PlayerValidation;

public class RegistrationController {
	Main main;
	
	@FXML
	private TextField usernameField;
	@FXML
	private Label usernameError;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label passwordError;
	@FXML
	private PasswordField cPasswordField;
	@FXML
	private Label passwordCError;
	@FXML
	private TextField firstNameField;
	@FXML
	private Label fNameError;
	@FXML
	private TextField lastNameField;
	@FXML
	private Label lNameError;
	@FXML
	private TextField dateField;
	@FXML
	private Label dateError;
	@FXML
	private TextField emailField;
	@FXML
	private Label emailError;
	@FXML
	private Button registrationButton;
	
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private PlayerValidation pv = new PlayerValidation();
	private Player player;
	
	@FXML
	public void regBtnOnClick(){
		validation();
		setPlayer();
		if (player != null) {
			try {
				Main.PM.savePlayer(player);
				main.createLoginView(Main.primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void validation(){
		pv.valid(usernameField,"([a-zA-Z])+([0-9])*",usernameError,"invalid",3,8);
		pv.passwordValidation(passwordField,cPasswordField,passwordError,passwordCError);
		pv.valid(firstNameField,"([a-zA-Z])+",fNameError,"invalid",3,50);
		pv.valid(lastNameField,"([a-zA-Z])+",lNameError,"invalid",3,50);
		pv.dateValidation(dateField, "\\d{4}-[01]\\d-[0-3]\\d", dateError);
		pv.valid(emailField,"^[(a-zA-Z-0-9-\\_\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$",emailError,"invalid",1,50);
	}

	
	public boolean validPlayer(){
		return usernameError.getText().equals("") && passwordError.getText().equals("") && passwordCError.getText().equals("")
				&& lNameError.getText().equals("") && fNameError.getText().equals("") && emailError.getText().equals("")
				&& dateError.getText().equals("") ;
	}
	
	public void setPlayer(){
		if (validPlayer()) {
			player = new Player();
			player.setUserName(usernameField.getText());
			player.setPassword(Main.PM.getPasswordEncryptor().encryptPassword(passwordField.getText()));
			player.setFirstName(firstNameField.getText());
			player.setLastName(lastNameField.getText());
			try {
				player.setDate(new java.sql.Date(dateFormatter.parse(dateField.getText()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			player.setEmail(emailField.getText());
		}
		
	}
	
	
	public void setMain(Main main){
		this.main=main;
	}
	
	

}
