package gov.nist.healthcare.acknowledgement.parser;

import java.io.InputStream;

import scala.collection.immutable.List;
import scala.util.Try;
import hl7.v2.instance.Element;
import hl7.v2.instance.Message;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import hl7.v2.parser.impl.DefaultParser;
import hl7.v2.parser.impl.DefaultParser$class;
import hl7.v2.profile.Profile;
import hl7.v2.profile.XMLDeserializer;

public class ParseUtils implements DefaultParser {
	/**
	  * A java friendly way to call the `parse` method of the default parser implementation
	  * @param message - The message as a string
	  * @param model   - The message model
	  * @return The message instance model encapsulated in a scala `scala.util.Try`
	  */
	@SuppressWarnings("unchecked")
	public Try<Message> parse(String message, hl7.v2.profile.Message model) {
		return (Try<Message>) DefaultParser$class.parse( this, message, model);
	}
	
	
	/**
	  * Call JParser.parse method and decapsulate the result
	  */
	public Message jparse(String message, hl7.v2.profile.Message model) throws Exception {
		return (Message) parse(message, model).get();
	}
	
	public static Profile getProfile() {
		// The profile in XML
		InputStream profileXML = ParseUtils.class.getResourceAsStream("/files/VXU-Z22_Profile.xml");
					
		// The get() at the end will throw an exception if something goes wrong
		Profile profile = XMLDeserializer.deserialize(profileXML).get();
		
		return profile;
	}
	
	public static String getControlID(String msg, Element e){
		List<Simple> elm = Query.queryAsSimple(e, "1[1].10[1]").get();
		return elm.apply(0).value().raw();
	}
	
	public static String getRecApp(String msg, Element e){
		List<Simple> elm = Query.queryAsSimple(e, "1[1].3[1].1[1]").get();
		return elm.apply(0).value().raw();
	}
	
	
	
	
}
