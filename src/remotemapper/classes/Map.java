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

package remoteMapper.classes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import remotemapper.classes.Node;

/**
 *  Map.java - Java file defining a map.
 *  Version: 1.2J
 * 
 *  @author Robert Hutter
 *  @date 2018.06.06
 */
public final class Map {

    /**
     *
     */
    protected char[][] map;

    /**
     *
     */
    protected final char obsticalMark;

    /**
     *
     */
    protected final char emptySpaceMark;
	
    /**
     * Create a new empty map.
     * 
     * @param xDim xDim int width of map
     * @param yDim yDim int height of map
     * @param obM
     * @param esM
     */
    public Map (int xDim, int yDim, char obM, char esM)
    {
            map = new char[yDim][xDim + 1];

            obsticalMark = obM;
            emptySpaceMark = esM;

            for (int y = 0; y < yDim; y++)
            {
                    for (int x = 0; x < xDim; x++)
                    {
                            map[y][x] = emptySpaceMark;
                    }
                    map[y][xDim] = 10;
            }
    }

    /**
     * Create a map object out of another map object.
     * Used for copying maps.
     * 
     * @param preloaded
     */
    public Map (Map preloaded)
    {
            this.map = preloaded.getMap();
            this.obsticalMark = preloaded.getObsticalMark();
            this.emptySpaceMark = preloaded.getEmptySpaceMark();
    }
	
    /**
     * Create a map object outof a file containing a map
     *
     * @param mapfile
     * @throws IOException
     */
    public Map (File mapfile) throws IOException
    {
            Map m = parseMapfile(mapfile);
            this.map = m.getMap();
            this.obsticalMark = m.getObsticalMark();
            this.emptySpaceMark = m.getEmptySpaceMark();
    }

    /**
     * Create a new Map out of raw data.
     * 
     * @param raw
     * @param obM
     * @param esM
     */
    public Map (char[][] raw, char obM, char esM)
    {
        this.map = raw;
        this.obsticalMark = obM;
        this.emptySpaceMark = esM;
    }
	
    /**
     * Pathfind using the A* search algorithm. Returns null if no path found.
     * 
     * @param start
     * @param goal
     * @return
     */
    public Node[] Astar (Node start, Node goal)
	{
            ArrayList<Node> openList = new ArrayList<>();
            ArrayList<Node> closedList = new ArrayList<>();

            start.setG(0);
            start.setF(start.getG() + start.heuristic(goal));
            openList.add(start);

            while (!openList.isEmpty())
            {
                Node current = Node.getLowestF(openList);
                if (current.equals(goal))
                        return Node.constructPath (current);

                openList.remove(current);
                closedList.add(current);

                for (Node n : Node.neighbors(current, this, this.emptySpaceMark))
                {
                    if (!Node.containsNode (closedList, n))
                    {
                        n.setF(n.getG() + n.heuristic(goal));

                        if (!Node.containsNode(openList, n))
                            openList.add(n);
                        else
                        {
                            Node openNeighbor = openList.get(Node.containsNodeIndex(openList, n));
                            if (n.getG() < openNeighbor.getG())
                            {
                                    openNeighbor.setG(n.getG());
                                    openNeighbor.setParent(n.getParent());
                            }
                        }
                    }
                }
            }

            return null;
	}
	
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param length
     * @param val
     */
    public void setPointRectangle (int x, int y, int width, int length, char val)
	{
		for (int y2 = y; y2 < y + length; y2++)
		{
			for (int x2 = x; x2 < x + width; x2++)
			{
				this.setPoint(y2, x2, val);
			}
		}
	}
	
    /**
     *
     * @param x
     * @param y
     * @param val
     */
    public void setPoint (int x, int y, char val)
	{
		map[y-1][x-1] = val;
	}
	
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public char getPoint (int x, int y)
	{
		return map[y-1][x-1];
	}

    /**
     * Save the contents of a map to a specified destination.
     * 
     * @param map
     * @param dest
     * @throws IOException
     */
    public static void exportToFile(Map map, File dest) throws IOException
    {
            final char[][] d = map.getMap();
            FileWriter out = new FileWriter(dest, false);

            // Write markers
            out.write (String.valueOf(map.obsticalMark) + "\n");
            out.write (String.valueOf(map.emptySpaceMark) + "\n");

    // Write map raw data
    for (char[] d1 : d) {
        for (int x = 0; x < d1.length; x++) {
            out.write(d1[x]);
        }
    }

            out.flush();
            out.close();
    }
	
    /**
     * A method for simplifying a map with a given length.
     * 
     * @param in
     * @param w
     * @return
     */
    public static Map simplfyMap (Map in, int w)
    {
        Map s = new Map ((int) in.getMap()[0].length / w, (int) in.getMap().length / w, in.getObsticalMark(), in.getEmptySpaceMark());

        int rY = in.getMap().length % w;
        int rX = in.getMap()[0].length % w;

        for (int y = 0; y < in.getMap().length - rY; y += w)
        {
            for (int x = 0; x < (int) in.getMap()[0].length - rX; x += w)
            {
                char[] b = new char[w*w];
                for (int y2 = 0; y2 < w; y2++)
                {
                    for (int x2 = 0; x2 < w; x2++)
                    {
                        b[x2 + w * y2] = in.getMap()[y2 + y][x2 + x];
                    }
                }

                if (new String (b).contains(Character.toString( (char) 49)))
                {
                     s.setPoint(x / w + 1, y / w + 1, '1');
                }
            }
        }

        return s;
    }

    /**
     * A method for extracting a map out of a file.
     * 
     * @param mapFile
     * @return
     * @throws IOException
     */
    protected Map parseMapfile(File mapFile) throws IOException
    {
        FileReader in = new FileReader(mapFile);
        int c, y = 0;

        // Buffer map
        ArrayList<ArrayList<Character>> b = new ArrayList<>();
        b.add(new ArrayList<>());

        char obM = (char) in.read();
        in.skip(1);
        char esM = (char) in.read();
        in.skip(1);


        while ((c = in.read()) != -1)
        {
            if (c == '\n')
            {
                    y++;
                    b.add(new ArrayList<>());
            }
            else
            {
                    b.get(y).add((char) c);
            }
        }

        in.close();

        int capX = b.get(0).size() + 1;
        int capY = b.size() -1;

        char[][] m = new char[capY][capX];

        for (y = 0; y < capY; y++)
        {
            for (int x = 0; x < capX - 1; x++)
            {
                    m[y][x] = b.get(y).get(x);
            }
            m[y][capX - 1] = 10;
        }

        return new Map (m, obM, esM);
    }
	
    /**
     *
     * @return
     */
    public char[][] getMap() {
		return map;
	}
	
    /**
     *
     * @param map
     */
    public void setMap(char[][] map) {
		this.map = map;
	}
	
    /**
     *
     * @return
     */
    public int getWidth ()
	{
		return this.map[0].length;
	}
	
    /**
     *
     * @return
     */
    public int getLength ()
	{
		return this.map.length;
	}

    /**
     *
     * @return
     */
    public char getObsticalMark() {
            return obsticalMark;
        }

    /**
     *
     * @return
     */
    public char getEmptySpaceMark() {
            return emptySpaceMark;
        }
}