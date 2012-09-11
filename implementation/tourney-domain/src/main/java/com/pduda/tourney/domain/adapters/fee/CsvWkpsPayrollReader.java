package com.pduda.tourney.domain.adapters.fee;

import au.com.bytecode.opencsv.CSVReader;
import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.fee.Payroll;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;

@Named
public class CsvWkpsPayrollReader {

    private static final transient Logger log = Logger.getLogger(CsvWkpsPayrollReader.class.getName());
    public static final String MEMBERSHIP_FILE = "D:\\wkps-finanse.csv";
    public static final String MEMBERSHIP_URL = "https://docs.google.com/spreadsheet/pub?key=0AgjwDuotxQJzdFUwa21udXNUNHFGR2wtS2I2QnoyOWc&single=true&gid=3&output=csv";
    public static final String FILE_ENCODING = "UTF-8";

    public Payroll loadFromCsvReader(CSVReader reader) throws IOException {
        Payroll payroll = new Payroll(new Date());

        String[] nextLine;
        boolean startProcessing = false;
        while ((nextLine = reader.readNext()) != null) {
            if (arrivedAtFirstPlayer(nextLine)) {
                startProcessing = true;
            }

            if (startProcessing) {
                String fullName = nextLine[1];
                String membershipType = nextLine[2];

                payroll.add(fullName, membershipType(membershipType));
            }
        }

        return payroll;
    }

    public Payroll loadFromUrl(URL url) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(url.openStream(), FILE_ENCODING));
            return loadFromCsvReader(reader);

        } catch (IOException e) {
            throw new RuntimeException("shite csv", e);
        }
    }

    public Payroll loadFromFile(String filepath) {
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
            return MembershipType.MEMBER_WITH_PAYMENT;
        } else if ("Członkostwo bez licencji".equals(membershipType)) {
            return MembershipType.MEMBER_WITHOUT_PAYMENT;
        }

        return MembershipType.NOT_MEMBER;
    }
}
