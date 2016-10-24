package gov.nist.healthcare.ack.generator.model;

public class ResponseMessage {
	private String content;
	private String report;
	
	public ResponseMessage(String content) {
		super();
		this.content = content;
	}

	
	
	public ResponseMessage(String content, String report) {
		super();
		this.content = content;
		this.report = report;
	}



	public String getReport() {
		return report;
	}


	public void setReport(String report) {
		this.report = report;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
