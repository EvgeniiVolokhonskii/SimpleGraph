package com.volokhonskii.simplegraph.impl;

import com.volokhonskii.simplegraph.Graph;

public class UndirectedGraph extends DirectedGraph implements Graph {

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        super.addEdge(v1, v2);
        super.addEdge(v2, v1);
    }
}
