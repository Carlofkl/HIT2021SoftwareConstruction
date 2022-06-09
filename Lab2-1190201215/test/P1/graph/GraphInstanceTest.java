/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //用Graph.empty()创建一个空的图，然后逐个测试里面的方法
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    @Test
    public void addTest() {
    	Graph<String> graph = Graph.empty();
    	graph.add("f");
    	graph.add("k");
    	graph.add("l");
    	assertEquals(true, graph.vertices().contains("f"));
    	assertEquals(true, graph.vertices().contains("k"));
    	assertEquals(true, graph.vertices().contains("l"));
    }
    @Test
    public void setTest() {
    	Graph<String> graph = Graph.empty();
    	assertEquals(0,graph.set("a", "b", 5));
    	assertEquals(0,graph.set("b", "c", 4));
    	assertEquals(5,graph.set("a", "b", 6));
    	assertEquals(6,graph.set("a", "b", 0));
    	assertEquals(0,graph.set("a", "c", 0));
    	assertEquals(-1,graph.set("a", "b", -3));	
    }
    @Test
    public void removeTest() {
    	Graph<String> graph = Graph.empty();
    	String a="f";
    	String b="k";
    	String c="l";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	graph.set(a, b, 5);
    	graph.set(b, c, 4);
    	assertEquals(true,graph.remove(a));
    	assertEquals(false,graph.remove(a));  	
    }
    @Test
    public void verticesTest() {
    	Graph<String> graph =emptyInstance();
    	String a="f";
    	String b="k";
    	String c="l";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	assertEquals(true,graph.vertices().contains(a));
    	assertEquals(true,graph.vertices().contains(b));
    	assertEquals(true,graph.vertices().contains(c));
    }
    @Test
    public void sourcesTest() {
    	Graph<String> graph =emptyInstance();
    	String a="f";
    	String b="k";
    	String c="l";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	graph.set(a, b, 5);
    	graph.set(b, c, 4);
    	graph.set(a, c, 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put(a,3);
    	map.put(b,4);
    	assertEquals(map,graph.sources(c)); 
    }
    @Test
    public void testTargets() {
    	Graph<String> graph =emptyInstance();
    	String a="f";
    	String b="k";
    	String c="l";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	graph.set(a, b, 5);
    	graph.set(b, c, 4);
    	graph.set(a, c, 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put(c,3);
    	map.put(b,5);
    	assertEquals(map,graph.targets(a)); 	
    }
}
