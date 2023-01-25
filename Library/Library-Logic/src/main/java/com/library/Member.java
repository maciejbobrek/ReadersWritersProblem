package com.library;

import java.util.concurrent.*;
enum memberType{
    READER,
    WRITER,
}
public class Member extends Thread{
    Semaphore sem;
    public memberType type;
    Library library;
    String name;
    Member(Library l, String threadName,memberType type){
        super(threadName);
        this.type=type;
        this.sem = l.room;
        this.library = l;
        this.name = threadName;
    }

    @Override
    public void run(){
        while(true){
            if(type==memberType.READER) {
                library.request(name,1);
                library.sleep(3000);
                library.finish(name, 1);
                library.sleep(3000);
            }
            else {
                library.request(name,5);
                library.sleep(6000);
                library.finish(name, 5);
                library.sleep(6000);
            }
        }
    }
}