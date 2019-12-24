package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import java.util.Queue;
import java.util.Stack;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.edgedata;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.nodedata;
import gui.Graph_GUI;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	private DGraph g; 

	public Graph_Algo(DGraph g) {
		this.g=new DGraph(g); 
	}
	@Override
	public void init(graph g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

		try {

			FileInputStream fileIn = new FileInputStream(file_name);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			DGraph graf=new DGraph((DGraph)obj);
			objectIn.close();


		} catch (Exception ex) {
			System.out.println("cant read from this file");

		}
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		try {

			FileOutputStream fileOut = new FileOutputStream(file_name);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this);
			objectOut.close();
			System.out.println("The graph  was succesfully written to a file");

		} catch (Exception ex) {
			System.out.println("Cant write this graph out");
		}

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub

		Queue <nodedata>q=new LinkedList<>(); 
        int count=0;
		//this.g.getHashnodes().get(1);  //check if there is a place like this
		q.add((nodedata) this.g.getHashnodes().get(1));
		while(!q.isEmpty()) {
			nodedata nodeq=new nodedata(q.peek());
			Collection<edge_data> nabers=this.g.getHashedges().get(nodeq.getKey()).values();
			Iterator<edge_data> I=nabers.iterator();
			while(I.hasNext()) {
				edgedata edge=(edgedata) I.next();
				if(edge.getDes().getTag()!=1) {
					q.add(edge.getDes());
				}
			}
			if(count!=0)
			q.peek().setTag(1);
			count=1;	
			q.poll();
		
		}
		Collection<node_data> nodesingraf=this.g.getV();
		Iterator<node_data> J=nodesingraf.iterator();
		while(J.hasNext()) {
			nodedata node=new nodedata((nodedata) J.next());
			if(node.getTag()==0)
				return false;
		}
		return true;	
		
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		Collection<node_data> c= this.g.getV();
		Iterator<node_data> I=c.iterator();
		while(I.hasNext()) {
			nodedata node=((nodedata) I.next());
			node.setWeight(Double.POSITIVE_INFINITY);
		}
		this.g.getHashnodes().get(src).setWeight(0);
		
		Queue <nodedata>q=new LinkedList<>(); 
		q.add((nodedata) this.g.getHashnodes().get(src));
		while(q.peek()!=this.g.getHashnodes().get(dest)) {    
			Collection<edge_data> edges=this.g.getHashedges().get(q.peek().getKey()).values();
			Iterator<edge_data> E=edges.iterator();
			while(E.hasNext()) {
				edgedata ed=(edgedata) E.next();
				double wed=ed.getDes().getWeight();
				
				if(q.peek().getWeight()+ed.getWeight()<wed) {
					
					ed.getDes().setWeight(q.peek().getWeight()+ed.getWeight());
					ed.getDes().setWay(Integer.toString(q.peek().getKey()));
					
				}
				q.add(ed.getDes());
			}	
			
			q.poll();
			
			
		}
		
		return this.g.getHashnodes().get(dest).getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		shortestPathDist(src, dest);
		List<node_data> shortt=new LinkedList<node_data>();
		nodedata move=(nodedata) this.g.getHashnodes().get(dest);
		while(move!=this.g.getHashnodes().get(src)) {
			shortt.add((nodedata) this.g.getHashnodes().get((Integer.valueOf(move.getKey()))));
			move=(nodedata) this.g.getHashnodes().get((Integer.valueOf(move.getWay())));
		}
		shortt.add((nodedata) this.g.getHashnodes().get(src));
		return shortt;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		
		
		
		
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		 DGraph returnn=new DGraph();
		 returnn.setHashnodes(new HashMap<Integer, node_data>(this.g.getHashnodes())) ;
		 returnn.setHashedges(new HashMap<Integer,HashMap<Integer,edge_data> >(this.g.getHashedges()));
		 returnn.setCountedgeg(this.g.getCountedgeg());
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);
		

		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
//		nodedata d=new nodedata(4,p4,0,0);
//		nodedata e=new nodedata(5,p5,0,0);
//		nodedata f=new nodedata(6,p6,0,0);
		
		
		HashMap<Integer, node_data> hashnodes=new HashMap<Integer, node_data> ();
		hashnodes.put(a.getKey(), a);
		hashnodes.put(b.getKey(), b);
		hashnodes.put(c.getKey(), c);
		//hashnodes.put(d.getKey(), d);
//		hashnodes.put(e.getKey(), e);
//		hashnodes.put(f.getKey(), f);
		
		edge_data aa=new edgedata(a,b,40,0);
		//edge_data bb=new edgedata(b,a,20,0);
		edge_data cc=new edgedata(b,c,20,0);
		edge_data dd=new edgedata(a,c,80,0);
		//edge_data eb=new edgedata(e,b,8,0);
		//edge_data fe=new edgedata(f,e,5,0);
		
		HashMap<Integer, edge_data>hash_a=new HashMap<Integer, edge_data>();
		HashMap<Integer, edge_data>hash_b=new HashMap<Integer, edge_data>();
		HashMap<Integer, edge_data>hash_c=new HashMap<Integer, edge_data>();
//		HashMap<Integer, edge_data>hash_d=new HashMap<Integer, edge_data>();
//		HashMap<Integer, edge_data>hash_e=new HashMap<Integer, edge_data>();
//		HashMap<Integer, edge_data>hash_f=new HashMap<Integer, edge_data>();
//		
		hash_a.put(aa.getDest(), aa);
		//hash_b.put(bb.getDest(), bb);
		hash_b.put(cc.getDest(), cc);
		hash_a.put(dd.getDest(), dd);
		//hash_e.put(eb.getDest(), eb);
		//hash_e.put(fe.getDest(), fe);
		
		
		DGraph x=new DGraph();
		HashMap<Integer, HashMap<Integer, edge_data>>hashedges=new HashMap<Integer, HashMap<Integer, edge_data>>();
		
		hashedges.put(a.getKey(), hash_a);
		hashedges.put(b.getKey(),hash_b);
		hashedges.put(c.getKey(),hash_c);
//		hashedges.put(d.getKey(),hash_d);
//		hashedges.put(e.getKey(),hash_e);
//		hashedges.put(f.getKey(),hash_f);
		x.setHashnodes(hashnodes);
		x.setHashedges(hashedges);
		
	
		Graph_GUI p=new Graph_GUI();
		p.drawFunctions(x);
		Graph_Algo test=new Graph_Algo(x);
		System.out.println(test.isConnected());
		System.out.println(test.shortestPathDist(1,3));
		List<node_data> shortt=new LinkedList<node_data>((test.shortestPath(1, 3)));
		Iterator I=shortt.iterator();
		while(I.hasNext()) {
			node_data noded=new nodedata ((nodedata) I.next());
			System.out.println(noded.getKey());
		}
		
	}


}
