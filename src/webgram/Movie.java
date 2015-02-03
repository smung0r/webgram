/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webgram;

import java.util.Date;

/**
 *
 * @author hannes.schulze01
 */
public class Movie {
    private String name;
    private String description;
    private String imageUri;
    private Date date;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getDate() + " / " + this.getName() + " / " + this.getDescription() + " / " + this.getImageUri(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the imageUri
     */
    public String getImageUri() {
        return imageUri;
    }

    /**
     * @param imageUri the imageUri to set
     */
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
