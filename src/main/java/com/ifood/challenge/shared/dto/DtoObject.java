package com.ifood.challenge.shared.dto;

import org.springframework.hateoas.EntityModel;

public interface DtoObject {
  EntityModel<? extends DtoObject> toEntityModel();
}
