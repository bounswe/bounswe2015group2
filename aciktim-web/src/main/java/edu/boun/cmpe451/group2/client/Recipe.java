package edu.boun.cmpe451.group2.client;

import java.util.*;

/**
 * This class includes all attributes of a Recipe
 *
 * @author Mustafa Taha Kocyigit
 */
public class Recipe {
    public Long id = null;
    public String name = "";
    public String pictureAddress = "";
    public Long ownerID = null;
    public Long likes = 0L;
    public List<Comment> commentList = new ArrayList<Comment>();
    public List<Ingredient> IngredientList = new LinkedList<>();
    public List<Tag> tagList = new ArrayList<Tag>();
    public String description = "";
    public double totalProtein=0;

    public List<Tag> getTagList() {
        return tagList;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getTotalCarb() {
        return totalCarb;
    }

    public void setTotalCarb(double totalCarb) {
        this.totalCarb = totalCarb;
    }

    public double getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(double totalCal) {
        this.totalCal = totalCal;
    }

    public double totalFat=0;
    public double totalCarb=0;
    public double totalCal=0;


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

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Ingredient> getIngredientList() {
        return IngredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        IngredientList = ingredientList;
    }



    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return name + " " + id;
    }


}
