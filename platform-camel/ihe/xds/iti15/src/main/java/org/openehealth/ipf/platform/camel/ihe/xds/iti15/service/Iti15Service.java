/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.xds.iti15.service;

import java.util.Map;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.openehealth.ipf.platform.camel.ihe.xds.commons.DefaultItiWebService;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.stub.ebrs21.rs.RegistryResponse;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.stub.ebrs21.rs.SubmitObjectsRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.iti15.service.ProvideAndRegisterDocumentSetRequestType.Document;

/**
 * Service implementation for the IHE ITI-15 transaction (Provide and Register Document Set).
 * <p>
 * This implementation delegates to a Camel consumer by creating an exchange.
 *
 * @author Jens Riemschneider
 */
public class Iti15Service extends DefaultItiWebService implements Iti15PortType {
    @Resource
    private WebServiceContext wsc;

    public RegistryResponse documentRepositoryProvideAndRegisterDocumentSet(SubmitObjectsRequest body) {
        // We need to put together a structure similar to what is done in ITI-41
        // This means we have to put any message attachments into a ProvideAndRegisterDocumentSet
        MessageContext messageContext = wsc.getMessageContext();
        Map<?, ?> dataHandlers = (Map<?, ?>) messageContext.get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
        
        ProvideAndRegisterDocumentSetRequestType request = new ProvideAndRegisterDocumentSetRequestType();
        request.setSubmitObjectsRequest(body);
        for (Map.Entry<?, ?> entry : dataHandlers.entrySet()) {
            Document doc = new Document();
            doc.setId((String) entry.getKey());
            doc.setValue((DataHandler) entry.getValue());
            request.getDocument().add(doc);
        }
        
        return process(request, RegistryResponse.class);
    }
}