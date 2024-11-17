package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class DummyHistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap map;

    public DummyHistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    /* q.words() gives two words in list; q.startYears() gives start year; q.endYear()
     * map.countHistory() to calculate this word frequency */
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        String response = "";

        TimeSeries ts1 = map.weightHistory(words.get(0), startYear, endYear);
        TimeSeries ts2 = map.weightHistory(words.get(1), startYear, endYear);
        response += words.get(0) + ": " + ts1.toString() + "\n";
        response += words.get(1) + ": " + ts2.toString() + "\n";

        return response;
    }
}
