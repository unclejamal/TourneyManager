package com.pduda.tourney.domain.adapters.ranking;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

@Named
public class CsvRankingReader {

    public static final String FILE_ENCODING = "UTF-8";

    public List<RankingEntry> loadFromFile(String filepath) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath), FILE_ENCODING));
            return loadFromCsvReader(reader);
        } catch (IOException e) {
            throw new RuntimeException("shite csv", e);
        }
    }

    public List<RankingEntry> loadFromCsvReader(CSVReader reader) throws IOException {
        List<RankingEntry> rankingEntries = new ArrayList<RankingEntry>();

        String[] nextLine;
        boolean startProcessing = false;
        while ((nextLine = reader.readNext()) != null) {
            if (arrivedAtFirstPlayer(nextLine)) {
                startProcessing = true;
            }

            if (startProcessing) {
                String rank = nextLine[1];
                String code = nextLine[2];
                String fullName = nextLine[3];
                String gender = nextLine[4];
                String city = nextLine[5];
                String club = nextLine[6];
                String points = nextLine[7];
                String pointsAdded = nextLine[8];
                String pointsDeleted = nextLine[9];
                String rankClass = nextLine[10];

                RankingEntry newEntry = new RankingEntry(Integer.valueOf(rank), code, fullName, gender, city, club, Integer.valueOf(points), Integer.valueOf(pointsAdded), Integer.valueOf(pointsDeleted), rankClass);
                rankingEntries.add(newEntry);
            }
        }

        return rankingEntries;
    }

    private boolean arrivedAtFirstPlayer(String[] nextLine) {
        return "1".equals(nextLine[1]);
    }
}
