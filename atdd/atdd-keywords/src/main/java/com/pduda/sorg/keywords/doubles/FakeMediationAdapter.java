package com.pduda.sorg.keywords.doubles;

import java.util.logging.Logger;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.pduda.tourney.domain.IMediation;
import com.pduda.tourney.domain.IMediationAdapter;
import com.pduda.tourney.domain.NetworkElement;
import com.pduda.tourney.domain.NetworkSoftware;
import com.pduda.tourney.domain.repository.INetworkElementRepository;

/**
 * Annotation primary is required because of two IMediationAdapter
 * implementation that can be injected into NetworkElement. In production code
 * the real implementation would be injected because FakeMediationAdapter is not
 * on the classpath.
 * 
 * @author unclejamal
 * 
 */
@Component
@Primary
public class FakeMediationAdapter implements IMediationAdapter {

  private Logger log = Logger.getLogger(this.getClass().getName());

  @Autowired
  private IMediation mediation;

  @Transient
  @Autowired
  private transient INetworkElementRepository networkElementRepo;

  @Override
  public void upload(long gid) {
    NetworkSoftware networkSoftware = executeUpload(gid);
    log.info("Request was sent to the fake Mediation: " + gid);

    uploadResults(gid, networkSoftware);

  }

  private NetworkSoftware executeUpload(long gid) {
    NetworkSoftware networkSoftware = mediation.upload(gid);
    return networkSoftware;
  }

  @Override
  public void uploadResults(long gid, NetworkSoftware networkSoftware) {
    NetworkElement ne = networkElementRepo.find(gid);
    ne.setNetworkSoftware(networkSoftware);
    networkElementRepo.update(ne);
  }
}
