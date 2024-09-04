package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

//@Repository
public interface EntityDAO {

  @Transactional
  void saveEntity(Object object);

  Object findEntityById(Integer id);

  List<?> getEntityList();

  void updateEntity(Object object);

  List<?> getEntityByColumn(String column, String columnValue);
}
