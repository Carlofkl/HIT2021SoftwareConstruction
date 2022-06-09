/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    
    
    @Test
    public void testtoString() throws IOException{
        /**Testing strategy
         * @throws IOException 
         *  ���ն����ļ��Ƿ��ǿ��ļ�
         *  һ�����뻹�Ƕ�������
         */
   	 final GraphPoet txt1 = new GraphPoet(new File("test/P1/poet/fkl1.txt"));
        assertEquals("",txt1.toString());
        final GraphPoet txt2 = new GraphPoet(new File("test/P1/poet/fkl2.txt"));
        assertEquals("this��is��Ȩ��Ϊ1\n" + "is��a��Ȩ��Ϊ1\n" + "a��test��Ȩ��Ϊ1\n" + "test��of��Ȩ��Ϊ1\n" + "of��the��Ȩ��Ϊ1\n" + "the��mugar��Ȩ��Ϊ1\n" + 
        		      "mugar��omni��Ȩ��Ϊ1\n" + "omni��theater��Ȩ��Ϊ1\n" + "theater��sound��Ȩ��Ϊ1\n" + "sound��system.��Ȩ��Ϊ1\n",txt2.toString());
   }
}
