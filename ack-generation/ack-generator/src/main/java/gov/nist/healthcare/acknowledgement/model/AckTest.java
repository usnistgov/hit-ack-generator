package gov.nist.healthcare.acknowledgement.model;

import java.io.InputStream;

import gov.nist.healthcare.acknowledgement.generation.Generator;
import gov.nist.healthcare.matrix.model.HL7Element;
import gov.nist.healthcare.matrix.model.ProfileMatrix;
import gov.nist.healthcare.matrix.model.Severity;
import gov.nist.healthcare.matrix.parser.Parser;
import gov.nist.healthcare.unified.enums.Context;
import gov.nist.healthcare.unified.model.EnhancedReport;
import gov.nist.healthcare.unified.proxy.ValidationProxy;

public class AckTest {

	
	public static void main(String[] args) {
		ValidationProxy vp = new ValidationProxy(
				"Unified Report Test Application", "NIST");
		
		try {
			
			InputStream pr = AckTest.class.getResourceAsStream("/files/VXU-Z22_Profile.xml");
			
			Parser p = new Parser();
			ProfileMatrix prM = p.parse(pr, "aa72383a-7b48-46e5-a74a-82e019591fe7");
			prM.init();
			EnhancedReport report = vp.validate("/files/msg.er7",
					"/files/VXU-Z22_Profile.xml",
					"/files/VXU-Z22_Constraints.xml",
					"/files/VXU-Z22_ValueSetLibrary.xml",
					"aa72383a-7b48-46e5-a74a-82e019591fe7", Context.Free);
			
//			System.out.println(report.to("json"));

//			String index = prM.getIndexFor("MSH-5");
//			HL7Element elm = prM.getElement(index);
//			elm.getIndexes().map.put("USAGE", Severity.Alert);
			
			System.out.println(prM.toJson());
			Generator g = new Generator();
//			AckMessage m = g.generateAck(report, prM);
			
//			System.out.println(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
