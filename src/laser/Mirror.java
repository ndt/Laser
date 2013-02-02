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
class Mirror {
    PVector _center = new PVector();
    PVector _start = new PVector();
    PVector _end = new PVector();
    float _angle;
    float _length = 100;
    private final Reflection ctx;

    Mirror(float x, float y, float a, final Reflection ctx) {
        this.ctx = ctx;
        _center = new PVector(x, y);
        _angle = Reflection.radians(a);
        _end = new PVector(_center.x + Reflection.cos(_angle) * _length, _center.y - Reflection.sin(_angle) * _length);
        _start = new PVector(_center.x - Reflection.cos(_angle) * _length, _center.y + Reflection.sin(_angle) * _length);
    }
    
    float getAngle() {
        return _angle;
    }

    void incAngle() {
        _angle = (float) (_angle + 0.01);
        _end = new PVector(_center.x + Reflection.cos(_angle) * _length, _center.y - Reflection.sin(_angle) * _length);
        _start = new PVector(_center.x - Reflection.cos(_angle) * _length, _center.y + Reflection.sin(_angle) * _length);
    }

    PVector getEnd() {
        return new PVector(_center.x + Reflection.cos(_angle) * _length, _center.y - Reflection.sin(_angle) * _length);
    }

    PVector getOrigin() {
        return new PVector(_center.x - Reflection.cos(_angle) * _length, _center.y + Reflection.sin(_angle) * _length);
    }

    void drawMirror() {
        ctx.stroke(222);
        ctx.line(_start.x, _start.y, _end.x, _end.y);
    }
    
}
