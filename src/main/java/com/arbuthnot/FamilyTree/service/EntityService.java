package com.arbuthnot.FamilyTree.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EntityService<T> {

  public boolean validateEntity(T entity);

  public T saveEntity(T entity);

  public T updateEntity(T entity);

  public void deleteEntity(T entity);

  public void addEntity(T entity);

  public T getEntityById(int id);

  public List<T> getAll();

  public List<T> getAllOrderBy(String column);

  public List<T> getAllByColumnValue(String column, String value);

}
