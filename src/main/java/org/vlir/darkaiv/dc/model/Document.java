/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.dc.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daniel
 *
 * It models a dublic core document
 */
public class Document implements Serializable {

    private String title;

    private String date;

    private String _abstract;

    private String subject;

    private String pages;

    private String publisher;

    private String volume;

    private String language;

    private String editor;

    private String coverageSpatial;

    private String chapter;

    // type + thesis_type
    private String type;

    private String issue;

    private String doi;
    private String isbn;
    private String issn;

    public void importMetadata(Map<String, Object> metadata) throws IllegalArgumentException, IllegalAccessException {
//        Using java reflection (find another alternatives to check performance)
        Field[] fields = this.getClass().getDeclaredFields();
//        System.out.println("We are working on....");
        Set<String> keys = metadata.keySet();
        System.out.println("Printing metadata map");
        for (String key : keys) {
            System.out.println(key + " " + metadata.get(key));
        }

        for (String key : keys) {
            boolean found = false;
            for (int i = 0; !found && i < fields.length; i++) {
                // check this
                if (key.equals(fields[i].getName())) {
                    fields[i].set(this, metadata.get(key).toString());
                    found = true;
                }
            }

        }

    }

}
