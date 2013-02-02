package laser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import processing.core.*;

/**
 *
 * @author NIESWANDT
 */
public class Reflection extends PApplet {

    int SCREEN_WIDTH = 500;
    int SCREEN_HIGHT = 500;
    boolean circleOver = false;
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
        background(0);
        mirror1 = createMirror(100, 400, -40);
        mirror2 = createMirror(380, 390, -120);
        button = createButton(100, 450, 10);
    }

    @Override
    public void draw() {
        background(0);

        Ray first = createRay(80, 0, -85);
        first.setEnd(detectEndOfRay(first, mirror1));

        float alpha = getAlpha(first, mirror1);
        Ray second = createRay(first.getEnd(), alpha);
        second.setEnd(detectEndOfRay(second, mirror2));

        float alpha2 = getAlpha(second, mirror2);
        Ray third = createRay(second.getEnd(), alpha2);

        button.draw();

        mirror1.drawMirror();
        mirror2.drawMirror();

        first.drawRay();
        second.drawRay();
        third.drawRay();
    }

    PVector detectEndOfRay(Ray r, Mirror m) {
        if (isOrthogonal(r, m)) {
            return null;
        }
        return getIntersection(r.getStart(), r.getDirection(), m.getStart(), m.getDirection());
    }

    PVector getIntersection(PVector r1, PVector a1, PVector r2, PVector a2) {
        PVector d = PVector.sub(r1, r2); // temp difference vector
        float lambda1 = (d.x * a2.y - d.y * a2.x) / (a1.y * a2.x - a1.x * a2.y);
        return new PVector(r1.x + lambda1 * a1.x, r1.y + lambda1 * a1.y);
    }

    float getAlpha(Ray ray, Mirror mirror) {
        float _angle = 0;
        PVector v3 = PVector.sub(ray.getStart(), ray.getEnd());
        PVector v4 = PVector.sub(mirror.getStart(), mirror.getEnd());

        if (ray.getAngle() < mirror.getAngle()) {
            _angle = 180 + degrees(PVector.angleBetween(v3, v4) + PVector.angleBetween(v4, new PVector(1, 0)));
        }

        if (ray.getAngle() > mirror.getAngle()) {
            _angle = 180 - degrees(PVector.angleBetween(v3, v4) - PVector.angleBetween(v4, new PVector(1, 0)));
        }
        return _angle;
    }

    @Override
    public void mousePressed() {
        if (button.isPressed()) {
            println("mousePressed");
            mirror1.incAngle();
        }
    }

    boolean isOthogonal(PVector p1, PVector p2) {
        return (PVector.dot(p1, p2) == 0);
    }

    boolean isOrthogonal(Ray r, Mirror m) {
        PVector a1 = r.getDirection();
        PVector a2 = m.getDirection();

        return (PVector.dot(a1, a2) == 0);
    }

    private Laser createLaser(float x, float y, float a) {
        return new Laser(x, y, a, this);
    }

    private Button createButton(float x, float y, float r) {
        return new Button(x, y, r, this);
    }

    private Ray createRay(float x, float y, float a) {
        return new Ray(x, y, a, this);
    }

    private Ray createRay(PVector v, float a) {
        return new Ray(v, a, this);
    }

    private Mirror createMirror(float x, float y, float a) {
        return new Mirror(x, y, a, this);
    }
}
