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
 *
 * @author rohu7
 */
public class Angle {
    private float size;
    private final int vollWinkel;
    
    public Angle (float size)
    {
        this(size, 360);
    }
    
    public Angle (float size, int vollWinkel)
    {
        this.vollWinkel = vollWinkel;
        setAngel (size);
    }
    
    public void add (float b)
    {
        setAngel (this.size + b);
    }
    
    public void subtract (float b)
    {
        setAngel (this.size - b);
    }
            
    public final void setAngel (float size)
    {
        if (size > 0)
            this.size = vollWinkel % size;
        else if (size < 0)
            this.size = vollWinkel - (vollWinkel % Math.abs(size));
        else
            this.size = 0;
    }
    
    public float size ()
    {
        return this.size;
    }
}
