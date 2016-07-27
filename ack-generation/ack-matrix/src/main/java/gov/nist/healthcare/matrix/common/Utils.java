package gov.nist.healthcare.matrix.common;

import gov.nist.healthcare.matrix.model.Element;
import scala.Option;

public class Utils {
	
	public static boolean isValid(String path){
		if(path != null && !path.equals("")){
			
			return true;	
		}
		return false;
		
	}
	
	public static <T> T extract(Option<T> obj){
		try {
			return obj.get();
		}
		catch( Exception e ){
			return null;
		}
	}
	
	public static String path(Element e, int id){
		if(e == null){
			return id + "";
		}
		else {
			return e.getInternalPath()+"."+id;
		}
	}
	
	public static String simplifyPath(String path){
		// XXX[a]-y[b].z.t to XXX-y.z.t
		return path.replaceAll("\\[[1-9]*\\]", "");
	}
}
