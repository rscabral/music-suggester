package com.ifood.challenge.shared.dto;

import java.io.Serializable;
import org.springframework.hateoas.EntityModel;

public interface DtoObject extends Serializable {
  EntityModel<? extends DtoObject> toEntityModel();
}
