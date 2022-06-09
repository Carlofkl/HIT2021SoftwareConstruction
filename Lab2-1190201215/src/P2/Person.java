package P2;


public class Person {

	private String name;
	
	//Abstraction function
	//AF(name) = 该Person的名字
	//Representation invariant
	//没有重复的名字
	//Safety from rep exposure
	//name 设置为private，immutable
	
	public Person(String name) {
		//if ()
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
