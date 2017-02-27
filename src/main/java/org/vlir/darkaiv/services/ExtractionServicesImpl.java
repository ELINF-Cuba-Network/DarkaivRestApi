/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.vlir.darkaiv.exceptions.ConnectionCanNotBeEstablishException;
import org.vlir.darkaiv.exceptions.OnlineConnectionFailsException;
import org.vlir.darkaiv.mappers.CRDocumentFactory;
import org.vlir.darkaiv.mappers.GrobidDocumentFactory;
import org.vlir.darkaiv.mappers.TikaDocumentFactory;
import org.vlir.darkaiv.mappers.WCDocumentFactory;
import org.vlir.darkaiv.md_extractor.GrobidExtractor;
import org.vlir.darkaiv.md_extractor.MDExtractorStrategy;
import org.vlir.darkaiv.md_extractor.TikaExtractor;
import org.vlir.darkaiv.model.Document;
import org.vlir.darkaiv.reviewer.CrossRef;
import org.vlir.darkaiv.reviewer.WorldCat;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.vlir.darkaiv.mappers.DocumentToDC;
import org.vlir.darkaiv.model.Identifier;

/**
 *
 * @author daniel
 */
public class ExtractionServicesImpl implements ExtractionServices {

    // use spring to autowired the load of these components
    @Autowired
    protected GrobidExtractor grobidExtractor;
    @Autowired
    protected CrossRef crossrefReviewer;
    @Autowired
    protected WorldCat worldcatReviwer;
    @Autowired
    protected TikaDocumentFactory tikaDocumentFactory;
    @Autowired
    protected GrobidDocumentFactory grobidDocumentFactory;
    @Autowired
    protected CRDocumentFactory crossrefDocumentFactory;

    @Autowired
    protected WCDocumentFactory worldcatDocumentFactory;

//    protected DocumentToDC dcTraslator = new DocumentToDC();
    @Autowired
    private DocumentToDC dcTraslator;

    public WorldCat getWorldcatReviwer() {
        return worldcatReviwer;
    }

    public void setWorldcatReviwer(WorldCat worldcatReviwer) {
        this.worldcatReviwer = worldcatReviwer;
    }

    public GrobidExtractor getGrobidExtractor() {
        return grobidExtractor;
    }

    public void setGrobidExtractor(GrobidExtractor grobidExtractor) {
        this.grobidExtractor = grobidExtractor;
    }

    public CrossRef getCrossrefReviwer() {
        return crossrefReviewer;
    }

    public void setCrossrefReviwer(CrossRef crossrefReviwer) {
        this.crossrefReviewer = crossrefReviwer;
    }

    public ExtractionServicesImpl() {
        

    }

    public TikaDocumentFactory getTikaDocumentFactory() {
        return tikaDocumentFactory;
    }

    public void setTikaDocumentFactory(TikaDocumentFactory tikaDocumentFactory) {
        this.tikaDocumentFactory = tikaDocumentFactory;
    }

    public GrobidDocumentFactory getGrobidDocumentFactory() {
        return grobidDocumentFactory;
    }

    public void setGrobidDocumentFactory(GrobidDocumentFactory grobidDocumentFactory) {
        this.grobidDocumentFactory = grobidDocumentFactory;
    }

    public CRDocumentFactory getCrossrefDocumentFactory() {
        return crossrefDocumentFactory;
    }

    public void setCrossrefDocumentFactory(CRDocumentFactory crossrefDocumentFactory) {
        this.crossrefDocumentFactory = crossrefDocumentFactory;
    }

    public WCDocumentFactory getWorldcatDocumentFactory() {
        return worldcatDocumentFactory;
    }

    public void setWorldcatDocumentFactory(WCDocumentFactory worldcatDocumentFactory) {
        this.worldcatDocumentFactory = worldcatDocumentFactory;
    }

    public DocumentToDC getDocumentToDC() {
        return dcTraslator;
    }

    public void setDocumentToDC(DocumentToDC documentToDC) {
        this.dcTraslator = documentToDC;
    }

    @Override
    public String processWorkflow(File file) {

        //the complete error handling is done in this method!!!
        // Complete workflow to metadata extraction
        Document doc = null;
        try {
            doc = metadataExtractionByTika(file);

            metadataExtractionByGrodib(doc, file);
//This data are for test purpose
//            doc.setType("book");
//            Identifier isbn = new Identifier();
//            isbn.setType("isbn");
//            isbn.setValue("078946988X");
//            doc.getIdentifiers().add(isbn);
            if (doc.getType().equals("book")) {

                metadataExtractionByWorldCat(doc);

            }

//            doc.setType("article");
//            Identifier doi = new Identifier();
//            doi.setType("doi");
//            doi.setValue("10.1037/0003-066X.59.1.29");
//            doc.getIdentifiers().add(doi);
            if (doc.getType().equals("article")) {
                System.out.println("Reviewing with CrossRef");

                metadataValidationByCrossRef(doc);

            }

        } catch (IOException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OnlineConnectionFailsException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionCanNotBeEstablishException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        HashMap<String, Object> metadataDC = (HashMap<String, Object>) dcTraslator.traslate(doc.exportMetadata());
        org.vlir.darkaiv.dc.model.Document docDC = new org.vlir.darkaiv.dc.model.Document();
        
        try {
            docDC.importMetadata(metadataDC);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExtractionServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

//        return docDC;
        Gson gson = new Gson();
        String json = gson.toJson(doc);
        System.out.println(json);

        return json;

    }

    private Document metadataExtractionByTika(File file) throws IOException, OnlineConnectionFailsException, IllegalArgumentException, IllegalAccessException {
        // use the abstraction instead the concret class
        // here it should be tehn exception handling
        System.out.println("Extracting with Tika");
        MDExtractorStrategy tika = TikaExtractor.instance();
        HashMap<String, Object> metadataTika = tika.getMetadata(file);

        Document doc = new Document();
        Map<String, Object> metadataTras = tikaDocumentFactory.traslate(metadataTika);
        Set<String> keys = metadataTras.keySet();
        for (String key : keys) {
            System.out.println(key + " " + metadataTras.get(key));
        }

        doc.importMetadata(metadataTras);

        return doc;
    }

    public Document metadataExtractionByGrodib(Document doc, File file) throws IOException, OnlineConnectionFailsException, IllegalArgumentException, IllegalAccessException {

        HashMap<String, Object> metadataGrobid = grobidExtractor.getMetadata(file);
        System.out.println("Size metadata extracted by grobid " + metadataGrobid.size());
        Map<String, Object> metadataTras = grobidDocumentFactory.traslate(metadataGrobid);
        Set<String> keys = metadataTras.keySet();
        for (String key : keys) {
            System.out.println(key + " " + metadataTras.get(key));
        }

        doc.importMetadata(metadataTras);
       
        return doc;
    }

    private Document metadataValidationByCrossRef(Document doc) throws IOException, OnlineConnectionFailsException, IllegalArgumentException, IllegalAccessException, ConnectionCanNotBeEstablishException {

        System.out.println("Validating with CrossRef");

        HashMap<String, Object> fromWC = crossrefReviewer.reviewMetadata(doc);

        doc.importMetadata(fromWC);

        return doc;
    }

    private Document metadataExtractionByWorldCat(Document doc) throws IOException, OnlineConnectionFailsException, IllegalArgumentException, IllegalAccessException, ConnectionCanNotBeEstablishException {

        System.out.println("Validating with WorldCat");

        HashMap<String, Object> fromWC = worldcatReviwer.reviewMetadata(doc);

        doc.importMetadata(fromWC);

        return doc;
    }

}
