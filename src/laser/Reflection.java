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
        first.setEnd(pointCollosion(first, mirror1));

        float alpha = getAlpha(first, mirror1);
        Ray second = createRay(first.getEnd(), alpha);
        second.setEnd(pointCollosion(second, mirror2));

        float alpha2 = getAlpha(second, mirror2);
        Ray third = createRay(second.getEnd(), alpha2);

        button.draw();

        mirror1.drawMirror();
        mirror2.drawMirror();
        
        first.drawRay();
        second.drawRay();
        third.drawRay();
    }

    PVector pointCollosion(Ray f, Mirror m) {
        float x1 = f.getOrigin().x;
        float y1 = f.getOrigin().y;

        PVector b = PVector.sub(f.getEnd(), f.getOrigin());
        PVector d = PVector.sub(m.getOrigin(), m.getEnd());

        if (isOthogonal(b, d)) {
            return null;
        }

        PVector c = PVector.sub(m.getOrigin(), f.getOrigin());

        float b_dot_d_perp = b.x * d.y - b.y * d.x;
        float t = (c.x * d.y - c.y * d.x) / b_dot_d_perp;

        return new PVector(x1 + t * b.x, y1 + t * b.y);
    }

    float getAlpha(Ray ray, Mirror mirror) {
        float _angle = 0;
        PVector v3 = PVector.sub(ray.getOrigin(), ray.getEnd());
        PVector v4 = PVector.sub(mirror.getOrigin(), mirror.getEnd());

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
    
    private Button createButton(float x, float y, float r) {
        return new Button(x,y,r,this);
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
