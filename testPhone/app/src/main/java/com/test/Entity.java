package com.test;

/**
 * desc
 * Author:shimao
 * Date:2017.09.11 10:11
 */
public class Entity {

    public Entity(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Entity(String name, String type, long time) {
        this.name = name;
        this.type = type;
        this.time = time;
    }

    private String name;
    private String type;
    private long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
