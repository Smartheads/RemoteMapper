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
public class Angle {
    protected float size;
    protected final int vollWinkel;
    
    public Angle (float size)
    {
        this.vollWinkel = 360;
        setAngel (size);
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
