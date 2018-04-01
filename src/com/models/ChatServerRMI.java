package com.models;

import java.rmi.Remote;
import java.util.ArrayList;
public interface ChatServerRMI extends Remote {

	
	public void sendMessage(Message message)throws java.rmi.RemoteException;
	public void addClient(ChatClientRMI chatClient) throws java.rmi.RemoteException;
	public ArrayList<User> receiveConnectedUsers()throws java.rmi.RemoteException;
	
}
