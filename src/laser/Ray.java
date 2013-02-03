/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

import processing.core.PVector;

/**
 *
 * @author Helge Wiethoff
 * @author Nicolas Nieswandt
 */
class Ray extends Optical {

    PVector _start = new PVector();
    PVector _end = new PVector();

    Ray(float x, float y, float a, final Reflection ctx) {
        super(ctx);
        _start = new PVector(x, y);
        float _angle = Reflection.radians(a);
        _end = new PVector(_start.x + Reflection.cos(_angle) * ctx.width * 2, _start.y - Reflection.sin(_angle) * ctx.height * 2);
    }

    Ray(PVector v, float a, final Reflection ctx) {
        this(v.x, v.y, a, ctx);
    }

    Ray(PVector a, PVector b, final Reflection ctx) {
        super(ctx);
        _start = a;
        _end = b;
    }

    float getAngle() {
        return getDirection().heading2D();
    }

    PVector getDirection() {
        return PVector.sub(_end, _start);
    }

    PVector getEnd() {
        return _end;
    }

    PVector getStart() {
        return _start;
    }

    void setEnd(PVector e) {
        _end = e;
    }

    @Override
    public void draw() {
        _ctx.stroke(255, 0, 0);
        _ctx.line(_start.x, _start.y, _end.x, _end.y);
    }
}
