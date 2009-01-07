/*
 * Copyright 2008 the original author or authors.
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
package org.openehealth.ipf.platform.camel.lbs.process;

import static org.apache.commons.lang.Validate.notNull;

import org.apache.camel.processor.DelegateProcessor;

/**
 * Base class for processors that require an {@link AttachmentHandler} to 
 * process exchanges.
 * @author Jens Riemschneider
 */
public abstract class AttachmentHandlingProcessor extends DelegateProcessor {
    private AttachmentHandler attachmentHandler;

    /**
     * Sets the {@link AttachmentHandler} of the processor.
     * <p>
     * An attachment handler enables the processor to handle a specific type of
     * endpoint exchange. The handler contains the actual strategy for 
     * integrating/extracting attachments into/from a specific message type 
     * (e.g. an HTTP message).
     * <p>
     * When calling this method multiple times the result is undefined. This 
     * method must be called at least once.
     * @param handler
     *          handler for integrating and extracting attachments
     * @return this instance for usage with a fluent API
     */
    public AttachmentHandlingProcessor with(AttachmentHandler handler) {
        notNull(handler, "handler cannot be null");
        this.attachmentHandler = handler;
        return this;
    }
    
    /**
     * @return the attachment handler configured by {@link #with(AttachmentHandler)}
     */
    protected final AttachmentHandler getAttachmentHandler() {
        return attachmentHandler;
    }
    
    /**
     * @return {@code true} if an attachment handler was configured
     */
    protected final boolean hasAttachmentHandler() {
        return attachmentHandler != null;
    }
    
    /* (non-Javadoc)
     * @see org.apache.camel.processor.DelegateProcessor#toString()
     */
    @Override
    public String toString() {
        return String.format("{%1$s: attachmentHandler=%2$s, super=%3$s}",
                getClass().getSimpleName(), attachmentHandler, super.toString());
    }
}