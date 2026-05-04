package org.valentingonzalezpuleo.practicaspringweb.model.repositories;

import org.valentingonzalezpuleo.practicaspringweb.model.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {}
