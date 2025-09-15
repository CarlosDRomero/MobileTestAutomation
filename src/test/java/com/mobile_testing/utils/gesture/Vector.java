package com.mobile_testing.utils.gesture;

/**
 * Defines a mathematical Vector, X and Y components, add and scale methods
 */
public class Vector {
    public float x;
    public float y;
    public Vector(){
        this.x = 0;
        this.y = 0;
    }
    public Vector(float value) {
        this.x = value;
        this.y = value;
    }
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector from the result of adding another vector to this
     * @param vector: The vector to be added
     * @return The add result vector
     */
    public Vector add(Vector vector) {
        return  new Vector(x + vector.x, y + vector.y);
    }

    /**
     * Scales the vector by a certain value
     * @param value: The value to multiply the vector components
     * @return Returns the scaled vector result
     */
    public Vector scale(float value) {
        return new Vector(x * value, y * value);
    }

    public int getIntX() {
        return (int)x;
    }
    public int getIntY() {
        return (int)y;
    }
}
