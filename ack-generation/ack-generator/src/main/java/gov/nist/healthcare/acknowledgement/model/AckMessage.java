package gov.nist.healthcare.acknowledgement.model;

import java.util.ArrayList;

import javax.print.attribute.standard.Severity;

public class AckMessage {
	
	private String msgControlId;
	private String ackCode;
	private boolean hasError;
	private boolean hasWarning;
	
	private ArrayList<ErrSegment> err;
	
	public AckMessage(){
		err = new ArrayList<ErrSegment>();
		hasError = false;
		hasWarning = false;
	}
	
	public String getMsgControlId() {
		return msgControlId;
	}
	public void setMsgControlId(String msgControlId) {
		this.msgControlId = msgControlId;
	}
	public String getAckCode() {
		return ackCode;
	}
	public ArrayList<ErrSegment> getErr() {
		return err;
	}
	public void setErr(ArrayList<ErrSegment> err) {
		
		this.err = err;
	}
	
	public void addErr(ErrSegment err) {
		
		if(!hasError && err.getSeverity() == Severity.ERROR.getValue()){
			hasError = true;
		}
		
		if(!hasWarning && err.getSeverity() == Severity.WARNING.getValue()){
			hasWarning = true;
		}
		
		if(!hasError && !hasWarning)
			ackCode = "AA";
		else if(hasError)
			ackCode = "AR";
		else if(hasWarning)
			ackCode = "AE";
		
		this.err.add(err);
	}
	
	public String toString(){
		String overhead = "MSH...\nMSA|"+ackCode+"\n";
		String err = "";
		for(ErrSegment er : this.err){
			err += er.toString()+"\n";
		}
		return overhead + err;
	}

	
}
