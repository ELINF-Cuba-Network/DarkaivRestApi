/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.vlir.darkaiv.mappers.CRDocumentFactory;
import org.vlir.darkaiv.mappers.GrobidDocumentFactory;
import org.vlir.darkaiv.mappers.TikaDocumentFactory;
import org.vlir.darkaiv.mappers.WCDocumentFactory;
import org.vlir.darkaiv.md_extractor.GrobidExtractor;
import org.vlir.darkaiv.reviewer.CrossRef;
import org.vlir.darkaiv.reviewer.WorldCat;
import org.vlir.darkaiv.services.ExtractionServicesImpl;

/**
 *
 * @author daniel
 */
public class MockExtractionServiceImpl extends ExtractionServicesImpl{

    public MockExtractionServiceImpl() {
        // open and close the database with a database manager
        // tika mapper could be a singleton
//        ApplicationContext context =
//    	new ClassPathXmlApplicationContext("spring-config.xml");
        ApplicationContext context =
    	new FileSystemXmlApplicationContext("./config/spring-config.xml");
//        AbstractApplicationContext context = new FileSystemXmlApplicationContext("./config/mappers/tika/tika_bean.xml");
        super.tikaDocumentFactory = (TikaDocumentFactory) context.getBean("tika");
//        tikaDocumentFactory = new TikaDocumentFactory();
//        context = new FileSystemXmlApplicationContext("/home/daniel/Belgium_Work/Develop/github/DarkaivRestApi/config/mappers/grobid/grobid_bean.xml");
        super.grobidDocumentFactory = (GrobidDocumentFactory) context.getBean("grobid");
//        grobidDocumentFactory = new GrobidDocumentFactory();
//        context = new FileSystemXmlApplicationContext("/home/daniel/Belgium_Work/Develop/github/DarkaivRestApi/config/mappers/crossref/crossref_bean.xml");
        super.crossrefDocumentFactory = (CRDocumentFactory) context.getBean("crossref");
//        crossrefDocumentFactory = new CRDocumentFactory();
//        context = new FileSystemXmlApplicationContext("/home/daniel/Belgium_Work/Develop/github/DarkaivRestApi/config/mappers/worldcat/worldcat_bean.xml");
        super.worldcatDocumentFactory = (WCDocumentFactory) context.getBean("worldcat");
//        worldcatDocumentFactory = new WCDocumentFactory();
        super.grobidExtractor = (GrobidExtractor) context.getBean("grobidExtractor");
        super.crossrefReviewer = (CrossRef) context.getBean("crossrefReviewer");
        super.worldcatReviwer = (WorldCat) context.getBean("worldcatReviewer");
    }
    
    
    
}
