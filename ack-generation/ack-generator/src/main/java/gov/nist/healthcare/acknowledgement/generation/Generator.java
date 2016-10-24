package gov.nist.healthcare.acknowledgement.generation;

import java.util.ArrayList;
import java.util.HashMap;

import gov.nist.healthcare.acknowledgement.model.AckMessage;
import gov.nist.healthcare.acknowledgement.model.Code;
import gov.nist.healthcare.acknowledgement.model.ErrSegment;
import gov.nist.healthcare.acknowledgement.model.MessageHeader;
import gov.nist.healthcare.acknowledgement.parser.ParseUtils;
import gov.nist.healthcare.matrix.common.Utils;
import gov.nist.healthcare.matrix.model.ElementView;
import gov.nist.healthcare.matrix.model.HL7Element;
import gov.nist.healthcare.matrix.model.MatrixView;
import gov.nist.healthcare.matrix.model.ProfileMatrix;
import gov.nist.healthcare.matrix.model.TraversableMatrix;
import gov.nist.healthcare.unified.model.EnhancedReport;
import gov.nist.healthcare.unified.model.Section;
import gov.nist.healthcare.unified.model.StringRef;
import hl7.v2.instance.Message;

public class Generator {

	private HashMap<String, String> mapping = new HashMap<String, String>();

	public Generator() {
		mapping.put("R Usage", "Usage");
		mapping.put("R-Usage", "Usage");
		mapping.put("Usage", "Usage");
		mapping.put("RE Usage", "Usage");
		mapping.put("RE-Usage", "Usage");
		mapping.put("X Usage", "Usage");
		mapping.put("X-Usage", "Usage");
		mapping.put("W Usage", "Usage");
		mapping.put("W-Usage", "Usage");
		mapping.put("PREDICATE_FAILURE", "Usage");
		mapping.put("Cardinality", "Cardinality");
		mapping.put("Format", "Format");
		mapping.put("Length", "Length");
		mapping.put("ValueSet", "ValueSet");
		mapping.put("Value Set", "ValueSet");
		mapping.put("Constraint Failure", "Profile Conformance Statement");
		mapping.put("Content Failure", "Content");
	}

	public AckMessage generateAck(EnhancedReport validation,
			ProfileMatrix matrix, String msg) {
		AckMessage message = new AckMessage();
		// Create Header

		ParseUtils pu = new ParseUtils();
		try {
			Message m = pu.jparse(
					msg,
					pu.getProfile().messages()
							.apply("aa72383a-7b48-46e5-a74a-82e019591fe7"));
			String controlID = pu.getControlID(msg, m.asGroup());
			String reF = pu.getRecApp(msg, m.asGroup());
			message.setMsgControlId(controlID);
			message.setHeader(new MessageHeader(reF));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Section> detections = validation.getDetections().entries();
		for (Section s : detections) {
			StringRef path = new StringRef();
			if (s.accessPrimitive("path", path)) {
				String simplePath = Utils.simplifyPath(path.value);
				String index = matrix.getIndexFor(simplePath);
				HL7Element matrixElm = matrix.getElement(index);

				if (matrixElm != null) {
					StringRef category = new StringRef();
					if (s.accessPrimitive("category", category)) {
						int severity = matrixElm.getIndexes().get(
								mapping.get(category.value));
						if (severity != -1) {
							StringRef desc = new StringRef();
							boolean b = s.accessPrimitive("description", desc);
							message.addErr(new ErrSegment(path.value, severity,
									mapDetection(category.value),
									b ? desc.value : ""));
						}
					}
				}
			}
		}
		return message;
	}

	public AckMessage generateAck(EnhancedReport validation,
			TraversableMatrix matrix, String msg) {
		AckMessage message = new AckMessage();
		// Create Header

		ParseUtils pu = new ParseUtils();
		try {
			Message m = pu.jparse(
					msg,
					pu.getProfile().messages()
							.apply("aa72383a-7b48-46e5-a74a-82e019591fe7"));
			String controlID = pu.getControlID(msg, m.asGroup());
			String reF = pu.getRecApp(msg, m.asGroup());
			message.setMsgControlId(controlID);
			message.setHeader(new MessageHeader(reF));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Section> detections = validation.getDetections().entries();
		for (Section s : detections) {
			StringRef path = new StringRef();
			StringRef category = new StringRef();
			StringRef classification = new StringRef();
			
			if (s.accessPrimitive("path", path) && s.accessPrimitive("category", category) && s.accessPrimitive("classification", classification)) {

				if(!classification.value.equals("Alert") && !classification.value.equals("Affirmative") && mapping.containsKey(category.value)){
					String simplePath = Utils.simplifyPath(path.value);
					ElementView matrixElm = matrix.getElement(simplePath);
					
					if(matrixElm.getIndexes().containsKey(mapping.get(category.value))){
						int severity = matrixElm.getIndexes().get(mapping.get(category.value));
						if (severity > 0) {
							StringRef desc = new StringRef();
							boolean b = s.accessPrimitive("description", desc);
							message.addErr(new ErrSegment(path.value, severity, mapDetection(category.value),
									b ? desc.value : ""));
						}
					}
				}
			}
		}
		return message;
	}

	public Code mapDetection(String category) {
		return null;
	}
}
