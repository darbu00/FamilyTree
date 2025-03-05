package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

//@Repository
public interface EntityDAO<T> {

  @Transactional
  T saveEntity(T object);

  T findEntityById(Integer id);

  List<T> getEntityList();

  T updateEntity(T object);

  List<T> getEntityByColumn(String column, String columnValue);
}
