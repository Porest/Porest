package com.planb.porest.support.vo;

/**
 * Created by dsm2016 on 2017-07-22.
 */

public class Tree {
    public static int focusPosition = 0;

    public int index;
    public String name;
    public int currentLeaves;
    public int maxLeaves;
    public String date;
    public boolean isShared;
    public int likeCount;

    public Tree(int index, String name, int currentLeaves, int maxLeaves, String date, boolean isShared, int likeCount) {
        this.index = index;
        this.name = name;
        this.currentLeaves = currentLeaves;;
        this.maxLeaves = maxLeaves;
        this.date = date;
        this.isShared = isShared;
        this.likeCount = likeCount;
    }
}
