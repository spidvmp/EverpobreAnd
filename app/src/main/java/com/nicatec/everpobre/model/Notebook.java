package com.nicatec.everpobre.model;

/**
 * Created by vtx on 18/4/16.
 */
public class Notebook {

    public static final String DEFAULT_NAME = "Notebook title";
    private long id;
    private String name;

    public Notebook(long id, String name) {

        if ( name == null || name.isEmpty() ){
            this.name = DEFAULT_NAME;
        } else {
            this.name = name;
        }

        this.id = id;

    }

    private  Notebook(){
        //deshabilito el contructor  por defecto
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
