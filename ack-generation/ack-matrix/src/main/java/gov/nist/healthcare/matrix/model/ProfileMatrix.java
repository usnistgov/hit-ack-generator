package gov.nist.healthcare.matrix.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import gov.nist.healthcare.matrix.common.Utils;

public class ProfileMatrix implements Matrix {
	
	public HashMap<String, String> index = new HashMap<String, String>();
	private ArrayList<String> headers = new ArrayList<String>();
	private Message message;

	public HashMap<String, String> getIndex() {
		return index;
	}

	public void setIndex(HashMap<String, String> index) {
		this.index = index;
	}

	public ArrayList<String> getHeaders() {
		return headers;
	}

	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}

	public void init() {
		Initializer.initialize(this);
	}
	
	public ProfileMatrix(){
		headers.add("Usage");
		headers.add("Cardinality");
		headers.add("Format");
		headers.add("Length");
		headers.add("Profile Conformance Statement");
		headers.add("ValueSet");
		headers.add("Content");
	}

	public HL7Element getElement(String path) {
		if(Utils.isValid(path)){

			Path p = new Path(path);
			
			HL7Element cursor = message.getChild(p.positions.get(0));
			
			if(p.positions.size() == 1){
				return cursor;
			}
			else {
				
				for(int i = 1; i < p.positions.size(); i++){
					cursor = cursor.getChild(p.positions.get(i));
					if(cursor == null){
						return null;
					}
				}
				return cursor;
			}
		}
		else
			return null;
	}

	public void addIndex(String path, String internalPath){
		index.put(path, internalPath);
	}
	
	public String getIndexFor(String path){
		return index.get(path);
	}

	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("header", this.headers);
		obj.put("message", message.toJSON());
		return obj.toString();
	}
	
	public MatrixView getView(){
		MatrixView mv = new MatrixView();
		mv.setHeader(this.headers);
		mv.setMessage(message.getView());
		return mv;
	}

	public void editElement(String path, HL7Element newElement) {
		HL7Element elm = this.getElement(path);
		elm.setDescription(newElement.getDescription());
		elm.setInternalPath(newElement.getInternalPath());
		elm.setName(newElement.getName());
		elm.setPosition(newElement.getPosition());
		elm.setPath(newElement.getPath());
		elm.setIndexes(newElement.getIndexes());
		elm.setUsage(newElement.getUsage());
		elm.setConfLength(newElement.getConfLength());
		elm.setCardinality(newElement.getCardinality());
		elm.setLength(newElement.getLength());
		elm.setVs(newElement.isVs());
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
