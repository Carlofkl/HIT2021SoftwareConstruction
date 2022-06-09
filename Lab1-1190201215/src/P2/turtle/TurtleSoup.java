/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator; 

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        //throw new RuntimeException("implement me!");
        int i;
        for (i=0; i<4; i++) {
        	turtle.forward(sideLength);
        	turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
        if (sides < 2)
        	return 0;
        else
        	return 180 - 360/(double)(sides);
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //throw new RuntimeException("implement me!");
    	if (angle > 180)
    		return -1;
    	else
    		return (int) (360/(int)(180 - angle));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        //throw new RuntimeException("implement me!");
    	int i;
    	double angle = 180 - TurtleSoup.calculateRegularPolygonAngle(sides);
    	for (i=0; i<sides; i++) {
    		turtle.turn(angle);
    		turtle.forward(sideLength);
    	}
    		
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
        //throw new RuntimeException("implement me!");
    	double x = (double)targetX - currentX;
    	double y = (double)targetY - currentY;
    	double degree = 90 - currentBearing - Math.toDegrees(Math.atan2(y, x));
    	if (degree < 0)
    		degree += 360;
    	return degree;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        //throw new RuntimeException("implement me!");
    	ArrayList<Double> degree = new ArrayList<Double>();
    	degree.add(calculateBearingToPoint(0, xCoords.get(0), yCoords.get(0), xCoords.get(1), yCoords.get(1)));
    	int i;
    	for (i=1; i<xCoords.size()-1; i++) {
    		degree.add(calculateBearingToPoint(degree.get(i-1), xCoords.get(i), yCoords.get(i), xCoords.get(i+1), yCoords.get(i+1)));
    	}
    	return degree;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    @SuppressWarnings("null")
	public static Set<Point> convexHull(Set<Point> points) {
    	ArrayList<Point> tubao=new ArrayList<Point>();
    	ArrayList<Point> point=new ArrayList<Point>();
    	point.addAll(points);  //设置新的点集，防止对原有的point进行了改变
    	int length;
    	length=point.size();
    	if(length<=3) {  //少于三个点直接是凸包
    		return points;
    	}
    	else {
    		Point start=point.get(0);  //首先找到左下角的点作为起始点
    		for(Point t:points) {
    			if(t.x()<start.x()) {
    				start=t;
    			}
    			else if(t.x()==start.x()&&t.y()<start.y()) {
    				start=t;
    			}
    		}
    		tubao.add(start); //把最左下角初始点加入凸包集合
    		int i=0;
    		point.remove(start);  //原集合除去初始点
    		Point forpoint=start;
    		do {
    			if(i==1) {
    				point.add(start);  //重新加入起始点判断截止
    			}
    			double toangle=360,todif=0;
    			Point topoint=null;  //需要加入的点
    			for(Point temp:point) {
    				double tempdushu=calculateBearingToPoint(0,(int)forpoint.x(),(int)forpoint.y(),(int)temp.x(),(int)temp.y());//前一个点到集合中点的偏转角
    				double tempdistance=Math.pow(forpoint.x() - temp.x(), 2) + Math.pow(forpoint.y() - temp.y(), 2);//计算距离
    				if(tempdushu<toangle) {  //找到最小的偏转角
    					toangle=tempdushu;
    					topoint=temp;
    					todif=tempdistance;
    				}
    				else if(tempdushu==toangle&&tempdistance>todif) { //若度数相同，找最远的点
    					topoint=temp;
    					todif=tempdistance;
    				}	
    			}
    			tubao.add(topoint);
    			point.remove(topoint);
    			forpoint=topoint;
    			i++;
    		}while(tubao.get(i)!=start);
    		Set<Point> result = new HashSet<Point>();
    		result.addAll(tubao);
    		return result;
    	}
    }
    
    /**
     * Draw your p
     * ersonal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        //throw new RuntimeException("implement me!");
    	PenColor[] color = new PenColor[5];
    	color[0] = PenColor.BLACK;
    	color[1] = PenColor.BLUE;
    	color[2] = PenColor.GREEN;
    	color[3] = PenColor.ORANGE;
    	color[4] = PenColor.YELLOW;
    	int i;
    	for (i=0; i<150; i++) {
    		drawRegularPolygon(turtle, i, i);
    		turtle.color(color[i%5]);
    		turtle.turn(180);
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
