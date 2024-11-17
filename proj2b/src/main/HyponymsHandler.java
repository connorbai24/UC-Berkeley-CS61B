package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    public WordNet wn;

    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) { // q represents the input text.
        List<String> words = q.words();
        List<Integer> listId = new ArrayList<>();
        List<String> allHyponyms = new ArrayList<>();

        if (words.size() >= 2) {
            for (int i = 0; i < words.size(); i++) {
                listId.addAll(wn.getId(words.get(i)));
                allHyponyms = wn.getAllHyponymsMulti(listId);
            }
        } else {
            listId.addAll(wn.getId(words.get(0)));
            allHyponyms = wn.getAllHyponymsOne(listId);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < allHyponyms.size() - 1; i++) {
            sb.append(allHyponyms.get(i));
            sb.append(", ");
        }
        sb.append(allHyponyms.get(allHyponyms.size() - 1));
        sb.append("]");
        return sb.toString();
    }

}
