package Modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Zone {

    private Coordonnees coordonnees;
    private EtatZone etat;
    private Color couleur;
    private Direction dir;
    private Grille grille;
    
    public Zone(Coordonnees coo, EtatZone etat, Grille grille) {
    	setEtat(etat);
        setcoordonnees(coo);
        setGrille(grille);
    }
    
    public int getX() {
        return coordonnees.getX();
    }
	
	public int getY() {
        return coordonnees.getY();
    }

	public static Zone randomZone(Grille grille) {
    	
		Zone z = grille.zones[new Random().nextInt(grille.HAUTEUR-1)][new Random().nextInt(grille.LARGEUR-1)];
    	
    	return z;
    }
    
    
    public EtatZone getEtat() {
        return etat;
    }

    public Direction getDirection() {
        return dir;
    }
    
    public Direction setDirection(Direction dir) {
        return this.dir = dir;
    }
    
    public void setEtat(EtatZone etat) {
        this.etat = etat;
        setColor(etat);
    }
    
    public void setGrille(Grille grille) {
        this.grille = grille;
    }
    
    public Grille getGrille() {
        return grille;
    }
    
    
    public Color getColor() {
        return couleur;
    }
    
    public void setColor(EtatZone etat) {
    	switch(etat){
        case normale:
            this.couleur=Color.WHITE;
            break;
        case inondée:
            this.couleur=Color.CYAN;
            break;
        case submergée:
            this.couleur=Color.BLUE;
            break;
    	}
    }
 
    public Coordonnees getcoordonnees() {
        return coordonnees;
    }

    
    public void setcoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }
    
    /**
	 * retourne ArrayList<Zone> des zones adjacentes sans tenir compte de leurs états
	 * assigner à chaque zone une direction par rapport à "this"
	 **/
    public ArrayList<Zone> adjacentes(){
    	ArrayList<Zone> adj = new ArrayList<>();
    	int x = coordonnees.getX();
    	int y = coordonnees.getY();
        if ( x-1 >= 0 ) {grille.zones[x-1][y].dir = Direction.LEFT; adj.add(grille.zones[x-1][y]); };
        if ( y-1 >= 0) {grille.zones[x][y-1].dir = Direction.UP; adj.add(grille.zones[x][y-1]); };
        if ( y+1 <= grille.LARGEUR-1) {grille.zones[x][y+1].dir = Direction.DOWN; adj.add(grille.zones[x][y+1]);  };
        if ( x+1 <= grille.HAUTEUR-1 ) {grille.zones[x+1][y].dir = Direction.RIGHT; adj.add(grille.zones[x+1][y]);  };
		return adj;
    }


    
}