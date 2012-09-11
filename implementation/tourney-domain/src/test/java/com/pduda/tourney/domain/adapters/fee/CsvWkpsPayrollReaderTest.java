package com.pduda.tourney.domain.adapters.fee;

import au.com.bytecode.opencsv.CSVReader;
import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.fee.Payroll;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CsvWkpsPayrollReaderTest {

    private CsvWkpsPayrollReader cut;
    private InputStreamReader reader;

    @Before
    public void setUp() throws Exception {
        InputStream finances = getClass().getResourceAsStream("/wkps-finanse.csv");
        reader = new InputStreamReader(finances, CsvWkpsPayrollReader.FILE_ENCODING);
        cut = new CsvWkpsPayrollReader();
    }

    @Test
    public void testSomeMethod() throws Exception {
        Payroll payroll = cut.loadFromCsvReader(new CSVReader(reader));

        assertEquals(MembershipType.MEMBER_WITH_PAYMENT, payroll.getMembershipType("Dawid Liptak"));
        assertEquals(MembershipType.MEMBER_WITHOUT_PAYMENT, payroll.getMembershipType("Maciej Drabik"));
        assertEquals(MembershipType.NOT_MEMBER, payroll.getMembershipType("Edek Klamka"));


    }
}
