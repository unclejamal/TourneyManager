package com.pduda.tourney.domain.fee;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;

@Named
public class CsvWkpsMembershipReader implements WkpsMembershipReader {

    public static final String FILE_ENCODING = "UTF-8";

    @Override
    public Map<String, MembershipType> loadFromCsvReader(CSVReader reader) throws IOException {
        Map<String, MembershipType> members = new HashMap<String, MembershipType>();

        String[] nextLine;
        boolean startProcessing = false;
        while ((nextLine = reader.readNext()) != null) {
            if (arrivedAtFirstPlayer(nextLine)) {
                startProcessing = true;
            }

            if (startProcessing) {
                String fullName = nextLine[1];
                String membershipType = nextLine[2];

                members.put(fullName, membershipType(membershipType));
            }
        }

        return members;
    }

    @Override
    public Map<String, MembershipType> loadFromUrl(URL url) {
         CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(url.openStream(), FILE_ENCODING));
            return loadFromCsvReader(reader);
            
        } catch (IOException e) {
            throw new RuntimeException("shite csv", e);
        }
    }

    @Override
    public Map<String, MembershipType> loadFromFile(String filepath) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath), FILE_ENCODING));
            return loadFromCsvReader(reader);
        } catch (IOException e) {
            throw new RuntimeException("shite csv", e);
        }
    }

    private boolean arrivedAtFirstPlayer(String[] nextLine) {
        return "1".equals(nextLine[0]);
    }

    private MembershipType membershipType(String membershipType) {
        if ("Opłacona legitymacja".equals(membershipType)) {
            return MembershipType.MEMBER_PAID;
        } else if ("Członkostwo bez licencji".equals(membershipType)) {
            return MembershipType.MEMBER_UNPAID;
        }

        return MembershipType.NOT_MEMBER;
    }
}
