package com.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LibraryTest {
    Library l = new Library();
    int writerscount = 0;
    int readerscount = 0;
    ArrayList<Member> list = l.createMembersRand(l, 8, 3);

    @Test
    void testCreateMembers() {

        Assertions.assertEquals(8, list.size());
        for (Member t : list) {
            if (t.type == memberType.WRITER)
                writerscount++;
            else
                readerscount++;
        }
        Assertions.assertEquals(5, readerscount);
        Assertions.assertEquals(3, writerscount);
    }

    /**
     * test whether Semaphores are fair, and whether they are released after action, and whether room is occupied by a reader or a writer
     */
    @Test
    void testRequest() {

        Member m=list.get(0);
        m.start();
        Assertions.assertTrue(l.queue.isFair());
        Assertions.assertEquals(0,l.queue.getQueueLength());
    }
    @Test
    void testSleep() {
        Member m=list.get(0);
        Assertions.assertEquals(Thread.State.NEW,m.getState());
        m.start();
        m.library.sleep(5000);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, m.getState());

    }
}
