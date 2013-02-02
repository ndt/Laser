/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

/**
 *
 * @author NIESWANDT
 */
class Button {
    int _x;
    int _y;
    int _r;
    private final Reflection ctx;

    Button(int x, int y, int r, final Reflection ctx) {
        this.ctx = ctx;
        _x = x;
        _y = y;
        _r = r;
    }

    public boolean isPressed() {
        return (Reflection.sqrt(Reflection.sq(_x - ctx.mouseX) + Reflection.sq(_y - ctx.mouseY))) < _r;
    }

    public void draw() {
        ctx.fill(255);
        ctx.ellipse(_x, _y, _r * 2, _r * 2);
    }
}
