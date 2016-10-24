package gov.nist.healthcare.matrix.model;

import org.json.JSONObject;

import gov.nist.healthcare.matrix.common.Utils;
import hl7.v2.profile.Req;


public abstract class Element implements HL7Element {
	
	private String path;
	private String name;
	private String description;
	private String usage;
	private String confLength;
	private Range cardinality;
	private Range length;
	private int position;
	private String internalPath;
	private Indexes indexes;
	private String type;
	private boolean vs;
	
	public JSONObject toJSON(String type){
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("type", type);
		obj.put("ID", internalPath);
		obj.put("description", description);
		obj.put("path", path);
		obj.put("indexes", indexes.toJSON());
		obj.put("usage", usage);
		obj.put("valueSet", vs);
		return obj;
	}
	
	public ElementView getView(String type){
		ElementView ev = new ElementView();
		ev.setName(name);
		ev.setDescription(description);
		ev.setType(type);
		ev.setID(internalPath);
		ev.setPath(path);
		ev.setIndexes(indexes.simplifiedMap());
		ev.setUsage(this.usage);
		ev.setValueSet(this.vs);
		return ev;
	}
	
	public Element(){
		
	}
	
	public Element(String name, Req req, String path, String internalPath){
		
		this.path = path;
		this.name = name;
		this.internalPath = internalPath;
		
		description = req.description();
		usage = req.usage().toString();
		confLength = Utils.extract(req.confLength());
		
		
		hl7.v2.profile.Range card = Utils.extract(req.cardinality());
		if(card != null){
			cardinality = new Range();
			cardinality.min = card.min();
			cardinality.max = card.max();
		}
		else {
			cardinality = null;
		}
		
		hl7.v2.profile.Range lnt = Utils.extract(req.length());
		if(lnt != null){
			length = new Range();
			length.min = lnt.min();
			length.max = lnt.max();
		}
		else {
			length = null;
		}
		
		position = req.position();
		vs = req.vsSpec().size() > 0;

	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Indexes getIndexes() {
		return indexes;
	}
	public void setIndexes(Indexes indexes) {
		this.indexes = indexes;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getInternalPath() {
		return internalPath;
	}
	public void setInternalPath(String internalPath) {
		this.internalPath = internalPath;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getConfLength() {
		return confLength;
	}

	public void setConfLength(String confLength) {
		this.confLength = confLength;
	}

	public Range getCardinality() {
		return cardinality;
	}

	public void setCardinality(Range cardinality) {
		this.cardinality = cardinality;
	}

	public Range getLength() {
		return length;
	}

	public void setLength(Range length) {
		this.length = length;
	}

	public boolean isVs() {
		return vs;
	}

	public void setVs(boolean vs) {
		this.vs = vs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
