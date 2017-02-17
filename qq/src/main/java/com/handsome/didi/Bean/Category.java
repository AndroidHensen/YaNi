package com.handsome.didi.Bean;

/**
 * Created by handsome on 2016/4/13.
 */
public class Category {

    private long id;
    private String img_url;
    private String go_url;
    private String name;
    private int sort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGo_url() {
        return go_url;
    }

    public void setGo_url(String go_url) {
        this.go_url = go_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
