package laser;

import processing.core.PVector;

/**
 *
 * @author nico
 */


public class Vector extends Optical {
    
    PVector _v;
    
    Vector(float x, float y, Reflection ctx) {
        super(ctx);
        _v = new PVector(x, y);
    }
    
    @Override
    public void draw() {
        drawAt(0,0);
    }
    
    public void drawAt(float x, float y) {
        PVector p1 = new PVector(x,y);
        PVector p2 = PVector.add(p1, new PVector(_v.x,_v.y));
        PVector n = p2.get();
        n.normalize();
        n.mult(-10);
        n.rotate(-Reflection.PI/16);
        PVector p3 = PVector.add(p2, n);
        n.rotate(Reflection.PI/8);
        PVector p4 = PVector.add(p2, n);
        _ctx.line(p1.x, p1.y, p2.x, p2.y);
        _ctx.triangle(p3.x, p3.y, p4.x, p4.y, p2.x, p2.y);
    }
    
    public void drawAt(PVector p) {
        drawAt(p.x,p.y);
    }
}
