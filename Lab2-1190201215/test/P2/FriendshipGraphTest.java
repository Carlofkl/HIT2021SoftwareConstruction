package P2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FriendshipGraphTest {
	@Test
	public void addVertextest() {
		/**
		 * testing strategy
		 * 加入Person，检查图中是否包含
		 */
		FriendshipGraph graph = new FriendshipGraph();
		Person Alice = new Person("Alice");
		Person Bob = new Person("BOb");
		graph.addVertex(Alice);
		graph.addVertex(Bob);
		assertTrue(graph.getPeople().vertices().contains(Alice));
		assertTrue(graph.getPeople().vertices().contains(Bob));
	}
	@Test
	public void addEdgetest() {
		/**
		 * testing strategy
		 * 加入Person，按照两个人距离划分，0、1
		 * 检查对方的朋友列表targets是否含有另一个人
		 */
		FriendshipGraph graph = new FriendshipGraph();
		Person Alice = new Person("Alice");
		Person Bob = new Person("BOb");
		graph.addVertex(Alice);
		graph.addVertex(Bob);
		graph.addEdge(Alice, Bob);
		graph.addEdge(Bob, Alice);
		assertTrue(graph.getPeople().targets(Alice).containsKey(Bob));
		assertTrue(graph.getPeople().targets(Bob).containsKey(Alice));
	}
	@Test
	public void getDistancetest() {
		/**
		 * testing strategy
		 * 按照两个距离划分，0、1、大于1
		 * 按照是否为同一个人，同一个距离为0
		 * 检查每个点之间的距离
		 */
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
