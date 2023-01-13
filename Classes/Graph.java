import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Graph {
    private int size;
    private Vertex[] vertexes;
    private int [][] adjacencyMatrix;

    public Graph(Vertex[] vertexes, int[][] adjacencyMatrix) {
        this.vertexes = vertexes;
        this.adjacencyMatrix = adjacencyMatrix;
        size = vertexes.length;

        for (int i = 0; i < size; i++) {
            vertexes[i].setNumber(i);
        }
    }

    public LinkedList<Vertex> getNeighbors(Integer vertex){
        LinkedList<Vertex> neighbors = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[vertex][i] != 0){
                neighbors.add(getVertexByNumber(i));
            }
        }
        return neighbors;
    }

    public LinkedList<Integer> getNeighborsIntegerList(Integer vertex){
        LinkedList<Integer> neighbors = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[vertex][i] != 0){
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public Vertex getVertexByNumber(Integer num){
        for (Vertex v:vertexes) {
            if (v.getNumber() == num){
                return v;
            }
        }
        return null;
    }

    public Vertex getVertexByLabel(String label){
        for (Vertex v:vertexes) {
            if (v.getLabel().equals(label)){
                return v;
            }
        }
        return null;
    }

    public void setVisitedFalseAll(){
        for (Vertex v:vertexes) {
            v.setVisited(false);
        }
    }

    public int countDistanceOfWay(List<Integer> way){
        if (way == null) return 0;
        int distance = 0;
        for (int i = 1; i < way.size(); i++) {
            distance += adjacencyMatrix[way.get(i - 1)][way.get(i)];
        }
        return distance;
    }

    public int countDistanceOfWayV(List<Vertex> way){
        if (way == null) return 0;
        int distance = 0;
//        System.out.println(way);
        for (int i = 1; i < way.size(); i++) {
//            System.out.println(way.get(i - 1).getNumber());

            distance += adjacencyMatrix[way.get(i - 1).getNumber()][way.get(i).getNumber()];
        }
        return distance;
    }

    public int countDistanceOfWayList(List<List<Integer>> way){
        int distance = 0;
        for (List<Integer> list:way) {
            distance += countDistanceOfWay(list);
        }
        return distance;
    }

    public int countDistanceOfWayVList(List<List<Vertex>> way){
        int distance = 0;
        for (List<Vertex> list:way) {
            distance += countDistanceOfWayV(list);
        }
        return distance;
    }



}
