package com.greencoder.fuzzyapp.com.greencoder.fuzzyapp.model;

/**
 * Created by newcomputer on 10/5/15.
 */

public class DataModel {

    private String id;
    private String type;
    private String date;
    private String data;
    private int maxCharAllowed=40;


    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The data
     */
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(String data) {
        this.data = data;
    }

    public String getSummary()
    {
        if(data!=null) {
            int totalChar = data.length();

            int summaryLenght = (totalChar > maxCharAllowed) ? maxCharAllowed : totalChar;

            return data.substring(0, summaryLenght) + "..";
        }

        return "";
    }

}

