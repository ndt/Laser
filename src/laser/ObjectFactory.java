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
public class ObjectFactory {
    
    Reflection _ctx;
    
    /**
     * 
     * @param ctx 
     */
    ObjectFactory(Reflection ctx) {
        _ctx = ctx;
    }

    /**
     * 
     * @param x
     * @param y
     * @param a
     * @return 
     */
    Mirror createMirror(float x, float y, float a) {
        return new Mirror(x, y, a, _ctx);
    }

    /**
     * 
     * @param x
     * @param y
     * @param a
     * @return 
     */
    Laser createLaser(float x, float y, float a) {
        return new Laser(x, y, a, _ctx);
    }

    /**
     * 
     * @param a
     * @param b
     * @return 
     */
    Ray createRay(PVector a, PVector b) {
        return new Ray(a, b, _ctx);
    }

    /**
     * 
     * @param x
     * @param y
     * @param r
     * @return 
     */
    Button createButton(float x, float y, float r) {
        return new Button(x, y, r, _ctx);
    }
    
    Vector createVector(float x, float y) {
        return new Vector(x, y, _ctx);
    }
}
