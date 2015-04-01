package CritterRush.controller;

import org.newdawn.slick.SlickException;

import CritterRush.model.CellType;

public class CellTypeManager {

		public static CellType scenery;
		public static CellType path;
		public static CellType entry;
		public static CellType exit;
		public static CellType obstacle;

		public CellTypeManager () {
			try {
				scenery = new CellType(ICManager.scenery, true, false);
				path = new CellType(ICManager.path, false, true);
				entry = new CellType(ICManager.entry, false, true);
				exit = new CellType(ICManager.exit, false, true);
			} catch (SlickException e) {
				e.printStackTrace();
			}	
		}
	}
