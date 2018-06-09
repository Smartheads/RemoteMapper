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
    
    public Rover (int x, int y, int dir, int width, int length)
    {
        this.x = x;
        this.y = y;
        this.direction = new Angle (dir);
        this.width = width;
        this.length = length;
    }
    
    public Rover (int x, int y, int dir, int width, int length, int height)
    {
        this.x = x;
        this.y = y;
        this.direction = new Angle (dir);
        this.width = width;
        this.length = length;
        this.height = height;
    }
}