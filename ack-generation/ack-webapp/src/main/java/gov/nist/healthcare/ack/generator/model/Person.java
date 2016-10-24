package gov.nist.healthcare.ack.generator.model;

import java.util.ArrayList;

public class Person {
	private String name;
	private String surname;
	private ArrayList<String> test;

	public Person() {
		super();
		test = new ArrayList<String>();
		test.add("AA");
		test.add("BB");
	}

	public Person(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
		test = new ArrayList<String>();
		test.add("AA");
		test.add("BB");
	}
	
	public ArrayList<String> getTest() {
		return test;
	}

	public void setTest(ArrayList<String> test) {
		this.test = test;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
