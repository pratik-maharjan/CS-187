package fractal;

import fractal.Turtle;

import java.awt.*;
import java.awt.geom.*;

/**
 * A class that implements some basic fractal drawing methods using recursion.
 * These methods include the Koch curve, tree, Sierpinski triangle and carpet.
 */
public class Fractal {
	private Graphics2D g2d = null;				// a Graphics2D object as canvas for drawing
	private int width=0, height=0;				// image width and height
	private int max_recursion_level = 0;		// maximum recursion level
	private String fractal_type = "Koch Curve";	// type of fractal
	private Color color = Color.green;			// draw color
	
	public void setGraphics(Graphics g, int w, int h)	{
		g2d = (Graphics2D)g; width = w; height = h;
	}
	public void setFractalType(String t)	{ fractal_type = t; }
	public void setColor(Color c)			{ color = c; }
	public void setMaxRecursion(int n)		{ max_recursion_level = n; }

	// Recursive method for drawing the Koch curve given two points and the recursion level
	private void drawKochCurve(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
			double totalDistance = 0;
			totalDistance = p1.distance(p2);
			double oneThirdDistance = totalDistance/3;
			Turtle one = new Turtle(p1, p2);
			one.move(oneThirdDistance);
			Point2D p3 = one.getPosition();
			one.turnLeft(60);
			one.move(oneThirdDistance);
			Point2D p4 = one.getPosition();
			one.turnRight(120);
			one.move(oneThirdDistance);
			Point2D p5 = one.getPosition();
			drawKochCurve(p1, p3, level-1);
			drawKochCurve(p3, p4, level-1);
			drawKochCurve(p4, p5, level-1);
			drawKochCurve(p5, p2, level-1);
		// Koch subdivision rule: ___ ->  _/\_
	}
	
	// Recursive method for drawing a fractal Tree given two points and the recursion level
	private void drawTree(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
			double totalDistance = 0;
			totalDistance = p1.distance(p2);
			double oneThirdDistance = totalDistance/3;
			double twoThirdDistance = (2*totalDistance)/3;
			Turtle one = new Turtle(p1, p2);
			one.move(oneThirdDistance);
			Point2D p3 = one.getPosition();
			one.turnLeft(60);
			one.move(twoThirdDistance);
			Point2D p4 = one.getPosition();
			Turtle two = new Turtle(p1,p2);
			two.move(oneThirdDistance);
			two.turnRight(15);
			two.move(twoThirdDistance);
			Point2D p5 = two.getPosition();
			drawLine(p1, p3);
			drawTree(p3, p4, level-1);
			drawTree(p3, p5, level-1);
		
	}
	
	// Recursive method for drawing the Sierpinski Triangle given the three points
	// that define the triangle and the recursion level
	private void drawSierpinskiTriangle(Point2D p1, Point2D p2, Point2D p3, int level) {
		if(level==0) {	// base case
			drawTriangle(p1, p2, p3);
			return;
		}
			Point2D midpointA = new Point2D.Double((p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2);
			Point2D midpointB = new Point2D.Double((p1.getX() + p3.getX())/2, (p1.getY() + p3.getY())/2);
			Point2D midpointC = new Point2D.Double((p2.getX() + p3.getX())/2, (p2.getY() + p3.getY())/2);
			drawSierpinskiTriangle(midpointA, p2, midpointC, level-1);
			drawSierpinskiTriangle(p1, midpointA, midpointB, level-1);
			drawSierpinskiTriangle(midpointB, midpointC, p3, level-1);
	}
	
	// Recursive method for drawing the Sierpinski Carpet given the lower-left corner
	// of the square (p), the side length (a) of the square, and the recursion level
	private void drawSierpinskiCarpet(Point2D p, double a, int level) {
		if(level==0) {	// base case
			drawRectangle(p, a, a);
			return;
		}
		double oneThirdA = a/3;
		//double twoThirdA = (2/3)*a;
		//Point2D q = new Point2D.Double(p.getX(),p.getY()+a);
		//Point2D r = new Point2D.Double(p.getX()+a,p.getY()+a);
		//Point2D s = new Point2D.Double(p.getX()+a,p.getY());
		//Point2D t = new Point2D.Double(p.getX()+(oneThirdA),p.getY()+(oneThirdA));
		
		//outer points
		Point2D x = new Point2D.Double(p.getX(), p.getY()+(oneThirdA));
		Point2D y = new Point2D.Double(p.getX(), x.getY()+(oneThirdA));
		Point2D e = new Point2D.Double(p.getX()+(oneThirdA), p.getY());
		Point2D z = new Point2D.Double(e.getX()+(oneThirdA), p.getY());
		
		//inner points
		Point2D u = new Point2D.Double(p.getX()+(oneThirdA), x.getY()+(oneThirdA));
		Point2D v = new Point2D.Double(e.getX()+(oneThirdA), x.getY()+(oneThirdA));
		Point2D w = new Point2D.Double(e.getX()+(oneThirdA), x.getY());
		
		drawSierpinskiCarpet(p,oneThirdA,level-1); //1
		drawSierpinskiCarpet(x,oneThirdA,level-1); //2
		drawSierpinskiCarpet(y,oneThirdA,level-1); //3
		drawSierpinskiCarpet(u,oneThirdA,level-1); //4
		drawSierpinskiCarpet(v,oneThirdA,level-1); //5
		drawSierpinskiCarpet(w,oneThirdA,level-1); //6
		drawSierpinskiCarpet(z,oneThirdA,level-1); //7
		drawSierpinskiCarpet(e,oneThirdA,level-1); //8
	}
	
	// This method is left for you to experiment with creative fractals
	// designed by yourself. You will NOT be graded on this method 
	void drawMyFractal(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
			double totalDistance = 0;
			totalDistance = p1.distance(p2);
			double oneThirdDistance = totalDistance/3;
			Turtle one = new Turtle(p1, p2);
			one.move(oneThirdDistance);
			Point2D p3 = one.getPosition();
			one.turnLeft(120);
			one.move(oneThirdDistance);
			Point2D p4 = one.getPosition();
			one.turnRight(60);
			one.move(oneThirdDistance);
			Point2D p5 = one.getPosition();
			drawKochCurve(p1, p3, level-1);
			drawKochCurve(p3, p4, level-1);
			drawKochCurve(p4, p5, level-1);
			drawKochCurve(p5, p2, level-1);
		/* Your creative fractal shape */
	}
	
	/** The code below provides utility methods.
	 *  You should NOT need to modify any code below.
	 */
	public void draw() {
		if(g2d==null || width==0 || height==0) return;
		g2d.setBackground(Color.black);
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(color);
		if(fractal_type.equalsIgnoreCase("Koch Curve")) {
			drawKochCurve(new Point2D.Double(0.0, 0.4), new Point2D.Double(1.0, 0.4), max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Snowflake")) {
			double r = 0.5;
			Point2D p1 = new Point2D.Double(r*Math.cos(Math.toRadians(150))+0.5,
											r*Math.sin(Math.toRadians(150))+0.5);
			Point2D p2 = new Point2D.Double(r*Math.cos(Math.toRadians(30))+0.5,
											r*Math.sin(Math.toRadians(30))+0.5);
			Point2D p3 = new Point2D.Double(r*Math.cos(Math.toRadians(-90))+0.5,
											r*Math.sin(Math.toRadians(-90))+0.5);
			// Snowflake is made of three Koch curves segments
			//  p1____p2
			//    \  /
			//     \/
			//     p3
			drawKochCurve(p1, p2, max_recursion_level);
			drawKochCurve(p2, p3, max_recursion_level);
			drawKochCurve(p3, p1, max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Tree")) {
			// double the maximum recursion level because tree subdivides very slowly
			drawTree(new Point2D.Double(0.6, 0.1), new Point2D.Double(0.6, 0.9),
					max_recursion_level*2);
		} else if(fractal_type.equalsIgnoreCase("Sp Triangle")) {
			double r = 0.5;
			Point2D p1 = new Point2D.Double(r*Math.cos(Math.toRadians(90))+0.5,
											r*Math.sin(Math.toRadians(90))+0.5);
			Point2D p2 = new Point2D.Double(r*Math.cos(Math.toRadians(-150))+0.5,
											r*Math.sin(Math.toRadians(-150))+0.5);
			Point2D p3 = new Point2D.Double(r*Math.cos(Math.toRadians(-30))+0.5,
											r*Math.sin(Math.toRadians(-30))+0.5);
			drawSierpinskiTriangle(p1, p2, p3, max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Sp Carpet")) {
			drawSierpinskiCarpet(new Point2D.Double(0, 0), 1, max_recursion_level);
		} else {
			drawMyFractal(new Point2D.Double(0.0, 0.4), new Point2D.Double(1.0, 0.4), max_recursion_level);
		}
	}
	/** Draw a straight line between two points P1 and P2.
	 * The given x and y values of p1 and p2 are assumed to be within [0, 1] (i.e. normalized).
	 * This allows our fractals to be resolution-independent. The method below converts the normalized
	 * coordinates to integer coordinates based on the actual image resolution. 
	 */
	private void drawLine(Point2D p1, Point2D p2) {
		int x1 = (int)(p1.getX()*width);
		int x2 = (int)(p2.getX()*width);
		// flip the Y coordinate
		// because screen Y axis is flipped from mathematical Y axis
		int y1 = (int)((1.0-p1.getY())*height);
		int y2 = (int)((1.0-p2.getY())*height);
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	// Draw a solid rectangle given its lower left corner and its size
	private void drawRectangle(Point2D p, double w, double h) {
		int x0 = (int)(p.getX()*width);
		int y0 = (int)((1.0-p.getY())*height);
		int x1 = (int)((p.getX()+w)*width);;
		int y1 = (int)((1.0-(p.getY()+h))*height);
		int xpoints[] = {x0, x0, x1, x1};
		int ypoints[] = {y0, y1, y1, y0};
		g2d.fillPolygon(xpoints, ypoints, 4);
	}
	
	// Draw a solid triangle given its three vertices
	private void drawTriangle(Point2D p1, Point2D p2, Point2D p3) {
		int xpoints[] = {(int)(p1.getX()*width),
						 (int)(p2.getX()*width),
						 (int)(p3.getX()*width)};
		int ypoints[] = {(int)((1.0-p1.getY())*height),
						 (int)((1.0-p2.getY())*height),
						 (int)((1.0-p3.getY())*height)};
		g2d.fillPolygon(xpoints, ypoints, 3);
	}
}
