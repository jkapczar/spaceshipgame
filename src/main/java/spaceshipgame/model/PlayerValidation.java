package spaceshipgame.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import spaceshipgame.view.Main;

/**
 *Játékos tulajdonságainak helyességét vizsgáló osztály.
 */
public class PlayerValidation {
	private static Logger logger = LoggerFactory.getLogger(PlayerValidation.class);
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 *Dátum helyességét vizsgáló metódus.
	 */
	public void dateValidation(TextField textField, String pattern, Label errorField) {
		errorField.setTextFill(Color.RED);
	    if (textField.getText().matches(pattern)){
	    	
	    	dateFormatter.setLenient(false);
	 	    try {
	 	    	dateFormatter.parse(textField.getText());
	 	        errorField.setText("");
	 	    } catch (ParseException ex) {
	 	    	logger.info("invalid date format");
	 	    	errorField.setText("invalid format. try yyyy-MM-dd");
	 	    }
	    }else{
	    	logger.info("invalid date");
	    	errorField.setText("invalid date");
	    }  
	}
	/**
	 *Jelszó helyességét vizsgáló metódus.
	 */
	public void passwordValidation(PasswordField passwordField,PasswordField cPasswordField,Label passwordError,Label passwordCError){
		passwordError.setTextFill(Color.RED);
		passwordCError.setTextFill(Color.RED);
		if (passwordField.getText().equals(cPasswordField.getText())) {
			valid(passwordField,".*",passwordError,"invalid",6,8);
			valid(cPasswordField,".*",passwordCError,"invalid",6,8);
		}else{
			logger.info("password and cpassword not match");
			passwordError.setText("password and cpassword");
			passwordCError.setText("not match");
		}
		
		
		
	}
	/**
	 *Egy játékos Tulajdonságait vizsgáló metódus.
	 *Invalid egy játékos például ha userneve nem 3 és 5 karakter között van.
	 */
	public void valid(TextField textField, String pattern, Label errorField, String errorMessage, int min, int max){
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(textField.getText());
		if (!m.matches() || textField.getText().length() < min || textField.getText().length() > max) {
			errorField.setText(errorMessage);
			errorField.setTextFill(Color.RED);
			logger.info("invalid textfield");
		}
		else{
			errorField.setText("");
			logger.info("valid textfield");
		}
	}
	/**
	 *Bejelentkezést vizsgáló metódus.
	 */
	public boolean loginValidation(TextField userName, PasswordField pwField ,Label errorField){
		errorField.setTextFill(Color.RED);
		if (!userName.getText().equals("") || !pwField.getText().equals("")) {
			try {
				Player player = Main.PM.getPlayerFromDB(userName.getText());
				if (Main.PM.getPasswordEncryptor().checkPassword(pwField.getText(),player.getPassword())) {
					Main.PM.setLoggedInPlayer(player);
					logger.info("successful login");
					return true;
				}else{
					logger.info("login failed");
					errorField.setText("username or password not correct");
				}
			} catch (Exception e) {
				logger.info("login failed");
				errorField.setText("username or password not correct");
			}
		
		}
		return false;
	}

}
