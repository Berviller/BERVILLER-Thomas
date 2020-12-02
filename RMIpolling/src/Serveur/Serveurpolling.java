package Serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Interfaces.Chat;

public class Serveurpolling extends UnicastRemoteObject implements Chat{
	private static final long serialVersionUID = 1L;
	java.util.Scanner entree = new java.util.Scanner(System.in);
	public static ArrayList<String> messages = new ArrayList<String>(); // création d'une array list pour stocker tous les messages

	public Serveurpolling() throws RemoteException{
	super(0);
	}
	
	@Override
	public String messageBienvenue() throws RemoteException{
		return "Bienvenue!";
	}
	
	public void envoyerMessage(String message) throws RemoteException{
		messages.add(message); // on ajoute le message à la liste de tous les messages...
		System.out.println(message); // ...et on l'affiche
	}
	
	public ArrayList<String> envoyerListeMessages() {
		return messages;
		}
	
	public String recupererMessage(int position) throws RemoteException{
		String message = messages.get(position); //on va chercher le position-ème message de la liste
		return message; // et on le renvoie
	}
	
	public int nbMessage() throws RemoteException{
		int nombre = messages.size(); // comme size donne la longueur de la liste, on obtient le nombre total de messages
		return nombre;
				
	}
	public String nouveauUtilisateur() throws RemoteException{
		String nom = entree.nextLine();
		return nom;
	}
	
	public String departUtilisateur(String nom) throws RemoteException{
		return nom + "viens de partir";
	}
	
	 public static void main(String args[]) throws Exception {
	        try { 
	            LocateRegistry.createRegistry(1099); 
	        } catch (RemoteException e) {
	        }
	        Serveurpolling chatServeur = new Serveurpolling();
	        Naming.rebind("//localhost/RmiServer", chatServeur);
	        System.out.println("Serveur prêt!");
	    }

}