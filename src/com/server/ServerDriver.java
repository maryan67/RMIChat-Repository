package com.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.models.ChatClientRMI;
import com.models.ChatServerRMI;
import com.models.Message;
import com.models.User;

public class ServerDriver extends UnicastRemoteObject implements ChatServerRMI {

	private static final long serialVersionUID = 1L;
	private List<ChatClientRMI> connectedUsers = new ArrayList<ChatClientRMI>();

	protected ServerDriver() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendMessage(Message message) throws RemoteException {
		int connectedClients = connectedUsers.size();
		for (int i = 0; i < connectedClients; i++) {
			if (connectedUsers.get(i).getUser().getName().equals(message.getReceiver().getName())) {
				connectedUsers.get(i).receiveMessage(message);
			}

		}

	}

	@Override
	public void addClient(ChatClientRMI chatClient) throws RemoteException {
		connectedUsers.add(chatClient);
		updateOnlineUsers();
	//	updateOnlineUsers();
	}

	@Override
	public ArrayList<User> receiveConnectedUsers() throws RemoteException {
		ArrayList<User> connectedUserNames = new ArrayList<User>();
		int connectedClients = connectedUsers.size();

		for (int i = 0; i < connectedClients; i++) {
			connectedUserNames.add(connectedUsers.get(i).getUser());
		}
		return connectedUserNames;
	}
	
	private void updateOnlineUsers() throws RemoteException {
		int connectedClients = connectedUsers.size();

		List<User> users =  receiveConnectedUsers();
		for (int i = 0; i < connectedClients; i++) {
			try {
				connectedUsers.get(i).updateOnlineUsers(users);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
