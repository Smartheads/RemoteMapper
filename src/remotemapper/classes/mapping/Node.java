/*
 * Copyright (C) 2018 Robert Hutter
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package remotemapper.classes.mapping;

import java.util.ArrayList;

/**
 *  Node.java
 *
 *  @version 1.4J
 *  @author Robert Hutter
 *  @date 2018.06.06
 *      updated: 2018.07.04
 * 
*/
public class Node extends Point
{
    protected Node parent;
    protected double f;
    protected double g;
    protected double cost = 1;

    /**
     * Constructor for creating a new Node.
     * 
     * @param x Position x
     * @param y Position y
     */
    public Node (int x, int y)
    {
        super(x, y);
        this.g = this.cost;
    }

    /**
     * Constructor for creating a new Node.
     * 
     * @param x Position x
     * @param y Position y
     * @param cost Cost to traverse node
     */
    public Node(int x, int y, double cost)
    {
        super(x, y);
        this.cost = cost;
        this.g = cost;
    }

    /**
     * Get the node with the lowest F from a collection.
     * 
     * @param list Collection of nodes to check
     * @return
     */
    public static Node getLowestF (Node[] list)
    {
        Node i = list[0];
        for (Node n : list)
        {
            if (n.getF() < i.getF())
            {
                i = n;
            }
        }

        return i;
    }

    /**
     * Construct a path after using A*
     * 
     * @param goal The last node in a pathfind algorithm
     * @return
     */
    public static Node[] constructPath (Node goal)
    {
        Node n = goal;
        ArrayList<Node> path = new ArrayList<>();
        path.add(n);

        while (n.getParent() != null)
        {
            path.add (n);
            n = n.getParent();
        }

        return path.toArray(new Node[path.size()]);
    }

    /**
     * Returns index of node in list. (see @param) Returns -1 if node not contained within list.
     * 
     * @param list Collection of Nodes
     * @param node Node to look for
     * @return 
     */
    public static int containsNodeIndex (Node[] list, Node node)
    {
        for (int i = 0; i < list.length; i++)
        {
            if (node.equals(list[i]))
            {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Check to see if a collection contains a node or not.
     * 
     * @param list Collection of Nodes
     * @param node Node to look for
     * @return
     */
    public static boolean containsNode (Node[] list, Node node)
    {
        return containsNodeIndex (list, node) != -1;
    }

    /**
     *  Return Node array of neighbors,that have the value of a specific character on a map.
     * 
    * @param center
    * @param map
    * @param ch
    * @return 
     */
    public static Node[] neighbors(Node center, CharMap map, char ch)
    {
        ArrayList<Node> neighbors = new ArrayList<>();

        /* XXX
         * XXX
         * XOX
         */
        if (map.getLength() > center.getY() + 1)
        {
            if (map.getPoint(center.getX(), center.getY() + 1) == ch)
            {
                neighbors.add(new Node(center.getX(), center.getY() + 1));
            }
        }

        /* XXX
         * XXO
         * XXX
         */
        if (map.getLength() > center.getX() + 1)
        {
            if (map.getPoint(center.getX() + 1, center.getY()) == ch)
            {
                neighbors.add(new Node(center.getX() + 1, center.getY()));
            }
        }

        /* XXX
         * OXX
         * XXX
         */
        if (center.getX() - 1 > 0)
        {
            if (map.getPoint(center.getX() - 1, center.getX()) == ch)
            {
                neighbors.add(new Node (center.getX() - 1, center.getY()));
            }
        }

        /* XOX
         * XXX
         * XXX
         */
        if (center.getY() - 1 > 0)
        {
            if (map.getPoint(center.getX(), center.getY() - 1) == ch)
            {
                neighbors.add(new Node (center.getX(), center.getY() - 1));
            }
        }

        neighbors.forEach(node -> node.setParent(center));

        return neighbors.toArray(new Node[neighbors.size()]);
    }
    
    /**
     * Compare two nodes
     * 
     * @param o Node to compare
     * @return
     */
    public boolean equals (Node o)
    {
        return (o.getX() == this.x) && (o.getY() == this.y);
    }

    /**
     * Estimating the heuristic of the node with the Manhattan method.
     * 
     * @param goal
     * @return
     */
    public int heuristic (Node goal)
    {
            return Math.abs(x - goal.getX()) + Math.abs(y - goal.getY());
    }

    /**
     *
     * @return
     */
    public Node getParent() {
            return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(Node parent) {
            this.parent = parent;
    }

    /**
     *
     * @return
     */
    public double getF() {
            return f;
    }

    /**
     *
     * @param f
     */
    public void setF(double f) {
            this.f = f;
    }

    /**
     *
     * @return
     */
    public double getG() {
            return g;
    }

    /**
     *
     * @param g
     */
    public void setG(double g) {
            this.g = g;
    }

    /**
     *
     * @return
     */
    public double getCost() {
            return cost;
    }

    /**
     *
     * @param cost
     */
    public void setCost(int cost) {
            this.cost = cost;
    }
}