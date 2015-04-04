package CritterRush.controller;

import java.util.ArrayList;
import CritterRush.model.map.Map;

public class MapManager {
	private static ArrayList<Map> maps;
	private static Map selectedMap;
	private static Map editorMap;
	private static int maxCount;
	
	public MapManager(){
		maps = new ArrayList<Map>();
        this.maxCount = 4;
        resetEditorMap();
        
        //Add default map
        maps.add(new Map(ICManager.fieldSizeX/ICManager.cellSize, ICManager.fieldSizeY/ICManager.cellSize, 
        		CellTypeManager.scenery));
        
        //Horizontal
        for(int i = 1; i <= 5 || i >= 21; i++) maps.get(0).getCellAt(i * ICManager.cellSize, 9 * ICManager.cellSize).setType(CellTypeManager.path);
        for(int i = 21; i <= 24; i++) maps.get(0).getCellAt(i * ICManager.cellSize, 9 * ICManager.cellSize).setType(CellTypeManager.path);
        for(int i = 6; i <= 8; i++) maps.get(0).getCellAt(i * ICManager.cellSize, 4 * ICManager.cellSize).setType(CellTypeManager.path);
        for(int i = 14; i <= 17; i++) maps.get(0).getCellAt(i * ICManager.cellSize, 4 * ICManager.cellSize).setType(CellTypeManager.path);
        for(int i = 10; (i <= 12); i++) maps.get(0).getCellAt(i * ICManager.cellSize, 14 * ICManager.cellSize).setType(CellTypeManager.path);
        for(int i = 18; (i <= 21); i++) maps.get(0).getCellAt(i * ICManager.cellSize, 14 * ICManager.cellSize).setType(CellTypeManager.path);
        
        //Vertical
        for(int j = 4; j <= 9; j++) maps.get(0).getCellAt(5 * ICManager.cellSize, j * ICManager.cellSize).setType(CellTypeManager.path);
        for(int j = 4; j <= 14; j++) maps.get(0).getCellAt(9 * ICManager.cellSize, j * ICManager.cellSize).setType(CellTypeManager.path);
        for(int j = 4; j <= 14; j++) maps.get(0).getCellAt(13 * ICManager.cellSize, j * ICManager.cellSize).setType(CellTypeManager.path);
        for(int j = 4; j <= 14; j++) maps.get(0).getCellAt(17 * ICManager.cellSize, j * ICManager.cellSize).setType(CellTypeManager.path);
        for(int j = 9; j <= 14; j++) maps.get(0).getCellAt(21 * ICManager.cellSize, j * ICManager.cellSize).setType(CellTypeManager.path);
        
        //Create path
        maps.get(0).getCellAt(0, 9 * ICManager.cellSize).setType(CellTypeManager.entry);
        maps.get(0).getCellAt(25 * ICManager.cellSize, 9 * ICManager.cellSize).setType(CellTypeManager.exit);
        
        maps.get(0).getPath().setEntry(maps.get(0).getCellAt(0, 9 * ICManager.cellSize));
        maps.get(0).getPath().setExit(maps.get(0).getCellAt(25 * ICManager.cellSize, 9 * ICManager.cellSize));
        maps.get(0).getPath().computePath();
	}
	
	/**
	 * Set editor map to an empty map.
	 */
	public static void resetEditorMap(){
		editorMap = (new Map(ICManager.fieldSizeX/ICManager.cellSize, ICManager.fieldSizeY/ICManager.cellSize, CellTypeManager.scenery));
	}
	
	public static void setSelectedMap(Map m) {
		MapManager.selectedMap = m;
	}
	public static Map getSelectedMap() {
		return selectedMap;
	}
	
	public static void setEditorMap(Map m) {
		MapManager.editorMap = m;
	}
	public static Map getEditorMap() {
		return editorMap;
	}
	
	public static int getMapCount() {
		return maps.size();
	}
	
	public static int getMaxCount(){
		return maxCount;
	}
	
	public static void addMap(Map m){
		if (maps.size() < maxCount)
			maps.add(m);
	}
	
	public static void deleteMap(Map m){
		maps.remove(m);
	}
	
	public static Map getMap(int i){
		return maps.get(i - 1);
	}
}
