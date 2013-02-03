/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

import processing.core.*;

/**
 *
 * @author Helge Wiethoff
 * @author Nicolas Nieswandt
 */
public class Reflection extends PApplet {

    int SCREEN_WIDTH = 500;
    int SCREEN_HIGHT = 500;
    float MAX_LENGTH;
    boolean circleOver = false;
    ObjectFactory of;
    Laser laser;
    Mirror mirror1;
    Mirror mirror2;
    float angle = -40;
    Button button;

    /**
     *
     */
    @Override
    public void setup() {
        size(SCREEN_WIDTH, SCREEN_HIGHT);
        MAX_LENGTH = sqrt(sq(SCREEN_WIDTH) + sq(SCREEN_HIGHT));
        background(0);

        of = new ObjectFactory(this);
        laser = of.createLaser(80, 0, 100);
        mirror1 = of.createMirror(100, 400, -25);
        mirror2 = of.createMirror(380, 390, -120);
        button = of.createButton(100, 450, 10);
    }

    @Override
    public void draw() {
        Ray beam1;
        Ray beam2;
        Ray beam3;

        PVector r3;
        PVector r4;
        
        background(0);

        beam1 = laser.createRay();
        beam1.setEnd(detectRayHit(beam1, mirror1));

        r3 = calcMirrorDirection(beam1, mirror1);
        beam2 = of.createRay(beam1.getEnd(), r3);
        beam2.setEnd(detectRayHit(beam2, mirror2));

        r4 = calcMirrorDirection(beam2, mirror2);
        beam3 = of.createRay(beam2.getEnd(), r4);

        // List of optical objects
        mirror1.draw();
        mirror2.draw();

        // List of the calculated laser beam polygon
        beam1.draw();
        beam2.draw();
        beam3.draw();

        // List of UI elememnts
        button.draw();
    }

    static PVector detectRayHit(Ray r, Mirror m) {
        return VectorUtil.calcIntersection(r.getStart(), r.getDirection(), m.getStart(), m.getDirection());
    }

    @Override
    public void mousePressed() {
        if (button.isPressed()) {
            mirror1.incAngle();
        }
    }

    private PVector calcMirrorDirection(Ray r, Mirror m) {
        float beta1 = r.getDirection().heading2D();
        float betaM = m.getDirection().heading2D();
        float beta2 = 2 * betaM - beta1;
        PVector a2 = PVector.fromAngle(beta2);
        text(degrees(beta1), 10, 10);
        text(degrees(betaM), 10, 20);
        text(degrees(beta2), 10, 30);
        PVector r3 = PVector.add(r.getEnd(), PVector.mult(a2, MAX_LENGTH));
        return r3;
    }
}
