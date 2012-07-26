package com.pduda.tourney.domain.ranking;

import au.com.bytecode.opencsv.CSVReader;
import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.RankClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class RankingCsvLoader {

    public static final String FILE_ENCODING = "UTF-8";

    public Ranking loadFromFile(String filepath) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath), FILE_ENCODING));
            return loadFromCsvReader(reader);
        } catch (IOException e) {
            throw new RuntimeException("shite csv", e);
        }
    }

    public Ranking loadFromCsvReader(CSVReader reader) throws IOException {
        Ranking ranking = new Ranking();

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

                ranking.addPlayer(Integer.valueOf(rank), code, fullName, gender(gender), city, club, Integer.valueOf(points), Integer.valueOf(pointsAdded), Integer.valueOf(pointsDeleted), rankClass(rankClass));
            }
        }

        return ranking;
    }

    private boolean arrivedAtFirstPlayer(String[] nextLine) {
        return "1".equals(nextLine[1]);
    }

    private RankClass rankClass(String rankClass) {
        if ("NOVICE".equals(rankClass)) {
            return RankClass.NOVICE;
        } else if ("AMATOR".equals(rankClass)) {
            return RankClass.AMATOR;
        } else if (("SEMIPRO".equals(rankClass)) || ("SEMI PRO".equals(rankClass)) || ("SEMI-PRO".equals(rankClass))) {
            return RankClass.SEMIPRO;
        } else if ("PRO".equals(rankClass)) {
            return RankClass.PRO;
        } else if ("MASTER".equals(rankClass)) {
            return RankClass.MASTER;
        }

        return RankClass.NOTRANKED;
    }

    private Gender gender(String gender) {
        if ("M".equals(gender)) {
            return Gender.MALE;
        } else if ("K".equals(gender)) {
            return Gender.FEMALE;
        }

        return Gender.UNKNOWN;
    }
}
