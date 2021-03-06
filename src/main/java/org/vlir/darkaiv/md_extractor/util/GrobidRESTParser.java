/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vlir.darkaiv.md_extractor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.xml.sax.ContentHandler;

public class GrobidRESTParser {

    private static final String GROBID_REST_HOST = "http://localhost:8080";

    private static final String GROBID_ISALIVE_PATH = "/grobid"; // isalive
    // doesn't work
    // nfc why

    private static final String GROBID_PROCESSHEADER_PATH = "/processHeaderDocument";

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    
    

    public GrobidRESTParser() {
        String restHostUrlStr = null;
        try {
            restHostUrlStr = readRestUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (restHostUrlStr == null
                || (restHostUrlStr != null && restHostUrlStr.equals(""))) {
            this.host = GROBID_REST_HOST;
        } else {
            this.host = restHostUrlStr;
        }
    }

    public void parse(String filePath, ContentHandler handler, Metadata metadata,
            ParseContext context) throws FileNotFoundException {

        File pdfFile = new File(filePath);
        ContentDisposition cd = new ContentDisposition(
                "form-data; name=\"input\"; filename=\"" + pdfFile.getName() + "\"");
        Attachment att = new Attachment("input", new FileInputStream(pdfFile), cd);
        MultipartBody body = new MultipartBody(att);

        Response response = WebClient
                .create(host + GROBID_PROCESSHEADER_PATH)
                .accept(MediaType.APPLICATION_XML).type(MediaType.MULTIPART_FORM_DATA)
                .post(body);

        try {
            String resp = response.readEntity(String.class);
            Metadata teiMet = new TikaTEIParser().parse(resp);
            for (String key : teiMet.names()) {
                metadata.add("grobid:header_" + key, teiMet.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readRestUrl() throws IOException {
//        File file = new File("./config/grobid_service/");
//         In next version it should be implemented using spring, so... it should not be necesary
        File file = new File(".");
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        ResourceBundle resources = ResourceBundle.getBundle("grobid", Locale.getDefault(), loader);
        return resources.getString("grobid.server.url");

        
    }

    protected static boolean canRun() {
        Response response = null;

        try {
            response = WebClient.create(readRestUrl() + GROBID_ISALIVE_PATH)
                    .accept(MediaType.TEXT_HTML).get();
            String resp = response.readEntity(String.class);
            return resp != null && !resp.equals("") && resp.startsWith("<h4>");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
