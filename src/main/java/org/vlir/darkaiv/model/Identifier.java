/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author fenriquez
 */
@Entity
@javax.persistence.Table(name = "identifier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Identifier implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    // how to play with 1-many relationships
//    private String document_id;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void set(String property, String value) {
        if (property.equals("type")) {
            this.type = value;
        }

        if (property.equals("value")) {
            this.type = value;
        }

    }

    @Override
    public String toString() {
        return "Identifier{" + "id=" + id + ", type=" + type + ", value=" + value + ", document=" + document + '}';
    }
    
    
}
