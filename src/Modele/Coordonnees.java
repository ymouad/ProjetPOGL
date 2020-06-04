package Modele;

public class Coordonnees {

	private int x;
	private int y;
	
	public Coordonnees(int x, int y) {
       this.setX(x);
       this.setY(y);
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

}