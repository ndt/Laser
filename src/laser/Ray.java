/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

import processing.core.PVector;

/**
 *
 * @author Nicolas Nieswandt
 */
class Ray extends Optical {

    PVector _start = new PVector();
    PVector _end = new PVector();

    /**
     * 
     * @param x
     * @param y
     * @param a
     * @param ctx 
     */
    Ray(float x, float y, float a, final Reflection ctx) {
        super(ctx);
        _start = new PVector(x, y);
        float _angle = Reflection.radians(a);
        _end = new PVector(_start.x + Reflection.cos(_angle) * ctx.width * 2, _start.y - Reflection.sin(_angle) * ctx.height * 2);
    }

    /**
     * 
     * @param v
     * @param a
     * @param ctx 
     */
    Ray(PVector v, float a, final Reflection ctx) {
        this(v.x, v.y, a, ctx);
    }

    /**
     * 
     * @param a
     * @param b
     * @param ctx 
     */
    Ray(PVector a, PVector b, final Reflection ctx) {
        super(ctx);
        _start = a;
        _end = b;
    }

    /**
     * 
     * @return 
     */
    float getAngle() {
        return getDirection().heading2D();
    }

    /**
     * 
     * @return 
     */
    PVector getDirection() {
        return PVector.sub(_end, _start);
    }

    /**
     * 
     * @return 
     */
    PVector getEnd() {
        return _end;
    }

    /**
     * 
     * @return 
     */
    PVector getStart() {
        return _start;
    }

    /**
     * 
     * @param e 
     */
    void setEnd(PVector e) {
        _end = e;
    }

    /**
     * 
     */
    @Override
    public void draw() {
        _ctx.stroke(255, 0, 0);
        _ctx.line(_start.x, _start.y, _end.x, _end.y);
    }
}
