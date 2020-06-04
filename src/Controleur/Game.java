package Controleur;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import Modele.*;
import Vue.*;

import Vue.CVue;

/**
 * Nous allons commencer � construire notre application, en voici la classe
 * principale.
 */
public class Game {
    
    public static void main(String[] args) {
    	
		
		EventQueue.invokeLater(() -> {
			
					Scanner sc = new Scanner(System.in);
			    	int nb = 0;
			    	System.out.println("L'�le interdite");
			    	System.out.println("---");
			    	System.out.println("Commandes :");
			    	System.out.println("D�placer (-1 action) : permet de se d�placer avec les fl�ches du clavier.");
			    	System.out.println("Ass�cher (-1 action) : permet d'ass�cher une zone avec les fl�ches du clavier ou (enter) pour la zone actuelle");
			    	System.out.println("Artefact : permet de r�cuperer l'artefact pr�sent la zone actuelle � condition d'avoir la cl� correspondante");
			    	System.out.println("Echange (-1 action) : permet l'�change de cl� avec un joueur de votre choix");
			    	System.out.println("Sp�cial : permet de r�aliser une action sp�ciale : Sable pour ass�cher et H�lico pour se d�placer ");
			    	System.out.println("Fin de tour : permet de finir le tour, inonder 3 zones al�atoires, donner (ou pas) au joueur actif une cl� et une action sp�ciale");
			    	System.out.println("---");
			    	System.out.println("Affichage :");
			    	System.out.println("Le joueur actif est affich� en carr� vert");
			    	System.out.println("Le nombre d'actions restantes est affich� au coin bas droit de chaque joueur");
			    	System.out.println("Sur chaque carr� representant un joueur est marqu�e l'initiale de la cl� que poss�de le joueur (A : air, F: feu, T: terre et E: eau");
			    	System.out.println("Au sud de chaque carr� de joueur est marqu� l'action sp�ciale (O pour rien)");
			    	System.out.println("L'�le est represent�e par la grille");
			    	System.out.println("Les zones inond�es sont en bleu clair, submerg�e (inaccessible) en bleu fonc�");
			    	System.out.println("Les artefacts sont represent�s par des petits carr�s ayant chacun une couleur significative");
			    	System.out.println("L'h�liport est represent� par un carr� noir");
			    	System.out.println("La console servira � afficher des informations importantes, comme le joueur actif et les actions n�cessitant une entr�e au clavier");
			    	System.out.println("---");
			    	System.out.println("R�gles :");
			    	System.out.println("Pour chaque Artefact il faut une cl�");
			    	System.out.println("Partie perdue lorsque : un joueur meurt, l'h�liport ou un artefact disparait");
			    	System.out.println("Partie gagn�e si tous les artefacts sont r�cup�r�s et tous les joueurs rassembl�s sur la zone de l'h�liport");
			    	System.out.println("---");
			    	System.out.println("Entrez le nombre de joueurs (2-4)");
			    	while (nb == 0)
			            try {
			                nb = Integer.parseInt(sc.nextLine());
			                break;
			            } catch (NumberFormatException nfe) {
			                System.out.println("Nombre invalide");
			            }
			    	System.out.println("---");
			    	System.out.println("D�marrage :");
			    	Grille modele = new Grille(nb);
	                CVue vue = new CVue(modele);
		    });
	    }
}