package com.grady.giftdb;

import org.junit.Test;

import static org.junit.Assert.*;

public class TupleTest {

    @Test
    public void hello() {
        System.out.println("hello");
    }

    /**
     * Unit test for Tuple.getField() and Tuple.setField()
     */
    @Test
    public void modifyFields() {
        TupleDesc td = Utility.getTupleDesc(2);

        Tuple tup = new Tuple(td);
        tup.setField(0, new IntField(-1));
        tup.setField(1, new IntField(0));

        assertEquals(new IntField(-1), tup.getField(0));
        assertEquals(new IntField(0), tup.getField(1));

        tup.setField(0, new IntField(1));
        tup.setField(1, new IntField(37));

        assertEquals(new IntField(1), tup.getField(0));
        assertEquals(new IntField(37), tup.getField(1));
    }
}