package main;

import java.util.*;

public class Graph {

    private final Map<Integer, List<String>> synset;
    private final Map<Integer, List<Integer>> hyponym;
    public Map<String, List<Integer>> getSynsets;

    public Graph() {
        synset = new HashMap<>();
        hyponym = new HashMap<>();
        getSynsets = new HashMap<>();
    }

    // creating directly connected node.
    public void createNode(int id, List<String> names) {
        synset.put(id, names);
        for (String str: names) {
            createCounterNode(str, id);
        }
    }

    private void createCounterNode(String name, int id) {
        getSynsets.computeIfAbsent(name, k -> new ArrayList<>()).add(id);
    }

    public void addEdge(int selfId, List<Integer> otherId) {
        for (int i: otherId) {
            hyponym.computeIfAbsent(selfId, k -> new ArrayList<>()).add(i);
            // adding different other edges into same selfId without repetition
        }
    }

    private void dfs(int currentNode, Set<Integer> visited, List<Integer> result) {
        if (!visited.contains(currentNode)) {
            visited.add(currentNode);
            result.add(currentNode);
            List<Integer> neighbors = hyponym.get(currentNode);
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    dfs(neighbor, visited, result);
                }
            }
        }
    }

    /* getting all nodes regardless of directed or undirected connection */
    public List<String> getNodes(int id) {
        List<String> output = new ArrayList<>();

        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        dfs(id, visited, result);

        for (int i : result) {
            output.addAll(synset.get(i));
        }
        return output;
    }

}
