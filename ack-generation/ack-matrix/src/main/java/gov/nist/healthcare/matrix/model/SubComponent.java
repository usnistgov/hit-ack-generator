package gov.nist.healthcare.matrix.model;

import org.json.JSONObject;

import hl7.v2.profile.Req;

public class SubComponent extends Element {

	
	
	public SubComponent(String name, Req req, String path, String internalPath) {
		super(name, req, path, internalPath);
		this.setType("SUBSEGMENT");
		// TODO Auto-generated constructor stub
	}

	public HL7Element getChild(int i) {
		return null;
	}
	
	public JSONObject toJSON() {
		JSONObject obj = super.toJSON("SUBCOMPONENT");
		return obj;
	}

	public boolean isSimple() {
		// TODO Auto-generated method stub
		return true;
	}

	public int nbChildren() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ElementView getView() {
		ElementView ev = super.getView("SUBCOMPONENT");
		return ev;
	}

}
