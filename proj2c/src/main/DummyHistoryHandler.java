package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class DummyHistoryHandler extends NgordnetQueryHandler {
    private NGramMap map;

    public DummyHistoryHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        TimeSeries ts1 = map.weightHistory(words.get(0), startYear, endYear);
        TimeSeries ts2 = map.weightHistory(words.get(1), startYear, endYear);

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        labels.add(words.get(0));
        labels.add(words.get(1));

        lts.add(ts1);
        lts.add(ts2);

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
