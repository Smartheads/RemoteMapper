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
public class Rover
{
    protected int x, y, width, length, height;
    protected Angle direction;
    
    public Rover (int x, int y, float dir, int width, int length)
    {
        this.x = x;
        this.y = y;
        this.direction = new Angle (dir);
        this.width = width;
        this.length = length;
    }
    
    public Rover (int x, int y, float dir, int width, int length, int height)
    {
        this.x = x;
        this.y = y;
        this.direction = new Angle (dir);
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setDirection (float dir)
    {
        this.direction.setAngel(dir);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }
    
    public float getDirection ()
    {
        return this.direction.size;
    }
}