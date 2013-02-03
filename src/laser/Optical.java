/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

/**
 *
 * @author NIESWANDT
 */
public abstract class Optical {
    protected final Reflection _ctx;
    
    Optical(Reflection ctx) {
        _ctx = ctx;
    }

    public abstract void draw();
    
}
