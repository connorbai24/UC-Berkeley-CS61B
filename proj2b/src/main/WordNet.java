package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {

    private Graph graph;

    public WordNet(String synsetsFileName, String hyponymFileName) {
        In synsetsFile = new In(synsetsFileName);
        In hyponymFile = new In(hyponymFileName);
        graph = new Graph();

        while (!synsetsFile.isEmpty()) {
            String nextLine = synsetsFile.readLine();
            String[] splitLine = nextLine.split(",");
            int id = Integer.parseInt(splitLine[0]);
            String[] values = splitLine[1].split(" ");
            List<String> names = Arrays.asList(values);
            graph.createNode(id, names);
        }

        while(!hyponymFile.isEmpty()) {
            String nextLine = hyponymFile.readLine();
            String[] splitLine = nextLine.split(",");
            List<Integer> lst = new ArrayList<>();
            int selfId = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                lst.add(Integer.parseInt(splitLine[i]));
            }
            graph.addEdge(selfId, lst);
        }
    }
    /* list should be alphabetical order, and no repetition*/
    public List<String> getAllHyponymsOne(List<Integer> id) {
        List<String> lst = new ArrayList<>();
        for (int i: id) {
            lst.addAll(graph.getNodes(i));
        }

        Set<String> set = new HashSet<>(lst);
        List<String> listWithoutDuplicates = new ArrayList<>(set);
        Collections.sort(listWithoutDuplicates);
        return listWithoutDuplicates;
    }

    public List<String> getAllHyponymsMulti(List<Integer> id) {
        List<String> lst = new ArrayList<>();
        for (int i: id) {
            lst.addAll(graph.getNodes(i));
        }
        lst = RepeatedList(lst);
        Collections.sort(lst);
        return lst;
    }

    private List<String> RepeatedList(List<String> originalList) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String str: originalList) {
            if (countMap.containsKey(str)) {
                int value = 1 + countMap.get(str);
                countMap.put(str, value);
            } else {
                countMap.put(str, 1);
            }
        }

        List<String> repeatedList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= 2) {
                repeatedList.add(entry.getKey());
            }
        }
        return repeatedList;
    }

    /* getting directly connected id */
    public List<Integer> getId(String name) {
        return graph.getSynsets.get(name);
    }

}
