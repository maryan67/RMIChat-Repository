package com.server;

import java.rmi.Naming;

public class ServerMain {

	private static final String remotePath = "rmi://localhost/ServerRMI";
	
	public static void main(String[] args) {

		ServerDriver remoteServer = null;
		try {
			remoteServer = new ServerDriver();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			Naming.rebind(remotePath, remoteServer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
