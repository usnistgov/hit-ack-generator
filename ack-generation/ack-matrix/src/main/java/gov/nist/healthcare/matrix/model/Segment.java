package gov.nist.healthcare.matrix.model;

import hl7.v2.profile.Req;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Segment extends Element implements SegmentOrGroup  {

	private ArrayList<Field> fields = new ArrayList<Field>();
	
	
	
	public Segment(String name, Req req, String path, String internalPath) {
		super(name, req, path, internalPath);
		this.setType("SEGMENT");
		// TODO Auto-generated constructor stub
	}

	public HL7Element getChild(int i) {
		if(i < fields.size())
			return fields.get(i);
		return null;
	}
	
	public void addField(Field a){
		fields.add(a);
	}



	public JSONObject toJSON() {
		JSONObject obj = super.toJSON("SEGMENT");
		JSONArray arr = new JSONArray();
		for(Field f : fields){
			arr.put(f.toJSON());
		}
		obj.put("children", arr);
		return obj;
	}
	
	public ElementView getView() {
		ElementView obj = super.getView("SEGMENT");
		for(Field f : fields){
			obj.addChild(f.getView());
		}
		return obj;
	}

	public boolean isSimple() {
		return false;
	}

	public int nbChildren() {
		// TODO Auto-generated method stub
		return fields.size();
	}


}
