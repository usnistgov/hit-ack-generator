package gov.nist.healthcare.matrix.model;

import java.util.ArrayList;

public class Path {
	public ArrayList<Integer> positions = new ArrayList<Integer>();
	
	public Path(String path){
		String[] ps = path.split("\\.");
		positions = new ArrayList<Integer>();
		for(String p : ps){
			positions.add(Integer.parseInt(p)-1);
		}
		
	}
}
