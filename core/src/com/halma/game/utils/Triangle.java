package com.halma.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Triangle {

    Vector2 point1, point2, point3;

    public Triangle(Vector2 point1, Vector2 point2, Vector2 point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        System.out.println("P1(" + point1.x + ", " + point1.y + "), P2(" + point2.x + ", " + point2.y + "), P3(" + point3.x + ", " + point3.y + ")");
    }

    //Methods
    public boolean contains(float x, float y) {
        /* Calculate area of triangle ABC */
        float A = area (point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);

        /* Calculate area of triangle PBC */
        float A1 = area (x, y, point2.x, point2.y, point3.x, point3.y);

        /* Calculate area of triangle PAC */
        float A2 = area (point1.x, point1.y, x, y, point3.x, point3.y);

        /* Calculate area of triangle PAB */
        float A3 = area (point1.x, point1.y, point2.x, point2.y, x, y);

        /* Check if sum of A1, A2 and A3 is same as A */
        return (A+2 > A1 + A2 + A3) && (A-2 < A1 + A2 + A3);
    }

    public boolean contains2(float x, float y) { //Another method of contains. Doesn't need to be used. But kept just in case other one doesn't work.

        float alpha = ((point2.y - point3.y)*(x - point3.x) + (point3.x - point2.x)*(y - point3.y)) /
                ((point2.y - point3.y)*(point1.x - point3.x) + (point3.x - point2.x)*(point1.y - point3.y));
        float beta = ((point3.y - point1.y)*(x - point3.x) + (point1.x - point3.x)*(y - point3.y)) /
                ((point2.y - point3.y)*(point1.x - point3.x) + (point3.x - point2.x)*(point1.y - point3.y));
        float gamma = 1.0f - alpha - beta;
        return (alpha > 0 && beta > 0 && gamma > 0);
    }

    public float area(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (float) Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
    }

    public void move(float x, float y) {
        Vector2 lineAB = new Vector2(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
        Vector2 lineBC = new Vector2(Math.abs(point2.x - point3.x), Math.abs(point2.y - point3.y));
        Vector2 lineCA = new Vector2(Math.abs(point3.x - point1.x), Math.abs(point3.y - point1.y));

        point1.x = x;
        point1.y = y;
        point2.x = x + lineAB.x;
        point2.y = y + lineAB.y;
        point3.x = x + lineCA.x;
        point3.y = y + lineCA.y;
        //System.out.println("P1(" + point1.x + ", " + point1.y + "), P2(" + point2.x + ", " + point2.y + "), P3(" + point3.x + ", " + point3.y + ")");
    }


    //Getters and Setters

}
