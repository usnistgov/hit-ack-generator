package gov.nist.healthcare.ack.generator.model;

import gov.nist.healthcare.matrix.model.MatrixView;

public class ValidationObject {
	private MatrixView matrix;
	private String message;
	public MatrixView getMatrix() {
		return matrix;
	}
	public void setMatrix(MatrixView matrix) {
		this.matrix = matrix;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
}
