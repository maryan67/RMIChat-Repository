package com.client;

import java.rmi.RemoteException;
import java.util.List;

import com.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class chatUiController {
	@FXML
	private Button disconnectButton;
	@FXML
	private Button connectButton;
	@FXML
	private Button sendMessageButton;
	@FXML
	private TextField messageText;
	@FXML
	private ListView onlineUsers;
	@FXML
	private Label chatLabel;
	@FXML
	private TextField userNameText;
	@FXML
	private Button selectParticipantButton;

	private ClientHandler clientHandler;

	public chatUiController() {
		try {
			clientHandler = new ClientHandler(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void initialize() {

		sendMessageButton.setDisable(true);
		disconnectButton.setDisable(true);
	}

	// Event Listener on Button[#disconnectButton].onAction
	@FXML
	public void onDisconnectClick(ActionEvent event) {
		clientHandler.setPartenerInConversation(null);
		selectParticipantButton.setDisable(false);
		onlineUsers.setDisable(false);
		chatLabel.setText(chatLabel.getText()+"\n"+"Choose another partener in chat");
		sendMessageButton.setDisable(true);

	}

	// Event Listener on Button[#connectButton].onAction
	@FXML
	public void onConnectClick(ActionEvent event) {
		User user = new User(userNameText.getText());
		clientHandler.setLoggedUser(user);
		connectButton.setDisable(true);
		userNameText.setDisable(true);

	}

	// Event Listener on Button[#sendMessageButton].onAction
	@FXML
	public void onSendClick(ActionEvent event) {
		try {
			chatLabel.setText(chatLabel.getText() + "\n" + clientHandler.getUser() + ":  "+messageText.getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientHandler.sendMessage(messageText.getText());
	}

	// Event Listener on Button[#selectParticipantButton].onAction
	@FXML
	public void onSelectClick(ActionEvent event) {
		clientHandler.setPartenerInConversation((User) onlineUsers.getSelectionModel().getSelectedItem());
		selectParticipantButton.setDisable(true);
		onlineUsers.setDisable(true);
		chatLabel.setText("");
		sendMessageButton.setDisable(false);
		disconnectButton.setDisable(false);
	}

	public void updateText(String newText) {

		chatLabel.setText(chatLabel.getText() + "\n" + newText);
	}

	public void updateUsers(List<User> users) {

		ObservableList<User> connectedUsers = FXCollections.observableArrayList(users);
		onlineUsers.setItems(connectedUsers);
	}
}
