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

    static boolean isOrthogonal(PVector v1, PVector v2) {
        return PVector.dot(v1, v2) == 0;
    }

    static PVector calcIntersection(PVector r1, PVector a1, PVector r2, PVector a2) {
        if (VectorUtil.isOrthogonal(a1, a2)) {
            return null;
        }
        PVector d = PVector.sub(r1, r2);
        float lambda1 = (d.x * a2.y - d.y * a2.x) / (a1.y * a2.x - a1.x * a2.y);
        return new PVector(r1.x + lambda1 * a1.x, r1.y + lambda1 * a1.y);
    }
    
}
