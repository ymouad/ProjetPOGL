package Modele;

import java.awt.Color;
import java.util.ArrayList;

import Controleur.Controleur;
import Controleur.Observable;

public class Artefact extends Observable {
	private String nom;
	private Zone position;
	private Color clr;
	
	public Artefact(String nom, Zone position) {
		setNom(nom);
		setPosition(position);
	}
	
	/**
	 * initialiser les artefacts de la grille passée en paramètre
	 * l'héliport est considéré comme un artefact sauf qu'il n'a pas de clé correspondante donc irrecuperable
	 **/
	public static ArrayList<Artefact> init(Grille grille) {
		ArrayList<Artefact> art = new ArrayList<Artefact>();
		Artefact art5 = new Artefact("héliport", Zone.randomZone(grille));
		art5.setColor();
		Artefact art1 = new Artefact("feu", Zone.randomZone(grille));
		art1.setColor();
		Artefact art2 = new Artefact("air", Zone.randomZone(grille));
		art2.setColor();
		Artefact art3 = new Artefact("terre", Zone.randomZone(grille));
		art3.setColor();
		Artefact art4 = new Artefact("eau", Zone.randomZone(grille));
		art4.setColor();
		art.add(art5);
		art.add(art1);
		art.add(art2);
		art.add(art3);
		art.add(art4);

		return art;
	}
	

	public void setPosition(Zone position) {
		this.position = position;
	}
	
	public Zone getPosition() {
		return position;
	}
	
	public int getX() {
        return position.getcoordonnees().getX();
    }
	
	public int getY() {
        return position.getcoordonnees().getY();
    }
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setColor() {
		String nom = this.nom;
    	switch(nom){
	        case "héliport":
	            this.clr=Color.BLACK;
	            break;
	        case "feu" :
	            this.clr=Color.RED;
	            break;
	        case "air":
	            this.clr=Color.GRAY;
	            break;
	        case "terre":
	            this.clr=Color.ORANGE;
	            break;
	        case "eau":
	            this.clr=Color.BLUE;
	            break;
    	}
    }

	public Zone getZone() {
		return this.position;
	}

	public Color getColor() {
		return this.clr;
	}
	
}
