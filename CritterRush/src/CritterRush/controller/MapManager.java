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
	}
	
	/**
	 * Set editor map to an empty map.
	 */
	public static void resetEditorMap(){
		editorMap = (new Map("map1", ICManager.fieldSizeX/ICManager.cellSize, ICManager.fieldSizeY/ICManager.cellSize, CellTypeManager.scenery));
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
