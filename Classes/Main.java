import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Example 1.1
        Vertex[] vertices = new Vertex[]{
                new Vertex("A"), new Vertex("B"),
                new Vertex("C"), new Vertex("D"), new Vertex("E"), new Vertex("F")
        };

        int[][] matrix = new int[][]{
                {0, 10, 0, 0, 0, 0},
                {10, 0, 20, 0, 100, 0},
                {0, 20, 0, 20, 0, 20},
                {0, 0, 20, 0, 40, 0},
                {0, 100, 0, 40, 0, 0},
                {0, 0, 20, 0, 0, 0}
        };
        Graph graph = new Graph(vertices, matrix);

        Vertex[] verticesToVisit = new Vertex[]{graph.getVertexByLabel("F"), graph.getVertexByLabel("E")};
        Vertex vertexStorage = graph.getVertexByLabel("A");



        List<List<Vertex>> waysList = Algorithm.getOptimalWayBetweenManyVertexes(vertexStorage,
                verticesToVisit, graph);
        System.out.println(Algorithm.convertVertexListsListToStringList(waysList));
        System.out.println("Distance = " + graph.countDistanceOfWayVList(waysList));


        //Example 1.2
//        Vertex[] vertices = new Vertex[]{
//                new Vertex("A"), new Vertex("B"),
//                new Vertex("C"), new Vertex("D"), new Vertex("E"), new Vertex("F")
//        };
//
//        int[][] matrix = new int[][]{
//                {0, 10, 0, 0, 0, 0},
//                {10, 0, 20, 0, 100, 0},
//                {0, 20, 0, 20, 0, 20},
//                {0, 0, 20, 0, 40, 0},
//                {0, 100, 0, 40, 0, 0},
//                {0, 0, 20, 0, 0, 0}
//        };
//        Graph graph = new Graph(vertices, matrix);
//        Vertex[] verticesToVisit = new Vertex[]{graph.getVertexByLabel("E"), graph.getVertexByLabel("C")};
//        Vertex vertexStorage = graph.getVertexByLabel("A");
//        Courier courier = new Courier(80, 0.5, 25);
//        Vertex[] verticesFuel = new Vertex[]{graph.getVertexByLabel("F")};
//
//        List<List<Vertex>> way = Algorithm.getOptimalWayBetweenManyVertexesWithFuel(vertexStorage,
//                verticesToVisit, graph, courier, verticesFuel);
//        System.out.println(Algorithm.convertVertexListsListToStringList(way));
//        System.out.println("Distance = " + graph.countDistanceOfWayVList(way));

        //Example 2.1
//        Vertex[] vertices = new Vertex[]{
//                new Vertex("A"), new Vertex("B"), new Vertex("C"),
//                new Vertex("D"), new Vertex("E"), new Vertex("F"),
//                new Vertex("G"), new Vertex("H"), new Vertex("I")
//        };
//
//        int[][] matrix = new int[][]{
//                {0, 40, 0, 0, 0, 0, 0, 0, 0},
//                {40, 0, 30, 0, 50, 0, 0, 0, 0},
//                {0, 30, 0, 0, 0, 50, 0, 0, 0},
//                {0, 0, 0, 0, 20, 0, 0, 0, 65},
//                {0, 50, 0, 20, 0, 35, 0, 80, 0},
//                {0, 0, 50, 0, 35, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 15, 0},
//                {0, 0, 0, 0, 80, 0, 15, 0, 20},
//                {0, 0, 0, 65, 0, 0, 0, 20, 0}
//        };
//        Graph graph = new Graph(vertices, matrix);
//
//        Vertex[] verticesToVisit = new Vertex[]{graph.getVertexByLabel("F"), graph.getVertexByLabel("I"), graph.getVertexByLabel("H")};
////                graph.getVertexByLabel("G"), graph.getVertexByLabel("D")};
//        Vertex vertexStorage = graph.getVertexByLabel("A");
//
//        List<List<Vertex>> waysList = Algorithm.getOptimalWayBetweenManyVertexes(vertexStorage,
//                verticesToVisit, graph);
//        System.out.println(waysList);
//        System.out.println(Algorithm.convertVertexListsListToStringList(waysList));
//        System.out.println("Distance = " + graph.countDistanceOfWayVList(waysList));


        //Example 2.2
//        Vertex[] vertices = new Vertex[]{
//                new Vertex("A"), new Vertex("B"), new Vertex("C"),
//                new Vertex("D"), new Vertex("E"), new Vertex("F"),
//                new Vertex("G"), new Vertex("H"), new Vertex("I")
//        };
//
//        int[][] matrix = new int[][]{
//                {0, 40, 0, 0, 0, 0, 0, 0, 0},
//                {40, 0, 30, 0, 50, 0, 0, 0, 0},
//                {0, 30, 0, 0, 0, 50, 0, 0, 0},
//                {0, 0, 0, 0, 20, 0, 0, 0, 65},
//                {0, 50, 0, 20, 0, 35, 0, 80, 0},
//                {0, 0, 50, 0, 35, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 15, 0},
//                {0, 0, 0, 0, 80, 0, 15, 0, 20},
//                {0, 0, 0, 65, 0, 0, 0, 20, 0}
//        };
//        Graph graph = new Graph(vertices, matrix);
//
//        Vertex[] verticesToVisit = new Vertex[]{graph.getVertexByLabel("F"), graph.getVertexByLabel("I"), graph.getVertexByLabel("H")};
//        Vertex vertexStorage = graph.getVertexByLabel("A");
//        Courier courier = new Courier(80, 0.5, 56);
//        Vertex[] verticesFuel = new Vertex[]{graph.getVertexByLabel("D"), graph.getVertexByLabel("G")};
//
//        List<List<Vertex>> way = Algorithm.getOptimalWayBetweenManyVertexesWithFuel(vertexStorage,
//                verticesToVisit, graph, courier, verticesFuel);
//        System.out.println(Algorithm.convertVertexListsListToStringList(way));
//        System.out.println("Distance = " + graph.countDistanceOfWayVList(way));

    }
}
