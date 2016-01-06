package edu.boun.cmpe451.group2.android.api;

import java.util.ArrayList;

/**
 * This class includes all attributes of a User
 *
 * @author Mustafa Taha Kocyigit
 */
public class User {
    public String id;
    public String email;
    public String passwd;
    public String full_name;
    public String username;
    public String api_key;
    public boolean isInst = false;
    public ArrayList<Tag> likes;
    public ArrayList<Tag> dislikes;
    public ArrayList<Tag> allergies;

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public String pictureAddress="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public boolean isInst() {
        return isInst;
    }

    public void setIsInst(boolean isInst) {
        this.isInst = isInst;
    }
}
