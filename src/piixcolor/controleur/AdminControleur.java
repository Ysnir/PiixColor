package piixcolor.controleur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import piixcolor.modele.Modele;
import piixcolor.utilitaire.Observateur;

public class AdminControleur extends Controleur {

	/**
	 * Constructeur du controleur de l'admin. Il se contente d'appeler celui de la classe Controleur.
	 * 
	 * @param m modele de l'application
	 * 
	 * @see Controleur#Controleur(Modele)
	 */
	public AdminControleur(Modele m) {
		super(m);
	}
	
	/**
	 * Enregistre une image � l'interieur de l'application (Forme).
	 * L'image est pr�alablement redimensionn�e pour gagner de l'espace disque.
	 * La m�thode poss�de une s�curit� pour �viter l'�crasement d'image (dans le cas o� le nom de l'image import�e est d�j� pr�sent). 
	 * 
	 * @param image image a enregist�
	 * @return true si l'image a pu �tre enregistr�, false sinon
	 */
	public boolean saveImage(File image) {
		try {
			BufferedImage i = ImageIO.read(image);
			String imageName = image.getName().split("\\.")[0];
			
			//s�curit� pour emp�cher l'�crasement de l'image
			File f = new File(Modele.DOSSIER_FORMES + imageName + "." + Modele.FORMAT_IMAGE_SAVE);
			int j = 1;
			while (f.exists()) {
				f = new File(Modele.DOSSIER_FORMES + imageName + " (" + j + ")." + Modele.FORMAT_IMAGE_SAVE);
				j++;
			}
			
			BufferedImage imageResize = resizeImage(i, Modele.IMG_SIZE, Modele.IMG_SIZE);
			ImageIO.write(imageResize, Modele.FORMAT_IMAGE_SAVE, f);
			
			getModele().notifier(Observateur.SIG_IMAGE_SAVE);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Un probleme est survenu lors de l'importaion de l'image");
			return false;
		}
	}

	/**
	 * Supprime une image de l'application.
	 * 
	 * @param image image a supprim�
	 * @return true si l'image a pu �tre supprim�, false sinon
	 */
	public boolean deleteImage(File image) {
		if (image.delete()) {
			return true;
		}
		return false;
	}
}
