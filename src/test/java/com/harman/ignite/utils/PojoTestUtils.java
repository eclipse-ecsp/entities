/*
 * *******************************************************************************
 *
 *  Copyright (c) 2023-24 Harman International
 *
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *
 *
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *       
 *
 *  Unless required by applicable law or agreed to in writing, software
 *
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and
 *
 *  limitations under the License.
 *
 *
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  *******************************************************************************
 */

package com.harman.ignite.utils;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import java.lang.reflect.Method;

/**
 * Pojo test utils class.
 */
public class PojoTestUtils {

    private PojoTestUtils() {
    }

    private static final Validator ACCESSOR_VALIDATOR = ValidatorBuilder.create()
            .with(new GetterTester())
            .with(new SetterTester())
            .build();

    public static void validateAccessors(final Class<?> clazz) {
        ACCESSOR_VALIDATOR.validate(PojoClassFactory.getPojoClass(clazz));
    }

    /**
     * Validate method accessors boolean.
     *
     * @param clazz
     *         the clazz
     * @param methodName
     *         the method name
     * @return the boolean
     */
    public static boolean validateMethodAccessors(final Class<?> clazz, String methodName) {
        Method toStringMethod;
        try {
            toStringMethod = clazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            return false;
        }
        return (toStringMethod.getDeclaringClass() != Object.class
                && toStringMethod.getDeclaringClass().getName() == clazz.getName());
    }

}