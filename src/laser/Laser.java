/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

import processing.core.PVector;

/**
 *
 * @author NIESWANDT
 */
public class Laser {

    PVector _pos = new PVector();
    float _angle;
    private final Reflection ctx;

    Laser(float x, float y, float a, final Reflection ctx) {
        this.ctx = ctx;
        _pos = new PVector(x, y);
        _angle = Reflection.radians(a);
    }
    
    public PVector getEnd() {
        return _pos;
    }
}
