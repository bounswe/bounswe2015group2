package edu.boun.cmpe451.group2.android.ingredient;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cafer Tayyar YORUK on 06.12.2015.
 */
public class IngredientList {
    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("sr")
    @Expose
    private String sr;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("end")
    @Expose
    private Integer end;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("item")
    @Expose
    private java.util.List<IngredientItem> item = new ArrayList<IngredientItem>();

    /**
     *
     * @return
     * The q
     */
    public String getQ() {
        return q;
    }

    /**
     *
     * @param q
     * The q
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     *
     * @return
     * The sr
     */
    public String getSr() {
        return sr;
    }

    /**
     *
     * @param sr
     * The sr
     */
    public void setSr(String sr) {
        this.sr = sr;
    }

    /**
     *
     * @return
     * The start
     */
    public Integer getStart() {
        return start;
    }

    /**
     *
     * @param start
     * The start
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     *
     * @return
     * The end
     */
    public Integer getEnd() {
        return end;
    }

    /**
     *
     * @param end
     * The end
     */
    public void setEnd(Integer end) {
        this.end = end;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
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
     * The sort
     */
    public String getSort() {
        return sort;
    }

    /**
     *
     * @param sort
     * The sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     *
     * @return
     * The item
     */
    public java.util.List<IngredientItem> getItem() {
        return item;
    }

    /**
     *
     * @param item
     * The item
     */
    public void setItem(java.util.List<IngredientItem> item) {
        this.item = item;
    }
}
