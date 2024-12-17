/*
 * Copyright(c) 2016 the original author or authors.
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

package com.sastix.cms.client.config;

import com.sastix.cms.common.Constants;
import com.sastix.cms.common.client.ApiVersionClient;
import com.sastix.cms.common.client.RetryRestTemplate;
import com.sastix.cms.common.client.impl.ApiVersionClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Profile("production")
@PropertySource("classpath:client-application.properties")
public class ApiVersionClientConfig {

    @Value("${cms.server.host}")
    private String host;

    @Value("${cms.server.port}")
    private String port;

    @Autowired
    @Qualifier("CmsRestTemplate")
    RetryRestTemplate retryRestTemplate;

    @Bean(name = "CmsApiVersionClient")
    public ApiVersionClient getApiVersionClient() throws Exception {


        return new ApiVersionClientImpl(host, port, Constants.REST_API_1_0, retryRestTemplate);
    }
}
