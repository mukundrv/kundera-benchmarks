/**
 * Copyright 2012 Impetus Infotech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kundera.ycsb;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.impetus.kundera.ycsb.runner.MongoRunner;

/**
 * MongoDB YCSB Kundera benchmarking.
 * 
 * @author vivek.mishra
 *
 */
public class MongoDBYCSBTest extends YCSBBaseTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        // in case property file name is not set as system property.
        System.setProperty("fileName", "src/main/resources/db-mongo.properties");
        super.setUp();
    }

    @Test
    public void onTest() throws Exception
    {
        testConcurrentWorkload();
        testRead();
        testUpdate();
    }

    private void testConcurrentWorkload() throws IOException, ConfigurationException
    {
        onChangeRunType("load");
        process();
    }
    
    
    private void testRead() throws Exception
    {
    	onChangeRunType("t");
    	onRead();
    }
	
    private void testUpdate() throws Exception
    {
    	onChangeRunType("t");
    	onUpdate();
    }
	
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        
    }

    /**
     * @param runType
     * @throws ConfigurationException
     */
    protected void onChangeRunType(final String runType) throws ConfigurationException {
		config.setProperty("run.type",runType);
    	config.save();
        runner = new MongoRunner(propsFileName, config);
	}

}
