package pixcolor.test;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Matrix extends JPanel {
	private int lines = 2;
	private int columns = 2;

	public void paintComponent(Graphics g) {

		int x = this.getWidth() / 4;
		int y = this.getHeight() / 4;
		g.drawRect(x, y, this.getWidth() / 2, this.getHeight() / 2);

		int largeur = (2 * x) / (columns);
		for (int i = 0; i < columns; i++) {
			g.drawLine(i * largeur + x, y, i * largeur + x, 3 * y);
		}

		int longeur = (2 * y) / (lines);
		for (int i = 0; i < lines; i++) {
			g.drawLine(x, i * longeur + y, 3 * x, i * longeur + y);
		}

	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

}