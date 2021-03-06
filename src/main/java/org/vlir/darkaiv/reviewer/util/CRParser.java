/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.reviewer.util;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class CRParser {

    public CRParser() {
    }

    /**
     *
     * @param source
     * @return a HashMap<String, Object> with the metadata validated with
     * Crossref
     */
    public HashMap<String, Object> parse(String source) {

        JSONObject obj = new JSONObject(source);
        HashMap<String, Object> metadata = new HashMap<String, Object>();
        if (obj.has("message")) {
            JSONObject message = obj.getJSONObject("message");
            createCrossRefMetadata(source, message, metadata);
        } else {
            //System.out.println("obj has no message section");
        }

        return metadata;

    }

    /**
     * parse a source to convert it to update a metadata object
     *
     * @param source
     * @param obj
     * @param metadata
     */
    private void createCrossRefMetadata(String source, JSONObject obj,
            HashMap<String, Object> metadata) {
        //System.out.println("On createCrossRefMetadata method");
        if (obj != null) {
            if (obj.has("publisher")) {                
                metadata.put("publisher", obj.get("publisher"));
            }

            if (obj.has("issue")) {
                metadata.put("issue", obj.get("issue"));
            }

            if (obj.has("DOI")) {
                metadata.put("DOI", obj.get("DOI"));
            }

            if (obj.has("type")) {
                metadata.put("type", obj.get("type"));
            }

            if (obj.has("page")) {
                metadata.put("page", obj.get("page"));
            }

            if (obj.has("title")) {
                if (obj.get("title") instanceof JSONArray) {
                    JSONArray titles = obj.getJSONArray("title");
                    String title = "";
                    for (int i = 0; i < titles.length(); i++) {
                        title += titles.get(i) + " ";
                    }
                    metadata.put("title", title);
                }
            }

            if (obj.has("author")) {
                if (obj.get("author") instanceof JSONArray) {
                    JSONArray authors = obj.getJSONArray("author");
//                    String author = "";
                    for (int i = 0; i < authors.length(); i++) {                        
                        if (authors.get(i) instanceof JSONObject) {
                            parseAuthorSection((JSONObject) authors.get(i), metadata);
                        }
                    }
                }
            }

            if (obj.has("ISSN")) {
                if (obj.get("ISSN") instanceof JSONArray) {
                    JSONArray ISSN = obj.getJSONArray("ISSN");
                    String issn = "";
                    for (int i = 0; i < ISSN.length(); i++) {
                        issn += ISSN.get(i) + ",";

                    }
                    metadata.put("ISSN", issn);
                }
            }

            if (obj.has("volume")) {
                metadata.put("volume", obj.get("volume"));
            }
        }

    }

    private void parseAuthorSection(JSONObject obj, HashMap<String, Object> metadata) {

        StringBuilder sb = new StringBuilder();
        if (obj.has("family")) {
            sb.append(obj.get("family"));
            sb.append(" ");
        }

        if (obj.has("given")) {
            sb.append(obj.get("given"));
        }

        if (metadata.get("author") != null) {
            metadata.put("author", (String) metadata.get("author") + ", " + sb.toString());
        } else {
            metadata.put("author", sb.toString());
        }
    }
}
