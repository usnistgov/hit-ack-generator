package gov.nist.healthcare.ack.generator.controller;

import java.io.InputStream;
import java.util.ArrayList;

import gov.nist.healthcare.ack.generator.model.Person;
import gov.nist.healthcare.ack.generator.model.ResponseMessage;
import gov.nist.healthcare.ack.generator.model.ValidationObject;
import gov.nist.healthcare.acknowledgement.generation.Generator;
import gov.nist.healthcare.acknowledgement.model.AckMessage;
import gov.nist.healthcare.acknowledgement.model.AckTest;
import gov.nist.healthcare.matrix.model.MatrixView;
import gov.nist.healthcare.matrix.model.ProfileMatrix;
import gov.nist.healthcare.matrix.model.TraversableMatrix;
import gov.nist.healthcare.matrix.parser.Parser;
import gov.nist.healthcare.unified.enums.Context;
import gov.nist.healthcare.unified.model.EnhancedReport;
import gov.nist.healthcare.unified.proxy.ValidationProxy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping(value = "/matrix", method = RequestMethod.GET)
	@ResponseBody
	public MatrixView getMatrix() {
		InputStream pr = MainController.class.getResourceAsStream("/files/VXU-Z22_Profile.xml");
		
		Parser p = new Parser();
		ProfileMatrix prM = p.parse(pr, "aa72383a-7b48-46e5-a74a-82e019591fe7");
		prM.init();
		return prM.getView();
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage getMatrix(@RequestBody ValidationObject obj) {
		ValidationProxy vp = new ValidationProxy(
				"Unified Report Test Application", "NIST");
		System.out.println(obj.getMessage());
		try {
			EnhancedReport report = vp.validateContent(obj.getMessage(),
					"/files/VXU-Z22_Profile.xml",
					"/files/VXU-Z22_Constraints.xml",
					"/files/VXU-Z22_ValueSetLibrary.xml",
					"aa72383a-7b48-46e5-a74a-82e019591fe7", Context.Free);
			System.out.println(report.to("json").toString());
			TraversableMatrix trM = new TraversableMatrix(obj.getMatrix());
			Generator g = new Generator();
			AckMessage m = g.generateAck(report, trM, obj.getMessage());
			String reportHTML = report.render("report", null);
			return new ResponseMessage(m.toString(),reportHTML);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
