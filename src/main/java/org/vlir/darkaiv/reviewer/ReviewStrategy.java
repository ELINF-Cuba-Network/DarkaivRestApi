/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vlir.darkaiv.reviewer;

import java.io.IOException;
import java.util.HashMap;
import org.vlir.darkaiv.exceptions.ConnectionCanNotBeEstablishException;
import org.vlir.darkaiv.exceptions.OnlineConnectionFailsException;
import org.vlir.darkaiv.model.Document;

/**
 *
 * @author daniel
 */
public interface ReviewStrategy {

    public HashMap<String, Object> reviewMetadata(Document doc) throws IOException, ConnectionCanNotBeEstablishException, OnlineConnectionFailsException;
}
