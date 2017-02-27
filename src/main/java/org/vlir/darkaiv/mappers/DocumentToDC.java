/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.mappers;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author daniel
 */
public class DocumentToDC {

    private Map<String, Object> traslator;

    public Map<String, Object> getTraslator() {
        return traslator;
    }

    public void setTraslator(Map<String, Object> traslator) {
        this.traslator = traslator;
    }

    public DocumentToDC() {


    }

    public Map<String, Object> traslate(Map<String, Object> metadata) {

        Map<String, Object> wFieldsTraslated = new HashMap<>();

        // saving data to document
        for (String field : metadata.keySet()) {

            // key comes from Tika and should be mapped to our bd
            if (metadata.get(field) != null) {
//                doc.set(metadataMapperDocument.get(field), metadata.get(field));
                wFieldsTraslated.put(metadata.get(field).toString(), metadata.get(field));
            }
        }
        
        return wFieldsTraslated;

    }

}
