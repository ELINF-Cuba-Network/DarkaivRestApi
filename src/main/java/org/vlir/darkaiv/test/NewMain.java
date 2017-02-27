/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.vlir.darkaiv.controller.ExtractorController;
import org.vlir.darkaiv.exceptions.OnlineConnectionFailsException;
import org.vlir.darkaiv.mappers.DocumentFactory;
import org.vlir.darkaiv.mappers.GrobidDocumentFactory;
import org.vlir.darkaiv.md_extractor.GrobidExtractor;
import org.vlir.darkaiv.md_extractor.MDExtractorStrategy;
import org.vlir.darkaiv.model.Document;
import org.vlir.darkaiv.services.DataServices;

/**
 *
 * @author daniel
 */
public class NewMain {
    
    @Autowired
    DataServices dataServices;
    
    static final Logger logger = Logger.getLogger(ExtractorController.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, OnlineConnectionFailsException, IllegalArgumentException, IllegalAccessException {
        // TODO code application logic here
        MDExtractorStrategy mdE = new GrobidExtractor();
        DocumentFactory mapper = new GrobidDocumentFactory();
        File file = new File("/home/daniel/Belgium_Work/Develop/github/DarkaivRestApi/Papers/repositorios institucionales.pdf");
        
        Document doc = new Document();
        HashMap<String, Object> metadata = mdE.getMetadata(file);
        HashMap<String, Object> traslated = (HashMap<String, Object>) mapper.traslate(metadata);
        System.out.println("Metadata extracted by grobid");
        System.out.println(metadata);
        doc.importMetadata(traslated);
        System.out.println(doc);
        
    }
    
}
