package Clientpolling;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Interfaces.Chat;

public class Clientpolling {
	Chat Serveurpolling;
	static java.util.Scanner entree = new java.util.Scanner(System.in); // création d'un scanner clavier
	static ArrayList<String> messagesclient = new ArrayList<String>(); // création d'une array list pour stocker tous les messages du client
	
	public static ArrayList<String> envoyerListeMessages() throws RemoteException{
		return messagesclient;
	}
	
	public static void main(String args[]) throws Exception {
		Chat stub = (Chat)Naming.lookup("//localhost/RmiServer"); // réalisation de la liaison client-serveur
		PollingThread thread = new PollingThread();
		System.out.println("Entrez votre nom : ");
		String nom = entree.nextLine(); // enregistre la prochaine ligne en tant que nom d'utilisateur
        System.out.println(stub.messageBienvenue()); // message de bienvenue pour informer l'utilisateur qu'il est bien connecté
        String message = entree.nextLine(); // enregistre la prochaine ligne en tant que premier message
        while(!message.equals("q")) { // tant que l'utilisateur n'écrit pas la ligne "q"...
        	thread.run();
        	stub.envoyerMessage(message); // on envoie le message au serveur
        	message=entree.nextLine(); // et on l'affiche chez le client
        }
        stub.departUtilisateur(nom); // on informe du départ du client sur le serveur
        System.exit(0); // et on quitte le programme
    }
}
