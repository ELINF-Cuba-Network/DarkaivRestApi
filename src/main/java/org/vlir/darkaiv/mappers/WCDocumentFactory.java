/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.mappers;

import java.util.HashMap;
import java.util.Map;
import org.vlir.darkaiv.exceptions.DocumentNotFoundException;
import org.vlir.darkaiv.model.Document;

/**
 *
 * @author admin
 */
public class WCDocumentFactory extends DocumentFactory {
    
    public WCDocumentFactory() {

       
    }


    /**
     *
     * @param doc
     * @param metadata
     * @return update a document with metadata HashMap and return the id in
     * database
     * @throws org.vlir.darkaiv.exceptions.DocumentNotFoundException
     */
    @Override
public Map<String, Object> traslate(Map<String, Object> metadata) throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> wFieldsTraslated = new HashMap<>();       

        for (String field : metadataMapperDocument.keySet()) {

            // key comes from Tika and should be mapped to our bd
            if (metadata.get(field) != null) {
                wFieldsTraslated.put(metadataMapperDocument.get(field), metadata.get(field));
            }
        }

        return wFieldsTraslated;
    }
    
}
