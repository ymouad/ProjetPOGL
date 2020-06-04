package Controleur;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Modele.*;

/**
 * Classe pour notre contrôleur rudimentaire.
 * 
 * Le contrôleur implémente l'interface [ActionListener] qui demande
 * uniquement de fournir une méthode [actionPerformed] indiquant la
 * réponse du contrôleur à la réception d'un événement.
 */
public class Controleur implements ActionListener, KeyListener {
    /**
     * On garde un pointeur vers le modèle, car le contrôleur doit
     * provoquer un appel de méthode du modèle.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du modèle est inutile. On pourrait se contenter de
     * faire directement référence au modèle enregistré pour la classe
     * englobante [VueCommandes].
     */
    Grille modele;
    public Controleur(Grille modele) { this.modele = modele; }

	Scanner sc = new Scanner(System.in);
    
    /**
     * Action effectuée à réception d'un événement : appeler la
     * méthode [avance] du modèle.
     */
    public void actionPerformed(ActionEvent e) {
    	if (!modele.partiePerdue && !modele.partieGagnee) {
    		switch(e.getActionCommand()) {
	    	case "tour" :
	    		modele.Tour();
	    		break;
	    	case "art" :
	    		modele.recupArt();
	    		break;
	    	case "ex" :
	    		System.out.println("Veuillez taper le numéro joueur avec qui vous voulez échanger votre clé");
	    		while (true)
	                try {
	                    int j = Integer.parseInt(sc.nextLine());
	    	    		modele.echange(j);
	                    break;
	                } catch (NumberFormatException nfe) {
	                    System.out.println("Joueur introuvable");
	                    break;
	                }
	    		break;
	    		
	    	case "sp" :
	    		if(modele.jActif.spx.name() == "Hélico") {
	    		while (true)
	                try {
	    	    		System.out.println("Veuillez entrer l'abscisse (0-5) de votre destination");
	                    int i = Integer.parseInt(sc.nextLine());
	    	    		System.out.println("Veuillez entrer l'ordonnée(0-5) de votre destination");
	                    int j = Integer.parseInt(sc.nextLine());
	                    System.out.println("Veuillez entrer le numéro de votre copilote (0 pour aucun)");
	                    int k = Integer.parseInt(sc.nextLine());
	                    modele.jActif.helico(i, j, k);
	                    break;
	                } catch (NumberFormatException nfe) {
	                    System.out.println("Coordonnées invalides ou joueur introuvable");
	                    break;
	                }
	    		} else if(modele.jActif.spx.name() == "Sable") {
		    		while (true)
		                try {
		    	    		System.out.println("Veuillez entrer l'abscisse (0-5) de votre destination");
		                    int i = Integer.parseInt(sc.nextLine());
		    	    		System.out.println("Veuillez entrer l'ordonnée (0-5) de votre destination");
		                    int j = Integer.parseInt(sc.nextLine());
		                    modele.jActif.sable(i, j);
		                    break;
		                } catch (NumberFormatException nfe) {
		                    System.out.println("Coordonnées invalides");
		                    break;
		                }
		    		break;
		    		}
	    		break;
	    		
	    	case "deplacer" :
	    		modele.action = "Déplacer";
	    		break;
	    	case "assecher" :
	    		modele.action = "Assécher";
	    		break;	    		
    		}
    	} else {
			System.exit(0);
    	}
        
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch(modele.action) {
    	case "Déplacer" :
    		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    			modele.jActif.move(Direction.LEFT);
    		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    			modele.jActif.move(Direction.RIGHT);
    		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
    			modele.jActif.move(Direction.UP);
    		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			modele.jActif.move(Direction.DOWN);
    		}
    		break;
    	case "Assécher" :
    		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    			modele.jActif.Assecher(Direction.LEFT);
    		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    			modele.jActif.Assecher(Direction.RIGHT);
    		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
    			modele.jActif.Assecher(Direction.UP);
    		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			modele.jActif.Assecher(Direction.DOWN);
    		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    			modele.jActif.Assecher(Direction.NONE);
    		}
    		break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
