package CritterRush.model.tower;
import java.util.ArrayList;

import CritterRush.model.critter.Critter;

public interface ITargetStrategy {

		void shootTarget(ArrayList<Critter> critters, Tower t);
	
}
