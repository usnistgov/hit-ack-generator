package gov.nist.healthcare.matrix.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ElementView {
	private String description;
	private String name;
	private String path;
	private String ID;
	private ArrayList<ElementView> children;
	private String type;
	private HashMap<String, Integer> indexes;
	private String usage;
	private boolean valueSet;
	
	
	
	@Override
	public String toString() {
		return "ElementView [description=" + description + ", name=" + name
				+ ", path=" + path + ", ID=" + ID + ", children=" + children
				+ ", type=" + type + ", indexes=" + indexes + "]";
	}
	public HashMap<String, Integer> getIndexes() {
		return indexes;
	}
	public void setIndexes(HashMap<String, Integer> indexes) {
		this.indexes = indexes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public ArrayList<ElementView> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<ElementView> children) {
		this.children = children;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void addChild(ElementView child) {
		if(children == null)
			children = new ArrayList<ElementView>();
		
		this.children.add(child);
	}
	
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public boolean isValueSet() {
		return valueSet;
	}
	public void setValueSet(boolean valueSet) {
		this.valueSet = valueSet;
	}

	
	
	
	
}
