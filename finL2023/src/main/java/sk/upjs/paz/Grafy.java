package sk.upjs.paz;

import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Grafy {

    static Graph g = Graph.createFromFile("graf");

    Map<Vertex,Double> ohodnotenzBFS = new HashMap<>();

    public void bfsVzdialenosti(Vertex start) {
        Map<Vertex, Integer> vzdialenosti = g.createVertexMap(-1);

        Queue<Vertex> rad = new LinkedList<>();
        rad.offer(start);
        vzdialenosti.put(start, 0);
        ohodnotenzBFS.put(start,0.0);

        while (!rad.isEmpty()) {
            Vertex aktualny = rad.poll();
            for (Vertex sused : aktualny.getNeighbours()) {
                Edge e = g.getEdge(aktualny,sused);
                if (vzdialenosti.get(sused) == -1) {
                    vzdialenosti.put(sused, vzdialenosti.get(aktualny) + 1);
                    ohodnotenzBFS.put(sused,e.getWeight());
                    rad.offer(sused);
                }
            }
        }
        System.out.println(ohodnotenzBFS);
        System.out.println(vzdialenosti);
    }

    public static void main(String[] args) {

        Vertex s = g.getVertex("A");
        Grafy grafy = new Grafy();
        grafy.bfsVzdialenosti(s);
    }



}


