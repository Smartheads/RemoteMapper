package remotemapper.classes;

import java.util.ArrayList;
import remoteMapper.classes.Map;

/*
 *  Node.java
 *
 *  Version: 1.2J
 *  @author Robert Hutter
 *
*/

public class Node
{
	private Node parent;
	private int x, y, f, g, cost = 1;
	
	public Node (int x, int y)
	{
		this.x = x;
		this.y = y;
		this.g = this.cost;
	}
		
	public Node(int x, int y, int cost)
	{
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.g = this.cost;
	}
	
	public boolean equals (Node o)
	{
		if (o.getX() == this.x && o.getY() == this.y)
			return true;
		else
			return false;
	}

	public static Node getLowestF (ArrayList<Node> list)
	{
		Node i = list.get(0);
		for (Node n : list)
		{
			if (n.getF() < i.getF())
				i = n;
		}
		
		return i;
	}
	
	public static Node[] constructPath (Node goal)
	{
		Node n = goal;
		ArrayList<Node> path = new ArrayList<>();
		path.add(n);
		
		while (n.getParent() != null)
		{
			n = n.getParent();
			path.add (n);
		}
		
		return path.toArray(new Node[path.size()]);
	}
	
	/*
	 * returns index of node in list. (see params) Returns -1 if node not contained within list.
	 */
	public static int containsNodeIndex (ArrayList<Node> list, Node node)
	{
		for (Node n : list)
		{
			if (node.equals(n))
				return list.indexOf(n);
			
		}
		return -1;
	}
	
	public static boolean containsNode (ArrayList<Node> list, Node node)
	{
		if (containsNodeIndex (list, node) != -1)
			return true;
		else
			return false;
	}
	
	/*
	 *  Return Node array of neighbors,that have the value of a specific character on a map.
	 */
	public static Node[] neighbors(Node center, Map map, char ch)
	{
		ArrayList<Node> neighbors = new ArrayList<>();
		char[][] m = map.getMap();
		
		/* XXX
		 * XXX
		 * XOX
		 */
		if (m.length > center.getY() - 1)
		{
			if (m[center.getY()][center.getX()-1] == ch)
				neighbors.add(new Node(center.getX(), center.getY() + 1));
		}
		
		/* XXX
		 * XXO
		 * XXX
		 */
		if (m[0].length - 1 > center.getX() - 1)
		{
			if (m[center.getY()-1][center.getX()] == ch)
				neighbors.add(new Node(center.getX()+1, center.getY()));
		}
		
		/* XXX
		 * OXX
		 * XXX
		 */
		if (center.getX() - 1 > 0)
		{
			if (m[center.getY()-1][center.getX()-2] == ch)
				neighbors.add(new Node (center.getX()-1, center.getY()));
		}
		
		/* XOX
		 * XXX
		 * XXX
		 */
		if (center.getY() - 1 > 0)
		{
			if (m[center.getY()-2][center.getX()-1] == ch)
				neighbors.add(new Node (center.getX(), center.getY() - 1));
		}
		
		neighbors.forEach(node -> node.setParent(center));
		
		return neighbors.toArray(new Node[neighbors.size()]);
	}
	
	/*
	 *  Estimating the heuristic of the node with the Manhattan method.
	 */
	public int heuristic (Node goal)
	{
		return Math.abs(x - goal.getX()) + Math.abs(y - goal.getY());
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}