package org.vlir.darkaiv.dao;

import java.util.List;
import org.vlir.darkaiv.model.Document;

//import org.vlir.darkaiv.model.Employee;
import org.vlir.darkaiv.model.Identifier;

public interface DataDao {

//	public boolean addEntity(Employee employee) throws Exception;
//	public Employee getEntityById(long id) throws Exception;
//	public List<Employee> getEntityList() throws Exception;
//	public boolean deleteEntity(long id) throws Exception;
        
	public boolean addDocument(Document document) throws Exception;
	public Document getDocumentById(int id) throws Exception;
	public List<Document> getDocumentList() throws Exception;
	public boolean deleteDocument(int id) throws Exception;
        
	public boolean addIdentifier(Identifier identifier) throws Exception;
	public Identifier getIdentiferById(int id) throws Exception;
	public List<Identifier> getIdentifierList() throws Exception;
	public boolean deleteIdentifier(int id) throws Exception;
}
