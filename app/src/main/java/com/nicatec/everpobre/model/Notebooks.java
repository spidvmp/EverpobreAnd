package com.nicatec.everpobre.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vtx on 18/4/16.
 */
public class Notebooks {

    List<Notebook> notebooks;

    //factory static method
    public static Notebooks createNotebooks(List<Notebook> notebooks){
        Notebooks myNotebooks = new Notebooks();

        for (Notebook n: notebooks) {
            myNotebooks.add(n);
        }
        
        return myNotebooks;
    }

    private Notebooks(){

    }

    //lazygetter
    public Notebooks getNotebooks() {
        if ( this.notebooks == null) {
            this.notebooks = new LinkedList<>();
        }
        return this.notebooks;
    }


    public void add(Notebook n) {
        getNotebooks().add(n);
    }
}
