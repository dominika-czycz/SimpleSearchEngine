package com.findwise.impl;

import com.findwise.IndexEntry;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class IndexEntryImpl implements IndexEntry, Comparable<IndexEntry> {
    private String id;
    private Double score;

    public IndexEntryImpl(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;

    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(IndexEntry indexEntry) {
        return Double.valueOf(indexEntry.getScore()).compareTo(this.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        IndexEntryImpl that = (IndexEntryImpl) o;

        return new EqualsBuilder().append(id, that.id).append(score, that.score).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(score).toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id='").append(id).append('\'');
        sb.append(", score=").append(score);
        sb.append("}");
        return sb.toString();
    }
}
