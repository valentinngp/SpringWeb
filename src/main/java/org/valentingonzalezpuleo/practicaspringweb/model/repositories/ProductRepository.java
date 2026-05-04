package org.valentingonzalezpuleo.practicaspringweb.model.repositories;

import org.valentingonzalezpuleo.practicaspringweb.model.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //tengo findAll(), save(), deleteById(), etc. directo a MySQL.
}
