package com.pduda.tourney.web.payments;

import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.fee.Payroll;
import com.pduda.tourney.domain.fee.PayrollMember;
import com.pduda.tourney.domain.service.payments.PaymentsHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("payments")
@Scope("session")
public class PaymentsBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(PaymentsBean.class.getClass().getName());
    @Inject
    private PaymentsHandler feeHandler;
    private List<PayrollPO> membersWithPayment = new ArrayList<PayrollPO>();
    private List<PayrollPO> membersWithoutPayment = new ArrayList<PayrollPO>();

    public Date getMembershipPaymentsLastUpdate() {
        return feeHandler.getPayrollLastUpdate();
    }

    public void updateMembershipPayments(ActionEvent event) {
        feeHandler.updatePayroll();
        membersWithPayment = buildMembersWithMembershipType(MembershipType.MEMBER_WITH_PAYMENT);
        membersWithoutPayment = buildMembersWithMembershipType(MembershipType.MEMBER_WITHOUT_PAYMENT);
    }

    private List<PayrollPO> buildMembersWithMembershipType(MembershipType type) {
        List<PayrollPO> members = new ArrayList<PayrollPO>();
        Payroll payroll = feeHandler.getPayroll();
        for (PayrollMember payrollMember : payroll.getMembersWithMembershipType(type)) {
            members.add(new PayrollPO(payrollMember.getName(), payrollMember.getMembershipType()));
        }

        return members;
    }

    public List<PayrollPO> getMembersWithPayment() {
        return membersWithPayment;
    }

    public List<PayrollPO> getMembersWithoutPayment() {
        return membersWithoutPayment;
    }
}
