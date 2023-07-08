/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.meta.src.jdk.vm.ci.meta;

















/**
 * A profiled type that has a probability. Profiled types are naturally sorted in descending order
 * of their probabilities.
 */
public abstract class AbstractProfiledItem<T> implements Comparable<AbstractProfiledItem<?>> {

    protected final T item;
    protected final double probability;

    public AbstractProfiledItem(T item, double probability) {
        assert item != null;
        assert probability >= 0.0D && probability <= 1.0D;
        this.item = item;
        this.probability = probability;
    }

    protected T getItem() {
        return item;
    }

    /**
     * Returns the estimated probability of {@link #getItem()}.
     *
     * @return double value &ge; 0.0 and &le; 1.0
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Returns -1 if the {@linkplain #getProbability() probability} of this item is greater than
     * {@code o}'s probability, 0 if there are equal otherwise 1.
     */
    @Override
    public int compareTo(AbstractProfiledItem<?> o) {
        // Need to swap the order of operands so that higher probabilities are sorted first
        return Double.compare(o.getProbability(), getProbability());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(probability);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + item.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractProfiledItem<?> other = (AbstractProfiledItem<?>) obj;
        if (Double.doubleToLongBits(probability) != Double.doubleToLongBits(other.probability)) {
            return false;
        }
        return item.equals(other.item);
    }

    @Override
    public abstract String toString();
}
