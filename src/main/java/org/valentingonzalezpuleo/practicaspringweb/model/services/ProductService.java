package org.valentingonzalezpuleo.practicaspringweb.model.services;

import lombok.RequiredArgsConstructor;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.ProductEntity;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.ProductDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.exceptions.ProductNotFoundException;
import org.valentingonzalezpuleo.practicaspringweb.model.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyecta las dependencias "final" automáticamente
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    // Obtener todos usando Streams (Programación Funcional)
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProductDTO.class))
                .collect(Collectors.toList());
    }

    // Buscar por ID usando Optionals
    public ProductDTO findById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));
        return modelMapper.map(entity, ProductDTO.class);
    }

    // Guardar nuevo producto
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity entity = modelMapper.map(productDTO, ProductEntity.class);
        ProductEntity savedEntity = productRepository.save(entity);
        return modelMapper.map(savedEntity, ProductDTO.class);
    }

    // Eliminar producto
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }
}
