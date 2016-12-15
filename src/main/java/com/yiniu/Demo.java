package com.yiniu;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    private Integer height;
    private Integer width;
    private Integer length;
    private String name;

    public Demo(Integer height, Integer width, Integer length, String name) {
        super();
        this.height = height;
        this.width = width;
        this.length = length;
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }


    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        Demo d0 = new Demo(0, 3, 4, "tom");
        Demo d1 = new Demo(1, 3, 4, "tom");
        Demo d2 = new Demo(2, 3, 4, "tom");
        Demo d3 = new Demo(3, 3, 4, "tom");
        List<Demo> list = new ArrayList<Demo>();
        list.add(d0);
        list.add(d1);
        list.add(d2);
        list.add(d3);
        Instances instances = Instances.initInstances(list, null);
        for (Instance ins : instances.getInstances()) {
            System.out.println(ins.getAttributeNum());
        }
    }

}
