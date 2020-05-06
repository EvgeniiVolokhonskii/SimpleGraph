package com.volokhonskii.simplegraph;

import com.volokhonskii.simplegraph.impl.Edge;
import com.volokhonskii.simplegraph.impl.Vertex;

import java.util.List;

public interface Graph {
    void addVertex(Vertex vertex);
    void addEdge(Vertex v1, Vertex v2);
    List<Edge> getPath(Vertex v1, Vertex v2);

    boolean hasVertex(Vertex vertex);

    boolean hasEdge(Edge edge);
}
