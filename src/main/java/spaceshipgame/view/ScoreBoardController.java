package spaceshipgame.view;
//CHECKSTYLE:OFF
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import spaceshipgame.model.Player;

public class ScoreBoardController {
	Main main;
	@FXML
	private TableView<Player> table;
	@FXML
 	private TableColumn<Player,String> username;
	@FXML
 	private TableColumn<Player,String> gametime;
	@FXML
 	private TableColumn<Player,String> stage;
	@FXML
 	private TableColumn<Player,String> score;
	
	ObservableList<Player> data = FXCollections.observableArrayList();
	
	public void getData(){
		for (Player p : Main.PM.getPlayersFromDB()) {
			data.add(p);
		}
	}
	
	public void setTable(){
		
		
        username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player, String>, 
        		ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Player, String> p) {
				// TODO Auto-generated method stub
				return new ReadOnlyStringWrapper( p.getValue().getUserName());
			}

			
        });
        
        gametime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player, String>, 
        		ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Player, String> p) {
				// TODO Auto-generated method stub
				return new ReadOnlyStringWrapper( p.getValue().getGameTime());
			}

			
        });
        
        stage.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player, String>, 
        		ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Player, String> p) {
				// TODO Auto-generated method stub
				return new ReadOnlyStringWrapper(String.valueOf(p.getValue().getHighestStage()));
			}

			
        });
        
        score.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player, String>, 
        		ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Player, String> p) {
				// TODO Auto-generated method stub
				return new ReadOnlyStringWrapper( String.valueOf(p.getValue().getHighestScore()));
			}

			
        });
	
		table.setItems(data);	
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
}
