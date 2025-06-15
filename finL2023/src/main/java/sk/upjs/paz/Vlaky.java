package sk.upjs.paz;

import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Vlaky {
    HashSet<Edge> denne = new HashSet<>();
    HashSet<Edge> nocny = new HashSet<>();
    HashSet<Vertex> h = new HashSet<>();
    Graph g;

    public void nacitaj(Graph g){
        this.g = g;
        for (Edge e : g.getEdges()) {
            if (e.getStringValue("typ").equals("denny")){
             denne.add(e);
            }else {
                nocny.add(e);
            }
        }
        System.out.println(denne);
        System.out.println(nocny);
    }


    public boolean bfs(Vertex start) {

        Map<Vertex, Boolean> navstiveny = g.createVertexMap(false);

        Queue<Vertex> rad = new LinkedList<>();
        rad.offer(start);
        navstiveny.put(start, true);
        int prev = 0;
        boolean dasa = true;

        while (!rad.isEmpty()) {
            Vertex v = rad.poll();
            for (Vertex sused : v.getNeighbours()) {
                if (!navstiveny.get(sused)) {

                    navstiveny.put(sused, true);

                    Edge e = g.getEdge(v,sused);
                    if (e.getStringValue("typ").equals("denny") && prev!=-1){
                        prev = -1;
                    }
                    rad.offer(sused);

                }
            }
        }
        return true;
    }





    public static void main(String[] args) {
        Vlaky v = new Vlaky();
        v.nacitaj(Graph.createFromFile("vlaky"));

    }
}
