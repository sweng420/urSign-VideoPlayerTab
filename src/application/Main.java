package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import player.Player;

public class Main extends Application {
	
	ComboBox<String> videosList;
	Player vidplayer;
	
	
	public static void main(String[] args) {
		// Notify the program of where to find VLCJ	
        launch(args);
    }

	@Override
	public void start(Stage stage) {		
		try{
			
			//Root pane
			BorderPane borderPaneBase = new BorderPane();
			
			//Initialise scene and add to stage
			Scene scene = new Scene(borderPaneBase,800,600);
			Canvas canvas = new Canvas(750,500);
			
			vidplayer  = new Player(canvas,0,0, stage);
			vidplayer.controls.disableButtons(true);
			String [] paths = {
					//ASL Alphabet
					"https://www.youtube.com/watch?v=tkMg8g8vVUo",
					//ASL Numbers 1-30
					"https://www.youtube.com/watch?v=hFCXyB6q2nU",
					//ASL Family Signs
					"https://www.youtube.com/watch?v=XUg1eKl65p4",
			};
		
			//Load videos
			vidplayer.loadPaths(paths);

			//Setup ComboBox for users to select a video to play
			videosList = new ComboBox<String>();
			videosList.setPromptText("Select video...");
			videosList.prefWidthProperty().bind(scene.widthProperty());
			videosList.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
			
			videosList.getItems().addAll(
					"ASL Alphabet",
					"ASL Numbers 1-30",
					"ASL Family Signs"
			);
			
			//Add components to scene and show stage
			borderPaneBase.setTop(videosList);
			borderPaneBase.setCenter(vidplayer);
			stage.setScene(scene);
			stage.show();
			
			runProgram();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void runProgram(){

		//When ComboBox value changes, load the selected Video
		videosList.setOnAction(e->{
			vidplayer.controls.disableButtons(false);
			vidplayer.loadVideo(videosList.getSelectionModel().getSelectedIndex());
		});

	}
	
	

}