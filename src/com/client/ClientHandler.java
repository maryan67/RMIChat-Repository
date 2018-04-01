package com.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.models.ChatClientRMI;
import com.models.ChatServerRMI;
import com.models.Message;
import com.models.User;

import javafx.application.Platform;

public class ClientHandler extends UnicastRemoteObject implements ChatClientRMI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChatServerRMI remoteServer;
	private chatUiController chatUiControllerObject;
	private static final String remotePath = "rmi://localhost/ServerRMI";
	private User loggedUser;
	private List<User> connectedUsers = new ArrayList<User>();
	private User partenerInConversation;

	public ClientHandler(chatUiController chatUiControllerObject) throws RemoteException {

		this.chatUiControllerObject = chatUiControllerObject;
		try {
			remoteServer = (ChatServerRMI) Naming.lookup(remotePath);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void receiveMessage(Message message) throws RemoteException {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				chatUiControllerObject.updateText(message.getRecipient()+":  " + message.getMessage());

			}

		});

	}

	@Override
	public User getUser() throws RemoteException {
		return loggedUser;
	}

	public void sendMessage(String message) {
		Message messageToSend = new Message(loggedUser, partenerInConversation, message);
		try {
			remoteServer.sendMessage(messageToSend);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPartenerInConversation(User partenerInConversation) {
		this.partenerInConversation = partenerInConversation;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;

		try {
			remoteServer.addClient(this);
			//connectedUsers = remoteServer.receiveConnectedUsers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Platform.runLater(new Runnable() {
//
//			@Override
//			public void run() {
//				chatUiControllerObject.updateUsers(connectedUsers);
//			}
//
//		});
	}

	@Override
	public void updateOnlineUsers(List<User> users) throws RemoteException {
		connectedUsers = users;

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				chatUiControllerObject.updateUsers(connectedUsers);
			}

		});

	}

}
