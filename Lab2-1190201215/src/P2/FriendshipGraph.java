package P2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import P1.graph.ConcreteEdgesGraph;


public class FriendshipGraph {
	
	final ConcreteEdgesGraph<Person> people;
	
	//Abstraction function
	//AF(people) = 所有人和关系
	//Representation invariant:
	//没有重复的Person，Person间构成的关系权重只为0或1
	//Safety from rep exposure
	//将people设置为private，immutable

	
	public FriendshipGraph() {
		people = new ConcreteEdgesGraph<>();
	}
	
		public ConcreteEdgesGraph<Person> getPeople(){
		return people;
	}
	
	public void addVertex(Person newPerson) {
		people.add(newPerson);
	}
	
	
	
	public void addEdge(Person a, Person b) {
		people.set(a, b, 1);
	}
	
	
	public int getDistance(Person a, Person b) {
		if (a.equals(b))
			return 0;
		Queue<Person> qu = new LinkedList<>();
		Map<Person, Integer> dis = new HashMap<Person, Integer>();
		qu.offer(a);
		dis.put(a, 0);
		while (qu != null) {
			Person temp = qu.poll();
			if (temp == null)
				return -1;			
			Set<Person> friends = people.targets(temp).keySet();
			for (Person t : friends) {
				if (dis.containsKey(t) == false) {
					qu.offer(t);
					dis.put(t, dis.get(temp) + 1);
					if (t == b)
						return dis.get(t);
				}
			}
		}
		return -1;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println("(rachel, ross)之间距离为"+graph.getDistance(rachel, ross));
		// should print 1
		System.out.println("(rachel, ben)之间距离为"+graph.getDistance(rachel, ben));
		// should print 2
		System.out.println("(rachel, rachel)之间距离为"+graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println("(rachel, kramer)之间距离为"+graph.getDistance(rachel, kramer));
		// should print -1
	}

}
