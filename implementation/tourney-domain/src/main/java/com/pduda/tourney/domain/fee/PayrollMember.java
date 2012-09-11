package com.pduda.tourney.domain.fee;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "PAYROLL_MEMBER")
@Configurable(autowire = Autowire.BY_TYPE)
public class PayrollMember implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(PayrollMember.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYROLL_MEMBER_ID")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MEMBERSHIP_TYPE")
    private MembershipType membershipType;

    public PayrollMember() {
        // JPA
    }

    public PayrollMember(String name, MembershipType membershipType) {
        this.name = name;
        this.membershipType = membershipType;
    }

    public String getName() {
        return name;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }
}
