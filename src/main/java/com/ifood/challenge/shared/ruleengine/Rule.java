package com.ifood.challenge.shared.ruleengine;

import java.io.Serializable;

public interface Rule<T extends Serializable> {
  boolean evaluate(T expression);

  Result getResult();
}
