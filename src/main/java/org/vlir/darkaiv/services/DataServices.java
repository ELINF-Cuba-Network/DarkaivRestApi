package org.vlir.darkaiv.services;

import java.util.List;
import org.vlir.darkaiv.model.Document;

import org.vlir.darkaiv.model.Identifier;

public interface DataServices {
        
        public boolean addDocument(Document document) throws Exception;
	public Document getDocumentById(int id) throws Exception;
	public List<Document> getDocumentList() throws Exception;
	public boolean deleteDocument(int id) throws Exception;
        
	public boolean addIdentifier(Identifier identifier) throws Exception;
	public Identifier getIdentiferById(int id) throws Exception;
	public List<Identifier> getIdentifierList() throws Exception;
	public boolean deleteIdentifier(int id) throws Exception;
}
