/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

/**
 *
 * @author Helge Wiethoff
 * @author Nicolas Nieswandt
 */
class Button {

    float _x;
    float _y;
    float _r;
    private final Reflection _ctx;

    Button(float x, float y, float r, final Reflection ctx) {
        _ctx = ctx;
        _x = x;
        _y = y;
        _r = r;
    }

    public boolean isPressed() {
        return (Reflection.sqrt(Reflection.sq(_x - _ctx.mouseX) + Reflection.sq(_y - _ctx.mouseY))) < _r;
    }

    public void draw() {
        _ctx.fill(255);
        _ctx.ellipse(_x, _y, _r * 2, _r * 2);
    }
}
