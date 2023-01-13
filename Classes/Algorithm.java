import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    public static List<String> getOptimalWay(Graph graph, Courier courier,
                                             String [] clients, Vertex departurePoint){

//        List<Vertex> way = new LinkedList();
//        departurePoint.setVisited(true);
//        way.add(departurePoint);

        Stack<Vertex> stack = new Stack<>();
        departurePoint.setVisited(true);
        stack.push(departurePoint);

//        List<Vertex> allNeighbors = graph.getNeighbors(departurePoint);
//
//        while (true){
//            for (Vertex v : allNeighbors) {
//                if (Arrays.stream(clients).findAny().filter(s -> s.equals(v.getLabel())) != null && !v.isVisited()){
//                    way.add(v);
//                    continue;
//                }
//
//            }
//        }

        return null;
    }

    public static boolean deepSearchRec(Vertex start, Vertex find, Graph graph, LinkedList<Vertex> way,
                                        Map<Integer, Integer> map, Set<Integer> vertexesSet) {
//        System.out.println("begin");
        if(start.equals(find)){
            way.add(start);
//            System.out.println("way True: " + way);
            return true;
        }
        if(start.isVisited()){
//            way.remove(start);
            return false;
        }
//        System.out.println("  map: " + map);
//        System.out.println("  veSet: " + vertexesSet);

        start.setVisited(true);
        way.add(start);
//        System.out.println("  way: " + way);
        LinkedList<Integer> neighbors = graph.getNeighborsIntegerList(start.getNumber());

        if (map != null){
            if (map.values().contains(way.size() - 1) && !vertexesSet.contains(way.size() - 1)){
                for (Integer vertexNumber : map.keySet()) {
                    if (map.get(vertexNumber).equals(way.size() - 1)){
                        vertexesSet.add(way.size() - 1);
//                        System.out.println("VertSet: " + vertexesSet);
                        graph.getVertexByNumber(vertexNumber).setVisited(false);
                    }
                }
            }
        }

        for(Integer neighbor : neighbors) {
            if(!graph.getVertexByNumber(neighbor).isVisited()) {
                boolean reached = deepSearchRec(graph.getVertexByNumber(neighbor), find, graph, way, map, vertexesSet);
                if (reached) {
                    return true;
                }
            }
        }
        way.remove(start);
//        System.out.println(" way-r: " + way);
        return false;
    }

    public static LinkedList<Vertex> deepSearch(Vertex start, Vertex find, Graph graph) {
        LinkedList<Vertex> way = new LinkedList<>();

        if (!deepSearchRec(start, find, graph, way, null, null)){
            return null;
        }

        graph.setVisitedFalseAll();
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Vertex> way2 = new LinkedList<>();
        LinkedList<Vertex> finalWay = new LinkedList<>();
        finalWay.addAll(way);
        Integer distanceFinalWay = graph.countDistanceOfWayV(way);
        way.clear();

//        System.out.println(finalWay);

        Set<Integer> set = new HashSet<>();

        for (int i = 1; i < finalWay.size() - 1; i++) {
//            System.out.println("finalWay: " + convertVertexListToStringList(finalWay));
            Vertex vToChange = finalWay.get(i);
            map.put(vToChange.getNumber(), i);
            for (Integer it:map.keySet()) {
                graph.getVertexByNumber(it).setVisited(true);
            }

            for (int j = 1; j < finalWay.size(); j++) {
                set.clear();
                if (deepSearchRec(start, find, graph, way, map, set)){
//                    System.out.println("got way: " + way);
//                    System.out.println(graph.countDistanceOfWayV(way));
                    if (graph.countDistanceOfWayV(way) < distanceFinalWay){
                        finalWay.clear();
                        finalWay.addAll(way);
                        distanceFinalWay = graph.countDistanceOfWayV(way);
                        way.clear();
                        map.clear();
                        i = 0;
                        break;
                    } else {
                        map.put(vToChange.getNumber(), i + j);
                    }
                    way.clear();
                } else {
//                    System.out.println("map Remove: " + vToChange.getNumber());
                    map.remove(vToChange.getNumber());
                    graph.setVisitedFalseAll();
                    break;
                }
                graph.setVisitedFalseAll();
                map.put(vToChange.getNumber(), i);
//                System.out.println("map put: " + map);
                for (Integer it:map.keySet()) {
                    graph.getVertexByNumber(it).setVisited(true);
                }
            }
        }
        map.clear();
        set.clear();
        graph.setVisitedFalseAll();

        for (int i = finalWay.size() - 1; i > 0; i--) {
//            System.out.println("finalWay: " + convertVertexListToStringList(finalWay));
            Vertex vToChange = finalWay.get(i);
            map.put(vToChange.getNumber(), finalWay.size() - i);
//            System.out.println(map);
            for (Integer it:map.keySet()) {
                graph.getVertexByNumber(it).setVisited(true);
            }

            for (int j = 1; j < finalWay.size(); j++) {
                set.clear();
                if (deepSearchRec(start, find, graph, way, map, set)){
                    if (graph.countDistanceOfWayV(way) < distanceFinalWay){
                        finalWay.clear();
                        finalWay.addAll(way);
                        distanceFinalWay = graph.countDistanceOfWayV(way);
                        way.clear();
                        i = 0;
                        break;
                    } else {
                        map.put(vToChange.getNumber(), finalWay.size() - i + j);
//                        System.out.println("map put: " + map);
                    }
                    way.clear();
                } else {
//                    System.out.println("map Remove: " + vToChange.getNumber());
                    map.remove(vToChange.getNumber());
                    graph.setVisitedFalseAll();
                    way.clear();
                    break;
                }
                graph.setVisitedFalseAll();
//                map.put(vToChange.getNumber(), finalWay.size() - i);
                for (Integer it:map.keySet()) {
                    graph.getVertexByNumber(it).setVisited(true);
                }
            }
        }
        graph.setVisitedFalseAll();
        return finalWay;
    }

    public static List<List<Vertex>> getOptimalWayBetweenManyVertexes(Vertex start, Vertex[] vertexesToVisit, Graph graph){
        List<List<Vertex>> finalWaysList = new LinkedList<>();
        int distanceFinalWay = Integer.MAX_VALUE;
        List<Vertex> vertexesList = new LinkedList<>();
        vertexesList.add(start);
        vertexesList.addAll(Arrays.stream(vertexesToVisit).toList());
        vertexesList.add(start);

        List<List<Vertex>> permutations = findPermutations(List.of(vertexesToVisit));

        for (List<Vertex> list:permutations) {
            List<List<Vertex>> vertexList = new LinkedList<>();
            vertexList.add(deepSearch(start, list.get(0), graph));
            for (int i = 1; i < list.size(); i++) {
                vertexList.add(deepSearch(list.get(i - 1), list.get(i), graph));
            }
            vertexList.add(deepSearch(list.get(list.size() - 1), start, graph));

            if (distanceFinalWay > graph.countDistanceOfWayVList(vertexList) && graph.countDistanceOfWayVList(vertexList) != 0){
                finalWaysList.clear();
                finalWaysList.addAll(vertexList);
                distanceFinalWay = graph.countDistanceOfWayVList(vertexList);
            }
        }

        return finalWaysList;
    }

    public static LinkedList<List<Vertex>>getOptimalWayBetweenManyVertexesWithFuel(Vertex start, Vertex[] vertexesToVisit, Graph graph, Courier courier, Vertex[] fuelStationsVertexes){
        LinkedList<List<Vertex>> finalWaysList = new LinkedList<>();
        int distanceFinalWay = Integer.MAX_VALUE;
        List<Vertex> vertexesList = new LinkedList<>();
        Set<Vertex> set = new HashSet<>();
        vertexesList.add(start);
        vertexesList.addAll(Arrays.stream(vertexesToVisit).toList());
        vertexesList.add(start);

        List<List<Vertex>> permutations = findPermutations(List.of(vertexesToVisit));
        double cFS = courier.getCurrentFuelSupply();
        System.out.println(permutations);
        for (List<Vertex> list:permutations) {
            courier.setCurrentFuelSupply(cFS);
            List<List<Vertex>> vertexList = new LinkedList<>();
            HashSet<Vertex> ignoreFuel = new HashSet<>();
            boolean interrupted = false;
            set.clear();

            //add way from start to first vertex
            if (courier.recalculateFuelSupply(graph, deepSearch(start, list.get(0), graph), List.of(fuelStationsVertexes)) >= 0){

                courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(start, list.get(0), graph), List.of(fuelStationsVertexes)));
                set.addAll(checkWayOnVertexesToVisit(deepSearch(start, list.get(0), graph), vertexesToVisit));
                vertexList.add(deepSearch(start, list.get(0), graph));
            } else if (courier.recalculateFuelSupply(graph, deepSearch(start, getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)) >= 0){
                courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(start, getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)));
                vertexList.add(deepSearch(start, getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel), graph));
                set.addAll(checkWayOnVertexesToVisit(deepSearch(start, getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel), graph), vertexesToVisit));
                ignoreFuel.add(getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel));
                while (true){
                    Vertex lastV = vertexList.get(vertexList.size() - 1).get(vertexList.get(vertexList.size() - 1).size() - 1);
                    if (courier.recalculateFuelSupply(graph, deepSearch(lastV, list.get(0), graph), List.of(fuelStationsVertexes)) >= 0){
                        set.addAll(checkWayOnVertexesToVisit(deepSearch(lastV, list.get(0), graph), vertexesToVisit));
                        courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastV, list.get(0), graph), List.of(fuelStationsVertexes)));
                        vertexList.add(deepSearch(lastV, list.get(0), graph));
                        ignoreFuel.clear();
                        break;
                    } else {
                        if (courier.recalculateFuelSupply(graph, deepSearch(lastV, getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)) < 0){
                            ignoreFuel.clear();
                            interrupted = true;
                            break;
                        } else {
                            set.addAll(checkWayOnVertexesToVisit(deepSearch(lastV, getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel), graph), vertexesToVisit));
                            courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastV, getNearestFuel(start, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)));
                            vertexList.add(deepSearch(lastV, getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel), graph));
                            ignoreFuel.add(getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel));
                        }
                    }
                }
                ignoreFuel.clear();
            } else {
                interrupted = true;
            }
            if (interrupted || courier.getCurrentFuelSupply() < 0){
                continue;
            }
//            System.out.println("  verList1: " + convertVertexListsListToStringList(vertexList));

            //add ways between vertexes to visit

            for (int i = 1; i < list.size(); i++) {
                if (set.contains(list.get(i))){
                    continue;
                }

//                vertexList.add(deepSearch(list.get(i - 1), list.get(i), graph));
                ignoreFuel.clear();
                Vertex lastVert = vertexList.get(vertexList.size() - 1).get(vertexList.get(vertexList.size() - 1).size() - 1);

                if (courier.recalculateFuelSupply(graph, deepSearch(lastVert, list.get(i), graph), List.of(fuelStationsVertexes)) >= 0){
                    set.addAll(checkWayOnVertexesToVisit(deepSearch(lastVert, list.get(i), graph), vertexesToVisit));
                    courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastVert, list.get(i), graph), List.of(fuelStationsVertexes)));
                    vertexList.add(deepSearch(lastVert, list.get(i), graph));
                } else if (courier.recalculateFuelSupply(graph, deepSearch(lastVert, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)) >= 0){
                    courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastVert, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)));
                    vertexList.add(deepSearch(lastVert, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph));
                    set.addAll(checkWaysListOnVertexesToVisit(vertexList, vertexesToVisit));
                    ignoreFuel.add(getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel));

                    while (true){
                        Vertex lastV = vertexList.get(vertexList.size() - 1).get(vertexList.get(vertexList.size() - 1).size() - 1);
                        if (courier.recalculateFuelSupply(graph, deepSearch(lastV, list.get(i), graph), List.of(fuelStationsVertexes)) >= 0){
                            courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastV, list.get(i), graph), List.of(fuelStationsVertexes)));
                            vertexList.add(deepSearch(lastV, list.get(i), graph));
                            set.addAll(checkWaysListOnVertexesToVisit(vertexList, vertexesToVisit));
                            ignoreFuel.clear();
                            break;
                        } else {
                            if (courier.recalculateFuelSupply(graph, deepSearch(lastV, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)) < 0){
                                ignoreFuel.clear();
                                interrupted = true;
                                break;
                            } else {
                                courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastV, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)));
                                vertexList.add(deepSearch(lastV, getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel), graph));
                                set.addAll(checkWaysListOnVertexesToVisit(vertexList, vertexesToVisit));
                                ignoreFuel.add(getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel));
                            }
                        }
                    }
                } else {
                    interrupted = true;
                }
            }
            if (interrupted || courier.getCurrentFuelSupply() < 0){
                continue;
            }

            //add way from last vertex to visit to start vertex
            ignoreFuel.clear();

            Vertex lastVert = vertexList.get(vertexList.size() - 1).get(vertexList.get(vertexList.size() - 1).size() - 1);

            if (courier.recalculateFuelSupply(graph, deepSearch(lastVert, start, graph), List.of(fuelStationsVertexes)) >= 0){
                courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastVert, start, graph), List.of(fuelStationsVertexes)));
                vertexList.add(deepSearch(list.get(list.size() - 1), start, graph));
            } else if (courier.recalculateFuelSupply(graph, deepSearch(lastVert, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)) >= 0){
                courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastVert, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)));
                vertexList.add(deepSearch(lastVert, getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel), graph));
                ignoreFuel.add(getNearestFuel(lastVert, fuelStationsVertexes, graph, ignoreFuel));
                while (true){
                    Vertex lastV = vertexList.get(vertexList.size() - 1).get(vertexList.get(vertexList.size() - 1).size() - 1);
                    if (courier.recalculateFuelSupply(graph, deepSearch(lastV, start, graph), List.of(fuelStationsVertexes)) >= 0){
                        courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastV, start, graph), List.of(fuelStationsVertexes)));
                        vertexList.add(deepSearch(lastV, start, graph));
                        ignoreFuel.clear();
                        break;
                    } else {
                        if (courier.recalculateFuelSupply(graph, deepSearch(lastV, getNearestFuel(list.get(list.size() - 1), fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)) < 0){
                            ignoreFuel.clear();
                            interrupted = true;
                            break;
                        } else {
                            courier.setCurrentFuelSupply(courier.recalculateFuelSupply(graph, deepSearch(lastV, getNearestFuel(list.get(list.size() - 1), fuelStationsVertexes, graph, ignoreFuel), graph), List.of(fuelStationsVertexes)));
                            vertexList.add(deepSearch(lastV, getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel), graph));
                            ignoreFuel.add(getNearestFuel(lastV, fuelStationsVertexes, graph, ignoreFuel));
                        }
                    }
                }
            } else {
                interrupted = true;
            }
            if (interrupted || courier.getCurrentFuelSupply() < 0){
                vertexList.clear();
                continue;
            }

            if (distanceFinalWay > graph.countDistanceOfWayVList(vertexList) && graph.countDistanceOfWayVList(vertexList) != 0
            && checkWaysListOnVertexesToVisit(vertexList, vertexesToVisit).size() == vertexesToVisit.length){
                finalWaysList.clear();
                finalWaysList.addAll(vertexList);
                distanceFinalWay = graph.countDistanceOfWayVList(vertexList);
            }
        }

        return finalWaysList;
    }

    public static List<String> convertWayToStringList(List<List<Integer>> list, Graph graph){
        List<String> wayString = new LinkedList<>();
        for (List <Integer> lst:list) {
            for (int i:lst) {
                if (!wayString.isEmpty()){
                    if (!wayString.get(wayString.size() - 1).equals(graph.getVertexByNumber(i).getLabel())){
                        wayString.add(graph.getVertexByNumber(i).getLabel());
                    }
                    continue;
                }
                wayString.add(graph.getVertexByNumber(i).getLabel());
            }
        }
        return wayString;
    }

    public static List<Vertex> convertIntegerListToVertexList(List<Integer> list, Graph graph){
        List<Vertex> wayString = new LinkedList<>();
        for (Integer i:list) {
            wayString.add(graph.getVertexByNumber(i));
        }
        return wayString;
    }

    public static List<String> convertVertexListToStringList(List<Vertex> list){
        List<String> wayString = new LinkedList<>();
        for (Vertex i:list) {
            wayString.add(i.getLabel());
        }
        return wayString;
    }

    public static List<String> convertVertexListsListToStringList(List<List<Vertex>> list){
        List<String> wayString = new LinkedList<>();
        if (list.isEmpty()) {
            return wayString;
        }
        for (List<Vertex> i:list) {
            for (int j = 0; j < i.size() - 1; j++) {
                wayString.add(i.get(j).getLabel());
            }
        }
        wayString.add(list.get(list.size() - 1).get(list.get(list.size() - 1).size() - 1).getLabel());
        return wayString;
    }

    public static List<List<Vertex>> findPermutations(List<Vertex> vertexList)
    {
        List<List<Vertex>> partial = new ArrayList<>();
        partial.add(Collections.singletonList(vertexList.get(0)));

        for (int i = 1; i < vertexList.size(); i++){
            for (int j = partial.size() - 1; j >= 0 ; j--)
            {
                List<Vertex> s = partial.remove(j);

                for (int k = 0; k <= s.size(); k++)
                {
                    List<Vertex> lst1 = new LinkedList<>();
                    for (int l = 0; l < k; l++) {
                        lst1.add(s.get(l));
                    }
                    lst1.add(vertexList.get(i));
                    for (int l = k; l < s.size(); l++) {
                        lst1.add(s.get(l));
                    }
                    partial.add(lst1);
                }
            }
        }
        return partial;
    }

    public static Vertex getNearestFuel(Vertex vertex, Vertex[] fuelStations, Graph graph, Set<Vertex> ignore){
        Vertex min = null;
        int minDist = Integer.MAX_VALUE;


        for (Vertex fuelVertex:fuelStations) {
            if (vertex.equals(fuelVertex) || ignore.contains(fuelVertex)){
                ignore.add(fuelVertex);
                continue;
            }
            LinkedList<Vertex> way = deepSearch(vertex, fuelVertex, graph);
            if (graph.countDistanceOfWayV(way) < minDist){
                min = fuelVertex;
                minDist = graph.countDistanceOfWayV(way);
            }
        }
        return min;
    }

    public static Set<Vertex> checkWayOnVertexesToVisit(List<Vertex> way, Vertex[] verticesToVisit){
        Set<Vertex> set = new HashSet<>();

        for (Vertex v:verticesToVisit) {
            if (way.contains(v)){
                set.add(v);
            }
        }
        return set;
    }

    public static Set<Vertex> checkWaysListOnVertexesToVisit(List<List<Vertex>> way, Vertex[] verticesToVisit){
        Set<Vertex> set = new HashSet<>();

        for (List<Vertex> vl:way) {
            set.addAll(checkWayOnVertexesToVisit(vl, verticesToVisit));
        }
        return set;
    }
}
