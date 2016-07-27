package gov.nist.healthcare.acknowledgement.generation;

import java.util.ArrayList;

import gov.nist.healthcare.acknowledgement.model.AckMessage;
import gov.nist.healthcare.acknowledgement.model.Code;
import gov.nist.healthcare.acknowledgement.model.ErrSegment;
import gov.nist.healthcare.matrix.common.Utils;
import gov.nist.healthcare.matrix.model.HL7Element;
import gov.nist.healthcare.matrix.model.ProfileMatrix;
import gov.nist.healthcare.unified.model.EnhancedReport;
import gov.nist.healthcare.unified.model.Section;
import gov.nist.healthcare.unified.model.StringRef;

public class Generator {
	
	public AckMessage generateAck(EnhancedReport validation, ProfileMatrix matrix){
		AckMessage message = new AckMessage();
		
		ArrayList<Section> detections = validation.getDetections().entries();
		for(Section s : detections){
			StringRef path = new StringRef();
			if(s.accessPrimitive("path", path)){
				String simplePath = Utils.simplifyPath(path.value);
				String index = matrix.getIndexFor(simplePath);
				HL7Element matrixElm = matrix.getElement(index);
				
				if(matrixElm != null){
					StringRef category = new StringRef();
					if(s.accessPrimitive("category", category)){
						int severity = matrixElm.getIndexes().get(category.value);
						if(severity != -1){
							message.addErr(new ErrSegment(path.value,severity,mapDetection(category.value)));
						}
					}
				}
			}
		}
		return message;
	}
	
	public Code mapDetection(String category){
		return null;
	}
}
