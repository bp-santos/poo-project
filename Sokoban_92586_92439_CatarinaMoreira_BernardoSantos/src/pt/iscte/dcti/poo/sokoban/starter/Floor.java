package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Floor extends AbstractObjects{
	
	public Floor(Point2D position){
		super(position,true,false);
	}
	
	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
