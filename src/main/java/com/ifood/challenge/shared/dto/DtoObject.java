package com.ifood.challenge.shared.dto;

import java.io.Serializable;
import org.springframework.hateoas.EntityModel;

public interface DtoObject extends Serializable {
  <T extends DtoObject> EntityModel<T> toEntityModel();
}
