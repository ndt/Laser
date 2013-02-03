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
public class VectorUtil {

    /**
     * 
     * @param v1
     * @param v2
     * @return 
     */
    static boolean isOrthogonal(PVector v1, PVector v2) {
        return PVector.dot(v1, v2) == 0;
    }

    /**
     * 
     * @param r1
     * @param a1
     * @param r2
     * @param a2
     * @return 
     */
    static PVector calcIntersection(PVector r1, PVector a1, PVector r2, PVector a2) {
        if (VectorUtil.isOrthogonal(a1, a2)) {
            return null;
        }
        PVector d = PVector.sub(r1, r2);
        float lambda1 = (d.x * a2.y - d.y * a2.x) / (a1.y * a2.x - a1.x * a2.y);
        return new PVector(r1.x + lambda1 * a1.x, r1.y + lambda1 * a1.y);
    }
    
    /**
     * 
     * @see http://en.wikipedia.org/wiki/Coordinate_rotations_and_reflections
     * @param v
     * @param theta
     * @return 
     */
    static PVector rotate(PVector v, float theta) {
        float x, y;
        x = (float) Math.cos(theta) * v.x - (float) Math.sin(theta) * v.y;
        y = (float) Math.sin(theta) * v.x + (float) Math.cos(theta) * v.y;
        return new PVector(x, y);
    }
    
    /**
     * 
     * @see http://mathworld.wolfram.com/Reflection.html
     * @param v
     * @param theta
     * @return 
     */
    static PVector mirror(PVector v, float theta) {
        float x, y;
        x = (float) Math.cos(2*theta) * v.x + (float) Math.sin(2*theta) * v.y;
        y = (float) Math.sin(2*theta) * v.x - (float) Math.cos(2*theta) * v.y;
        return new PVector(x, y);
    }
    
}
