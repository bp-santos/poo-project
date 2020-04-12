package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

	private Player player;
	private ArrayList<AbstractObjects> objects = new ArrayList<AbstractObjects>();
	private int energy = 100;
	private int steps = 0;
	private int nLevel = 0;
	private String playerName = "";

	public SokobanGame(){
		playerName();

		ImageMatrixGUI.getInstance().setName("Best Sokoban Ever");
		ImageMatrixGUI.getInstance().setStatusMessage("   Player: " + playerName + 
				"   |    Level: " + nLevel + "    |    Energy: " + 
				energy + "    |    Steps: " + steps);

		buildSampleLevel();
	}

	public ArrayList<AbstractObjects> getObjects() {
		return objects;
	}

	public int getEnergy() {
		return energy;
	}

	public int getSteps() {
		return steps;
	}

	public void setEnergy(int energia) {
		this.energy = energia;
	}

	public void setSteps(int passos) {
		this.steps = passos;
	}

	public void removeObject(AbstractObjects object) {
		for (AbstractObjects obj: objects) {
			if (obj == object) objects.remove(object);
		}
	}

	public void playerName() {
		String response = JOptionPane.showInputDialog(null,"What is your name?",
				"Sokoban by Hiroyuki Imabayashi (1981)",JOptionPane.QUESTION_MESSAGE);
		if(response == null) throw new NullPointerException("See you soon my friend");
		if (response.length()==0) {
			JOptionPane.showMessageDialog(null,"You must have a gamer name");
			playerName();
			playerName = response;
		}

		playerName = response;
	}

	public void buildSampleLevel() {
		try {

			String filename = "levels/level" + nLevel + ".txt";
			File file = new File(filename);
			Scanner s =  new Scanner(file);

			String line;
			String[] symbols;
			int y = 0;

			this.steps=0;
			this.energy=100;

			while(s.hasNextLine()) {

				line = s.nextLine();
				symbols = line.split("");

				for(int x = 0; x!=10 ; x++) {
					switch(symbols[x]) {
					case "#" :
						objects.add(new Wall(new Point2D(x,y)));
						break;
					case "C":
						objects.add(new Box(new Point2D(x,y),this));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "O":
						objects.add(new Hole(new Point2D(x,y),this));
						break;
					case "X":
						objects.add(new Target(new Point2D(x,y)));
						break;
					case "E":
						player = new Player(new Point2D(x,y),this);
						objects.add(new Floor(new Point2D(x,y)));
						objects.add(player);
						break;
					case "b":
						objects.add(new Battery(new Point2D(x,y),this));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "S":
						objects.add(new BigStone(new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "s":
						objects.add(new SmallStone(new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case " ": 
						objects.add(new Floor(new Point2D(x,y)));
						break;
					}
				}
				y++;
			}
			s.close();
			for(ImageTile image: objects)
				ImageMatrixGUI.getInstance().addImage(image);

		}catch (FileNotFoundException e){
		}
	}

	public void repeatLevel() {
		Object[] options = {"Repeat level","Leave"};
		int response = JOptionPane.showOptionDialog(null,"What do you want to do?","GAME OVER",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(response == 0) {
			ImageMatrixGUI.getInstance().clearImages();
			objects.removeAll(objects);
			buildSampleLevel();
		}
		else {
			ImageMatrixGUI.getInstance().dispose();
		}
	}

//	public boolean overlay() {
//		
//		boolean val = false;
//		int i = 0;
//		
//		ArrayList<AbstractObjects> targets = new ArrayList<AbstractObjects>();
//		ArrayList<AbstractObjects> boxes = new ArrayList<AbstractObjects>();
//
//		for (AbstractObjects object: objects) {
//			if (object.getName()=="Target")
//				targets.add(object);
//			if(object.getName()=="Box")
//				boxes.add(object);
//		}
//
//		for (AbstractObjects target: targets) {
//			if (target.getPosition() == boxes.get(i).getPosition()) {
//				val = true;
//				i++;
//				if (i==boxes.size()+1) i = 0;
//			}
//			return false;
//		}
//		
//		return val;
//
//	}
//
//	public void levelComplete() {	
//		if(overlay())
//			System.out.println("Conseguiste terminar o jogo");
//			try {
//				FileWriter file = new FileWriter("points/punctuation.txt");
//				file.write(playerName + " " + steps + " " + nLevel);
//				file.close();
//				
//				JOptionPane.showMessageDialog(null, " Name: "+ playerName + "    Steps: "  + steps);
//				
//				ImageMatrixGUI.getInstance().clearImages();
//				objects.removeAll(objects);
//				nLevel++;
//				buildSampleLevel();
//
//			}catch(IOException e) {}
//	}

@Override
public void update(Observed arg0) {
	int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
	if (player != null) {
		if(energy==0 || lastKeyPressed == KeyEvent.VK_R || lastKeyPressed == KeyEvent.VK_ESCAPE) {
			repeatLevel();	
			ImageMatrixGUI.getInstance().update();
		}
//		levelComplete();
		if (Direction.isDirection(lastKeyPressed)) {
			player.move(lastKeyPressed);
			ImageMatrixGUI.getInstance().update();
		}
		ImageMatrixGUI.getInstance().setStatusMessage("   Player: " + playerName +
				"   |    Level: " + nLevel + "    |    Energy: " + energy + "    |    "
				+ "Steps: " + steps);
	}
}
}