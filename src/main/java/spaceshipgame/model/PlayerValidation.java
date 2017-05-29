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
	 *@param textField Az input dátum.
	 *@param pattern A dátum formája.
	 *@param errorField Error mező.
	 */
	public void dateValidation(TextField textField, String pattern, Label errorField) {
		errorField.setTextFill(Color.RED);
	    if (textField.getText().matches(pattern)){
	    	
	    	dateFormatter.setLenient(false);
	 	    try {
	 	    	dateFormatter.parse(textField.getText());
	 	    	logger.info("valid date");
	 	        errorField.setText("");
	 	    } catch (ParseException ex) {
	 	    	logger.warn("invalid date format");
	 	    	errorField.setText("invalid format. try yyyy-MM-dd");
	 	    }
	    }else{
	    	logger.warn("invalid date");
	    	errorField.setText("invalid date");
	    }  
	}
	/**
	 *Jelszó helyességét vizsgáló metódus.<br>
	 *Meghívásra kerül a {@link valid(TextField,String,Label,String,int,int)} <br>
	 *hogy megvizsgáljuk megfelelő hosszúságú e a jelszó.
	 *@param passwordField Jelszómező.
	 *@param cPasswordField Megerősítő jelszómező.
	 *@param passwordError Error mező.
	 *@param passwordCError Megerősítő error mező.
	 */
	public void passwordValidation(PasswordField passwordField,PasswordField cPasswordField,Label passwordError,Label passwordCError){
		passwordError.setTextFill(Color.RED);
		passwordCError.setTextFill(Color.RED);
		if (passwordField.getText().equals(cPasswordField.getText())) {
			valid(passwordField,".*",passwordError,"invalid",6,8);
			valid(cPasswordField,".*",passwordCError,"invalid",6,8);
			logger.info("valid pw");
		}else{
			logger.warn("password and cpassword not match");
			passwordError.setText("password and cpassword");
			passwordCError.setText("not match");
		}
		
		
		
	}
	/**
	 *Egy játékos Tulajdonságait vizsgáló metódus.
	 *Invalid egy játékos például ha userneve nem 3 és 5 karakter között van.
	 *@param textField Az input text.
	 *@param pattern A text formája.
	 *@param errorField Error mező.
	 *@param errorMessage A hibaüzenet.
	 *@param min Minimális karakterszám.
	 *@param max Maximális karakterszám.
	 */
	public void valid(TextField textField, String pattern, Label errorField, String errorMessage, int min, int max){
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(textField.getText());
		if (!m.matches() || textField.getText().length() < min || textField.getText().length() > max) {
			errorField.setText(errorMessage);
			errorField.setTextFill(Color.RED);
			logger.warn("invalid textfield");
		}
		else{
			errorField.setText("");
			logger.info("valid textfield");
		}
	}
	
	/**
	 *Játékos felhasználónevének ellenörzése.
	 *@param textField Felhasználónév.
	 *@param usernameError Error mező.
	 *@return Igaz ha még nincs ilyen felhasználónév a rendszerben, hamis egyébként. 
	 */
	public boolean userNameValidation(TextField textField,Label usernameError){
		usernameError.setTextFill(Color.RED);
		try {
			Player p =  Main.PM.getPlayerFromDB(textField.getText());
		} catch (Exception e) {
			logger.info("username is free");
			return true;
		}
		logger.info("username already taken");
		usernameError.setText("already taken");
		return false;
	}
	
	/**
	 *Bejelentkezést vizsgáló metódus.
	 *Lekérdezésre kerül a játékos az adatbázisból<br>
	 *Ez a {@link PlayerManager#getPlayerFromDB(String)} függvénnyel történik <br>
	 *A jelszó visszafejtése a {@link PlayerManager#getPasswordEncryptor()} <br>
	 *függvénnyle történik.
	 *@param userName Játékos felhasználó neve.
	 *@param pwField Jelszómező.
	 *@param errorField Error mező.
	 *@return Igaz ha sikerült bejelentkezni, hamis egyébként.
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
					logger.warn("login failed");
					errorField.setText("username or password not correct");
				}
			} catch (Exception e) {
				logger.warn("login failed");
				errorField.setText("username or password not correct");
			}
		
		}
		return false;
	}

}
