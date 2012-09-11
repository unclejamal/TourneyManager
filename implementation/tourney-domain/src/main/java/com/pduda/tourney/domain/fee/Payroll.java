package com.pduda.tourney.domain.fee;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "PAYROLL")
@Configurable(autowire = Autowire.BY_TYPE)
public class Payroll implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(Payroll.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYROLL_MEMBER_ID")
    private long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<PayrollMember> members = new HashSet<PayrollMember>();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;

    public Payroll() {
        // JPA
    }

    public Payroll(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public MembershipType getMembershipType(String fullName) {
        PayrollMember payrollMember = findPayrollMember(fullName);
        return payrollMember.getMembershipType();
    }

    public Set<PayrollMember> getMembersWithMembershipType(MembershipType type) {
        Set<PayrollMember> toReturn = new HashSet<PayrollMember>();
        for (PayrollMember payrollMember : members) {
            if (type.equals(payrollMember.getMembershipType())) {
                toReturn.add(payrollMember);
            }
        }
        return toReturn;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void add(String fullName, MembershipType membershipType) {
        PayrollMember payrollMember = new PayrollMember(fullName, membershipType);
        members.add(payrollMember);
    }

    private PayrollMember findPayrollMember(String fullName) {
        for (PayrollMember payrollMember : members) {
            if (fullName.equals(payrollMember.getName())) {
                return payrollMember;
            }
        }

        return new NullPayrollMember(fullName);
    }

    public long getId() {
        return id;
    }

    public Set<PayrollMember> getMembers() {
        return members;
    }

    private class NullPayrollMember extends PayrollMember {

        public NullPayrollMember(String name) {
            super(name, MembershipType.NOT_MEMBER);
        }
    }
}
