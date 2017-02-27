/*
 Copyright 2009private String 2010 Igor Polevoy

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package org.vlir.darkaiv.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "document")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Document implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "creation_date")
    private String creation_date;

    @Column(name = "updated_date")
    private String updated_date;

    @Column(name = "abstract")
    private String _abstract;

    @Column(name = "year")
    private String year;

    @Column(name = "key_words")
    private String key_words;

    @Column(name = "pages")
    private String pages;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "volume")
    private String volume;

    @Column(name = "source")
    private String source;

    @Column(name = "idiom")
    private String idiom;

    @Column(name = "editors")
    private String editors;

    @Column(name = "edition")
    private String edition;

    @Column(name = "city")
    private String city;

    @Column(name = "chapter")
    private String chapter;

    @Column(name = "department")
    private String department;

    @Column(name = "university")
    private String university;

    @Column(name = "thesis_type")
    private String thesis_type;

    @Column(name = "issue")
    private String issue;

    @Column(name = "institution")
    private String institution;

    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "document",
            cascade = CascadeType.ALL)
    private Set<Identifier> identifiers;

    public Document() {
        identifiers = new HashSet<>();
    }

    public Set<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Set<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIdiom() {
        return idiom;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public String getEditors() {
        return editors;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getThesis_type() {
        return thesis_type;
    }

    public void setThesis_type(String thesis_type) {
        this.thesis_type = thesis_type;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> exportMetadata() {
        // we should use reflexive programming or put it one by one??
        Map<String, Object> metadata = new HashMap<>();

        metadata.put("id", id);
        metadata.put("title", title);
        metadata.put("created_at", created_at);
        metadata.put("updated_at", updated_at);
        metadata.put("creation_date", creation_date);
        metadata.put("updated_date", updated_date);
        metadata.put("abstract", _abstract);
        metadata.put("year", year);
        metadata.put("key_words", key_words);
        metadata.put("pages", pages);
        metadata.put("publisher", publisher);
        metadata.put("volume", volume);
        metadata.put("source", source);
        metadata.put("idiom", idiom);
        metadata.put("editors", editors);
        metadata.put("edition", edition);
        metadata.put("city", city);
        metadata.put("chapter", chapter);
        metadata.put("department", department);
        metadata.put("university", university);
        metadata.put("thesis_type", thesis_type);
        metadata.put("issue", issue);
        metadata.put("institution", institution);
        metadata.put("type", type);

//        Retrieving the identifiers 
        for (Identifier identifier : identifiers) {
            metadata.put(identifier.getType(), identifier.getValue());
        }

        return metadata;
    }

    public void importMetadata(Map<String, Object> metadata) throws IllegalArgumentException, IllegalAccessException {
//        Using java reflection (find another alternatives to check performance)
        Field[] fields = this.getClass().getDeclaredFields();
//        System.out.println("We are working on....");
        Set<String> keys = metadata.keySet();
        System.out.println("Printing metadata map");
        for (String key : keys) {
            System.out.println(key + " " + metadata.get(key));
        }
//        for (String key : keys) {
//            if (key.equals("doi")) {
//                Identifier doi = new Identifier();
//                doi.setType("doi");
//                doi.setValue(metadata.get(key).toString());
//                // how to put into table identifier the link to doc
//
//                identifiers.add(doi);
//            }
//            if (key.equals("isbn")) {
//                Identifier isbn = new Identifier();
//                isbn.setType("isbn");
//                isbn.setValue(metadata.get(key).toString());
//                // how to put into table identifier the link to doc
//
//                identifiers.add(isbn);
//            }
//        }
        for (String key : keys) {
            boolean found = false;
            for (int i = 0; !found && i < fields.length; i++) {
                // check this
                if (key.equals(fields[i].getName())) {
                    fields[i].set(this, metadata.get(key).toString());
                    found = true;
                }
            }
            if (key.equals("doi")) {
                Identifier ident = new Identifier();
                ident.setType("doi");
                ident.setValue(metadata.get(key).toString());
                identifiers.add(ident);
            }
            if (key.equals("isbn")) {
                Identifier ident = new Identifier();
                ident.setType("isbn");
                ident.setValue(metadata.get(key).toString());
                identifiers.add(ident);
            }

            if (key.equals("issn")) {
                Identifier ident = new Identifier();
                ident.setType("issn");
                ident.setValue(metadata.get(key).toString());
                identifiers.add(ident);
            }
        }

    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", title=" + title + ", created_at=" + created_at + ", updated_at=" + updated_at + ", creation_date=" + creation_date + ", updated_date=" + updated_date + ", _abstract=" + _abstract + ", year=" + year + ", key_words=" + key_words
                + ", pages=" + pages + ", publisher=" + publisher + ", volume=" + volume + ", source=" + source + ", idiom=" + idiom + ", editors=" + editors + ", edition=" + edition + ", city=" + city + ", chapter="
                + chapter + ", department=" + department + ", university=" + university + ", thesis_type=" + thesis_type + ", issue=" + issue + ", institution=" + institution + ", type=" + type + ", identifiers=" + identifiers + '}';
    }

}
