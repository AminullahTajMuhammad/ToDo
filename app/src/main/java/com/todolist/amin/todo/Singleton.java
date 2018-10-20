package com.todolist.amin.todo;

import java.util.ArrayList;

public class Singleton {
    private static Singleton sSoleInstance;

    public ArrayList<Task> arrayList = new ArrayList<>();

    private Singleton(){}  //private constructor.

    public static Singleton getInstance(){
        if (sSoleInstance == null) {
            //if there is no instance available... create new one
            sSoleInstance = new Singleton();
            }
            return sSoleInstance;
        }
}
