import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Courier {
    private int maxFuelSupply;
    private double fuelConsumption;
    private double currentFuelSupply;

    public double recalculateFuelSupply(Graph graph, List<Vertex> wayList, List<Vertex> fuelVertexes){
        if (wayList == null){
            return -1;
        }
        double cFS = currentFuelSupply;
        for (int i = 0; i < wayList.size() - 1; i++) {
            if (fuelVertexes.contains(wayList.get(i))){
                cFS = maxFuelSupply;
            }
            cFS -= fuelConsumption * graph.getAdjacencyMatrix()[wayList.get(i).getNumber()][wayList.get(i + 1).getNumber()];
            if (cFS < 0){
                return cFS;
            }
        }
        if (fuelVertexes.contains(graph.getVertexByNumber(wayList.get(wayList.size() - 1).getNumber()))){
            cFS = maxFuelSupply;
        }
        return cFS;
    }

}
