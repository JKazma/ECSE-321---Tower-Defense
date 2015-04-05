package CritterRush.controller;

import CritterRush.model.map.CellType;

public class CellTypeManager {

	private static CellTypeManager uniqueInstance = null;
	
		public static CellType scenery;
		public static CellType path;
		public static CellType entry;
		public static CellType exit;
		public static CellType obstacle;
		
		/**
		 * Initialize the different cell types
		 */
		private CellTypeManager () {
			scenery = new CellType(ICManager.scenery, true, false);
			path = new CellType(ICManager.path, false, true);
			entry = new CellType(ICManager.entry, false, true);
			exit = new CellType(ICManager.exit, false, true);
			obstacle = new CellType(ICManager.obstacleCell, false, false);
		}
		
		/**
		 * Return unique instance of game controller
		 * @return
		 */
	    public static synchronized CellTypeManager getUniqueInstance() {
	        if (uniqueInstance == null) {
	            uniqueInstance = new CellTypeManager();
	        }
	        return uniqueInstance;
	    }
	}
