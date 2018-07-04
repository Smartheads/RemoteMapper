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

import remotemapper.classes.Angle;

/**
 * A class defining a mathematical vector.
 * 
 * @author Robert Hutter
 */
public class Vector
{
    private double x;
    private double y;
    private double length;

    /**
     *
     * @param x
     * @param y
     */
    public Vector (double x, double y)
    {
        this.x = x;
        this.y = y;
        updateLength();
    }

    /**
     *
     * @param direction
     * @param length
     */
    public Vector (Angle direction, int length)
    {
        this.length = length;
        this.x = length * Math.cos(Math.toRadians(90-direction.size()));
        this.y = length * Math.cos(Math.toRadians(direction.size()));
    }
    
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector addition(Vector a, Vector b)
    {        
        return new Vector(a.getX()+b.getX(), a.getY()+b.getY());
    }
    
    /**
     * 
     * @param vecs
     * @return 
     */
    public static Vector addition(Vector[] vecs)
    {
        Vector v = new Vector (0, 0);
        for (Vector e : vecs)
        {
            v = v.add(e);
        }
        
        return v;
    }
    
    /**
     * 
     * @param b
     * @return 
     */
    public Vector add(Vector b)
    {
        return addition(this, b);
    }
    
    private void updateLength()
    {
        length = Math.sqrt(x*x+y*y);
    }

    /**
     *
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
        updateLength();
    }

    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
        updateLength();
    }
}
