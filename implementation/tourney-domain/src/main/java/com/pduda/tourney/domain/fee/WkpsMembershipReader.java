package com.pduda.tourney.domain.fee;

import au.com.bytecode.opencsv.CSVReader;
import com.pduda.tourney.domain.fee.WkpsMembership.MembershipType;
import java.io.IOException;
import java.util.Map;


public interface WkpsMembershipReader {

    Map<String, MembershipType> loadFromCsvReader(CSVReader reader) throws IOException;

    Map<String, MembershipType> loadFromFile(String filepath);

}
