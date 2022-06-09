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
    //   TODO����һ��ͼ��Ȼ�����Graph.toString���رȽ�
    
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
    	assertEquals("ָ���a�ĵ�ͱߵ�ȨֵΪ��{c=3}, ָ��a�ĵ�ͱߵ�ȨֵΪ��{b=5}\n"
    				+"ָ���b�ĵ�ͱߵ�ȨֵΪ��{a=5}, ָ��b�ĵ�ͱߵ�ȨֵΪ��{c=4}\n"
    				+"ָ���c�ĵ�ͱߵ�ȨֵΪ��{b=4}, ָ��c�ĵ�ͱߵ�ȨֵΪ��{a=3}\n",graph.toString());
    } 
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    /**
     * ����Vertex�������ÿһ�ַ���
     */
    
    // TODO tests for operations of Vertex
    @Test
    public void getNameTest() {
        /* Testing strategy
         * ��ӵ�֮�󣬲���Դ��ͱ߹��ɵ�ӳ��
         */
    	Vertex<String> vertex = new Vertex<String>("a");
		assertEquals("a", vertex.getName());
	}

    @Test
	public void getSourceTest() {
        /* Testing strategy
         * ��ӵ�֮�󣬲���Դ��ͱ߹��ɵ�ӳ��
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
         * ��ӵ�֮�󣬲����յ�ͱ߹��ɵ�ӳ��
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
         * ��ӵ�֮���жϵ��Ƿ����
         * �ж���ӵĵ��Ȩֵ�Ƿ����0
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
         * ��ӵ�֮���жϵ��Ƿ����
         * �ж���ӵĵ��Ȩֵ�Ƿ����0
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
         * �����Ƴ��ĵ㻮�֣�����ڣ��㲻����
         * ����ÿ��ȡֵ���£�
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
