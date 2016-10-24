package gov.nist.healthcare.acknowledgement.model;

import gov.nist.healthcare.matrix.common.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HL7Path {
	private String segName;
	private int segSeq;
	private int fieldId;
	private int fieldSeq;
	private int compId;
	private int subCompId;
	private final Pattern pHL7path = Pattern.compile("([A-Z0-9]{3})\\[([0-9]+)\\](?:-([0-9]+)\\[([0-9]+)\\](?:.([0-9]+)(?:.([0-9]+))?)?)?");
	
	
	public HL7Path(String path){
		String simplePath = Utils.simplifyPath(path);
		if((simplePath.length() > 3 && Utils.simplifyPath(path).charAt(3) != '-') || simplePath.length() < 3){
			segName   = "";
			segSeq    = -1;
			fieldId   = -1;
			fieldSeq  = -1;
			compId    = -1;
			subCompId = -1;
		}
		else {
			Matcher m = pHL7path.matcher(path);
			if(m.find()){
				segName   = m.group(1);
				segSeq    = m.group(2) == null ? -1 : Integer.parseInt(m.group(2));
				fieldId   = m.group(3) == null ? -1 : Integer.parseInt(m.group(3));
				fieldSeq  = m.group(4) == null ? -1 : Integer.parseInt(m.group(4));
				compId    = m.group(5) == null ? -1 : Integer.parseInt(m.group(5));
				subCompId = m.group(6) == null ? -1 : Integer.parseInt(m.group(6));
			}
			else {
				segName   = "";
				segSeq    = -1;
				fieldId   = -1;
				fieldSeq  = -1;
				compId    = -1;
				subCompId = -1;
			}
		}
	}
	
	public String toString(){
		return segName+"^"+
				(segSeq == -1 ? "" : segSeq)+"^"+
				(fieldId == -1 ? "" : fieldId)+"^"+
				(fieldSeq == -1 ? "" : fieldSeq)+"^"+
				(compId == -1 ? "" : compId)+"^"+
				(subCompId == -1 ? "" : subCompId);
	}
	
	public String getSegName() {
		return segName;
	}
	public void setSegName(String segName) {
		this.segName = segName;
	}
	public int getSegSeq() {
		return segSeq;
	}
	public void setSegSeq(int segSeq) {
		this.segSeq = segSeq;
	}
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public int getFieldSeq() {
		return fieldSeq;
	}
	public void setFieldSeq(int fieldSeq) {
		this.fieldSeq = fieldSeq;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getSubCompId() {
		return subCompId;
	}
	public void setSubCompId(int subCompId) {
		this.subCompId = subCompId;
	}
	
	
}
