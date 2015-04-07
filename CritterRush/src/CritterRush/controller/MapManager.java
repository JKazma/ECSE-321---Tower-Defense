package CritterRush.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import CritterRush.model.map.Map;


public class MapManager {
	
	private static MapManager uniqueInstance = null;

	private static ArrayList<Map> maps;
	private static Map selectedMap;
	private static Map editorMap;
	private static int maxCount;
	
	private MapManager(){
		maps = new ArrayList<Map>();
        maxCount = 4;
        
        //Add default map
        maps.add(new Map(ICManager.fieldSizeX/ICManager.cellSize, ICManager.fieldSizeY/ICManager.cellSize, 
        		CellTypeManager.scenery));
        
        resetEditorMap();
        
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
	 * Return unique instance of game controller
	 * @return
	 */
    public static synchronized MapManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MapManager();
        }
        return uniqueInstance;
    }
	
	/**
	 * Set editor map to an empty map.
	 */
	public static void resetEditorMap(){
		editorMap = (new Map(ICManager.fieldSizeX/ICManager.cellSize, ICManager.fieldSizeY/ICManager.cellSize, CellTypeManager.scenery));
	}
	
	
	//Getters and setters
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
	
	public static int getIndex(){
		return maps.indexOf(selectedMap);
	}
	
	public static void deleteMap(Map m){
		for(Map map:maps)
			if (map == m){
				maps.remove(map);
				break;
			}
	}
	
	public static Map getMap(int i){
		return maps.get(i - 1);
	}
	
	public void loadMaps(){
		
	}
	
	public void saveMap() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("map2.txt", "UTF-8");
		
		
		//s -> scenery, e -> entry, x -> exit, o -> obstacle p -> path
		for(int j = 0; j < MapManager.getSelectedMap().getSizeY(); j++){
			for(int i = 0; i < MapManager.getSelectedMap().getSizeX(); i++){
				if(MapManager.getSelectedMap().getCellAt(i*ICManager.cellSize, j*ICManager.cellSize).getType() == CellTypeManager.scenery)
					writer.print("s");
				else if(MapManager.getSelectedMap().getCellAt(i*ICManager.cellSize, j*ICManager.cellSize).getType() == CellTypeManager.entry)
					writer.print("e");
				else if(MapManager.getSelectedMap().getCellAt(i*ICManager.cellSize, j*ICManager.cellSize).getType() == CellTypeManager.exit)
					writer.print("x");
				else if(MapManager.getSelectedMap().getCellAt(i*ICManager.cellSize, j*ICManager.cellSize).getType() == CellTypeManager.obstacle)
					writer.print("o");
				else if(MapManager.getSelectedMap().getCellAt(i*ICManager.cellSize, j*ICManager.cellSize).getType() == CellTypeManager.path)
					writer.print("p");
				
			}
			writer.println("");
		}
		writer.close();
	}
}
