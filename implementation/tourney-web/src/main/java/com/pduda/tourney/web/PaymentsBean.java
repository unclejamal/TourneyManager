package com.pduda.tourney.web;

import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.service.FeeHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named("payments")
@Scope("session")
public class PaymentsBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(PaymentsBean.class.getClass().getName());
    @Inject
    private FeeHandler feeHandler;

    public Date getMembershipPaymentsLastUpdate() {
        return feeHandler.getMembershipPaymentsLastUpdate();
    }

    public void updateMembershipPayments(ActionEvent event) {
        feeHandler.updateMembershipPayments();
    }

    public List<PayrollPO> getPayroll() {
        List<PayrollPO> members = new ArrayList<PayrollPO>();
        Map<String, MembershipType> payroll = feeHandler.getPayroll();
        for (Map.Entry<String, MembershipType> entry : payroll.entrySet()) {
            members.add(new PayrollPO(entry.getKey(), entry.getValue()));
        }

        return members;
    }
}
