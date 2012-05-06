/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.spring.context;

import org.jboss.arquillian.spring.model.PlainClass;
import org.jboss.arquillian.spring.model.WebAnnotatedClass;
import org.jboss.arquillian.test.spi.TestClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <p>Tests {@link WebApplicationContextProducer} class.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class WebApplicationContextProducerTestCase {

    /**
     * <p>Represents the instance of tested class.</p>
     */
    private WebApplicationContextProducer instance;

    /**
     * <p>Sets up the test environment.</p>
     */
    @Before
    public void setUp() {

        instance = new WebApplicationContextProducer();
    }

    /**
     * <p>Tests the {@link WebApplicationContextProducer#supports(TestClass)} method.</p>
     */
    @Test
    public void testSupportsFalse() {
        TestClass testClass = new TestClass(PlainClass.class);

        assertFalse("Class without annotations shouldn't be supported.", instance.supports(testClass));
    }

    /**
     * <p>Tests the {@link WebApplicationContextProducer#supports(TestClass)} method.</p>
     */
    @Test
    public void testSupportsTrue() {
        TestClass testClass = new TestClass(WebAnnotatedClass.class);

        assertTrue("Class should be supported.", instance.supports(testClass));
    }
}