package sk.upjs.paz;

import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.*;

public class Dj {

    // Implementácia Dijkstrovho algoritmu pre neorientovaný graf
    // Hľadá najkratšiu cestu z 'start' do 'finish', pričom pri rovnosti vzdialenosti preferuje cestu s menším počtom prístavov
    public static Map<Vertex, Vertex> dijkstraUndir(Graph g, Vertex start, Vertex finish) {
        // Mapa predchodcov na obnovenie cesty (kľúč = vrchol, hodnota = predchodca)
        Map<Vertex, Vertex> prev = g.createVertexMap(null);

        // Mapa vzdialeností od štartu (d(v))
        Map<Vertex, Double> d = g.createVertexMap(Double.POSITIVE_INFINITY);

        // Mapa počtu hrán od štartu (h(v))
        Map<Vertex, Integer> h = g.createVertexMap(-1);

        d.put(start, 0.0);     // vzdialenosť do seba je 0
        h.put(start, 1);       // začíname v 1. prístave (alebo ber ako počet vrcholov na ceste)
        
        // Množina vrcholov, ktoré ešte treba spracovať (Q)
        Set<Vertex> q = new HashSet<>(g.getVertices());

        // Hlavný cyklus Dijkstrovho algoritmu
        while (!q.isEmpty()) {
            // Nájdeme vrchol s najmenšou vzdialenosťou d(v), pri rovnosti s menším počtom prístavov h(v)
            Vertex min = q.iterator().next();
            for (Vertex v : q) {
                if (d.get(v) < d.get(min) || d.get(v).equals(d.get(min)) && h.get(v) < h.get(min)) {
                    min = v;
                }
            }

            // Pre všetky hrany z vrcholu min (neorientovaný graf → treba spracovať obojsmerne)
            for (Edge e : min.getOutEdges()) {
                relaxUndirected(e, d, h, prev);
            }

            // Označíme vrchol ako spracovaný
            q.remove(min);
        }

        return prev;  // vraciame mapu predchodcov na zostavenie cesty
    }

    // Uvoľňovanie hrany v neorientovanom grafe – skúšame uvoľniť v oboch smeroch
    public static boolean relaxUndirected(Edge e, Map<Vertex, Double> d, Map<Vertex, Integer> h, Map<Vertex, Vertex> prev) {
        Vertex u = e.getSource();
        Vertex v = e.getTarget();
        boolean changed = false;

        // Uvoľnenie u -> v
        if (d.get(u) + e.getWeight() < d.get(v)) {
            d.put(v, d.get(u) + e.getWeight());
            h.put(v, h.get(u) + 1);
            prev.put(v, u);
            changed = true;
        } else if (d.get(u) + e.getWeight() == d.get(v) && h.get(u) + 1 < h.get(v)) {
            // rovnaká vzdialenosť, ale menej prístavov
            h.put(v, h.get(u) + 1);
            prev.put(v, u);
            changed = true;
        }

        // Uvoľnenie v -> u (lebo graf je neorientovaný)
        if (d.get(v) + e.getWeight() < d.get(u)) {
            d.put(u, d.get(v) + e.getWeight());
            h.put(u, h.get(v) + 1);
            prev.put(u, v);
            changed = true;
        } else if (d.get(v) + e.getWeight() == d.get(u) && h.get(v) + 1 < h.get(u)) {
            h.put(u, h.get(v) + 1);
            prev.put(u, v);
            changed = true;
        }

        return changed;
    }

    // Zostavenie cesty z mapy predchodcov od konca do začiatku
    public static List<Vertex> path(Graph g, Map<Vertex, Vertex> prev, Vertex start, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        Vertex v = end;
        path.add(v);

        // Ideme späť po predchodcoch až k začiatku
        while (v != start) {
            path.add(prev.get(v));
            v = prev.get(v);
        }

        // Prevrátime cestu (lebo sme šli od konca)
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // Načítanie grafu zo súboru (neorientovaný, ohodnotený)
        Graph g = Graph.createFromFile("uups.txt");

        // Výber štartu a cieľa
        Vertex a = g.getVertex("A");
        Vertex c = g.getVertex("C");

        // Výpočet najkratšej cesty
        List<Vertex> path = path(g, dijkstraUndir(g, a, c), a, c);

        // Výpočet celkovej dĺžky trasy (súčet váh hrán)
        double totalLength = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalLength += g.getEdge(path.get(i), path.get(i + 1)).getWeight();
        }

        // Výpis výsledkov
        System.out.println(totalLength);           // dĺžka trasy
        System.out.println(path);                 // vypísaná trasa (zoznam vrcholov)
    }
}
