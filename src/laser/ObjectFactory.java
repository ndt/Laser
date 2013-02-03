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
    
    ObjectFactory(Reflection ctx) {
        _ctx = ctx;
    }

    Mirror createMirror(float x, float y, float a) {
        return new Mirror(x, y, a, _ctx);
    }

    Laser createLaser(float x, float y, float a) {
        return new Laser(x, y, a, _ctx);
    }

    Ray createRay(PVector a, PVector b) {
        return new Ray(a, b, _ctx);
    }

    Button createButton(float x, float y, float r) {
        return new Button(x, y, r, _ctx);
    }
    
}
