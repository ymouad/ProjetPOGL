package Vue;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Modele.Artefact;
import Modele.Grille;
import Modele.Joueur;
import Modele.Zone;

/**
 * Une classe pour représenter la zone d'affichage des cellules.
 *
 * JPanel est une classe d'éléments graphiques, pouvant comme JFrame contenir
 * d'autres éléments graphiques.
 *
 * Cette vue va être un observateur du modèle et sera mise à jour à chaque
 * nouvelle génération des cellules.
 */

class VueGrille extends JPanel implements Controleur.Observer {
    /** On maintient une référence vers le modèle. */
    private Grille modele;
    /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
    private final static int TAILLE = 120;

    /** Constructeur. */
    public VueGrille(Grille modele) {
	this.modele = modele;
	/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
	modele.addObserver(this);
	/**
	 * Définition et application d'une taille fixe pour cette zone de
	 * l'interface, calculée en fonction du nombre de cellules et de la
	 * taille d'affichage.
	 */
	Dimension dim = new Dimension(TAILLE*Grille.LARGEUR,
				      TAILLE*Grille.HAUTEUR);
	this.setPreferredSize(dim);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se content de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() { repaint(); }

    /**
     * Les éléments graphiques comme [JPanel] possèdent une méthode
     * [paintComponent] qui définit l'action à accomplir pour afficher cet
     * élément. On la redéfinit ici pour lui confier l'affichage des cellules.
     *
     * La classe [Graphics] regroupe les éléments de style sur le dessin,
     * comme la couleur actuelle.
     */
    public void paintComponent(Graphics g) {
		super.repaint();
		
		for(int i=0; i<Grille.LARGEUR; i++) {
		    for(int j=0; j<Grille.HAUTEUR; j++) {
		    	paint(g, modele.getZone(i, j), (i)*TAILLE, (j)*TAILLE);
		    }
		}
		for(Artefact a : modele.art) {
			Zone z = a.getZone();
	    	int i = z.getX();
	    	int j = z.getY();
	    	switch(a.getNom()){
	        case "feu" :
	        	paintArt(g, a, (i)*TAILLE, (j)*TAILLE);
	            break;
	        case "air":
	        	paintArt(g, a, (i)*TAILLE + TAILLE/4, (j)*TAILLE + TAILLE/4);
	            break;
	        case "terre":
	        	paintArt(g, a, (i)*TAILLE + TAILLE*3/4, (j)*TAILLE + TAILLE*3/4);
	            break;
	        case "eau":
	        	paintArt(g, a, (i)*TAILLE + TAILLE*2/4, (j)*TAILLE + TAILLE*2/4);
	            break;
	        case "héliport":
	        	paintArt(g, a, (i)*TAILLE + TAILLE/4, (j)*TAILLE + TAILLE/4);
	            break;
    	}
	    	
		}
		for(Joueur J : modele.joueurs) {
			Zone z = J.getZone();
	    	int i = z.getX();
	    	int j = z.getY();
	    	paintJ(g, J, (i)*TAILLE  + TAILLE/3, (j)*TAILLE  + TAILLE/3);
		}
		paintJ(g, modele.jActif, (modele.jActif.getX())*TAILLE  + TAILLE/3, (modele.jActif.getY())*TAILLE  + TAILLE/3);
    }
    
    private void paint(Graphics g, Zone z, int x, int y) {
    	g.setColor(z.getColor());
    	g.fillRect(x, y, TAILLE, TAILLE);
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, TAILLE, TAILLE);
    }
    
    private void paintArt(Graphics g, Artefact z, int x, int y) {
    	g.setColor(z.getColor());
    	if(z.getNom() == "héliport") {
    		g.fillRect(x, y, TAILLE/2, TAILLE/2);
    	} else {
    		g.fillRect(x, y, TAILLE/4, TAILLE/4);
    	}
    	g.setColor(Color.BLACK);
    	Character firstLetter = z.getNom().toUpperCase().charAt(0);
    	g.drawString(Character.toString(firstLetter), x + TAILLE/8, y + TAILLE/8);
    }
    
    private void paintJ(Graphics g, Joueur z, int x, int y) {
    	g.setColor(z.getColor());
    	g.fillRect(x, y, TAILLE/3, TAILLE/3);
    	g.setColor(Color.BLACK);
    	g.drawString(z.getNom(), x, y);
    	
    	Character firstLetter = z.getCle().name().toUpperCase().charAt(0);
    	g.drawString(Character.toString(firstLetter), x + TAILLE/6, y + TAILLE/6);
    	
    	g.drawString(Integer.toString(z.actions), x + TAILLE/3, y + TAILLE/3);
    	
    	g.drawString(z.spx.name(), x, y + TAILLE/3);
    }

}

