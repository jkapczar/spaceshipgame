package spaceshipgame.model;

import java.sql.Date;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *Játékos modellt megvalosító osztály.
 */
@Entity(name = "PLAYERS")
@Table(name = "PLAYERS")
@NamedQueries({
	@NamedQuery(name = "player.findAll" , query = "select p from PLAYERS p"),
	@NamedQuery(name = "player.find" , query = "select p from PLAYERS p where p.userName = :name"),
	@NamedQuery(name = "player.update.gameTime" , query = "update PLAYERS p set p.gameTime = :time where p.userName = :name"),
	@NamedQuery(name = "player.update.highestScore" , query = "update PLAYERS p set p.highestScore = :score where p.userName = :name"),
	@NamedQuery(name = "player.update.highestStage" , query = "update PLAYERS p set p.highestStage = :stage where p.userName = :name")
})
public class Player {
	@Id
	@NotNull
	@Column(name = "ID")
	@SequenceGenerator(name="szekv",sequenceName = "PLAYERS_SEQ",allocationSize = 1,initialValue=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="szekv"  )
	private int id;
	//db
	@NotNull
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DATE_OF_BIRTH")
	private Date date;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GAMETIME")
	private String gameTime = "00:00:00";
	
	@Column(name = "HIGHEST_STAGE")
	private int highestStage;
	
	@Column(name = "HIGHEST_SCORE")
	private int highestScore;
	//time
	
	//game
	@Transient
	private int stage;
	@Transient
	private int score;
	@Transient
	private int lifes;
	@Transient
	private long time;
	@Transient
	private long startTime;
	@Transient
	private long elapsedTime;
	
	/**
	 *Új játék eseték visszaállítja a statisztikákat alapértelmezettre.
	 */
	public void setDefault(){
		stage = 1;
		score = 0;
		lifes = 3;
		time = 0;
		elapsedTime = 0;
		startTimer();
	}
	/**
	 *Játék kezdetekor elindítja a idő számlálóját.
	 */
	public void startTimer(){
		startTime = Instant.now().toEpochMilli();
	}
	/**
	 *Frissíti az idő számlálóját.
	 */
	public void updateTimer(){
		setTime(elapsedTime + ((Instant.now().toEpochMilli() - startTime) / 1000));
	}
	/**
	 *Megállítja az idő számlálóját.
	 */
	public void stopTimer(){
		elapsedTime = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date bDate) {
		this.date = bDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGameTime() {
		return gameTime;
	}
	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}
	public int getHighestStage() {
		return highestStage;
	}
	public void setHighestStage(int highestStage) {
		this.highestStage = highestStage;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLifes() {
		return lifes;
	}
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	
}
