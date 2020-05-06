package com.volokhonskii.simplegraph.test;

import com.volokhonskii.simplegraph.Graph;
import com.volokhonskii.simplegraph.impl.Edge;
import com.volokhonskii.simplegraph.impl.UndirectedGraph;
import com.volokhonskii.simplegraph.impl.Vertex;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphUnitTest {
    @Test
    public void addEdge() {
        Graph graph = new UndirectedGraph();
        Vertex v1 = new Vertex("Start");
        Vertex v2 = new Vertex("Finish");
        Edge edgeForward = new Edge(v1, v2);
        Edge edgeBackward = new Edge(v2, v1);
        assertFalse(graph.hasEdge(edgeForward));
        assertFalse(graph.hasEdge(edgeBackward));
        graph.addEdge(v1, v2);
        assertTrue(graph.hasEdge(edgeForward));
        assertTrue(graph.hasEdge(edgeBackward));
    }
}
