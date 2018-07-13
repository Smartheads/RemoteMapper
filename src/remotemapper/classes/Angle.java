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

/**
 * A geometric angle.
 * 
 * @author Robert Hutter
 */
public class Angle {
    private float size;
    private final int vollWinkel;
    
    /**
     *
     * @param size Initial size of the angle
     */
    public Angle (float size)
    {
        this(size, 360);
    }
    
    /**
     *
     * @param size Initial size of the angle
     * @param vollWinkel Max value of the angle. Default: 360
     */
    public Angle (float size, int vollWinkel)
    {
        this.vollWinkel = vollWinkel;
        setAngel (size);
    }
    
    /**
     * 
     * @param b Add b to the size of the angle
     */
    public void add (float b)
    {
        setAngel (this.size + b);
    }
    
    /**
     *
     * @param b Subtract b for the sie of the angle
     */
    public void subtract (float b)
    {
        setAngel (this.size - b);
    }
            
    /**
     *
     * @param size Set the size of the angle
     */
    public final void setAngel (float size)
    {
        if (size > 0)
        {
            this.size = vollWinkel % size;
        }
        else if (size < 0)
        {
            this.size = vollWinkel - (vollWinkel % Math.abs(size));
        }
        else
        {
            this.size = 0;
        }
    }
    
    /**
     * 
     * @return
     * 
     * The size of the angle
     */
    public float size ()
    {
        return size;
    }
}