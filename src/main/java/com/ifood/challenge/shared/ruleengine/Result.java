package com.ifood.challenge.shared.ruleengine;

import java.io.Serializable;

public interface Result<T extends Serializable> {
  T getValue();

}
