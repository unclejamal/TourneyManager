package com.pduda.tourney.domain.adapters.fee;

import com.pduda.tourney.domain.fee.Payroll;
import com.pduda.tourney.domain.fee.WkpsPayrollReader;
import java.net.URL;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class InternetThenFileWkpsPayrollReader implements WkpsPayrollReader {

    private static final transient Logger log = Logger.getLogger(InternetThenFileWkpsPayrollReader.class.getName());
    public static final String MEMBERSHIP_FILE = "D:\\wkps-finanse.csv";
    public static final String MEMBERSHIP_URL = "https://docs.google.com/spreadsheet/pub?key=0AgjwDuotxQJzdFUwa21udXNUNHFGR2wtS2I2QnoyOWc&single=true&gid=3&output=csv";
    @Inject
    private CsvWkpsPayrollReader reader;

    @Override
    public Payroll loadPayroll() {
        log.info("Loading WKPS membership data");
        Payroll payroll;
        try {
            payroll = reader.loadFromUrl(new URL(MEMBERSHIP_URL));
            log.info("Successfully loaded WKPS membership data from Internet");

        } catch (Exception ex) {
            log.info("Loading WKPS membership data from Internet failed. Trying to load from a file");
            payroll = reader.loadFromFile(MEMBERSHIP_FILE);
            log.info("Successfully loaded WKPS membership data from a file");
        }

        return payroll;
    }
}
