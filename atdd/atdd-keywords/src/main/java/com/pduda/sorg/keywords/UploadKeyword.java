package com.pduda.sorg.keywords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pduda.tourney.domain.NetworkElement;
import com.pduda.tourney.domain.NetworkSoftware;
import com.pduda.tourney.domain.repository.INetworkElementRepository;
import com.pduda.tourney.domain.service.TaskHandler;
import com.pduda.sorg.keywords.doubles.FakeMediation;

public class UploadKeyword {

  private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("keywords-context.xml");

  private TaskHandler sut = CONTEXT.getBean(TaskHandler.class);
  
  @Autowired
  private INetworkElementRepository networkElementRepo;

  public void upload(long gid, String swName, String expectedNetworkSoftwareName) {
    givenDatabaseContainsNetworkElementWithGid(gid);
    givenMediationReturnsSoftwareForGid(gid, swName);

    sut.upload(gid);

    NetworkElement result = networkElementRepo.find(gid);
    checkEquals("sw name", expectedNetworkSoftwareName, result.getNetworkSoftware().getName());
  }

  private void checkEquals(String attr, Object expected, Object actual) {
    if (!expected.equals(actual))
      throw new RuntimeException(String.format("%s - Expected: %s, Actual: %s", attr, expected, actual));
  }

  private void givenDatabaseContainsNetworkElementWithGid(long gid) {
    NetworkElement ne = new NetworkElement(gid, "rodey");
    networkElementRepo.update(ne);

  }

  private void givenMediationReturnsSoftwareForGid(long gid, String swName) {
    FakeMediation mediation = CONTEXT.getBean(FakeMediation.class);
    mediation.addSoftware(gid, new NetworkSoftware(1L, swName));
  }
}
