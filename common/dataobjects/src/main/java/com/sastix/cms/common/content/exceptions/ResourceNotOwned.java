/*
 * Copyright(c) 2017 the original author or authors.
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

package com.sastix.cms.common.content.exceptions;

import com.sastix.cms.common.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The specified Resource cannot be modified, the lock is held by someone else already.
 * <p/>
 * Note:
 * The exception object should contain the user holding the lock and expiry date/time.
 */
@ResponseStatus(value = HttpStatus.ACCEPTED, reason = Constants.RESOURCE_NOT_OWNED)
public class ResourceNotOwned extends GeneralResourceException {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8223702520237666604L;

    /**
     * Constructor that allows a specific error message to be specified.
     *
     * @param message detail message.
     */
    public ResourceNotOwned(String message) {
        super(message);
    }
}
