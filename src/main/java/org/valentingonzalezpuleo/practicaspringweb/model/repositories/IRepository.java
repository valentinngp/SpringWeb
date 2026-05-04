package org.valentingonzalezpuleo.practicaspringweb.model.repositories;

import org.valentingonzalezpuleo.practicaspringweb.model.entities.SaleEntity;

import java.util.List;

public interface IRepository <T>{

    List<T> findAll();

    SaleEntity save(T entity);

    void delete(T entity);

    void update(T entity);
}
