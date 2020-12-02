package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Chat extends Remote{
	public String messageBienvenue() throws RemoteException;
	public void envoyerMessage(String message) throws RemoteException; 
	public String recupererMessage(int position) throws RemoteException;
	public int nbMessage() throws RemoteException;
	public String nouveauUtilisateur() throws RemoteException;
	public String departUtilisateur(String nom) throws RemoteException;
	public ArrayList<String> envoyerListeMessages() throws RemoteException;
}
