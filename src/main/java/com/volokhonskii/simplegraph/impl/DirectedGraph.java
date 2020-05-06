package com.volokhonskii.simplegraph.impl;

import com.volokhonskii.simplegraph.Graph;
import com.volokhonskii.simplegraph.exception.SimpleGraphException;

import java.util.*;

public class DirectedGraph implements Graph {

    protected Map<Vertex, Set<Edge>> adjacencyMap = new HashMap<>();

    @Override
    public void addVertex(Vertex vertex) {
        if(adjacencyMap.containsKey(vertex)) {
            throw new SimpleGraphException("Vertex already exists");
        }

        adjacencyMap.put(vertex, new HashSet<Edge>());
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        if(!adjacencyMap.containsKey(v1)) {
            addVertex(v1);
        }

        if(!adjacencyMap.containsKey(v2)) {
            addVertex(v2);
        }

        Set<Edge> edges = adjacencyMap.get(v1);
        Edge edge = new Edge(v1, v2);
        if(edges.contains(edge)) {
            throw new SimpleGraphException("Edge already exists");
        } else {
            edges.add(edge);
        }
    }

    @Override
    public List<Edge> getPath(Vertex v1, Vertex v2) {
        Set<Vertex> processedVertices = new HashSet<>();
        Queue<Vertex> vertexQueue = new LinkedList<>();
        LinkedList<Edge> edgeList = new LinkedList<>();

        vertexQueue.add(v1);
        processedVertices.add(v1);

        while(!vertexQueue.isEmpty() && !processedVertices.contains(v2)) {
            Set<Edge> edges = adjacencyMap.get(vertexQueue.poll());
            for(Edge edge : edges) {
                if (!processedVertices.contains(edge.getVertex2())) {
                    processedVertices.add(edge.getVertex2());
                    edgeList.add(edge);
                    // break the cycle if the current edge is to wanted vertex
                    if (edge.getVertex2().equals(v2)) {
                        break;
                    }
                    vertexQueue.add(edge.getVertex2());
                }
            }
        }

        return extractPath(edgeList);
    }

    protected List<Edge> extractPath(LinkedList<Edge> edgeList) {
        Iterator<Edge> edgeIterator = edgeList.descendingIterator();
        Edge lastEdge = null;

        // keep only edges to destination
        while(edgeIterator.hasNext()) {
            Edge currentEdge = edgeIterator.next();
            if (lastEdge == null) {
                lastEdge = currentEdge;
            } else {
                if (!currentEdge.getVertex2().equals(lastEdge.getVertex1())) {
                    edgeIterator.remove();
                } else {
                    lastEdge = currentEdge;
                }
            }
        }

        return edgeList;
    }

    @Override
    public boolean hasVertex(Vertex vertex) {
        return adjacencyMap.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(Edge edge) {
        if (!adjacencyMap.containsKey(edge.getVertex1())) {
            return false;
        }

        return adjacencyMap.get(edge.getVertex1()).contains(edge);
    }
}
