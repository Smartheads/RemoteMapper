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
package remotemapper;

import java.util.ArrayList;
import remoteMapper.classes.Map;
import remotemapper.classes.Node;

/**
 * Defines a route on a map.
 * 
 * @author Robert Hutter
 */
public class Route
{
    private final Map map;
    private final Node[] nodes;
    private final ArrayList<Vector> path;
    
    /**
     * Creates a new Route object.
     * 
     * @param map Map to display route on
     * @param path Path of nodes
     * @param routeMark Char to mark the route on the map with
     */
    public Route (Map map, Node[] path, char routeMark)
    {
        this.map = new Map (map);
        this.nodes = path;
        
        for (Node n : path)
        {
            this.map.setPoint(n.getX(), n.getY(), routeMark);
        }
        
        this.path = new ArrayList<>();
        
        for (int i = 0; i < path.length-1; i++)
        {
            this.path.add(new Vector (path[i].getX() - path[i+1].getX(),
                                      path[i].getY() - path[i+1].getY()));
        }
    }
    
    /**
     * 
     * @return 
     */
    public double getDisplacement ()
    {
        return Vector.addition(path.toArray(new Vector[path.size()])).getLength();
    }
    
    /**
     * 
     * @return 
     */
    public double getDistance ()
    {       
        double d = 0.0;
        d = path.stream().map((v) -> v.getLength()).reduce(d, (accumulator, _item) -> accumulator + _item);
        return d;
    }

    /**
     *
     * @return
     */
    public Map getMap() {
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
    public ArrayList<Vector> getPath() {
        return path;
    }
}