package com.library;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        ArrayList<Member> list = library.createMembersRand(library,13,3);
        for (Thread t : list) {
            System.out.printf("%s, ",t.getName());
        }
        System.out.printf("\n");
        for (Thread t : list) {
            t.start();
            library.sleep(20);
        }
    }
}