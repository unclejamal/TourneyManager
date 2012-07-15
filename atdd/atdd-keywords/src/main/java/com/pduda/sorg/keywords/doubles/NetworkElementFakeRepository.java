package com.pduda.sorg.keywords.doubles;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.pduda.tourney.domain.NetworkElement;
import com.pduda.tourney.domain.repository.INetworkElementRepository;

@Repository
@Primary
public class NetworkElementFakeRepository implements INetworkElementRepository, Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public void createDummyNetworkElements() {

    NetworkElement ne1 = new NetworkElement(1L, "adam");
    FakeDatabase.getInstance().putNetworkElement(1L, ne1);

    NetworkElement ne2 = new NetworkElement(2L, "bartek");
    FakeDatabase.getInstance().putNetworkElement(2L, ne2);

    NetworkElement ne3 = new NetworkElement(3L, "czeslaw");
    FakeDatabase.getInstance().putNetworkElement(3L, ne3);

    NetworkElement ne4 = new NetworkElement(4L, "darek");
    FakeDatabase.getInstance().putNetworkElement(4L, ne4);
  }

  @Override
  public List<NetworkElement> findAll() {
    return (List<NetworkElement>) FakeDatabase.getInstance().getAllNetworkElements();
  }

  @Override
  public NetworkElement find(long gid) {
    return (NetworkElement) FakeDatabase.getInstance().getNetworkElement(gid);
  }

  @Override
  public NetworkElement update(NetworkElement ne) {
    FakeDatabase.getInstance().putNetworkElement(ne.getId(), ne);
    return ne;
  }
}
