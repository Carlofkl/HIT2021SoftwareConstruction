package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FriendshipGraph {

	public ArrayList<Person> people = new ArrayList<Person>();
	public Set<String> nameSet = new HashSet<String>();

	public void addVertex(Person a) {
		if (nameSet.contains(a.getName())) {
			System.out.print("ÓÐÖØ¸´Ãû×Ö");
			System.exit(0);
		}
		people.add(a);
		nameSet.add(a.getName());
	}

	public void addEdge(Person m, Person n) {
		m.friendList.add(n);
	}

	public int getDistance(Person m, Person n) {
		if (m.equals(n))
			return 0;
		Queue<Person> qu = new LinkedList<>();
		Map<Person, Integer> dis = new HashMap<Person, Integer>();
		qu.offer(m);
		dis.put(m, 0);
		while (qu != null) {
			Person temp = qu.poll();
			if (temp == null)
				return -1;
			for (Person t : temp.friendList) {
				if (dis.containsKey(t) == false) {
					qu.offer(t);
					dis.put(t, dis.get(temp) + 1);
					if (t == n)
						return dis.get(t);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {

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

		System.out.println(graph.getDistance(rachel, ross));
		// should print 1
		System.out.println(graph.getDistance(rachel, ben));
		// should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		// should print -1
	}

}
