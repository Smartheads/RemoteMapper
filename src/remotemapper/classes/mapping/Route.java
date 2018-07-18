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

import remotemapper.classes.Vector;

/**
 * Defines a route on a map.
 * 
 * @author Robert Hutter
 */
public class Route
{
    private final CharMap map;
    private final Node[] nodes;
    private final Vector[] path;
    
    /**
     * Creates a new Route object.
     * 
     * @param map CharMap to display route on
     * @param path Path of nodes
     * @param routeMark Char to mark the route on the map with
     */
    public Route (CharMap map, Node[] path, char routeMark)
    {
        this.map = new CharMap (map);
        this.nodes = path;
        
        for (Node n : path)
        {
            this.map.setPoint(n.getX(), n.getY(), routeMark);
        }
        
        this.path = new Vector[path.length - 1];
        
        for (int i = 0; i < path.length - 1; i++)
        {
            this.path[i] = new Vector (path[i].getX() - path[i+1].getX(),
                                      path[i].getY() - path[i+1].getY());
        }
    }
    
    /**
     * 
     * @return 
     */
    public double getDisplacement ()
    {
        return Vector.addition(path).getLength();
    }
    
    /**
     * 
     * @return 
     */
    public double getDistance ()
    {       
        double d = 0.0;
        for (Vector v : path)
        {
            d += v.getLength();
        }
        return d;
    }

    /**
     *
     * @return
     */
    public CharMap getMap() {
        return map;
    }

    /**
     *
     * @return
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     *
     * @return
     */
    public Vector[] getPath() {
        return path;
    }
}