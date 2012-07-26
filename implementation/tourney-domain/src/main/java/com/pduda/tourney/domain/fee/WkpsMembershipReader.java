package com.pduda.tourney.domain.fee;

import au.com.bytecode.opencsv.CSVReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;


public interface WkpsMembershipReader {

    Map<String, MembershipType> loadFromCsvReader(CSVReader reader) throws IOException;

    Map<String, MembershipType> loadFromFile(String filepath);
    
    Map<String, MembershipType> loadFromUrl(URL url);

}
