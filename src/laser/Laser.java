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

    Laser(float x, float y, float a, Reflection ctx) {
        super(ctx);
        _angle = a;
        _origin = new PVector(x, y);
    }

    Ray createRay() {
        PVector a = _origin;
        PVector b = new PVector(a.x + Reflection.cos(_angle) * _ctx.width * 2, a.y - Reflection.sin(_angle) * _ctx.height * 2);
        return new Ray(a, b, _ctx);
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
