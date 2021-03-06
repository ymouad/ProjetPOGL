package Vue;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controleur.*;

import Modele.Grille;


class VueCommandes extends JPanel {
    
    private Grille modele;

    
    public VueCommandes(Grille modele) {
		this.modele = modele;
		
		Controleur ctrl = new Controleur(modele);
		
		JButton finDeTour = new JButton("Fin de tour");
		this.add(finDeTour);
		finDeTour.addActionListener(ctrl);
		finDeTour.setActionCommand("tour");
		
		JButton deplacer = new JButton("Déplacer");
		this.add(deplacer);
		deplacer.addActionListener(ctrl);
		deplacer.addKeyListener(ctrl);
		deplacer.setActionCommand("deplacer");
		
		JButton assecher = new JButton("Assécher");
		this.add(assecher);
		assecher.addActionListener(ctrl);
		assecher.addKeyListener(ctrl);
		assecher.setActionCommand("assecher");
		
		JButton art = new JButton("Artefact");
		this.add(art);
		art.addActionListener(ctrl);
		art.setActionCommand("art");
		
		JButton ex = new JButton("Echange");
		this.add(ex);
		ex.addActionListener(ctrl);
		ex.setActionCommand("ex");
		
		JButton sp = new JButton("Special");
		this.add(sp);
		sp.addActionListener(ctrl);
		sp.setActionCommand("sp");

    }
}
