package com.pduda.tourney.domain.fee;

import au.com.bytecode.opencsv.CSVReader;
import com.pduda.tourney.domain.fee.WkpsMembership.MembershipType;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class WkpsMembershipReaderTest {

    private CsvWkpsMembershipReader cut;
    private InputStreamReader reader;

    @Before
    public void setUp() throws Exception {
        InputStream finances = getClass().getResourceAsStream("/wkps-finanse.csv");
        reader = new InputStreamReader(finances, CsvWkpsMembershipReader.FILE_ENCODING);
        cut = new CsvWkpsMembershipReader();
    }

    @Test
    public void testSomeMethod() throws Exception {
        Map<String, MembershipType> members = cut.loadFromCsvReader(new CSVReader(reader));

        assertEquals(MembershipType.MEMBER_PAID, members.get("Dawid Liptak"));
        assertEquals(MembershipType.MEMBER_UNPAID, members.get("Maciej Drabik"));
        assertNull(members.get("Edek Klamka"));


    }
}
