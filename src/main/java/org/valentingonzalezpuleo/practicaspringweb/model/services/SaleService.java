package org.valentingonzalezpuleo.practicaspringweb.model.services;

import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.SaleDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.SaleRequestDTO;
import java.util.List;

public interface SaleService {
    SaleDTO registrarVenta(SaleRequestDTO saleRequest);
    List<SaleDTO> listarVentas();
}
