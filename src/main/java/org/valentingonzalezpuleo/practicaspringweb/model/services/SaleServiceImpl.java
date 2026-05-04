package org.valentingonzalezpuleo.practicaspringweb.model.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.ProductEntity;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.SaleEntity;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.UserEntity;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.SaleDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.SaleRequestDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.exceptions.ProductNotFoundException;
import org.valentingonzalezpuleo.practicaspringweb.model.exceptions.UserNotFoundException;
import org.valentingonzalezpuleo.practicaspringweb.model.repositories.ProductRepository;
import org.valentingonzalezpuleo.practicaspringweb.model.repositories.SaleRepository;
import org.valentingonzalezpuleo.practicaspringweb.model.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
    public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SaleDTO registrarVenta(SaleRequestDTO request) {
        // 1. Validar existencia
        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        // 2. Construir la entidad
        SaleEntity sale = SaleEntity.builder()
                .product(product)
                .user(user)
                .quantity(request.getQuantity())
                .date(LocalDate.now())
                .build();

        return modelMapper.map(saleRepository.save(sale), SaleDTO.class);
    }

    @Override
    public List<SaleDTO> listarVentas() {
        return saleRepository.findAll().stream()
                .map(sale -> modelMapper.map(sale, SaleDTO.class))
                .collect(Collectors.toList());
    }

    }
