package com.pduda.tourney.web.payments;

import com.pduda.tourney.domain.fee.MembershipType;

public class PayrollPO {

    private String fullName;
    private MembershipType membershipType;

    public PayrollPO(String fullName, MembershipType membershipType) {
        this.fullName = fullName;
        this.membershipType = membershipType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }
}
