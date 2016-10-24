package gov.nist.healthcare.matrix.model;

import org.json.JSONObject;

public interface HL7Element {

	public HL7Element getChild(int i);
	public String getPath();
	public void setPath(String path);
	public String getName();
	public void setName(String name);
	public String getDescription();
	public void setDescription(String description);
	public Indexes getIndexes();
	public void setIndexes(Indexes indexes);
	public int getPosition();
	public void setPosition(int position);
	public String getInternalPath();
	public void setInternalPath(String internalPath);
	public JSONObject toJSON();
	public ElementView getView();
	public String getUsage();
	public void setUsage(String usage);
	public String getConfLength();
	public void setConfLength(String confLength);
	public Range getCardinality();
	public void setCardinality(Range cardinality);
	public Range getLength();
	public void setLength(Range length);
	public boolean isVs();
	public void setVs(boolean vs);
	public boolean isSimple();
	public int nbChildren();
	public String getType();
	public void setType(String type);
}
