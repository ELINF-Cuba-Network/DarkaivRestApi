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
public class CRDocumentFactory extends DocumentFactory {
    // separate document's hashmap to identifier's hashmap (...)

    public CRDocumentFactory() {
        super();
 
    }
   
    // change document to long
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

                if (field.equals("type")) {
                    if (!(metadata.get(field).equals("journal-article") || metadata.get(field).equals("book")
                            || metadata.get(field).equals("proceedings"))) {
                        metadata.put("type", "generic");
                    }
                }

                wFieldsTraslated.put(metadataMapperDocument.get(field), metadata.get(field));
            }
        }

        for (String field : metadataMapperIdentifier.keySet()) {

            if (metadata.get(field) != null) {

                wFieldsTraslated.put(metadataMapperIdentifier.get(field), metadata.get(field));
            }
        }


        return wFieldsTraslated;

    }
}
