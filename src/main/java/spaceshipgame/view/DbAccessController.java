package spaceshipgame.view;
//CHECKSTYLE:OFF


import org.jasypt.util.password.BasicPasswordEncryptor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class DbAccessController {
	Main main;
	@FXML
	private PasswordField pwfield;
	@FXML
	private Button confirmbtn;
	@FXML
	private Button exitbtn;
	@FXML
	private Label errorlabel;
	
	private String pwhash = "Md2j2/xP7wwU+JnbTkNGxu9hdh+Qv97I";
	private BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	@FXML
	public void confirmOnClick(){
		if (passwordEncryptor.checkPassword(pwfield.getText(), pwhash)) {
			Main.PM.createConnection(pwfield.getText());
			main.createLoginView(Main.primaryStage);
		}else{
			errorlabel.setText("Invalid password");
		}
	}
	@FXML
	public void exitOnClick(){
		System.exit(0);
	}
	public void setMain(Main main){
		this.main = main;
	}

}
