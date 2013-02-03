/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laser;

/**
 *
 * @author Nicolas Nieswandt
 */
class Button extends Optical {

    float _x;
    float _y;
    float _r;

    /**
     * 
     * @param x
     * @param y
     * @param r
     * @param ctx 
     */
    Button(float x, float y, float r, final Reflection ctx) {
        super(ctx);
        _x = x;
        _y = y;
        _r = r;
    }

    /**
     * 
     * @return 
     */
    public boolean isPressed() {
        return (Reflection.sqrt(Reflection.sq(_x - _ctx.mouseX) + Reflection.sq(_y - _ctx.mouseY))) < _r;
    }

    /**
     * 
     */
    @Override
    public void draw() {
        _ctx.fill(255);
        _ctx.ellipse(_x, _y, _r * 2, _r * 2);
    }
}