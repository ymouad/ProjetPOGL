package Controleur;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Modele.*;

/**
 * Classe pour notre contr�leur rudimentaire.
 * 
 * Le contr�leur impl�mente l'interface [ActionListener] qui demande
 * uniquement de fournir une m�thode [actionPerformed] indiquant la
 * r�ponse du contr�leur � la r�ception d'un �v�nement.
 */
public class Controleur implements ActionListener, KeyListener {
    /**
     * On garde un pointeur vers le mod�le, car le contr�leur doit
     * provoquer un appel de m�thode du mod�le.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du mod�le est inutile. On pourrait se contenter de
     * faire directement r�f�rence au mod�le enregistr� pour la classe
     * englobante [VueCommandes].
     */
    Grille modele;
    public Controleur(Grille modele) { this.modele = modele; }

	Scanner sc = new Scanner(System.in);
    
    /**
     * Action effectu�e � r�ception d'un �v�nement : appeler la
     * m�thode [avance] du mod�le.
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
	    		System.out.println("Veuillez taper le num�ro joueur avec qui vous voulez �changer votre cl�");
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
	    		if(modele.jActif.spx.name() == "H�lico") {
	    		while (true)
	                try {
	    	    		System.out.println("Veuillez entrer l'abscisse (0-5) de votre destination");
	                    int i = Integer.parseInt(sc.nextLine());
	    	    		System.out.println("Veuillez entrer l'ordonn�e(0-5) de votre destination");
	                    int j = Integer.parseInt(sc.nextLine());
	                    System.out.println("Veuillez entrer le num�ro de votre copilote (0 pour aucun)");
	                    int k = Integer.parseInt(sc.nextLine());
	                    modele.jActif.helico(i, j, k);
	                    break;
	                } catch (NumberFormatException nfe) {
	                    System.out.println("Coordonn�es invalides ou joueur introuvable");
	                    break;
	                }
	    		} else if(modele.jActif.spx.name() == "Sable") {
		    		while (true)
		                try {
		    	    		System.out.println("Veuillez entrer l'abscisse (0-5) de votre destination");
		                    int i = Integer.parseInt(sc.nextLine());
		    	    		System.out.println("Veuillez entrer l'ordonn�e (0-5) de votre destination");
		                    int j = Integer.parseInt(sc.nextLine());
		                    modele.jActif.sable(i, j);
		                    break;
		                } catch (NumberFormatException nfe) {
		                    System.out.println("Coordonn�es invalides");
		                    break;
		                }
		    		break;
		    		}
	    		break;
	    		
	    	case "deplacer" :
	    		modele.action = "D�placer";
	    		break;
	    	case "assecher" :
	    		modele.action = "Ass�cher";
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
    	case "D�placer" :
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
    	case "Ass�cher" :
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
