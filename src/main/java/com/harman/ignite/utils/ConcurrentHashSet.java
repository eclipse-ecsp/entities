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

import lombok.EqualsAndHashCode;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concurrent hash set.
 *
 * @param <E>
 *         the type parameter
 */
@EqualsAndHashCode
public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E> {
    private final Map<E, Boolean> booleanMap = new ConcurrentHashMap<>();
    private  Set<E> keySet = booleanMap.keySet();


    @Override
    public boolean add(E e) {
        return booleanMap.put(e, Boolean.TRUE) == null;
    }

    @Override
    public void clear() {
        booleanMap.clear();
    }

    @Override
    public boolean contains(Object o) {
        return booleanMap.containsKey(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return keySet.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return booleanMap.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return keySet.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return booleanMap.remove(o) != null;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return keySet.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return keySet.retainAll(c);
    }

    @Override
    public int size() {
        return booleanMap.size();
    }

    @Override
    public Object[] toArray() {
        return keySet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return keySet.toArray(a);
    }

    @Override
    public String toString() {
        return keySet.toString();
    }
}
