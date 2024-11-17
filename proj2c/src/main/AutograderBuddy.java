package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import pro2B.AncestorsHandler;
import pro2B.HyponymsHandler;
import pro2B.WordNet;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        NGramMap map = new NGramMap(wordFile, countFile);
        WordNet wn = new WordNet(synsetFile, hyponymFile);
        return new AncestorsHandler(wn, map);
    }
}
