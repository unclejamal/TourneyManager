package com.pduda.tourney.web.po;

import java.io.Serializable;

import javax.faces.model.ListDataModel;


public class TopologyDataModel extends ListDataModel<TopologyPO> implements Serializable {

  private static final long serialVersionUID = 1L;

//  public TopologyDataModel(List<NetworkElement> networkElements) {
//    super(convertToPO(networkElements));
//  }
//
//  private static List<TopologyPO> convertToPO(List<NetworkElement> networkElements) {
//    List<TopologyPO> list = new ArrayList<>();
//    for (NetworkElement networkElement : networkElements) {
//      TopologyPO po = new TopologyPO(networkElement);
//      list.add(po);
//    }
//    return list;
//  }

}
