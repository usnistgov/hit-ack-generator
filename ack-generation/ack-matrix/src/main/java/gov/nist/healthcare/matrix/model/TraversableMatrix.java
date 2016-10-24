package gov.nist.healthcare.matrix.model;

import gov.nist.healthcare.matrix.common.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class TraversableMatrix extends MatrixView {
	
	public TraversableMatrix(MatrixView mv){
		this.setHeader(mv.getHeader());
		this.setMessage(mv.getMessage());
		
		initIndexes(mv.getMessage().getChildren());
	}
	
	
	
	private void initIndexes(ArrayList<ElementView> children) {
		if(children == null)
			return;
		
		for(ElementView ev : children){
			index.put(ev.getPath(), ev.getID());
			initIndexes(ev.getChildren());
		}
	}



	public HashMap<String, String> index = new HashMap<String, String>();
	
	public ElementView getElement(String path) {
		System.out.println(path);
		if(Utils.isValid(path) && index.containsKey(path)){
			System.out.println(index.get(path));
			Path p = new Path(index.get(path));
			
			ElementView cursor = this.getMessage().getChildren().get(p.positions.get(0));
			
			if(p.positions.size() == 1){
				return cursor;
			}
			else {
				
				for(int i = 1; i < p.positions.size(); i++){
					cursor = cursor.getChildren().get(p.positions.get(i));
					if(cursor == null){
						return null;
					}
				}
				return cursor;
			}
		}
		else{
			System.out.println("ELSE");
			return null;
		}
			
	}
}
