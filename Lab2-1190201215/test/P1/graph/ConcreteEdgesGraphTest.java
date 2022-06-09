/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    //创建一个图，然后返回比较
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void toStringTest() {
    	Graph<String> graph =emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.set("a", "b", 5);
    	graph.set("b", "c", 4);
    	graph.set("a", "c", 3);
    	assertEquals("a到b的权重为5\n"+"b到c的权重为4\n"+"a到c的权重为3\n",graph.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    @Test
    public void getSourceTest() {
    	/** testing strategy
    	 * 检查返回的始点是否正确
    	 */
    	Edge<String> one=new Edge<String>("a","b",5);
    	assertEquals("a", one.getSource());
    }
    @Test
    public void getTargetTest() {
    	/** testing strategy
    	 * 检查返回的终点是否正确
    	 */
    	Edge<String> one=new Edge<String>("a","b",5);
    	assertEquals("b", one.getTarget());
    }
    @Test
    public void getWeightTest() {
    	/** testing strategy
    	 * 检查返回的边的权重是否正确
    	 */
    	Edge<String> one=new Edge<String>("a","b",5);
    	assertEquals(5, one.getWeight());
    }
}
