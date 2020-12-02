package Clientpolling;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

import Interfaces.Chat;

public class Clientpolling {
	Chat Serveurpolling;
	public static Scanner entree = new Scanner(System.in); // création d'un scanner clavier
	public ArrayList<String> messagesclient = new ArrayList<String>(); // création d'une array list pour stocker tous les messages du client
	
	public static void main(String args[]) throws Exception {
		Chat stub = (Chat)Naming.lookup("//localhost/RmiServer"); // réalisation de la liaison client-serveur
		Clientpolling client = new Clientpolling();
		System.out.println("Entrez votre nom : ");
		String nom = entree.nextLine(); // enregistre la prochaine ligne en tant que nom d'utilisateur
        System.out.println(stub.messageBienvenue()); // message de bienvenue pour informer l'utilisateur qu'il est bien connecté
		new PollingThread(client, stub).start(); //Demarrage du Thread de polling
        String message = ""; // enregistre la prochaine ligne en tant que premier message
        while(!message.equals("q")) { // tant que l'utilisateur n'écrit pas la ligne "q"...
        	message = entree.nextLine();
        	client.messagesclient.add(message);
        	stub.envoyerMessage(message); // on envoie le message au serveur
        }
        stub.departUtilisateur(nom); // on informe du départ du client sur le serveur
        System.exit(0); // et on quitte le programme
    }
}