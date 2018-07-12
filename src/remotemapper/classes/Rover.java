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
 * Class defining a rover.
 * 
 * @author rohu7
 */
public class Rover
{

    /**
     *
     */
    private int x;

    /**
     *
     */
    private int y;

    /**
     * Rover width in mm
     */
    private int width;
    
    /**
     * Rover length in mm
     */
    private int length;

    /**
     * Rover height in mm
     */
    private int height;

    /**
     * Rover diagonal between width and length
     */
    private double flatDiagonal;

    /**
     * Rover diagonal between width, length and height
     */
    private double diagonal;

    /**
     * Heading the rover is facing in. 0° = up
     */
    private Angle direction;
    
    /**
     *
     * @param x
     * @param y
     * @param dir
     * @param width
     * @param length
     */
    public Rover (int x, int y, float dir, int width, int length)
    {
        this(x, y, dir, width, length, 0);
    }
    
    /**
     *
     * @param x
     * @param y
     * @param dir
     * @param width
     * @param length
     * @param height
     */
    public Rover (int x, int y, float dir, int width, int length, int height)
    {
        this.x = x;
        this.y = y;
        this.direction = new Angle (dir);
        this.width = width;
        this.length = length;
        this.height = height;
        updateDiagonals();
    }
    
    private void updateDiagonals()
    {
        flatDiagonal = Math.sqrt(width*width + length*length);
        diagonal = Math.sqrt(width*width + length*length + height*height);
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
     * @return
     */
    public int getY() {
        return y;
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
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
        updateDiagonals();
    }

    /**
     *
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
        updateDiagonals();
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
        updateDiagonals();
    }
    
    /**
     *
     * @param dir
     */
    public void setDirection (float dir)
    {
        this.direction.setAngel(dir);
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }
    
    /**
     *
     * @return
     */
    public float getDirection ()
    {
        return this.direction.size();
    }

    /**
     * Returns the rover's diagonal between its width and length in mm.
     * 
     * @return 
     */
    public double getFlatDiagonal() {
        return flatDiagonal;
    }

    /**
     * Returns the rover's diagonal between its width, length and height in mm.
     * 
     * @return 
     */
    public double getDiagonal() {
        return diagonal;
    }
}