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
class Mirror extends Optical {

    PVector _center = new PVector();
    PVector _start = new PVector();
    PVector _end = new PVector();
    float _angle;
    float _length = 100;

    Mirror(float x, float y, float a, final Reflection ctx) {
        super(ctx);
        _center = new PVector(x, y);
        _angle = Reflection.radians(a);
        _start = PVector.mult(PVector.fromAngle(_angle), _length);
        _end = PVector.mult(PVector.fromAngle(Reflection.PI + _angle), _length);
    }

    float getAngle() {
        return getDirection().heading2D();
    }

    void incAngle() {
        _start.rotate(0.05f);
        _end.rotate(0.05f);
    }

    PVector getDirection() {
        return _start.normalize(null);
    }

    PVector getEnd() {
        return PVector.add(_center, _end);
    }

    PVector getStart() {
        return PVector.add(_center, _start);
    }

    @Override
    public void draw() {
        _ctx.stroke(222);

        PVector a = PVector.add(_center, _start);
        PVector b = PVector.add(_center, _end);

        _ctx.line(a.x, a.y, b.x, b.y);
    }
}
