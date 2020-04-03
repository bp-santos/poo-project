package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Chao extends Objetos {
	
	public Chao(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return "Chao";
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
