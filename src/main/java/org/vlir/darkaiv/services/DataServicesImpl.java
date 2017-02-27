package org.vlir.darkaiv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.vlir.darkaiv.dao.DataDao;
import org.vlir.darkaiv.model.Document;
import org.vlir.darkaiv.model.Identifier;

public class DataServicesImpl implements DataServices {

    @Autowired
    DataDao dataDao;


    @Override
    public boolean addDocument(Document document) throws Exception {
        return dataDao.addDocument(document);
    }

    @Override
    public Document getDocumentById(int id) throws Exception {
        return dataDao.getDocumentById(id);
    }

    @Override
    public List<Document> getDocumentList() throws Exception {
        return dataDao.getDocumentList();
    }

    @Override
    public boolean deleteDocument(int id) throws Exception {
        return dataDao.deleteDocument(id);
    }

    @Override
    public boolean addIdentifier(Identifier identifier) throws Exception {
        return dataDao.addIdentifier(identifier);
    }

    @Override
    public Identifier getIdentiferById(int id) throws Exception {
        return dataDao.getIdentiferById(id);
    }

    @Override
    public List<Identifier> getIdentifierList() throws Exception {
        return dataDao.getIdentifierList();
    }

    @Override
    public boolean deleteIdentifier(int id) throws Exception {
        return dataDao.deleteIdentifier(id);
    }

}
