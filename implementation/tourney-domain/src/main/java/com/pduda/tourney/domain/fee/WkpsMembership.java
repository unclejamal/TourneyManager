package com.pduda.tourney.domain.fee;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class WkpsMembership {

    @Inject
    private WkpsMembershipReader reader;
    private Map<String, MembershipType> members;

    @PostConstruct
    public void init() {
        members = reader.loadFromFile("D:\\wkps-finanse.csv");
    }
    
    public MembershipType getMembershipType(String fullName) {
        if (members.keySet().contains(fullName)) {
            return members.get(fullName);
        } else {
            return MembershipType.NOT_MEMBER;
        }
    }

    public static enum MembershipType {

        MEMBER_PAID, MEMBER_UNPAID, NOT_MEMBER;
    }
}
