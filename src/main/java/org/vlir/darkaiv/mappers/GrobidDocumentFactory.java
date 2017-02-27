/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.vlir.darkaiv.exceptions.DocumentNotFoundException;
import org.vlir.darkaiv.model.Document;
import org.vlir.darkaiv.model.Identifier;

/**
 *
 * @author daniel
 */
public class GrobidDocumentFactory extends DocumentFactory {

    public GrobidDocumentFactory() {
        super();
        
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

        //obtaining the document type
        if (metadata.get("volume") != null || metadata.get("source") != null
                || metadata.get("issn") != null) {
            metadata.put("type", "journal-article");
        }
        if (metadata.get("isbn") != null) {
            metadata.put("type", "book");
        }
        for (String field : metadataMapperDocument.keySet()) {

            // key comes from Grobid and should be mapped to our bd
            if (metadata.get(field) != null) {
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
