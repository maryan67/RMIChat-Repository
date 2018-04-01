package com.client;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		URL url = Paths.get("./src/chatUi.fxml").toUri().toURL();
		Parent root = FXMLLoader.load(url);
		Scene scene = new Scene(root);
		primaryStage.setTitle("CHAT");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
