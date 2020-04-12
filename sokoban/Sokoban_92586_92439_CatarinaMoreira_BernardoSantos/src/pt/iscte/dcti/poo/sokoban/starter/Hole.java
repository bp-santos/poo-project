package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObjects {
	
	private SokobanGame game;
	
	public Hole(Point2D position, SokobanGame game) {
		super(position);
		this.game = game;
	}

	@Override
	public String getName() {
		return "Hole";
	}

	public void disappear() {
		ImageMatrixGUI.getInstance().update();
		game.setEnergy(0);
		JOptionPane.showMessageDialog(null, "You have fallen into a hole.");
		game.repeatLevel();
	}
	
}
