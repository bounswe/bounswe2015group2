package edu.boun.cmpe451.group2.android.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class includes all attributes of a Recipe
 *
 * @author Mustafa Taha Kocyigit
 */
public class Recipe {
    public Long id ;
    public String name ;
    public String pictureAddress;
    public Long ownerID ;
    public Long likes ;
    public List<Comment> commentList ;
    public HashMap<Ingredient, Long> IngredientAmountMap ;
    public List<Tag> tagList ;
    public String description ;
    public double totalProtein;
    public double totalFat;
    public double totalCarb;
    public double totalCal;

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

    public HashMap<Ingredient, Long> getIngredientAmountMap() {
        return IngredientAmountMap;
    }

    public void setIngredientAmountMap(HashMap<Ingredient, Long> ingredientAmountMap) {
        IngredientAmountMap = ingredientAmountMap;
    }

    public List<Tag> getTagList() {
        return tagList;
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
}
