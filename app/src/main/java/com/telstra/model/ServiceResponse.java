package com.telstra.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 461495 on 11/15/2015.
 */
public class ServiceResponse {

    private String title = "";
    private List<Rows> rows = new ArrayList<Rows>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public class Rows{

        private String title = "";
        private String description = "";
        private String imageHref = "";


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }
    }
}
