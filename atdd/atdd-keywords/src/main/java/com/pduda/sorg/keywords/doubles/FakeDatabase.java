package com.pduda.sorg.keywords.doubles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Singleton for fake database access.
 * 
 * @author unclejamal
 * 
 */
@Component
public class FakeDatabase {

  private static FakeDatabase instance;

  private Map<Long, Object> dbNetworkElements = new HashMap<>();

  /** Singleton's private constructor. */
  private FakeDatabase() {
  }

  /** Singleton's access method. */
  public synchronized static FakeDatabase getInstance() {
    if (instance == null) {
      instance = new FakeDatabase();
    }
    return instance;
  }

  public Object getNetworkElement(long oid) {
    return dbNetworkElements.get(oid);
  }

  public Object getAllNetworkElements() {
    return dbNetworkElements.values();
  }

  public void putNetworkElement(Long oid, Object object) {
    dbNetworkElements.put(oid, object);
  }

}
