package com.caching.inlinecachinggeode1.model;

public enum Operator {
    FACTORIAL("factorial(%1$d) = %2$d"),
    SQUARE_ROOT("sqrt(%1$d) = %2$d");

    private final String representation;

    Operator(String definition) {
        this.representation = definition;
    }

    @Override
    public String toString() {
        return this.representation;
    }

    public String toString(Object... args) {
        return String.format(toString(), args);
    }
}
