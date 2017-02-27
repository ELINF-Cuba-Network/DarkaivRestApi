/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.md_extractor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.vlir.darkaiv.exceptions.OnlineConnectionFailsException;

/**
 *
 * @author daniel
 */
public interface MDExtractorStrategy {

    public String getDocumentType(File file) throws IOException;

    public HashMap< String, Object> getMetadata(File file) throws IOException, OnlineConnectionFailsException;

}
