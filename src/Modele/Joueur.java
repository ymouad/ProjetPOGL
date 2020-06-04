package Modele;

import java.awt.Color;
import java.util.ArrayList;

import Controleur.Controleur;
import Controleur.Observable;

public class Joueur extends Observable {
	private String nom;
	public Zone position;
	private Color clr;
	private Cle cle;
	public int actions;
	public int idx;
	public SpecialAction spx;
	
	public Joueur(String nom, Zone position, Cle cle, int i) {
		setNom(nom);
		setPosition(position);
		clr = Color.YELLOW;
		spx = SpecialAction.O;
		setCle(cle);
		actions = 3;
		idx = i;
	}
	
	/**
	 * initialisation des "size" joueurs sur la grille pass�e en param�tre
	 **/
	public static ArrayList<Joueur> init(Grille grille, int size) {
		ArrayList<Joueur> J = new ArrayList<Joueur>();
		for(int i = 0; i < size; i++) {
			J.add(new Joueur("J" + Integer.toString(i+1), Zone.randomZone(grille), Cle.O, i) );
		}
		return J;
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
	
	public void setColor(Color clr) {
		this.clr = clr;
	}

	public void setCle(Cle cle) {
		this.cle = cle;
	}
	
	public Cle getCle() {
		return cle;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	/**
	 * retourne la zone ayant la direction pass�e en param�tre si cette zone est adjacente � position 
	 * et n'est pas submerg�e
	 **/
	public Zone Destination(Direction dir) {
		if(dir == Direction.NONE) {
			return this.position;
		} 
		ArrayList<Zone> adj = this.position.adjacentes();
		for(Zone z : adj) {
			if(z.getDirection() == dir && z.getEtat() != EtatZone.submerg�e) {
				return z;
			}
		}
		return null;
	}
	
	/**
	 * Assecher la zone ayant la direction pass�e en param�tre 
	 * et realiser un tour du jeu si le joueur n'a plus d'actions
	 **/
	public void Assecher(Direction dir) {
		Zone z = Destination(dir);
		if(z!=null && z.getEtat() == EtatZone.inond�e && actions > 0) {
			z.setEtat(EtatZone.normale);
			actions--;
		}
		if(actions == 0) {
			position.getGrille().Tour();
		}
		notifyObservers();
	}
	
	/**
	 * deplacer le joueur vers la zone ayant la direction pass�e en param�tre 
	 * et realiser un tour du jeu si le joueur n'a plus d'actions
	 **/
	public void move(Direction dir) {
			Zone z = Destination(dir);
			if(z!=null && actions > 0) {
				this.setPosition(z);
				actions--;
			} else {
				System.out.println("D�placement impossible");
			}
			if(actions == 0) {
				position.getGrille().Tour();
			}
			position.getGrille().partieGagnee();
			notifyObservers();
	}
	
	/**
	 * R�aliser l'action sp�ciale SacDeSable
	 **/
	public void sable(int i, int j) {
		if(0<=i && 0<=j && i<position.getGrille().HAUTEUR && j<position.getGrille().LARGEUR) {
			Zone z = position.getGrille().zones[i][j];
			if(z.getEtat() == EtatZone.inond�e) {
				z.setEtat(EtatZone.normale);
			}
			spx = SpecialAction.O;
			notifyObservers();
		}
	}
	
	/**
	 * R�aliser l'action sp�ciale H�licopt�re
	 **/
	public void helico(int i, int j, int k) {
		if(0<=i && 0<=j && i<position.getGrille().HAUTEUR && j<position.getGrille().LARGEUR) {
			Zone z = position.getGrille().zones[i][j];
			if(position == z) {
				System.out.println("Vous �tes deja sur la zone de destination.");
			} else if(z.getEtat() == EtatZone.submerg�e) {
				System.out.println("La zone de destination est submerg�e.");
			} else if(k!=0){
				Grille g = this.position.getGrille();
				if( idx != k-1 && 0<k && k<=g.joueurs.size() && g.joueurs.get(k-1).getX() == getX() && g.joueurs.get(k-1).getY() == getY()) {
					g.joueurs.get(k-1).position = z;
					position = z;
					spx = SpecialAction.O;
					position.getGrille().partieGagnee();
				} else {
					System.out.println("Joueur introuvable");
				}
			} else {
				position = z;
				spx = SpecialAction.O;
				position.getGrille().partieGagnee();
			}
			notifyObservers();
		}
	}
	

	public Color getColor() {
		return clr;
	}

	public Zone getZone() {
		return position;
	}

	public void setCle(String c) {
		switch(c){
        case "O":
            this.cle=Cle.O;
            break;
        case "feu" :
        	this.cle=Cle.feu;
            break;
        case "air":
        	this.cle=Cle.air;
            break;
        case "terre":
        	this.cle=Cle.terre;
            break;
        case "eau":
        	this.cle=Cle.eau;
            break;
		}
	}
	
}
