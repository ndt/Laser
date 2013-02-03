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

    /**
     *
     */
    PVector _a = new PVector();
    PVector _b = new PVector();

    /**
     *
     * @param x
     * @param y
     * @param theta
     * @param ctx
     */
    Ray(float x, float y, float theta, final Reflection ctx) {
        super(ctx);
        _a = new PVector(x, y);
        _b = new PVector(
                _a.x + (float) Math.cos(Math.toRadians(theta)) * ctx.width * 2,
                _a.y - (float) Math.sin(Math.toRadians(theta)) * ctx.height * 2);
    }

    /**
     *
     * @param v
     * @param theta
     * @param ctx
     */
    Ray(PVector v, float theta, final Reflection ctx) {
        this(v.x, v.y, theta, ctx);
    }

    /**
     *
     * @param a
     * @param b
     * @param ctx
     */
    Ray(PVector a, PVector b, final Reflection ctx) {
        super(ctx);
        _a = a;
        _b = b;
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
        return PVector.sub(_b, _a);
    }

    /**
     *
     * @return
     */
    PVector getEnd() {
        return _b;
    }

    /**
     *
     * @return
     */
    PVector getStart() {
        return _a;
    }

    /**
     *
     * @param e
     */
    void setEnd(PVector e) {
        _b = e;
    }

    /**
     *
     */
    @Override
    public void draw() {
        _ctx.stroke(255, 0, 0);
        _ctx.line(_a.x, _a.y, _b.x, _b.y);
    }
}
