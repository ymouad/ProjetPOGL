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
 * Nous allons commencer à construire notre application, en voici la classe
 * principale.
 */
public class Game {
    
    public static void main(String[] args) {
    	
		
		EventQueue.invokeLater(() -> {
			
					Scanner sc = new Scanner(System.in);
			    	int nb = 0;
			    	System.out.println("L'île interdite");
			    	System.out.println("---");
			    	System.out.println("Commandes :");
			    	System.out.println("Déplacer (-1 action) : permet de se déplacer avec les flèches du clavier.");
			    	System.out.println("Assécher (-1 action) : permet d'assécher une zone avec les flèches du clavier ou (enter) pour la zone actuelle");
			    	System.out.println("Artefact : permet de récuperer l'artefact présent la zone actuelle à condition d'avoir la clé correspondante");
			    	System.out.println("Echange (-1 action) : permet l'échange de clé avec un joueur de votre choix");
			    	System.out.println("Spécial : permet de réaliser une action spéciale : Sable pour assécher et Hélico pour se déplacer ");
			    	System.out.println("Fin de tour : permet de finir le tour, inonder 3 zones aléatoires, donner (ou pas) au joueur actif une clé et une action spéciale");
			    	System.out.println("---");
			    	System.out.println("Affichage :");
			    	System.out.println("Le joueur actif est affiché en carré vert");
			    	System.out.println("Le nombre d'actions restantes est affiché au coin bas droit de chaque joueur");
			    	System.out.println("Sur chaque carré representant un joueur est marquée l'initiale de la clé que possède le joueur (A : air, F: feu, T: terre et E: eau");
			    	System.out.println("Au sud de chaque carré de joueur est marqué l'action spéciale (O pour rien)");
			    	System.out.println("L'île est representée par la grille");
			    	System.out.println("Les zones inondées sont en bleu clair, submergée (inaccessible) en bleu foncé");
			    	System.out.println("Les artefacts sont representés par des petits carrés ayant chacun une couleur significative");
			    	System.out.println("L'héliport est representé par un carré noir");
			    	System.out.println("La console servira à afficher des informations importantes, comme le joueur actif et les actions nécessitant une entrée au clavier");
			    	System.out.println("---");
			    	System.out.println("Régles :");
			    	System.out.println("Pour chaque Artefact il faut une clé");
			    	System.out.println("Partie perdue lorsque : un joueur meurt, l'héliport ou un artefact disparait");
			    	System.out.println("Partie gagnée si tous les artefacts sont récupérés et tous les joueurs rassemblés sur la zone de l'héliport");
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
			    	System.out.println("Démarrage :");
			    	Grille modele = new Grille(nb);
	                CVue vue = new CVue(modele);
		    });
	    }
}