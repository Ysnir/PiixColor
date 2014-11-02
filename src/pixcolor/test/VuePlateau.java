import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class VuePlateau extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel matrice;
	JLabel formeCourante;
	Container caseFormeCourante;
	int ajustementX;
	int ajustementY;
	int nbcouleur = 3;
	int nbforme = 3;

	public VuePlateau(int taille) {
		
		// Utilisation du JLayeredPane
		Dimension dimensionVue = new Dimension(taille, taille);
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(dimensionVue);

		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Ajout de la matrice
		matrice = new JPanel();
		layeredPane.add(matrice, JLayeredPane.DEFAULT_LAYER);
		matrice.setPreferredSize(new Dimension(dimensionVue.width, dimensionVue.height));
		matrice.setLayout(new GridLayout((nbcouleur*2) + 1, nbforme + 1));
		matrice.setBounds(0, 0, dimensionVue.width, dimensionVue.height);
		
		for (int i = 0; i < ((nbcouleur*2) + 1) * (nbforme + 1); i++) {
			JPanel square = new JPanel(new BorderLayout());
			matrice.add(square);
			square.setBackground(Color.white);
			if(i < (nbcouleur+1) * (nbforme +1)) {
				square.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}

		//###############Ajout des formes et couleurs de la matrice##############################
		
		JLabel vide = new JLabel();
		JPanel panel = (JPanel) matrice.getComponent(0);
		panel.add(vide);
		
		//Ajout couleurs :
		JLabel image = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/blue.png"));
		panel =  (JPanel) matrice.getComponent(4);
		panel.add(image);
		
		image = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/red.png"));
		panel = (JPanel) matrice.getComponent(8);
		panel.add(image);
		
		
		image = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/green.png"));
		panel = (JPanel) matrice.getComponent(12);
		panel.add(image);
		
		//Ajout formes :
		image = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/triangle.png"));
		panel = (JPanel) matrice.getComponent(1);
		panel.add(image);
		
		image = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/cercle.png"));
		panel = (JPanel) matrice.getComponent(2);
		panel.add(image);
		
		image = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/carre.png"));
		panel = (JPanel) matrice.getComponent(3);
		panel.add(image);
		//####################################################################################
		
		//###############Ajout des formes color�s � "formes"##############################
		
		//Ajout couleurs :
		JLabel objetColore = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/carreRouge.png"));
		panel =  (JPanel) matrice.getComponent(17);
		panel.add(objetColore);
		
		objetColore = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/cercleVert.png"));
		panel = (JPanel) matrice.getComponent(18);
		panel.add(objetColore);
		
		objetColore = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/triangleVert.png"));
		panel = (JPanel) matrice.getComponent(19);
		panel.add(objetColore);
		
		objetColore = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/carreBleu.png"));
		JPanel panel1 =  (JPanel) matrice.getComponent(21);
		panel1.add(objetColore);
		
		objetColore = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/triangleRouge.png"));
		panel1 = (JPanel) matrice.getComponent(22);
		panel1.add(objetColore);
		
		objetColore = new JLabel(new ImageIcon("C:/Users/Yann/Pictures/Sprite/cercleBleu.png"));
		panel1 = (JPanel) matrice.getComponent(23);
		panel1.add(objetColore);
		//####################################################################################
		
	}


	public void mouseDragged(MouseEvent me) {
		if (formeCourante == null)
			return;
		formeCourante.setLocation(me.getX() + ajustementX, me.getY() + ajustementY);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mousePressed(MouseEvent e) {
		formeCourante = null;
		Component c = matrice.findComponentAt(e.getX(), e.getY());
		caseFormeCourante =  c.getParent();
		if (c instanceof JPanel)
			return;
		Point emplacementParent = c.getParent().getLocation();
		if(emplacementParent.getY() >= matrice.getComponent((nbcouleur+1)*(nbforme+1)).getLocation().getY()) {
			ajustementX = emplacementParent.x - e.getX();
			ajustementY = emplacementParent.y - e.getY();
			formeCourante = (JLabel) c;
			formeCourante.setLocation(e.getX() + ajustementX, e.getY() + ajustementY);
			formeCourante.setSize(formeCourante.getWidth(), formeCourante.getHeight());
			layeredPane.add(formeCourante, JLayeredPane.DRAG_LAYER);
		}
	}


	public void mouseReleased(MouseEvent e) {
		if (formeCourante == null)
			return;
		formeCourante.setVisible(false);
		Component c = matrice.findComponentAt(e.getX(), e.getY());
		if (c instanceof JLabel) {
			caseFormeCourante.add(formeCourante);
		} else {
			Container parent = (Container) c;
			parent.add(formeCourante);
		}
		formeCourante.setVisible(true);

	}
	
	public static void main(String[] args) {
		JFrame frame = new VuePlateau(1000);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}