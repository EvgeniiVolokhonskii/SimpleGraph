package com.volokhonskii.simplegraph.impl;

import java.util.Objects;

public class Edge {
    private Vertex v1, v2;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    Vertex getVertex1() {
        return v1;
    }

    Vertex getVertex2() {
        return v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(v1, edge.v1) &&
                Objects.equals(v2, edge.v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }
}
