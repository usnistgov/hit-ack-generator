package gov.nist.healthcare.matrix.model;

import java.util.ArrayList;

public class MatrixView {
	private MessageView message;
	private ArrayList<String> header;
	
	public MessageView getMessage() {
		return message;
	}
	public void setMessage(MessageView message) {
		this.message = message;
	}
	public ArrayList<String> getHeader() {
		return header;
	}
	public void setHeader(ArrayList<String> header) {
		this.header = header;
	}
	
}
