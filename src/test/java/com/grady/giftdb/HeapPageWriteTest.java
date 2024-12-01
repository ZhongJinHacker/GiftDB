package com.grady.giftdb;

import com.grady.giftdb.systemtest.SystemTestUtil;
import com.grady.giftdb.systemtest.TestUtil;
import org.junit.Before;

import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class HeapPageWriteTest {

    private HeapPageId pid;

    /**
     * Set up initial resources for each unit test.
     */
    @Before
    public void addTable() throws IOException {
        this.pid = new HeapPageId(-1, -1);
        Database.getCatalog().addTable(new TestUtil.SkeletonFile(-1, Utility.getTupleDesc(2)), SystemTestUtil.getUUID());
    }

    /**
     * Unit test for HeapPage.isDirty()
     */
    @Test
    public void testDirty() throws Exception {
        TransactionId tid = new TransactionId();
        HeapPage page = new HeapPage(pid, HeapPageReadTest.EXAMPLE_DATA);
        page.markDirty(true, tid);
        TransactionId dirtier = page.isDirty();
        assertEquals(true, dirtier != null);
        assertEquals(true, dirtier == tid);

        page.markDirty(false, tid);
        dirtier = page.isDirty();
        assertEquals(false, dirtier != null);
    }
}

