package edu.boun.cmpe451.group2.android.ingredient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cafer Tayyar YORUK on 06.12.2015.
 */
public class IngredientItem {
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ndbno")
    @Expose
    private String ndbno;

    public IngredientItem( String IngredientName, String IngredientNDBNO ){
        setName( IngredientName );
        setNdbno( IngredientNDBNO );
    }

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The group
     */
    public String getGroup() {
        return group;
    }

    /**
     *
     * @param group
     * The group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The ndbno
     */
    public String getNdbno() {
        return ndbno;
    }

    /**
     *
     * @param ndbno
     * The ndbno
     */
    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }
}
