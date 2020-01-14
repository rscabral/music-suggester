package com.ifood.challenge.shared.mapper;

import com.ifood.challenge.shared.dto.DtoObject;

public interface IRepositoryMapper<F extends Object, T extends DtoObject> {
  T toDto(F repositoryObject);
}
