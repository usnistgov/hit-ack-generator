package gov.nist.healthcare.matrix.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		InputStream pr = ParserTest.class.getResourceAsStream("/profile/VXU-Z22_Profile.xml");
//		Parser p = new Parser();
//		ProfileMatrix prM = p.parse(pr, "aa72383a-7b48-46e5-a74a-82e019591fe7");
//		
//		//System.out.println(prM.getElement("1.1").toJSON());
//		prM.getElement("1.1").setName("CUSTOM");
//		System.out.println(prM.toJson());
		String p1 = "XXX[1]";
		String p2 = "XXX[1]-2[3]";
		String p3 = "XXX[1]-2[3].4";
		String p4 = "XXX[1]-2[3].4.5";
		
		Pattern pInstance = Pattern.compile("([A-Z0-9]{3})\\[([0-9]+)\\](?:-([0-9]+)\\[([0-9]+)\\](?:.([0-9]+)(?:.([0-9]+))?)?)?");
		Matcher matcher = pInstance.matcher(p1);
		//System.out.println(matcher.matches());
		strip(matcher);
		matcher = pInstance.matcher(p2);
		//System.out.println(matcher.matches());
		strip(matcher);
		matcher = pInstance.matcher(p3);
		//System.out.println(matcher.matches());
		strip(matcher);
		matcher = pInstance.matcher(p4);
		//System.out.println(matcher.matches());
		strip(matcher);
		
	}
	
	public static void strip(Matcher m){
		if(m.find()){
			System.out.println("SegName   : "+m.group(1));
			System.out.println("SegSeq    : "+m.group(2));
			System.out.println("FieldId   : "+m.group(3));
			System.out.println("FieldSeq  : "+m.group(4));
			System.out.println("CompId    : "+m.group(5));
			System.out.println("SubCompId : "+m.group(6));
		}
	}

}
