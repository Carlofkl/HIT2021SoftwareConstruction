package P2;


public class Person {

	private String name;
	
	//Abstraction function
	//AF(name) = ��Person������
	//Representation invariant
	//û���ظ�������
	//Safety from rep exposure
	//name ����Ϊprivate��immutable
	
	public Person(String name) {
		//if ()
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
