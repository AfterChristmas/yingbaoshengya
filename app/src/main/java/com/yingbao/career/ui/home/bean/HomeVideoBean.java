package com.yingbao.career.ui.home.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Description:
 * @Date: 2020/3/29 11:16
 * @Auther: wanyan
 */
public class HomeVideoBean implements MultiItemEntity{

    public static final int VIDEO = 0;
    public static final int TIME = 1;
    private String imagUrl;
    private String name;
    private String personIconUrl;
    private int itemType;
    //一行有几个布局
    private int spanSize;

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonIconUrl() {
        return personIconUrl;
    }

    public void setPersonIconUrl(String personIconUrl) {
        this.personIconUrl = personIconUrl;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}
