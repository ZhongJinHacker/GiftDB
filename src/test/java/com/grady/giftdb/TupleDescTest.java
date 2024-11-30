package com.grady.giftdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TupleDescTest {

    /**
     * Unit test for TupleDesc.combine()
     */
    @Test public void combine() {
        TupleDesc td1, td2, td3;

        td1 = Utility.getTupleDesc(1, "td1");
        td2 = Utility.getTupleDesc(2, "td2");

        // test td1.combine(td2)
        td3 = TupleDesc.merge(td1, td2);
        assertEquals(3 , td3.numFields());
        assertEquals(3 * Type.INT_TYPE.getLen(), td3.getSize());
        for (int i = 0; i < 3; ++i)
            assertEquals(Type.INT_TYPE, td3.getFieldType(i));
        assertEquals(combinedStringArrays(td1, td2, td3), true);

        // test td2.combine(td1)
        td3 = TupleDesc.merge(td2, td1);
        assertEquals(3 , td3.numFields());
        assertEquals(3 * Type.INT_TYPE.getLen(), td3.getSize());
        for (int i = 0; i < 3; ++i)
            assertEquals(Type.INT_TYPE, td3.getFieldType(i));
        assertEquals(combinedStringArrays(td2, td1, td3), true);

        // test td2.combine(td2)
        td3 = TupleDesc.merge(td2, td2);
        assertEquals(4 , td3.numFields());
        assertEquals(4 * Type.INT_TYPE.getLen(), td3.getSize());
        for (int i = 0; i < 4; ++i)
            assertEquals(Type.INT_TYPE, td3.getFieldType(i));
        assertEquals(combinedStringArrays(td2, td2, td3), true);
    }

    /**
     * Ensures that combined's field names = td1's field names + td2's field names
     */
    private boolean combinedStringArrays(TupleDesc td1, TupleDesc td2, TupleDesc combined) {
        for (int i = 0; i < td1.numFields(); i++) {
            if (!(((td1.getFieldName(i) == null) && (combined.getFieldName(i) == null)) ||
                    td1.getFieldName(i).equals(combined.getFieldName(i)))) {
                return false;
            }
        }

        for (int i = td1.numFields(); i < td1.numFields() + td2.numFields(); i++) {
            if (!(((td2.getFieldName(i-td1.numFields()) == null) && (combined.getFieldName(i) == null)) ||
                    td2.getFieldName(i-td1.numFields()).equals(combined.getFieldName(i)))) {
                return false;
            }
        }

        return true;
    }
}

