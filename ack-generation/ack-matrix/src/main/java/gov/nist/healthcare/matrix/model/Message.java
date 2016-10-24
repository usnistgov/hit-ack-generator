package gov.nist.healthcare.matrix.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Message {
	
	private ArrayList<SegmentOrGroup> segmentOrGroup = new ArrayList<SegmentOrGroup>();
	private String id;
	private String desc;
	
	public Message(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}


	public HL7Element getChild(int i) {
		if(i < segmentOrGroup.size())
			return segmentOrGroup.get(i);
		return null;
	}
	
	public int size() {
		return segmentOrGroup.size();
	}
	
	public void addSegOrGroup(SegmentOrGroup a){
		segmentOrGroup.add(a);
	}
	
	public JSONObject toJSON(){
		JSONObject msg = new JSONObject();
		msg.put("id", id);
		msg.put("desc", desc);
		JSONArray children = new JSONArray();
		for(SegmentOrGroup sg : segmentOrGroup){
			children.put(sg.toJSON());
		}
		msg.put("children", children);
		return msg;
	}

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

	public MessageView getView() {
		MessageView mv = new MessageView();
		mv.setId(id);
		mv.setDesc(desc);
		for(SegmentOrGroup sg : segmentOrGroup){
			mv.addChild(sg.getView());
		}
		return mv;
	}
	
	

}
