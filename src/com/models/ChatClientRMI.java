package com.models;

import java.rmi.Remote;
import java.util.List;

public interface ChatClientRMI extends Remote {

	public void receiveMessage(Message message) throws java.rmi.RemoteException;

	public User getUser() throws java.rmi.RemoteException;

	public void updateOnlineUsers(List<User> users) throws java.rmi.RemoteException;
}
