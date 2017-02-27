package org.vlir.darkaiv.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.vlir.darkaiv.model.Document;
import org.vlir.darkaiv.model.Identifier;

public class DataDaoImpl implements DataDao {

    @Autowired
    SessionFactory sessionFactory;

    Session session = null;
    Transaction tx = null;



    @Override
    public boolean addDocument(Document document) throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(document);
        tx.commit();
        session.close();

        return false;
    }

    @Override
    public Document getDocumentById(int id) throws Exception {
        session = sessionFactory.openSession();
        Document document = (Document) session.load(Document.class,
                new Long(id));
        tx = session.getTransaction();
        session.beginTransaction();
        tx.commit();

        return document;

    }

    @Override
    public List<Document> getDocumentList() throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<Document> documentList = session.createCriteria(Document.class)
                .list();
        tx.commit();
        session.close();
        return documentList;
    }

    @Override
    public boolean deleteDocument(int id) throws Exception {
        session = sessionFactory.openSession();
        Object o = session.load(Document.class, id);
        tx = session.getTransaction();
        session.beginTransaction();
        session.delete(o);
        tx.commit();
        return false;
    }

    @Override
    public boolean addIdentifier(Identifier identifier) throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(identifier);
        tx.commit();
        session.close();

        return false;
    }

    @Override
    public Identifier getIdentiferById(int id) throws Exception {
        session = sessionFactory.openSession();
        Identifier identifier = (Identifier) session.load(Identifier.class,
                new Long(id));
        tx = session.getTransaction();
        session.beginTransaction();
        tx.commit();

        return identifier;
    }

    @Override
    public List<Identifier> getIdentifierList() throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<Identifier> identifierList = session.createCriteria(Identifier.class)
                .list();
        tx.commit();
        session.close();
        return identifierList;
    }

    @Override
    public boolean deleteIdentifier(int id) throws Exception {
        session = sessionFactory.openSession();
        Object o = session.load(Identifier.class, id);
        tx = session.getTransaction();
        session.beginTransaction();
        session.delete(o);
        tx.commit();
        return false;
    }

}
