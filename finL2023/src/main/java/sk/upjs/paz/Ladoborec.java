package sk.upjs.paz;

import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ladoborec {



    public void ladoborec(){

    }

    public Map<Vertex, Vertex> dijkstraUndir(Graph g, Vertex start) {
        //distance
        Map<Vertex, Double> d = g.createVertexMap(Double.POSITIVE_INFINITY);
        //robime novu mapu pristavov ktorymi prechadzame
        Map<Vertex,Integer> h = g.createVertexMap(-1);
        Map<Vertex, Vertex> prev = g.createVertexMap(null);
        d.put(start, 0.0);
        h.put(start,1);
        Set<Vertex> q = new HashSet<>(g.getVertices());
        while (!q.isEmpty()) {
            Vertex min = q.iterator().next();
            for (Vertex v : q) {
                if (d.get(v) < d.get(min) || (d.get(v) == d.get(min))&& h.get(v) < h.get(min)) {
                    min = v;
                }
            }
            for (Edge e : min.getOutEdges()) {
                relaxUndirected(e, d,h,prev);
            }
            q.remove(min);
        }
        return prev;
    }

    public boolean relaxUndirected(Edge e, Map<Vertex, Double> d,Map<Vertex,Integer> h, Map<Vertex,Vertex> prev) {
        Vertex u = e.getSource();
        Vertex v = e.getTarget();
        boolean changed = false;

        if (d.get(u) + e.getWeight() < d.get(v)) {
            d.put(v, d.get(u) + e.getWeight());
            prev.put(v,u);
            changed = true;
        }
        else if (d.get(u) + e.getWeight() < d.get(v) || (d.get(u) + e.getWeight() == d.get(v) && h.get(u)+1 < h.get(v))){
            d.put(v,h.get(v) + e.getWeight());
            prev.put(u,v);
            changed = true;
        }
        if (d.get(v) + e.getWeight() < d.get(u)) {
            d.put(u, d.get(v) + e.getWeight());
            prev.put(u,v);
            changed = true;
        }
        return changed;

    }






}
