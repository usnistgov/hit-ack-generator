package gov.nist.healthcare.matrix.model;

import java.util.HashMap;

import org.json.JSONObject;

public class Indexes {
	public HashMap<String, Severity> map = new HashMap<String, Severity>();
	
	public HashMap<String, Integer> simplifiedMap(){
		HashMap<String, Integer> mp = new HashMap<String, Integer>();
		for(String key : map.keySet()){
			mp.put(key, map.get(key).ordinal() - 1);
		}
		return mp;
	}
	
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		for(String key : map.keySet()){
			obj.put(key, map.get(key).ordinal() - 1);
		}
		return obj;
	}
	
	public int get(String categ){
		if(map.containsKey(categ.toUpperCase())){
			return map.get(categ.toUpperCase()).ordinal();
		}
		return -1;
	}
}
