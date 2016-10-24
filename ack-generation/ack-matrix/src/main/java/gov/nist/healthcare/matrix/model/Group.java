package gov.nist.healthcare.matrix.model;

import hl7.v2.profile.Req;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Group extends Element implements SegmentOrGroup {

	private ArrayList<SegmentOrGroup> segmentOrGroup = new ArrayList<SegmentOrGroup>();

	
	public Group(String name, Req req, String path, String internalPath) {
		super(name, req, path, internalPath);
		this.setType("GROUP");
		// TODO Auto-generated constructor stub
	}

	public HL7Element getChild(int i) {
		if(i < segmentOrGroup.size())
			return segmentOrGroup.get(i);
		return null;
	}

	public void addSegOrGroup(SegmentOrGroup a){
		segmentOrGroup.add(a);
	}
	
	public JSONObject toJSON() {
		JSONObject obj = super.toJSON("GROUP");
		JSONArray arr = new JSONArray();
		for(SegmentOrGroup f : segmentOrGroup){
			arr.put(f.toJSON());
		}
		obj.put("children", arr);
		return obj;
	}
	
	public ElementView getView() {
		ElementView obj = super.getView("GROUP");
		for(SegmentOrGroup f : segmentOrGroup){
			obj.addChild(f.getView());
		}
		return obj;
	}

	public boolean isSimple() {
		return false;
	}

	public int nbChildren() {
		// TODO Auto-generated method stub
		return segmentOrGroup.size();
	}
}
