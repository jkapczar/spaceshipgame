package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spaceshipgame.model.PlayerValidation;
import spaceshipgame.view.Main;



public class PlayerValidationTest extends ApplicationTest{
	PlayerValidation pv;
	
	TextField textField;
	Label errorField;
	
	PasswordField passwordField;
	PasswordField cPasswordField;
	Label passwordError;
	Label passwordCError;
	
	Parent mainNode;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
			mainNode = FXMLLoader.load(Main.class.getResource("/fxml/test.fxml"));
			stage.setScene(new Scene(mainNode));
			stage.show();
	        stage.toFront();
		
	}
	
	public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }
	
	@Before
	public void setUp() throws Exception  {
		pv = new PlayerValidation();
		
		textField = find("#textField");
		errorField = find("#errorLabel");
		
		passwordField = find("#passwordField");
		cPasswordField = find("#cPasswordField");
		passwordError = find("#passwordError");
		passwordCError = find("#passwordCError");
		
	}
	
	
	@Test
	public void dateValidation1(){
		textField.setText("1111-11-11");
		errorField.setText("");
		String pattern = "\\d{4}-[01]\\d-[0-3]\\d";
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				pv.dateValidation(textField, pattern, errorField);				
			}
			   
			});
		sleep(300);
		assertEquals("",errorField.getText());
		
	}
	@Test
	public void dateValidation2(){
		textField.setText("1111-aa-11");
		errorField.setText("");
		String pattern = "\\d{4}-[01]\\d-[0-3]\\d";
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				pv.dateValidation(textField, pattern, errorField);				
			}
			   
			});
		sleep(300);
		assertEquals("invalid date",errorField.getText());
	}
	
	@Test
	public void dateValidation3(){
		textField.setText("1111-13-11");
		errorField.setText("");
		String pattern = "\\d{4}-[01]\\d-[0-3]\\d";
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				pv.dateValidation(textField, pattern, errorField);				
			}
			   
			});
		sleep(300);
		assertEquals("invalid format. try yyyy-MM-dd",errorField.getText());
	}
	
	@Test
	public void pwtest1(){
		
		passwordField.setText("pw1");
		cPasswordField.setText("pw2");
		passwordError.setText("");
		passwordCError.setText("");
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				pv.passwordValidation(passwordField, cPasswordField, passwordError, passwordCError);				
			}
			   
			});
		sleep(300);
		assertEquals("password and cpassword",passwordError.getText());
		assertEquals("not match",passwordCError.getText());
		
	}
	
	@Test
	public void pwtest2(){
		passwordField.setText("pw1");
		cPasswordField.setText("pw1");
		passwordError.setText("");
		passwordCError.setText("");
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				pv.passwordValidation(passwordField, cPasswordField, passwordError, passwordCError);				
			}
			   
			});
		sleep(300);
		assertEquals("invalid",passwordError.getText());
		assertEquals("invalid",passwordCError.getText());
	}
	
	@Test
	public void pwtest3(){
		passwordField.setText("pwtest01");
		cPasswordField.setText("pwtest01");
		passwordError.setText("");
		passwordCError.setText("");
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				pv.passwordValidation(passwordField, cPasswordField, passwordError, passwordCError);				
			}
			   
			});
		sleep(300);
		assertEquals("",passwordError.getText());
		assertEquals("",passwordCError.getText());
	}

	@Test
	public void loginTest1(){
		textField.setText("asdasd");
		passwordField.setText("asdasd");
		errorField.setText("");
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
						
				assertEquals(false,pv.loginValidation(textField, passwordField, errorField));
			}
			   
			});
		sleep(500);
	
		
		
		
	}
	@Test
	public void loginTest2(){
		textField.setText("test3");
		passwordField.setText("asd123a");
		errorField.setText("");
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
						
				assertEquals(false,pv.loginValidation(textField, passwordField, errorField));
			}
			   
			});
		sleep(500);
	}
	
	@Test
	public void loginTest3(){
		textField.setText("test3");
		passwordField.setText("asdasd");
		errorField.setText("");
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
						
				assertEquals(true,pv.loginValidation(textField, passwordField, errorField));
			}
			   
			});
		sleep(500);
	}
	
	 @After
	 public void tearDown() throws TimeoutException {
	        /* Close the window. It will be re-opened at the next test. */
	        FxToolkit.hideStage();
	        
	 }

	
	
	
	
	

}
