/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.mappers;

import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author daniel
 */
public class DocumentFactory {

    protected Map< String, String> metadataMapperDocument;
    protected Map< String, String> metadataMapperIdentifier;
    private String authorKey;

    public DocumentFactory() {
    }

    public Map<String, String> getMetadataMapperDocument() {
        return metadataMapperDocument;
    }

    public void setMetadataMapperDocument(HashMap<String, String> metadataMapperDocument) {
        this.metadataMapperDocument = metadataMapperDocument;
    }

    public Map<String, String> getMetadataMapperIdentifier() {
        return metadataMapperIdentifier;
    }

    public void setMetadataMapperIdentifier(HashMap<String, String> metadataMapperIdentifier) {
        this.metadataMapperIdentifier = metadataMapperIdentifier;
    }

    /**
     *
     * @param collection
     * @param metadata
     * @return create a document with metadata HashMap and return the id in
     * database
     */
    public Map<String, Object> traslate(Map<String, Object> metadata) throws IllegalArgumentException, IllegalAccessException {


        Map<String, Object> wFieldsTraslated = new HashMap<>();

        // saving data to document
        for (String field : metadataMapperDocument.keySet()) {

            // key comes from Tika and should be mapped to our bd
            if (metadata.get(field) != null) {

                wFieldsTraslated.put(metadataMapperDocument.get(field), metadata.get(field));
            }
        }

        // saving into year the first 4 characters of creation-date
        if (metadata.containsKey("creation-date") && metadata.get("creation-date") != null) {
            try {
                Integer.decode(metadata.get("creation-date").toString().substring(0, 4));
                wFieldsTraslated.put("year", metadata.get("creation-date").toString().substring(0, 4));
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }

        // setting generic as default document type
        if (!metadata.containsKey("type") || metadata.get("type") == null) {
            wFieldsTraslated.put("type","generic");
        }

        // put data about identifiers on wFieldsTraslated
        for (String field : metadataMapperIdentifier.keySet()) {

            // key comes from Tika and should be mapped to our bd
            if (metadata.get(field) != null) {

                wFieldsTraslated.put(metadataMapperIdentifier.get(field), metadata.get(field));

            }
        }

        return wFieldsTraslated;
    }


}


