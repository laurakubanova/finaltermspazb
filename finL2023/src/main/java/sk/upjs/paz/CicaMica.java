package sk.upjs.paz;

import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;
import sk.upjs.paz.graph.visualization.GraphVisualizer;

import java.util.ArrayList;

public class CicaMica {
    ArrayList<Vertex> vrcholy = new ArrayList<>();
    Graph g = new Graph();


    public void graf(String[][] vstup) {

        for (int i = 0; i < vstup.length; i++) {
            for (int j = 0; j < vstup[0].length; j++) {
                Vertex v = g.addVertex();
                g.addVertex(vstup[i][0] + ":" + vstup[0][1] );
            }
        }
        System.out.println(g);
        new GraphVisualizer(g);

        for (int i = 0; i < vstup.length; i++) {
            for (int j = 0; j < vstup[0].length; j++) {

            }
        }



    }


    public static void main(String[] args) {
        String[][] vstupString = {
                {"1", "2"},
                {"3", "2"},
                {"1", "2"},
                {"8", "3"}
        };
        CicaMica cm = new CicaMica();
        cm.graf(vstupString);


    }

}
