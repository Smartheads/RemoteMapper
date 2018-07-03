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

package remotemapper.classes;

import java.util.ArrayList;
import remoteMapper.classes.Map;

/**
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

    /**
     * Constructor for creating a new Node.
     * 
     * @param x Position x
     * @param y Position y
     */
    public Node (int x, int y)
    {
            this.x = x;
            this.y = y;
            this.g = this.cost;
    }

    /**
     * Constructor for creating a new Node.
     * 
     * @param x Position x
     * @param y Position y
     * @param cost Cost to traverse node
     */
    public Node(int x, int y, int cost)
    {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.g = this.cost;
    }

    /**
     * Check to see if node is equal to Param
     * 
     * @param o Node to compare
     * @return
     */
    public boolean equals (Node o)
    {
        return o.getX() == this.x && o.getY() == this.y;
    }

    /**
     * Get the node with the lowest F from a collection.
     * 
     * @param list Collection of nodes to check
     * @return
     */
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
                    n = n.getParent();
                    path.add (n);
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
    public static int containsNodeIndex (ArrayList<Node> list, Node node)
    {
        for (Node n : list)
        {
            if (node.equals(n))
                return list.indexOf(n);

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
    public static boolean containsNode (ArrayList<Node> list, Node node)
    {
        return containsNodeIndex (list, node) != -1;
    }

    /**
     *  Return Node array of neighbors,that have the value of a specific character on a map.
    * @param center
    * @param map
    * @param ch
    * @return 
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
    public int getX() {
            return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
            this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
            return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
            this.y = y;
    }

    /**
     *
     * @return
     */
    public int getF() {
            return f;
    }

    /**
     *
     * @param f
     */
    public void setF(int f) {
            this.f = f;
    }

    /**
     *
     * @return
     */
    public int getG() {
            return g;
    }

    /**
     *
     * @param g
     */
    public void setG(int g) {
            this.g = g;
    }

    /**
     *
     * @return
     */
    public int getCost() {
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