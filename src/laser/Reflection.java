package laser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        laser = createLaser(80, 0, 100);
        mirror1 = createMirror(100, 400, -25);
        mirror2 = createMirror(380, 390, -120);
        button = createButton(100, 450, 10);
    }

    @Override
    public void draw() {
        background(0);

        Ray first = laser.createRay();
        first.setEnd(detectRayHit(first, mirror1));

        PVector r3 = calcMirrorDirection(first, mirror1);
        Ray second = createRay(first.getEnd(), r3);
        second.setEnd(detectRayHit(second, mirror2));

        PVector r4 = calcMirrorDirection(second, mirror2);
        Ray third = createRay(second.getEnd(), r4);

        button.draw();

        mirror1.drawMirror();
        mirror2.drawMirror();

        first.drawRay();
        second.drawRay();
        third.drawRay();
    }

    PVector detectRayHit(Ray r, Mirror m) {
        return getIntersection(r.getStart(), r.getDirection(), m.getStart(), m.getDirection());
    }

    PVector getIntersection(PVector r1, PVector a1, PVector r2, PVector a2) {
        if (isOrthogonal(a1, a2)) {
            return null;
        }
        PVector d = PVector.sub(r1, r2); // temp difference vector
        float lambda1 = (d.x * a2.y - d.y * a2.x) / (a1.y * a2.x - a1.x * a2.y);
        return new PVector(r1.x + lambda1 * a1.x, r1.y + lambda1 * a1.y);
    }

    @Override
    public void mousePressed() {
        if (button.isPressed()) {
            mirror1.incAngle();
        }
    }

    boolean isOrthogonal(PVector v1, PVector v2) {
        return (PVector.dot(v1, v2) == 0);
    }

    private Button createButton(float x, float y, float r) {
        return new Button(x, y, r, this);
    }

    private Ray createRay(PVector a, PVector b) {
        return new Ray(a, b, this);
    }

    private Mirror createMirror(float x, float y, float a) {
        return new Mirror(x, y, a, this);
    }

    private Laser createLaser(float x, float y, float a) {
        return new Laser(x, y, a, this);
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
