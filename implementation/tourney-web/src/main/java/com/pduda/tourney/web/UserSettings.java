package com.pduda.tourney.web;

import com.pduda.tourney.domain.service.FeeHandler;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named("settings")
@Scope("session")
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(UserSettings.class.getClass().getName());
    @Inject
    private FeeHandler feeHandler;

    @PostConstruct
    public void init() {
        log.info("Settings Bean: Post Construct");
        
    }
    
    public Date getMembershipPaymentsLastUpdate() {
        return feeHandler.getMembershipPaymentsLastUpdate();
    }
    
    public void updateMembershipPayments(ActionEvent event) {
        feeHandler.updateMembershipPayments();
        
    }
}
