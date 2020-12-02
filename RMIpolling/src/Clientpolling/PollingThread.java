package Clientpolling;

import java.rmi.RemoteException;

import Interfaces.Chat;

public class PollingThread extends Thread{
	private Clientpolling client;
	private Chat serveur;
	
	public PollingThread(Clientpolling client, Chat serveur) {
		this.client = client;
		this.serveur = serveur;
	}

	public void run(){
		try {
			client.messagesclient = serveur.envoyerListeMessages();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				if(client.messagesclient.size()<serveur.envoyerListeMessages().size()) { // si tous les messages du serveur ne sont pas dans la liste client...
					int debut = client.messagesclient.size();
					client.messagesclient = serveur.envoyerListeMessages();
					for(int i = debut; i<serveur.envoyerListeMessages().size(); i++) {
						System.out.println(client.messagesclient.get(i));
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
}