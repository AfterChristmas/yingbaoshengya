package com.yingbao.career.ui.home.bean;

/**
 * @Description:
 * @Date: 2020/4/2 00:04
 * @Auther: wanyan
 */
public class FilterTagBean {
    private int tagId;
    private String tagName;
    private int subjectLevelId;
    private String subjectLevel;
    private boolean checked;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getSubjectLevelId() {
        return subjectLevelId;
    }

    public void setSubjectLevelId(int subjectLevelId) {
        this.subjectLevelId = subjectLevelId;
    }

    public String getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(String subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
