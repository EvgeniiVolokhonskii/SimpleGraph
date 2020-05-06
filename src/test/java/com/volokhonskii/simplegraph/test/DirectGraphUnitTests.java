package com.volokhonskii.simplegraph.test;

import com.volokhonskii.simplegraph.Graph;
import com.volokhonskii.simplegraph.exception.SimpleGraphException;
import com.volokhonskii.simplegraph.impl.DirectedGraph;
import com.volokhonskii.simplegraph.impl.Edge;
import com.volokhonskii.simplegraph.impl.Vertex;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DirectGraphUnitTests {

    @Rule
    public ExpectedException edgeAlreadyExists = ExpectedException.none();

    @Rule
    public ExpectedException vertexAlreadyExists = ExpectedException.none();

    @Test
    public void addVertex() {
        Graph graph = new DirectedGraph();
        Vertex vertex = new Vertex("Start");
        assertFalse(graph.hasVertex(vertex));
        graph.addVertex(vertex);
        assertTrue(graph.hasVertex(vertex));
    }

    @Test
    public void addVertex_already_exists() {
        Graph graph = new DirectedGraph();
        Vertex v1 = new Vertex("Start");
        graph.addVertex(v1);

        vertexAlreadyExists.expect(SimpleGraphException.class);
        vertexAlreadyExists.expectMessage("Vertex already exists");

        graph.addVertex(v1);
    }

    @Test
    public void addEdge() {
        Graph graph = new DirectedGraph();
        Vertex v1 = new Vertex("Start");
        Vertex v2 = new Vertex("Finish");
        Edge edge = new Edge(v1, v2);
        assertFalse(graph.hasEdge(edge));
        graph.addEdge(v1, v2);
        assertTrue(graph.hasEdge(edge));
    }

    @Test
    public void addEdge_already_exists() {
        Graph graph = new DirectedGraph();
        Vertex v1 = new Vertex("Start");
        Vertex v2 = new Vertex("Finish");
        graph.addEdge(v1, v2);

        edgeAlreadyExists.expect(SimpleGraphException.class);
        edgeAlreadyExists.expectMessage("Edge already exists");

        graph.addEdge(v1, v2);
    }

    @Test
    public void getPath() {
        Graph graph = new DirectedGraph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v2, v4);
        graph.addEdge(v4, v7);
        graph.addEdge(v3, v5);
        graph.addEdge(v3, v6);

        /*
                          1
                        /   \
                       2     3
                      /    /   \
                     4    5     6
                    /
                   7
        */

        List<Edge> path = graph.getPath(v1, v7);

        assertThat(path, equalTo(Arrays.asList(new Edge(v1, v2),
                new Edge(v2, v4),
                new Edge(v4, v7))));

        assertThat(path, not(equalTo(Arrays.asList(new Edge(v1, v2),
                new Edge(v2, v4),
                new Edge(v4, v6)))));

        List<Edge> path2 = graph.getPath(v1, v5);

        assertThat(path2, equalTo(Arrays.asList(new Edge(v1, v3),
                new Edge(v3, v5))));

        List<Edge> path3 = graph.getPath(v2, v2);
        assertTrue(path3.isEmpty());

        List<Edge> path4 = graph.getPath(v6, v4);
        assertTrue(path4.isEmpty());
    }
}
