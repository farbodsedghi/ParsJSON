package com.tredolf.demo.parsjson.json;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * You just need to change these class to make JSON daa model
 * Includes constructor, parameters, setters, and getters
 * Created by Farbod Sedghi on 3/3/2015.
 */
public class PostObject {
    protected int pID;
    protected String pTitle;
    protected String pContent;
    protected String pExcerpt;
    protected String pDate;

    private JSONObject obj;

    public PostObject(JSONObject obj) throws JSONException {
        this.obj = obj;

        this.setID();
        this.setTitle();
        this.setContent();
        this.setExcerpt();
        this.setDate();
    }

    public int getID() {
        return pID;
    }

    public void setID() throws JSONException {
        if (!obj.has("id")) {
            this.pID = -1;
        } else {
            this.pID = obj.getInt("id");
        }
    }

    public String getTitle() {
        return pTitle;
    }

    public void setTitle() throws JSONException {
        if (!obj.has("title")) {
            this.pTitle = "None";
        } else {
            this.pTitle = obj.getString("title");
        }
    }

    public String getContent() {
        return pContent;//Html.fromHtml(pContent).toString();
    }

    public void setContent() throws JSONException {
        if (!obj.has("content")) {
            this.pContent = "None";
        } else {
            this.pContent = obj.getString("content");
        }
    }

    public String getExcerpt() {
        return pExcerpt;
    }

    public void setExcerpt() throws JSONException {
        if (!obj.has("excerpt")) {
            this.pExcerpt = "None";
        } else {
            this.pExcerpt = obj.getString("excerpt");
        }
    }

    public String getDate() {
        return pDate;
    }

    public void setDate() throws JSONException {
        if (!obj.has("date")) {
            this.pDate = "None";
        } else {
            this.pDate = obj.getString("date");
        }
    }

}
