package gov.nist.healthcare.matrix.model;

import hl7.v2.profile.Req;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Field extends Element {
	
	private ArrayList<Component> components = new ArrayList<Component>(); 

	
	public Field(String name, Req req, String path, String internalPath) {
		super(name, req, path, internalPath);
		// TODO Auto-generated constructor stub
	}

	public HL7Element getChild(int i) {
		if(i < components.size())
			return components.get(i);
		return null;
	}
	
	public void addComponent(Component a){
		components.add(a);
	}
	
	public JSONObject toJSON() {
		JSONObject obj = super.toJSON("FIELD");
		JSONArray arr = new JSONArray();
		for(Component f : components){
			arr.put(f.toJSON());
		}
		obj.put("children", arr);
		return obj;
	}

	public boolean isSimple() {
		// TODO Auto-generated method stub
		return components.size() == 0;
	}

	public int nbChildren() {
		// TODO Auto-generated method stub
		return components.size();
	}
}
