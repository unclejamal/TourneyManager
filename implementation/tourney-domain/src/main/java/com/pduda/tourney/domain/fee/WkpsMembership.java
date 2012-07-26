package com.pduda.tourney.domain.fee;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class WkpsMembership {

    private static final transient Logger log = Logger.getLogger(WkpsMembership.class.getClass().getName());
    @Inject
    private WkpsMembershipReader reader;
    private Map<String, MembershipType> members;
    public static final String MEMBERSHIP_FILE = "D:\\wkps-finanse.csv";
    public static final String MEMBERSHIP_URL = "https://docs.google.com/spreadsheet/pub?key=0AgjwDuotxQJzdFUwa21udXNUNHFGR2wtS2I2QnoyOWc&single=true&gid=3&output=csv";
    private Date lastUpdate;

    @PostConstruct
    public void init() {
        loadMembershipData();
    }

    public MembershipType getMembershipType(String fullName) {
        if (members.keySet().contains(fullName)) {
            return members.get(fullName);
        } else {
            return MembershipType.NOT_MEMBER;
        }
    }

    public void loadMembershipData() {
        log.info("Loading WKPS membership data");
        try {
            members = reader.loadFromUrl(new URL(MEMBERSHIP_URL));
            log.info("Successfully loaded WKPS membership data from Internet");

        } catch (Exception ex) {
            log.info("Loading WKPS membership data from Internet failed. Trying to load from a file");
            members = reader.loadFromFile(MEMBERSHIP_FILE);
            log.info("Successfully loaded WKPS membership data from a file");
        }

        lastUpdate = new Date();
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
