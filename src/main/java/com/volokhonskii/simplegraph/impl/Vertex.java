package com.volokhonskii.simplegraph.impl;

import java.util.Objects;

public class Vertex {
    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return name.equals(vertex.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
