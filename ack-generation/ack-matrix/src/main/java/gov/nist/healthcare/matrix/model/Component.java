package gov.nist.healthcare.matrix.model;

import hl7.v2.profile.Req;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Component extends Element {

	private ArrayList<SubComponent> subComponents = new ArrayList<SubComponent>();

	
	public Component(String name, Req req, String path, String internal) {
		super(name, req, path, internal);
		this.setType("COMPONENT");
		// TODO Auto-generated constructor stub
	}

	public HL7Element getChild(int i) {
		if(i < subComponents.size())
			return subComponents.get(i);
		return null;
	}

	public void addSubComponent(SubComponent a){
		subComponents.add(a);
	}
	
	public JSONObject toJSON() {
		JSONObject obj = super.toJSON("COMPONENT");
		JSONArray arr = new JSONArray();
		for(SubComponent f : subComponents){
			arr.put(f.toJSON());
		}
		obj.put("children", arr);
		return obj;
	}

	public boolean isSimple() {
		// TODO Auto-generated method stub
		return subComponents.size() == 0;
	}

	public int nbChildren() {
		// TODO Auto-generated method stub
		return subComponents.size();
	}

	public ElementView getView() {
		ElementView ev = super.getView("COMPONENT");
		for(SubComponent f : subComponents){
			ev.addChild(f.getView());
		}
		return ev;
	}
}
