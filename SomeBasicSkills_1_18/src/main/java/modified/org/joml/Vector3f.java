/*
 * The MIT License
 *
 * Copyright (c) 2015-2024 Richard Greenlees
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package modified.org.joml;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Contains the definition of a Vector comprising 3 floats and associated
 * transformations.
 *
 * @author Richard Greenlees
 * @author Kai Burjack
 * @author F. Neurath
 */
public class Vector3f implements Externalizable, Cloneable, Vector3fc {

    private static final long serialVersionUID = 1L;

    /**
     * The x component of the vector.
     */
    public float x;
    /**
     * The y component of the vector.
     */
    public float y;
    /**
     * The z component of the vector.
     */
    public float z;

    /**
     * Create a new {@link Vector3f} of <code>(0, 0, 0)</code>.
     */
    public Vector3f() {
    }

    /**
     * Create a new {@link Vector3f} and initialize all three components with the given value.
     *
     * @param d
     *          the value of all three components
     */
    public Vector3f(float d) {
        this.x = d;
        this.y = d;
        this.z = d;
    }

    /**
     * Create a new {@link Vector3f} with the given component values.
     * 
     * @param x
     *          the value of x
     * @param y
     *          the value of y
     * @param z
     *          the value of z
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new {@link Vector3f} with the same values as <code>v</code>.
     * 
     * @param v
     *          the {@link Vector3fc} to copy the values from
     */
    public Vector3f(Vector3fc v) {
        this.x = v.x();
        this.y = v.y();
        this.z = v.z();
    }

    /**
     * Create a new {@link Vector3f} and initialize its three components from the first
     * three elements of the given array.
     * 
     * @param xyz
     *          the array containing at least three elements
     */
    public Vector3f(float[] xyz) {
        this.x = xyz[0];
        this.y = xyz[1];
        this.z = xyz[2];
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public float z() {
        return this.z;
    }

    /**
     * Set the x, y and z components to match the supplied vector.
     * 
     * @param v
     *          contains the values of x, y and z to set
     * @return this
     */
    public Vector3f set(Vector3fc v) {
        if (v == this)
            return this;
        this.x = v.x();
        this.y = v.y();
        this.z = v.z();
        return this;
    }

    /**
     * Set the x, y, and z components to the supplied value.
     *
     * @param d
     *          the value of all three components
     * @return this
     */
    public Vector3f set(float d) {
        this.x = d;
        this.y = d;
        this.z = d;
        return this;
    }

    /**
     * Set the x, y and z components to the supplied values.
     * 
     * @param x
     *          the x component
     * @param y
     *          the y component 
     * @param z
     *          the z component
     * @return this
     */
    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * Set the x, y, and z components to the supplied value.
     *
     * @param d
     *          the value of all three components
     * @return this
     */
    public Vector3f set(double d) {
        this.x = (float) d;
        this.y = (float) d;
        this.z = (float) d;
        return this;
    }

    /**
     * Set the x, y and z components to the supplied values.
     * 
     * @param x
     *          the x component
     * @param y
     *          the y component 
     * @param z
     *          the z component
     * @return this
     */
    public Vector3f set(double x, double y, double z) {
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        return this;
    }

    /**
     * Set the three components of this vector to the first three elements of the given array.
     * 
     * @param xyz
     *          the array containing at least three elements
     * @return this
     */
    public Vector3f set(float[] xyz) {
        this.x = xyz[0];
        this.y = xyz[1];
        this.z = xyz[2];
        return this;
    }

    /**
     * Subtract the supplied vector from this one and store the result in <code>this</code>.
     * 
     * @param v
     *          the vector to subtract
     * @return this
     */
    public Vector3f sub(Vector3fc v) {
        return sub(v, this);
    }

    public Vector3f sub(Vector3fc v, Vector3f dest) {
        dest.x = x - v.x();
        dest.y = y - v.y();
        dest.z = z - v.z();
        return dest;
    }

    /**
     * Decrement the components of this vector by the given values.
     * 
     * @param x
     *          the x component to subtract
     * @param y
     *          the y component to subtract
     * @param z
     *          the z component to subtract
     * @return this
     */
    public Vector3f sub(float x, float y, float z) {
        return sub(x, y, z, this);
    }

    public Vector3f sub(float x, float y, float z, Vector3f dest) {
        dest.x = this.x - x;
        dest.y = this.y - y;
        dest.z = this.z - z;
        return dest;
    }

    /**
     * Add the supplied vector to this one.
     * 
     * @param v
     *          the vector to add
     * @return this
     */
    public Vector3f add(Vector3fc v) {
        return add(v, this);
    }

    public Vector3f add(Vector3fc v, Vector3f dest) {
        dest.x = this.x + v.x();
        dest.y = this.y + v.y();
        dest.z = this.z + v.z();
        return dest;
    }

    /**
     * Increment the components of this vector by the given values.
     * 
     * @param x
     *          the x component to add
     * @param y
     *          the y component to add
     * @param z
     *          the z component to add
     * @return this
     */
    public Vector3f add(float x, float y, float z) {
        return add(x, y, z, this);
    }

    public Vector3f add(float x, float y, float z, Vector3f dest) {
        dest.x = this.x + x;
        dest.y = this.y + y;
        dest.z = this.z + z;
        return dest;
    }

    /**
     * Add the component-wise multiplication of <code>a * b</code> to this vector.
     * 
     * @param a
     *          the first multiplicand
     * @param b
     *          the second multiplicand
     * @return this
     */
    public Vector3f fma(Vector3fc a, Vector3fc b) {
        return fma(a, b, this);
    }

    /**
     * Add the component-wise multiplication of <code>a * b</code> to this vector.
     * 
     * @param a
     *          the first multiplicand
     * @param b
     *          the second multiplicand
     * @return this
     */
    public Vector3f fma(float a, Vector3fc b) {
        return fma(a, b, this);
    }

    public Vector3f fma(Vector3fc a, Vector3fc b, Vector3f dest) {
        dest.x = Math.fma(a.x(), b.x(), x);
        dest.y = Math.fma(a.y(), b.y(), y);
        dest.z = Math.fma(a.z(), b.z(), z);
        return dest;
    }

    public Vector3f fma(float a, Vector3fc b, Vector3f dest) {
        dest.x = Math.fma(a, b.x(), x);
        dest.y = Math.fma(a, b.y(), y);
        dest.z = Math.fma(a, b.z(), z);
        return dest;
    }

    /**
     * Add the component-wise multiplication of <code>this * a</code> to <code>b</code>
     * and store the result in <code>this</code>.
     * 
     * @param a
     *          the multiplicand
     * @param b
     *          the addend
     * @return this
     */
    public Vector3f mulAdd(Vector3fc a, Vector3fc b) {
        return mulAdd(a, b, this);
    }

    /**
     * Add the component-wise multiplication of <code>this * a</code> to <code>b</code>
     * and store the result in <code>this</code>.
     * 
     * @param a
     *          the multiplicand
     * @param b
     *          the addend
     * @return this
     */
    public Vector3f mulAdd(float a, Vector3fc b) {
        return mulAdd(a, b, this);
    }

    public Vector3f mulAdd(Vector3fc a, Vector3fc b, Vector3f dest) {
        dest.x = Math.fma(x, a.x(), b.x());
        dest.y = Math.fma(y, a.y(), b.y());
        dest.z = Math.fma(z, a.z(), b.z());
        return dest;
    }

    public Vector3f mulAdd(float a, Vector3fc b, Vector3f dest) {
        dest.x = Math.fma(x, a, b.x());
        dest.y = Math.fma(y, a, b.y());
        dest.z = Math.fma(z, a, b.z());
        return dest;
    }

    /**
     * Multiply this vector component-wise by another Vector3fc.
     * 
     * @param v
     *          the vector to multiply by
     * @return this
     */
    public Vector3f mul(Vector3fc v) {
        return mul(v, this);
    }

    public Vector3f mul(Vector3fc v, Vector3f dest) {
        dest.x = x * v.x();
        dest.y = y * v.y();
        dest.z = z * v.z();
        return dest;
    }

    /**
     * Divide this vector component-wise by another Vector3fc.
     * 
     * @param v
     *          the vector to divide by
     * @return this
     */
    public Vector3f div(Vector3fc v) {
        return div(v, this);
    }

    public Vector3f div(Vector3fc v, Vector3f dest) {
        dest.x = x / v.x();
        dest.y = y / v.y();
        dest.z = z / v.z();
        return dest;
    }


    /**
     * Multiply all components of this vector by the given scalar
     * value.
     * 
     * @param scalar
     *          the scalar to multiply this vector by
     * @return this
     */
    public Vector3f mul(float scalar) {
        return mul(scalar, this);
    }

    public Vector3f mul(float scalar, Vector3f dest) {
        dest.x = this.x * scalar;
        dest.y = this.y * scalar;
        dest.z = this.z * scalar;
        return dest;
    }

    /**
     * Multiply the components of this vector by the given scalar values and store the result in <code>this</code>.
     * 
     * @param x
     *          the x component to multiply this vector by
     * @param y
     *          the y component to multiply this vector by
     * @param z
     *          the z component to multiply this vector by
     * @return this
     */
    public Vector3f mul(float x, float y, float z) {
        return mul(x, y, z, this);
    }

    public Vector3f mul(float x, float y, float z, Vector3f dest) {
        dest.x = this.x * x;
        dest.y = this.y * y;
        dest.z = this.z * z;
        return dest;
    }

    /**
     * Divide all components of this vector by the given scalar
     * value.
     * 
     * @param scalar
     *          the scalar to divide by
     * @return this
     */
    public Vector3f div(float scalar) {
        return div(scalar, this);
    }

    public Vector3f div(float scalar, Vector3f dest) {
        float inv = 1.0f / scalar;
        dest.x = this.x * inv;
        dest.y = this.y * inv;
        dest.z = this.z * inv;
        return dest;
    }

    /**
     * Divide the components of this vector by the given scalar values and store the result in <code>this</code>.
     * 
     * @param x
     *          the x component to divide this vector by
     * @param y
     *          the y component to divide this vector by
     * @param z
     *          the z component to divide this vector by
     * @return this
     */
    public Vector3f div(float x, float y, float z) {
        return div(x, y, z, this);
    }

    public Vector3f div(float x, float y, float z, Vector3f dest) {
        dest.x = this.x / x;
        dest.y = this.y / y;
        dest.z = this.z / z;
        return dest;
    }

    /**
     * Rotate this vector by the given quaternion <code>quat</code> and store the result in <code>this</code>.
     * 
     * @see Quaternionfc#transform(Vector3f)
     * 
     * @param quat
     *          the quaternion to rotate this vector
     * @return this
     */
    public Vector3f rotate(Quaternionfc quat) {
        return quat.transform(this, this);
    }

    public Vector3f rotate(Quaternionfc quat, Vector3f dest) {
        return quat.transform(this, dest);
    }

    public Quaternionf rotationTo(Vector3fc toDir, Quaternionf dest) {
        return dest.rotationTo(this, toDir);
    }

    public Quaternionf rotationTo(float toDirX, float toDirY, float toDirZ, Quaternionf dest) {
        return dest.rotationTo(x, y, z, toDirX, toDirY, toDirZ);
    }

    /**
     * Rotate this vector the specified radians around the given rotation axis.
     * <p>
     * If the rotation axis is either <code>(1, 0, 0)</code>, <code>(0, 1, 0)</code> or <code>(0, 0, 1)</code>.
     * then {@link #rotateX(float) rotateX()}, {@link #rotateY(float) rotateY()} or
     * {@link #rotateZ(float) rotateZ()}, respectively, should be used instead.
     *
     * @param angle
     *          the angle in radians
     * @param x
     *          the x component of the rotation axis
     * @param y
     *          the y component of the rotation axis
     * @param z
     *          the z component of the rotation axis
     * @return this
     */
    public Vector3f rotateAxis(float angle, float x, float y, float z) {
        return rotateAxis(angle, x, y, z, this);
    }

    public Vector3f rotateAxis(float angle, float aX, float aY, float aZ, Vector3f dest) {
        if (aY == 0.0f && aZ == 0.0f && Math.absEqualsOne(aX))
            return rotateX(aX * angle, dest);
        else if (aX == 0.0f && aZ == 0.0f && Math.absEqualsOne(aY))
            return rotateY(aY * angle, dest);
        else if (aX == 0.0f && aY == 0.0f && Math.absEqualsOne(aZ))
            return rotateZ(aZ * angle, dest);
        return rotateAxisInternal(angle, aX, aY, aZ, dest);
    }
    private Vector3f rotateAxisInternal(float angle, float aX, float aY, float aZ, Vector3f dest) {
        float hangle = angle * 0.5f;
        float sinAngle = Math.sin(hangle);
        float qx = aX * sinAngle, qy = aY * sinAngle, qz = aZ * sinAngle;
        float qw = Math.cosFromSin(sinAngle, hangle);
        float w2 = qw * qw, x2 = qx * qx, y2 = qy * qy, z2 = qz * qz, zw = qz * qw;
        float xy = qx * qy, xz = qx * qz, yw = qy * qw, yz = qy * qz, xw = qx * qw;
        float x = this.x, y = this.y, z = this.z;
        dest.x = (w2 + x2 - z2 - y2) * x + (-zw + xy - zw + xy) * y + (yw + xz + xz + yw) * z;
        dest.y = (xy + zw + zw + xy) * x + ( y2 - z2 + w2 - x2) * y + (yz + yz - xw - xw) * z;
        dest.z = (xz - yw + xz - yw) * x + ( yz + yz + xw + xw) * y + (z2 - y2 - x2 + w2) * z;
        return dest;
    }

    /**
     * Rotate this vector the specified radians around the X axis.
     * 
     * @param angle
     *          the angle in radians
     * @return this
     */
    public Vector3f rotateX(float angle) {
        return rotateX(angle, this);
    }

    public Vector3f rotateX(float angle, Vector3f dest) {
        float sin = Math.sin(angle), cos = Math.cosFromSin(sin, angle);
        float y = this.y * cos - this.z * sin;
        float z = this.y * sin + this.z * cos;
        dest.x = this.x;
        dest.y = y;
        dest.z = z;
        return dest;
    }

    /**
     * Rotate this vector the specified radians around the Y axis.
     * 
     * @param angle
     *          the angle in radians
     * @return this
     */
    public Vector3f rotateY(float angle) {
        return rotateY(angle, this);
    }

    public Vector3f rotateY(float angle, Vector3f dest) {
        float sin = Math.sin(angle), cos = Math.cosFromSin(sin, angle);
        float x =  this.x * cos + this.z * sin;
        float z = -this.x * sin + this.z * cos;
        dest.x = x;
        dest.y = this.y;
        dest.z = z;
        return dest;
    }

    /**
     * Rotate this vector the specified radians around the Z axis.
     * 
     * @param angle
     *          the angle in radians
     * @return this
     */
    public Vector3f rotateZ(float angle) {
        return rotateZ(angle, this);
    }

    public Vector3f rotateZ(float angle, Vector3f dest) {
        float sin = Math.sin(angle), cos = Math.cosFromSin(sin, angle);
        float x = this.x * cos - this.y * sin;
        float y = this.x * sin + this.y * cos;
        dest.x = x;
        dest.y = y;
        dest.z = this.z;
        return dest;
    }

    public float lengthSquared() {
        return Math.fma(x, x, Math.fma(y, y, z * z));
    }

    /**
     * Get the length squared of a 3-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @param z The vector's z component
     *
     * @return the length squared of the given vector
     *
     * @author F. Neurath
     */
    public static float lengthSquared(float x, float y, float z) {
        return Math.fma(x, x, Math.fma(y, y, z * z));
    }

    public float length() {
        return Math.sqrt(Math.fma(x, x, Math.fma(y, y, z * z)));
    }

    /**
     * Get the length of a 3-dimensional single-precision vector.
     *
     * @param x The vector's x component
     * @param y The vector's y component
     * @param z The vector's z component
     *
     * @return the length of the given vector
     *
     * @author F. Neurath
     */
    public static float length(float x, float y, float z) {
        return Math.sqrt(Math.fma(x, x, Math.fma(y, y, z * z)));
    }

    /**
     * Normalize this vector.
     * 
     * @return this
     */
    public Vector3f normalize() {
        return normalize(this);
    }

    public Vector3f normalize(Vector3f dest) {
        float scalar = Math.invsqrt(Math.fma(x, x, Math.fma(y, y, z * z)));
        dest.x = this.x * scalar;
        dest.y = this.y * scalar;
        dest.z = this.z * scalar;
        return dest;
    }

    /**
     * Scale this vector to have the given length.
     * 
     * @param length
     *          the desired length
     * @return this
     */
    public Vector3f normalize(float length) {
        return normalize(length, this);
    }

    public Vector3f normalize(float length, Vector3f dest) {
        float scalar = Math.invsqrt(Math.fma(x, x, Math.fma(y, y, z * z))) * length;
        dest.x = this.x * scalar;
        dest.y = this.y * scalar;
        dest.z = this.z * scalar;
        return dest;
    }

    /**
     * Set this vector to be the cross product of itself and <code>v</code>.
     * 
     * @param v
     *          the other vector
     * @return this
     */
    public Vector3f cross(Vector3fc v) {
        return cross(v, this);
    }

    /**
     * Set this vector to be the cross product of itself and <code>(x, y, z)</code>.
     * 
     * @param x
     *          the x component of the other vector
     * @param y
     *          the y component of the other vector
     * @param z
     *          the z component of the other vector
     * @return this
     */
    public Vector3f cross(float x, float y, float z) {
        return cross(x, y, z, this);
    }

    public Vector3f cross(Vector3fc v, Vector3f dest) {
        float rx = Math.fma(y, v.z(), -z * v.y());
        float ry = Math.fma(z, v.x(), -x * v.z());
        float rz = Math.fma(x, v.y(), -y * v.x());
        dest.x = rx;
        dest.y = ry;
        dest.z = rz;
        return dest;
    }

    public Vector3f cross(float x, float y, float z, Vector3f dest) {
        float rx = Math.fma(this.y, z, -this.z * y);
        float ry = Math.fma(this.z, x, -this.x * z);
        float rz = Math.fma(this.x, y, -this.y * x);
        dest.x = rx;
        dest.y = ry;
        dest.z = rz;
        return dest;
    }

    public float distance(Vector3fc v) {
        float dx = this.x - v.x();
        float dy = this.y - v.y();
        float dz = this.z - v.z();
        return Math.sqrt(Math.fma(dx, dx, Math.fma(dy, dy, dz * dz)));
    }

    public float distance(float x, float y, float z) {
        float dx = this.x - x;
        float dy = this.y - y;
        float dz = this.z - z;
        return Math.sqrt(Math.fma(dx, dx, Math.fma(dy, dy, dz * dz)));
    }

    public float distanceSquared(Vector3fc v) {
        float dx = this.x - v.x();
        float dy = this.y - v.y();
        float dz = this.z - v.z();
        return Math.fma(dx, dx, Math.fma(dy, dy, dz * dz));
    }

    public float distanceSquared(float x, float y, float z) {
        float dx = this.x - x;
        float dy = this.y - y;
        float dz = this.z - z;
        return Math.fma(dx, dx, Math.fma(dy, dy, dz * dz));
    }

    /**
     * Return the distance between <code>(x1, y1, z1)</code> and <code>(x2, y2, z2)</code>.
     *
     * @param x1
     *          the x component of the first vector
     * @param y1
     *          the y component of the first vector
     * @param z1
     *          the z component of the first vector
     * @param x2
     *          the x component of the second vector
     * @param y2
     *          the y component of the second vector
     * @param z2
     *          the z component of the second vector
     * @return the euclidean distance
     */
    public static float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
        return Math.sqrt(distanceSquared(x1, y1, z1, x2, y2, z2));
    }

    /**
     * Return the squared distance between <code>(x1, y1, z1)</code> and <code>(x2, y2, z2)</code>.
     *
     * @param x1
     *          the x component of the first vector
     * @param y1
     *          the y component of the first vector
     * @param z1
     *          the z component of the first vector
     * @param x2
     *          the x component of the second vector
     * @param y2
     *          the y component of the second vector
     * @param z2
     *          the z component of the second vector
     * @return the euclidean distance squared
     */
    public static float distanceSquared(float x1, float y1, float z1, float x2, float y2, float z2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float dz = z1 - z2;
        return Math.fma(dx, dx, Math.fma(dy, dy, dz * dz));
    }

    public float dot(Vector3fc v) {
        return Math.fma(this.x, v.x(), Math.fma(this.y, v.y(), this.z * v.z()));
    }

    public float dot(float x, float y, float z) {
        return Math.fma(this.x, x, Math.fma(this.y, y, this.z * z));
    }

    public float angleCos(Vector3fc v) {
        float x = this.x, y = this.y, z = this.z;
        float length1Squared = Math.fma(x, x, Math.fma(y, y, z * z));
        float length2Squared = Math.fma(v.x(), v.x(), Math.fma(v.y(), v.y(), v.z() * v.z()));
        float dot = Math.fma(x, v.x(), Math.fma(y, v.y(), z * v.z()));
        return dot / (float)Math.sqrt(length1Squared * length2Squared);
    }

    public float angle(Vector3fc v) {
        float cos = angleCos(v);
        // This is because sometimes cos goes above 1 or below -1 because of lost precision
        cos = cos < 1 ? cos : 1;
        cos = cos > -1 ? cos : -1;
        return Math.acos(cos);
    }

    public float angleSigned(Vector3fc v, Vector3fc n) {
        return angleSigned(v.x(), v.y(), v.z(), n.x(), n.y(), n.z());
    }

    public float angleSigned(float x, float y, float z, float nx, float ny, float nz) {
        float tx = this.x, ty = this.y, tz = this.z;
        return Math.atan2(
                (ty * z - tz * y) * nx + (tz * x - tx * z) * ny + (tx * y - ty * x) * nz,
                tx * x + ty * y + tz * z);
    }

    /**
     * Set the components of this vector to be the component-wise minimum of this and the other vector.
     *
     * @param v
     *          the other vector
     * @return this
     */
    public Vector3f min(Vector3fc v) {
        return min(v, this);
    }

    public Vector3f min(Vector3fc v, Vector3f dest) {
        float x = this.x, y = this.y, z = this.z;
        dest.x = x < v.x() ? x : v.x();
        dest.y = y < v.y() ? y : v.y();
        dest.z = z < v.z() ? z : v.z();
        return dest;
    }

    /**
     * Set the components of this vector to be the component-wise maximum of this and the other vector.
     *
     * @param v
     *          the other vector
     * @return this
     */
    public Vector3f max(Vector3fc v) {
        return max(v, this);
    }

    public Vector3f max(Vector3fc v, Vector3f dest) {
        float x = this.x, y = this.y, z = this.z;
        dest.x = x > v.x() ? x : v.x();
        dest.y = y > v.y() ? y : v.y();
        dest.z = z > v.z() ? z : v.z();
        return dest;
    }

    /**
     * Set all components to zero.
     * 
     * @return this
     */
    public Vector3f zero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        return this;
    }

    /**
     * Return a string representation of this vector.
     * <p>
     * This method creates a new {@link DecimalFormat} on every invocation with the format string "<code>0.000E0;-</code>".
     * 
     * @return the string representation
     */
    public String toString() {
        return Runtime.formatNumbers(toString(Options.NUMBER_FORMAT));
    }

    /**
     * Return a string representation of this vector by formatting the vector components with the given {@link NumberFormat}.
     * 
     * @param formatter
     *          the {@link NumberFormat} used to format the vector components with
     * @return the string representation
     */
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + ")";
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        set(in.readFloat(), in.readFloat(), in.readFloat());
    }

    /**
     * Negate this vector.
     * 
     * @return this
     */
    public Vector3f negate() {
        return negate(this);
    }

    public Vector3f negate(Vector3f dest) {
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        return dest;
    }

    /**
     * Set <code>this</code> vector's components to their respective absolute values.
     * 
     * @return this
     */
    public Vector3f absolute() {
        return absolute(this);
    }

    public Vector3f absolute(Vector3f dest) {
        dest.x = Math.abs(this.x);
        dest.y = Math.abs(this.y);
        dest.z = Math.abs(this.z);
        return dest;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        result = prime * result + Float.floatToIntBits(z);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector3f other = (Vector3f) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
            return false;
        return true;
    }

    public boolean equals(Vector3fc v, float delta) {
        if (this == v)
            return true;
        if (v == null)
            return false;
        if (getClass() != v.getClass())
            return false;
        if (!Runtime.equals(x, v.x(), delta))
            return false;
        if (!Runtime.equals(y, v.y(), delta))
            return false;
        if (!Runtime.equals(z, v.z(), delta))
            return false;
        return true;
    }

    public boolean equals(float x, float y, float z) {
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(x))
            return false;
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(y))
            return false;
        if (Float.floatToIntBits(this.z) != Float.floatToIntBits(z))
            return false;
        return true;
    }

    /**
     * Reflect this vector about the given <code>normal</code> vector.
     * 
     * @param normal
     *          the vector to reflect about
     * @return this
     */
    public Vector3f reflect(Vector3fc normal) {
        float x = normal.x();
        float y = normal.y();
        float z = normal.z();
        float dot = Math.fma(this.x, x, Math.fma(this.y, y, this.z * z));
        this.x = this.x - (dot + dot) * x;
        this.y = this.y - (dot + dot) * y;
        this.z = this.z - (dot + dot) * z;
        return this;
    }

    /**
     * Reflect this vector about the given normal vector.
     * 
     * @param x
     *          the x component of the normal
     * @param y
     *          the y component of the normal
     * @param z
     *          the z component of the normal
     * @return this
     */
    public Vector3f reflect(float x, float y, float z) {
        float dot = Math.fma(this.x, x, Math.fma(this.y, y, this.z * z));
        this.x = this.x - (dot + dot) * x;
        this.y = this.y - (dot + dot) * y;
        this.z = this.z - (dot + dot) * z;
        return this;
    }

    public Vector3f reflect(Vector3fc normal, Vector3f dest) {
        return reflect(normal.x(), normal.y(), normal.z(), dest);
    }

    public Vector3f reflect(float x, float y, float z, Vector3f dest) {
        float dot = this.dot(x, y, z);
        dest.x = this.x - (dot + dot) * x;
        dest.y = this.y - (dot + dot) * y;
        dest.z = this.z - (dot + dot) * z;
        return dest;
    }

    /**
     * Compute the half vector between this and the other vector.
     * 
     * @param other
     *          the other vector
     * @return this
     */
    public Vector3f half(Vector3fc other) {
        return this.set(this).add(other.x(), other.y(), other.z()).normalize();
    }

    /**
     * Compute the half vector between this and the vector <code>(x, y, z)</code>.
     * 
     * @param x
     *          the x component of the other vector
     * @param y
     *          the y component of the other vector
     * @param z
     *          the z component of the other vector
     * @return this
     */
    public Vector3f half(float x, float y, float z) {
        return half(x, y, z, this);
    }

    public Vector3f half(Vector3fc other, Vector3f dest) {
        return half(other.x(), other.y(), other.z(), dest);
    }

    public Vector3f half(float x, float y, float z, Vector3f dest) {
        return dest.set(this).add(x, y, z).normalize();
    }

    public Vector3f smoothStep(Vector3fc v, float t, Vector3f dest) {
        float x = this.x, y = this.y, z = this.z;
        float t2 = t * t;
        float t3 = t2 * t;
        dest.x = (x + x - v.x() - v.x()) * t3 + (3.0f * v.x() - 3.0f * x) * t2 + x * t + x;
        dest.y = (y + y - v.y() - v.y()) * t3 + (3.0f * v.y() - 3.0f * y) * t2 + y * t + y;
        dest.z = (z + z - v.z() - v.z()) * t3 + (3.0f * v.z() - 3.0f * z) * t2 + z * t + z;
        return dest;
    }

    public Vector3f hermite(Vector3fc t0, Vector3fc v1, Vector3fc t1, float t, Vector3f dest) {
        float x = this.x, y = this.y, z = this.z;
        float t2 = t * t;
        float t3 = t2 * t;
        dest.x = (x + x - v1.x() - v1.x() + t1.x() + t0.x()) * t3 + (3.0f * v1.x() - 3.0f * x - t0.x() - t0.x() - t1.x()) * t2 + x * t + x;
        dest.y = (y + y - v1.y() - v1.y() + t1.y() + t0.y()) * t3 + (3.0f * v1.y() - 3.0f * y - t0.y() - t0.y() - t1.y()) * t2 + y * t + y;
        dest.z = (z + z - v1.z() - v1.z() + t1.z() + t0.z()) * t3 + (3.0f * v1.z() - 3.0f * z - t0.z() - t0.z() - t1.z()) * t2 + z * t + z;
        return dest;
    }

    /**
     * Linearly interpolate <code>this</code> and <code>other</code> using the given interpolation factor <code>t</code>
     * and store the result in <code>this</code>.
     * <p>
     * If <code>t</code> is <code>0.0</code> then the result is <code>this</code>. If the interpolation factor is <code>1.0</code>
     * then the result is <code>other</code>.
     * 
     * @param other
     *          the other vector
     * @param t
     *          the interpolation factor between 0.0 and 1.0
     * @return this
     */
    public Vector3f lerp(Vector3fc other, float t) {
        return lerp(other, t, this);
    }

    public Vector3f lerp(Vector3fc other, float t, Vector3f dest) {
        dest.x = Math.fma(other.x() - x, t, x);
        dest.y = Math.fma(other.y() - y, t, y);
        dest.z = Math.fma(other.z() - z, t, z);
        return dest;
    }

    public float get(int component) throws IllegalArgumentException {
        switch (component) {
        case 0:
            return x;
        case 1:
            return y;
        case 2:
            return z;
        default:
            throw new IllegalArgumentException();
        }
    }

    public Vector3f get(Vector3f dest) {
        dest.x = this.x();
        dest.y = this.y();
        dest.z = this.z();
        return dest;
    }

    public int maxComponent() {
        float absX = Math.abs(x);
        float absY = Math.abs(y);
        float absZ = Math.abs(z);
        if (absX >= absY && absX >= absZ) {
            return 0;
        } else if (absY >= absZ) {
            return 1;
        }
        return 2;
    }

    public int minComponent() {
        float absX = Math.abs(x);
        float absY = Math.abs(y);
        float absZ = Math.abs(z);
        if (absX < absY && absX < absZ) {
            return 0;
        } else if (absY < absZ) {
            return 1;
        }
        return 2;
    }

    public Vector3f orthogonalize(Vector3fc v, Vector3f dest) {
        /*
         * http://lolengine.net/blog/2013/09/21/picking-orthogonal-vector-combing-coconuts
         */
        float rx, ry, rz;
        if (Math.abs(v.x()) > Math.abs(v.z())) {
            rx = -v.y();
            ry = v.x();
            rz = 0.0f;
        } else {
            rx = 0.0f;
            ry = -v.z();
            rz = v.y();
        }
        float invLen = Math.invsqrt(rx * rx + ry * ry + rz * rz);
        dest.x = rx * invLen;
        dest.y = ry * invLen;
        dest.z = rz * invLen;
        return dest;
    }

    /**
     * Transform <code>this</code> vector so that it is orthogonal to the given vector <code>v</code> and normalize the result.
     * <p>
     * Reference: <a href="https://en.wikipedia.org/wiki/Gram%E2%80%93Schmidt_process">Gram–Schmidt process</a>
     * 
     * @param v
     *          the reference vector which the result should be orthogonal to
     * @return this
     */
    public Vector3f orthogonalize(Vector3fc v) {
        return orthogonalize(v, this);
    }

    public Vector3f orthogonalizeUnit(Vector3fc v, Vector3f dest) {
        return orthogonalize(v, dest);
    }

    /**
     * Transform <code>this</code> vector so that it is orthogonal to the given unit vector <code>v</code> and normalize the result.
     * <p>
     * The vector <code>v</code> is assumed to be a {@link #normalize() unit} vector.
     * <p>
     * Reference: <a href="https://en.wikipedia.org/wiki/Gram%E2%80%93Schmidt_process">Gram–Schmidt process</a>
     * 
     * @param v
     *          the reference unit vector which the result should be orthogonal to
     * @return this
     */
    public Vector3f orthogonalizeUnit(Vector3fc v) {
        return orthogonalizeUnit(v, this);
    }

    /**
     * Set each component of this vector to the largest (closest to positive
     * infinity) {@code float} value that is less than or equal to that
     * component and is equal to a mathematical integer.
     *
     * @return this
     */
    public Vector3f floor() {
        return floor(this);
    }

    public Vector3f floor(Vector3f dest) {
        dest.x = Math.floor(x);
        dest.y = Math.floor(y);
        dest.z = Math.floor(z);
        return dest;
    }

    /**
     * Set each component of this vector to the smallest (closest to negative
     * infinity) {@code float} value that is greater than or equal to that
     * component and is equal to a mathematical integer.
     *
     * @return this
     */
    public Vector3f ceil() {
        return ceil(this);
    }

    public Vector3f ceil(Vector3f dest) {
        dest.x = Math.ceil(x);
        dest.y = Math.ceil(y);
        dest.z = Math.ceil(z);
        return dest;
    }

    /**
     * Set each component of this vector to the closest float that is equal to
     * a mathematical integer, with ties rounding to positive infinity.
     *
     * @return this
     */
    public Vector3f round() {
        return round(this);
    }

    public Vector3f round(Vector3f dest) {
        dest.x = Math.round(x);
        dest.y = Math.round(y);
        dest.z = Math.round(z);
        return dest;
    }

    public boolean isFinite() {
        return Math.isFinite(x) && Math.isFinite(y) && Math.isFinite(z);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
