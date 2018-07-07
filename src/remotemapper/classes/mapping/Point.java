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

/**
 * Class defining a point.
 * 
 * @author Robert Hutter
 */
public class Point
{

    /**
     * Point's x value
     */
    protected int x;

    /**
     * Points's y value
     */
    protected int y;
    private char val;
    
    /**
     * Creates a new Point 
     * 
     * @param x the points x coordinate
     * @param y the point y coordinate
     * @param val the points character value
     */
    public Point (int x, int y, char val)
    {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    /**
     * Creates a new Point
     * 
     * @param x the points x coordinate
     * @param y the points y coordinate
     */
    public Point (int x, int y)
    {
        this(x, y, '0');
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
    public char getVal() {
        return val;
    }

    /**
     *
     * @param val
     */
    public void setVal(char val) {
        this.val = val;
    }
    
}
