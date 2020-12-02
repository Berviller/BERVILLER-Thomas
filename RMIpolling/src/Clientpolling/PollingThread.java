package Clientpolling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import Serveur.Serveurpolling;

public class PollingThread extends Thread{
	
	Serveurpolling serveur = new Serveurpolling();

	public void run(){
		try {
			ArrayList<String> lstClient = Clientpolling.envoyerListeMessages();// création d'une array list pour stocker tous les messages du client 
		while (true) {
			if(lstClient.size()<serveur.envoyerListeMessages().size()) { // si tous les messages du serveur ne sont pas dans la liste client...
				int debut = lstClient.size();
				lstClient = serveur.envoyerListeMessages();
				for(int i = debut; i<serveur.envoyerListeMessages().size(); i++) {
					System.out.println(lstClient.get(i));
				}
			}
		}
		}catch (IOException e) {};
	}
}
