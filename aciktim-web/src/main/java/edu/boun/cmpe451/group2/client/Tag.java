package edu.boun.cmpe451.group2.client;


public class Tag {
    public Long id = null;
    public String name ="";
    public String parentTag="";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTag() {
        return parentTag;
    }

    public void setParentTag(String parentTag) {
        this.parentTag = parentTag;
    }

    public String toString() {
        return name;
    }
}
