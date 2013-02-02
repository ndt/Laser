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

    int circleX, circleY;
    int circleSize = 20;
    boolean circleOver = false;
    Mirror mirror;
    float angle = -40;

    /**
     *
     */
    @Override
    public void setup() {
        size(SCREEN_WIDTH, SCREEN_HIGHT);
        background(0);
        mirror = new Mirror(new PVector(100, 400), angle);
    }

    @Override
    public void draw() {
        update(mouseX, mouseY);
        background(0);

        fill(255);
        circleX = 100;
        circleY = 450;
        ellipse(circleX, circleY, circleSize, circleSize);

        mirror.drawMirror();

        Ray first = new Ray(new PVector(80, 0), -85);

        first.setEnd(pointCollosion(first, mirror));

        first.drawRay();

        float alpha = getAlpha(first, mirror);

        Ray second = new Ray(first.getEnd(), alpha);

        Mirror mirror2 = new Mirror(new PVector(380, 390), -120);
        mirror2.drawMirror();

        second.setEnd(pointCollosion(second, mirror2));
        second.drawRay();

        float alpha2 = getAlpha(second, mirror2);

        Ray third = new Ray(second.getEnd(), alpha2);
        third.drawRay();
    }

    class Mirror {

        PVector _center = new PVector();
        PVector _start  = new PVector();
        PVector _end    = new PVector();
        float _angle;
        float _length = 100;

        Mirror(PVector c, float a) {
            _center = c;
            _angle = radians(a);
            _end = new PVector(_center.x + cos(_angle) * _length, _center.y - sin(_angle) * _length);
            _start = new PVector(_center.x - cos(_angle) * _length, _center.y + sin(_angle) * _length);
        }

        float getAngle() {
            return _angle;
        }

        void incAngle() {
            _angle = (float) (_angle + 0.01);
            _end = new PVector(_center.x + cos(_angle) * _length, _center.y - sin(_angle) * _length);
            _start = new PVector(_center.x - cos(_angle) * _length, _center.y + sin(_angle) * _length);
        }

        PVector getEnd() {
            return new PVector(_center.x + cos(_angle) * _length, _center.y - sin(_angle) * _length);
        }

        PVector getOrigin() {
            return new PVector(_center.x - cos(_angle) * _length, _center.y + sin(_angle) * _length);
        }

        void drawMirror() {
            stroke(222);
            line(_start.x, _start.y, _end.x, _end.y);
        }
    }

    class Ray {

        PVector _start = new PVector();
        PVector _end   = new PVector();
        float _angle;

        Ray(PVector o, float a) {
            _start = o;
            _angle = radians(a);
            _end = new PVector(_start.x + cos(_angle) * width * 2, _start.y - sin(_angle) * height * 2);
        }

        float getAngle() {
            return _angle;
        }

        PVector getEnd() {
            return _end;
        }

        PVector getOrigin() {
            return _start;
        }

        void setEnd(PVector e) {
            _end = e;
        }

        void drawRay() {
            stroke(255, 0, 0);
            line(_start.x, _start.y, _end.x, _end.y);
        }
    }

    PVector pointCollosion(Ray f, Mirror m) {
        float x1 = f.getOrigin().x;
        float y1 = f.getOrigin().y;
        float x2 = f.getEnd().x;
        float y2 = f.getEnd().y;

        float x3 = m.getOrigin().x;
        float y3 = m.getOrigin().y;
        float x4 = m.getEnd().x;
        float y4 = m.getEnd().y;

        float bx = x2 - x1;
        float by = y2 - y1;
        float dx = x4 - x3;
        float dy = y4 - y3;
        float b_dot_d_perp = bx * dy - by * dx;
        if (b_dot_d_perp == 0) {
            return null;
        }
        float cx = x3 - x1;
        float cy = y3 - y1;
        float t = (cx * dy - cy * dx) / b_dot_d_perp;

        return new PVector(x1 + t * bx, y1 + t * by);
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
        if (circleOver) {
            println("mousePressed");
            mirror.incAngle();
        }
    }

    boolean overCircle(int x, int y, int diameter) {
        float disX = x - mouseX;
        float disY = y - mouseY;
        if (sqrt(sq(disX) + sq(disY)) < diameter / 2) {
            return true;
        } else {
            return false;
        }
    }

    void update(int x, int y) {
        if (overCircle(circleX, circleY, circleSize)) {
            circleOver = true;
        } else {
            circleOver = false;
        }
    }
}
