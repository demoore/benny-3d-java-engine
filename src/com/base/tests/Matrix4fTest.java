package com.base.tests;

import com.base.engine.Matrix4f;

import static org.junit.Assert.assertTrue;

/**
 * Created by dylan on 2014-03-23.
 */
public class Matrix4fTest {
    public Matrix4f mahMatrix;

    @org.junit.Before
    public void setUp() throws Exception {
        mahMatrix = new Matrix4f();
        mahMatrix.initIdentity();

    }

    @org.junit.Test
    public void testInitIdentity() throws Exception {
        assertTrue(mahMatrix.get(0,0) == 1);
        assertTrue(mahMatrix.get(0,1) == 0);
    }

    @org.junit.Test
    public void testToString() throws Exception {
        System.out.println(mahMatrix.toString());
        assertTrue(true);
    }
}
