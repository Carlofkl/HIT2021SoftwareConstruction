package P3;

import java.util.ArrayList;

public class Person{
	String name;
	ArrayList<Person> friendList = new ArrayList<>();
	

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
