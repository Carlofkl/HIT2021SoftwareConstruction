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
         *  按照读入文件是否是空文件
         *  一行输入还是多行输入
         */
   	 final GraphPoet txt1 = new GraphPoet(new File("test/P1/poet/fkl1.txt"));
        assertEquals("",txt1.toString());
        final GraphPoet txt2 = new GraphPoet(new File("test/P1/poet/fkl2.txt"));
        assertEquals("this到is的权重为1\n" + "is到a的权重为1\n" + "a到test的权重为1\n" + "test到of的权重为1\n" + "of到the的权重为1\n" + "the到mugar的权重为1\n" + 
        		      "mugar到omni的权重为1\n" + "omni到theater的权重为1\n" + "theater到sound的权重为1\n" + "sound到system.的权重为1\n",txt2.toString());
   }
}
