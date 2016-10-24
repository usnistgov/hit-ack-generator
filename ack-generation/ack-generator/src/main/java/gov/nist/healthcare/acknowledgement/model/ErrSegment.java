package gov.nist.healthcare.acknowledgement.model;

public class ErrSegment {
	
	private HL7Path path;
	private int severity;
	private Code code;
	private String msg;
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ErrSegment(String path, int severity, Code c, String msg){
		this.path = new HL7Path(path);
		this.severity = severity;
		this.code = c;
		this.msg = msg;
	}
	
	public HL7Path getPath() {
		return path;
	}
	public void setPath(HL7Path path) {
		this.path = path;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
	
	public String toString(){
		String sev = "";
		if(severity == 1){
			sev = "I";
		}
		else if(severity == 2){
			sev = "W";
		}
		else {
			sev = "E";
		}
		return "ERR||"+path.toString()+"|[HL7 ERROR CODE]|"+sev+"|[APPLICATION ERROR CODE]|"+msg;
	}

}
