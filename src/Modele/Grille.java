package Modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import Controleur.Observable;

public class Grille extends Observable {
	public static final int HAUTEUR=6, LARGEUR=6;
	public Zone[][] zones;
	public ArrayList<Artefact> art;
	public ArrayList<Joueur> joueurs;
	public Joueur jActif ;
	public String action = "0";
	public boolean partiePerdue = false;
	public boolean partieGagnee = false;
	public ArrayList<Cle> cles;
	public ArrayList<SpecialAction> spx;

	/*
	 * Constructeur.
	 */
	public Grille(int nbJoueurs) {
        zones = new Zone[HAUTEUR][LARGEUR];
        init(nbJoueurs);
    }

	/*
	 * initialiser grille
	 */
	public void init(int nbJoueurs){
		/**
		 * Initialisation des zones
		 **/
		for(int i = 0; i<HAUTEUR; i++) {
			for(int j = 0; j<LARGEUR; j++) {
				Coordonnees c = new Coordonnees(i,j);
				zones[i][j] = new Zone(c, EtatZone.normale, this);
			}
		}
		/**
		 * Initialisation des Artefacts
		 **/
		art = Artefact.init(this);
		/**
		 * Initialisation des clés
		 **/
		initCles();
		/**
		 * Initialisation des actions spéciales
		 **/
		initSpx();
		/**
		 * Initialisation des joueurs
		 **/
		if( 2 <= nbJoueurs && nbJoueurs <=4) {
			joueurs = Joueur.init(this, nbJoueurs);
			jActif = joueurs.get(0);
			jActif.setColor(Color.green);
			System.out.println("Joueur actif : "+ (jActif.idx+1));
		} else if(nbJoueurs == 0){
			System.out.println("Pas de joueurs !");
			System.exit(0);
		} else {
			System.out.println("Nombre de joueurs invalide.");
			System.exit(0);
		}
	}
	
	private void initSpx() {
		spx = new ArrayList<SpecialAction>();
		spx.add(SpecialAction.O);
		spx.add(SpecialAction.Sable);
		spx.add(SpecialAction.Hélico);
		
	}

	private void initCles() {
		cles = new ArrayList<Cle>();
		cles.add(Cle.O);
		cles.add(Cle.air);
		cles.add(Cle.feu);
		cles.add(Cle.terre);
		cles.add(Cle.eau);
		
	}

	public Zone getZone(int i, int j) {
		return zones[i][j];
	}
	/**
	 * Retourne les zones non submergées d'une grille
	 **/
	private ArrayList<Zone> nonSubmerg(){
    	ArrayList<Zone> nonSub = new ArrayList<Zone>();
    	for(Zone[] z : zones) {
    		for(Zone zz : z) {
        		if(zz.getEtat() != EtatZone.submergée) {
        			nonSub.add(zz);
        		}
        	}
    	}
    	return nonSub;
    }
	
	/**
	 * Fin de tour
	 **/
	public void Tour() {
		/**
		 * définition d'une action
		 **/
		action = "0";
		/**
		 * inondation de 3 zones aléatoires
		 **/
		int i = 0;
		ArrayList<Zone> nonSub = this.nonSubmerg();
		while(nonSub.size()> 0 && i<3) {
			int idx = new Random().nextInt(nonSub.size());
			Zone rand = nonSub.get(idx);
			inondZone(rand);
			nonSub.remove(rand);
			i++;
		}
		/**
		 * verification des zones sur lesquelles se trouvent des artefacts
		 **/
		for(int l = 0; l<art.size(); l++) {
			Artefact a = art.get(l);
			Zone z = a.getZone();
	    	int k = z.getX();
	    	int j = z.getY();
	    	z = this.getZone(k, j);
			if(z.getEtat() == EtatZone.submergée) {
				art.remove(a);
				partiePerdue = true;
				if(a.getNom() == "héliport") {
					System.out.println("L'héliport a disparu.");
				} else {
					System.out.println("L'artefact "+ a.getNom()+" a disparu.");
				}
	    		System.out.println("Partie Perdue !");
			}
		}
		/**
		 * Réinitialisation des actions du joueur actif
		 **/
		jActif.actions = 3;
		/**
		 * attributuion d'une clé au joueur actif
		 **/
		if(cles.size() > 0 && jActif.getCle() == Cle.O) {
			int idx = new Random().nextInt(cles.size());
			jActif.setCle(cles.get(idx));
			cles.remove(idx);
		}
		/**
		 * attributuion d'une action spéciale au joueur actif
		 **/
		if(jActif.spx == SpecialAction.O) {
			int idx = new Random().nextInt(spx.size());
			jActif.spx = spx.get(idx);
		}
		/**
		 * selectionner le prochain joueur  comme joueur actif
		 **/
		setJActif();
		/**
		 * verification si la partie est gagnée
		 **/
		partieGagnee();
		/**
		 * MAJ de l'affichage
		 **/
		notifyObservers();
	}
	
	/**
	 * Inonder la zone passé en paramètre, et déplacer les joueurs présents sur cette zone si elle est inondé et sera submergée
	 **/
	public void inondZone(Zone rand) {
    	int k = rand.getX(), j = rand.getY();
		EtatZone etat = zones[k][j].getEtat();
		switch(etat){
	        case normale:
	        	zones[k][j].setEtat(EtatZone.inondée);
				break;
	        case inondée:
	        	zones[k][j].setEtat(EtatZone.submergée);
	        	for(int i = 0; i<joueurs.size(); i++) {
	        		Joueur J = joueurs.get(i); 
	        		if(J.getX() == k && J.getY() == j) {
		        		if (J.Destination(Direction.UP) != null) {
		        			J.position = J.Destination(Direction.UP);
		        		} else if (J.Destination(Direction.DOWN) != null) {
		        			J.position = J.Destination(Direction.DOWN);
		        		} else if (J.Destination(Direction.LEFT) != null) {
		        			J.position = J.Destination(Direction.LEFT);
		        		} else if (J.Destination(Direction.RIGHT) != null) {
		        			J.position = J.Destination(Direction.RIGHT);
		        		} else {
		        			joueurs.remove(J);
		        			partiePerdue = true;
		    				System.out.println("Un joueur est mort.");
		    	    		System.out.println("Partie Perdue !");
		        		}
		        	}
	        	}
			break;
		}
	}
	
	public void setJActif() {
		if(joueurs.size() != 0) {
			jActif.setColor(Color.YELLOW);
			jActif = joueurs.get((jActif.idx + 1) % joueurs.size());
			jActif.setColor(Color.green);
	    	System.out.println("---");
			System.out.println("Joueur actif : "+ (jActif.idx+1));
		}
	}
	
	/**
	 * retourne un artefact en penant son nom comme paramètre
	 **/
	public Artefact findArt(String name) {
		for(Artefact a : art){
			if(a.getNom() == name) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * récuperer l'artefact si le joueur possede la clé et est sur la meme zone
	 **/
	public void recupArt() {
		Artefact a = findArt(jActif.getCle().name());
		if(a!=null && a.getX() == jActif.getX() && a.getY() == jActif.getY()) {
			System.out.println("L'artefact " + a.getNom() + " est récuperé !");
			art.remove(a);
			cles.add(Cle.O);
			jActif.setCle(Cle.O);
		}
	}
	
	/**
	 * verification si l'heliport est toujours sur la grille et que tous les joueurs sont sur la meme zone
	 **/
	public boolean partieGagnee() {
		if(art.size() == 1 && art.get(0).getNom() == "héliport") {
			for(Joueur j : joueurs) {
				int x = j.getX(), y = j.getY();
				if(x != art.get(0).getPosition().getX() || y != art.get(0).getPosition().getY()) {
					return false;
				}
			}
			partieGagnee = true;
    		System.out.println("Partie gagnée !");
			return true;
		}
		return false;
	}
	
	/**
	 * Echange de clé avec un joueur dont le numero passe en paramètre
	 **/
	public void echange(int i) {
		if( 0<i && i<=joueurs.size() && jActif != joueurs.get(i-1)) {
			jActif.actions--;
			String c1 = jActif.getCle().name();
			String c2 = joueurs.get(i-1).getCle().name();
			jActif.setCle(c2);
			joueurs.get(i-1).setCle(c1);
			System.out.println("échange réussi !");
			notifyObservers();
			if(jActif.actions == 0) {
				Tour();
			}
		} else {
			System.out.println("Joueur introuvable");
		}
	}
	
}