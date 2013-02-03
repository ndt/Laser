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
public class Laser extends Optical {

    PVector _origin;
    float _angle;

    /**
     * 
     * @param x
     * @param y
     * @param a
     * @param ctx 
     */
    Laser(float x, float y, float a, Reflection ctx) {
        super(ctx);
        _angle = a;
        _origin = new PVector(x, y);
    }

    /**
     * 
     * @return 
     */
    Ray createRay() {
        PVector a = _origin;
        PVector b = new PVector(a.x + (float) Math.cos(_angle) * _ctx.width * 2, a.y - (float) Math.sin(_angle) * _ctx.height * 2);
        return new Ray(a, b, _ctx);
    }

    /**
     * 
     */
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
