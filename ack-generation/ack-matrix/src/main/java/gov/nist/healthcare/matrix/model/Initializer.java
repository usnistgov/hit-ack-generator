package gov.nist.healthcare.matrix.model;

public class Initializer {

	public static void initialize(ProfileMatrix mtx){
		Message m = mtx.getMessage();
		for(int i = 0; i < m.size(); i++){
			browse(m.getChild(i));
		}
	}
	
	public static void browse(HL7Element elm){
		elm.setIndexes(indexes(usage(elm),cardinality(elm),length(elm),format(elm),4,vs(elm),4));
		for(int i = 0; i < elm.nbChildren(); i++){
			browse(elm.getChild(i));
		}
	}
	
	public static Indexes indexes(int usage, int cardinality, int length, int format, int confStat, int valueSet, int content){
		
		Indexes ind = new Indexes();
		ind.map.put("Usage", Severity.values()[usage]);
		ind.map.put("Cardinality", Severity.values()[cardinality]);
		ind.map.put("Length", Severity.values()[length]);
		ind.map.put("Format", Severity.values()[format]);
		ind.map.put("Profile Conformance Statement", Severity.values()[confStat]);
		ind.map.put("ValueSet", Severity.values()[valueSet]);
		ind.map.put("Content", Severity.values()[content]);
		
		return ind;
	}
	
	public static int usage(HL7Element e){
		if(e.getUsage() != "O")
			return 4;
		else
			return 0;
	}
	
	public static int cardinality(HL7Element e){
		Range r = e.getCardinality();
		if(r == null)
			return 0;
		
		if(r.min == 0 && (r.max == null || r.max.equals("*")))
			return 0;
		else
			return 4;
	}
	
	public static int format(HL7Element e){
		if(e.isSimple())
			return 4;
		else
			return 0;
	}
	
	public static int vs(HL7Element e){
		if(e.isVs())
			return 4;
		else
			return 0;
	}
	
	public static int length(HL7Element e){
		if(e.getConfLength() != null)
			return 4;
		else
			return 0;
	}
}
