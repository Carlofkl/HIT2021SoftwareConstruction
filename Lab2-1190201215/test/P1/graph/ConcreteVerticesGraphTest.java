/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO创建一个图，然后调用Graph.toString返回比较
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void toStringTest() {
    	Graph<String> graph =emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.set("a", "b", 5);
    	graph.set("b", "c", 4);
    	graph.set("c", "a", 3);
    	assertEquals("指向点a的点和边的权值为：{c=3}, 指向a的点和边的权值为：{b=5}\n"
    				+"指向点b的点和边的权值为：{a=5}, 指向b的点和边的权值为：{c=4}\n"
    				+"指向点c的点和边的权值为：{b=4}, 指向c的点和边的权值为：{a=3}\n",graph.toString());
    } 
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    /**
     * 测试Vertex类里面的每一种方法
     */
    
    // TODO tests for operations of Vertex
    @Test
    public void getNameTest() {
        /* Testing strategy
         * 添加点之后，测试源点和边构成的映射
         */
    	Vertex<String> vertex = new Vertex<String>("a");
		assertEquals("a", vertex.getName());
	}

    @Test
	public void getSourceTest() {
        /* Testing strategy
         * 添加点之后，测试源点和边构成的映射
         */
    	Vertex<String> vertex = new Vertex<String>("a");
		vertex.addSource("b", 5);
		vertex.addSource("c", 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b",5);
    	map.put("c",3);
    	assertEquals(map, vertex.getSources());
    }
    
    @Test
	public void testGettarget() {
        /* Testing strategy
         * 添加点之后，测试终点和边构成的映射
         */
    	Vertex<String> vertex = new Vertex<String>("a");
		vertex.addTarget("b", 5);
		vertex.addTarget("c", 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b",5);
    	map.put("c",3);
    	assertEquals(map, vertex.getTargets());
    }
    
    @Test
	public void addSourceTest() {
        /* Testing strategy
         * 添加点之后，判断点是否存在
         * 判断添加的点的权值是否大于0
         */
    	Vertex<String> vertex = new Vertex<String>("a");
    	vertex.addSource("b", 5);
    	vertex.addSource("c", 3);
    	vertex.addSource("b", 4);
    	vertex.addSource("b", 0);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b", 0);
    	map.put("c", 3);
    	assertEquals(map, vertex.getSources());
    }
    
	public void addTargetTest() {
        /* Testing strategy
         * 添加点之后，判断点是否存在
         * 判断添加的点的权值是否大于0
         */
    	Vertex<String> vertex = new Vertex<String>("a");
    	vertex.addTarget("b", 5);
    	vertex.addTarget("c", 3);
    	vertex.addTarget("b", 4);
    	vertex.addTarget("b", 0);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b", 0);
    	map.put("c", 3);
    	assertEquals(map, vertex.getTargets());
    }
	
	@Test
	public void getSourceWeightTest() {
		Vertex<String> vertex = new Vertex<String>("a");
		vertex.addSource("b", 5);
		vertex.addSource("c", 3);
		assertEquals(5, vertex.getSourceWeight("b"));
	}
	
	@Test
	public void getTargetWeightTest() {
		Vertex<String> vertex = new Vertex<String>("a");
		vertex.addTarget("b", 5);
		vertex.addTarget("c", 3);
		assertEquals(5, vertex.getTargetWeight("b"));
	}
	
	@Test
	public void removeSourceTest() {
        /* Testing strategy
         * 按照移除的点划分，点存在，点不存在
         * 覆盖每个取值如下：
         */
		Vertex<String> vertex = new Vertex<String>("a");
		vertex.addSource("b", 5);
    	vertex.addSource("c", 10);
    	vertex.removeSource("b");
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("c", 10);
    	assertEquals(map, vertex.getSources());
	}
	
	@Test
	public void removeTargetTest() {
		Vertex<String> vertex = new Vertex<String>("a");
		vertex.addTarget("b", 5);
    	vertex.addTarget("c", 10);
    	vertex.removeSource("d");
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b", 5);
    	map.put("c", 10);
    	assertEquals(map, vertex.getTargets());
	}
}
