package gov.nist.healthcare.matrix.model;

public interface Matrix {
	//public void init(String configXml);
	public HL7Element getElement(String path);
	public void editElement(String path, HL7Element newElement);
	public String toJson();
}
