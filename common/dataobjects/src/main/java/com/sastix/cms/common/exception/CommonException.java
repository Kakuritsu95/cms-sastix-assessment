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

package com.sastix.cms.common.exception;

import org.springframework.web.client.RestClientException;

/**
 * This is the parent Exception.
 * All other exceptions should be extend CommonException .
 */
public class CommonException extends RestClientException {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7037833658038044223L;

    /**
     * Constructor that allows a specific error message to be specified.
     *
     * @param message detail message.
     */
    public CommonException(String message) {
        super(message);
    }

    /**
     * Creates a {@code CommonException} with the specified
     * detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
