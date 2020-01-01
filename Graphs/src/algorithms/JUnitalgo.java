package algorithms;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.nodedata;
import gui.GraphGUIstddraw;
import utils.Point3D;

class JUnitalgo {

	@Test
	void testconnect() {
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);
		

		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);
		
		DGraph x=new DGraph();
		
		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);
		
		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);
		
		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);
		x.connect(d.getKey(), f.getKey(), 40);
		x.connect(e.getKey(), c.getKey(), 50);
		x.connect(c.getKey(), b.getKey(), 5);
		Graph_Algo test=new Graph_Algo(x);
		boolean before=test.isConnected();
		x.removeEdge(2,4);
		boolean after=test.isConnected();
		if(before==after)
		fail("not supose to be the same");
	}
	@Test
	void testinitsave() {
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);
		

		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);
		
		DGraph x=new DGraph();
		
		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);
		
		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);
		
		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);
		x.connect(d.getKey(), f.getKey(), 40);
		x.connect(e.getKey(), c.getKey(), 50);
		x.connect(c.getKey(), b.getKey(), 5);
		Graph_Algo test=new Graph_Algo(x);
		try {
		test.save("testout");
		
		}
		catch(Exception e1) {
			fail();
		}
		try {
			Graph_Algo testin=new Graph_Algo();
			testin.init("testout");
			GraphGUIstddraw draw=new GraphGUIstddraw();
			draw.drawFunctions(testin.getG());
		}
		catch(Exception e1) {
			fail();
		}
	
	}

}
