/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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