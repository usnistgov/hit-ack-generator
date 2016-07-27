package gov.nist.healthcare.acknowledgement.model;

public class ErrSegment {
	
	private HL7Path path;
	private int severity;
	private Code code;
	
	public ErrSegment(String path, int severity, Code c){
		this.path = new HL7Path(path);
		this.severity = severity;
		this.code = c;
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
		return "ERR|"+path.toString()+"|"+severity;
	}

}
