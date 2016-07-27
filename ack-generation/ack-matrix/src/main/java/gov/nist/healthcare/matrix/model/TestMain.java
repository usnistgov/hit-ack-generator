package gov.nist.healthcare.matrix.model;

public class TestMain {

	public static void main(String[] args) {
		Test t = new Test();
		t.one = "1";
		t.two = "2";
		Class<?> reflector = t.getClass();
		try {
			java.lang.reflect.Field f = reflector.getDeclaredField("one");
			java.lang.reflect.Field ff = reflector.getDeclaredField("two");
			
			f.set(t, "2");
			ff.set(t, "3");
			
			System.out.println(t.one+","+t.two);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
