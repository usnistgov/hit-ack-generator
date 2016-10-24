package gov.nist.healthcare.matrix.model;

import java.util.ArrayList;

public class MessageView {
	private String id;
	private String desc;
	private ArrayList<ElementView> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<ElementView> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<ElementView> children) {
		this.children = children;
	}
	
	public void addChild(ElementView child) {
		if(children == null)
			children = new ArrayList<ElementView>();
		
		this.children.add(child);
	}
	
	
}
