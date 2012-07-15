package com.pduda.sorg.keywords.doubles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pduda.tourney.domain.IMediation;
import com.pduda.tourney.domain.NetworkSoftware;

@Component
public class FakeMediation implements IMediation {

  private Map<Long, NetworkSoftware> softwareMap = new HashMap<>();

  public void addSoftware(Long gid, NetworkSoftware soft) {
    softwareMap.put(gid, soft);
  }

  @Override
  public NetworkSoftware upload(long gid) {
    return softwareMap.get(gid);
  }

}
