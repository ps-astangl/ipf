/*
 * Copyright 2010 the original author or authors.
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
package org.openehealth.ipf.commons.core.extend.config;

import org.openehealth.ipf.commons.core.config.Customized;

/**
 * DSL extensions implementing this marker interface can be
 * auto discovered by IPF's configuration framework. Works for
 * both OSGi and non-OSGi environment. 
 * 
 * @author Boris Stanojevic
 */
public interface Extension extends Customized {

}