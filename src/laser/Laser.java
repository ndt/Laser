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

    PVector _origin;
    float _angle;
    private final Reflection _ctx;

    Laser(float x, float y, float a, Reflection ctx) {
        _ctx = ctx;
        _angle = a;
        _origin = new PVector(x, y);
    }

    Ray createRay() {
        PVector a = _origin;
        PVector b = new PVector(a.x + Reflection.cos(_angle) * _ctx.width * 2, a.y - Reflection.sin(_angle) * _ctx.height * 2);
        return new Ray(a, b, _ctx);
    }
}
