package gov.nist.healthcare.acknowledgement.model;

import gov.nist.healthcare.matrix.model.Severity;

import java.util.ArrayList;

public class AckMessage {
	
	private String msgControlId;
	private String ackCode;
	private boolean hasError;
	private boolean hasWarning;
	private MessageHeader header;
	
	private ArrayList<ErrSegment> err;
	
	public AckMessage(){
		err = new ArrayList<ErrSegment>();
		hasError = false;
		hasWarning = false;
		ackCode = "AA";
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
	
	
	
	public MessageHeader getHeader() {
		return header;
	}

	public void setHeader(MessageHeader header) {
		this.header = header;
	}

	public void addErr(ErrSegment err) {

		if(!hasError && (err.getSeverity() + 1) == Severity.Error.ordinal()){
			hasError = true;
		}
		
		if(!hasWarning && (err.getSeverity() + 1) == Severity.Warning.ordinal()){
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
		String overhead = this.header.toString()+"\n"+"MSA|"+this.ackCode+"|"+this.msgControlId+"\n";
		String err = "";
		for(ErrSegment er : this.err){
			err += er.toString()+"\n";
		}
		return overhead + err;
	}

	
}
