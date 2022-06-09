package P3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FriendshipGraphTest {
	@Test
	public void addVertextest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person Alice = new Person("Alice");
		Person Bob = new Person("BOb");
		graph.addVertex(Alice);
		graph.addVertex(Bob);
		assertTrue(graph.people.contains(Alice));
		assertTrue(graph.people.contains(Bob));
	}
	@Test
	public void addEdgetest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person Alice = new Person("Alice");
		Person Bob = new Person("BOb");
		graph.addVertex(Alice);
		graph.addVertex(Bob);
		graph.addEdge(Alice, Bob);
		graph.addEdge(Bob, Alice);
		assertTrue(graph.people.get(0).friendList.contains(Bob));
		assertTrue(graph.people.get(1).friendList.contains(Alice));
	}
	@Test
	public void getDistancetest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person alice = new Person("Alice");
		Person bob = new Person("Bob");
		Person fkl = new Person("fkl");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(alice);
		graph.addVertex(bob);
		graph.addVertex(fkl);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(rachel, ben);
		graph.addEdge(ben, rachel);
		graph.addEdge(alice, ben);
		graph.addEdge(ben, alice);
		graph.addEdge(bob, ross);
		graph.addEdge(ross, bob);
		graph.addEdge(alice, kramer);
		graph.addEdge(kramer, alice);
		graph.addEdge(alice, bob);
		graph.addEdge(bob, alice);
		assertEquals(0, graph.getDistance(ben, ben));
		assertEquals(2, graph.getDistance(rachel, bob));
		assertEquals(3, graph.getDistance(rachel, kramer));
		assertEquals(2, graph.getDistance(kramer, bob));
		assertEquals(-1, graph.getDistance(kramer, fkl));
	}
}
