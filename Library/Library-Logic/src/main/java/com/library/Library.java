package com.library;
import java.util.ArrayList;
import java.util.concurrent.*;

import static java.util.Collections.shuffle;

public class Library {
    Semaphore room;
    Semaphore queue;
    int maxReaders = 5;


    public Library(){
        this.room = new Semaphore(maxReaders, true);
        this.queue = new Semaphore(1, true);
    }
    /**
     * Instead of making 2 methods for writer and reader there is one that take argument int permit that allows to
     *         differentiate reader and writer
     *         First we acquire queue semaphore to create something like a memory unit. We always know what type of Member is first in line
     *         Semaphore room is like a virtual library. When a writer can enter library it blocks this semaphore with permits=5.
     *         When members successfully acquires room semaphore it releases queue.
     */
    public void request(String name,int permits){

        System.out.println(name + " wants to enter the library.");
        try {
            queue.acquire();
            room.acquire(permits);
            if(permits==1) {
                System.out.println(name + " is currently reading");
            }
            else {
                System.out.println(name + " is currently writing");

            }
            sleep(10);
            queue.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
    }
    public void finish(String name, int permit){
        /**
         *  Releases room Semaphore after Thread finished reading/writing
         */
        System.out.println(name + " finished reading.");
        room.release(permit);
    }
    public  ArrayList<Member> createMembersRand(Library library,int amount,int writers) {
        //Creates Members with given amount of Readers and Writers
        ArrayList<Member> list = new ArrayList<>();
        for (int i = 0; i < amount-writers; i++) {
                Member memberTemp = new Member(library, "R" + i,memberType.READER);
                list.add(memberTemp);

        }
        for (int i = 0; i < writers; i++) {
            Member memberTemp = new Member(library, "W" + i,memberType.WRITER);
            list.add(memberTemp);
        }
        shuffle(list);
        return list;
    }
    void sleep(int time){
        /**
        * Handles Thread sleeping
        */
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

