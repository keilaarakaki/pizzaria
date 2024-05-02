package com.pizzaria.repositories;

import com.pizzaria.models.PizzaPedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaPedidaRepository extends JpaRepository <PizzaPedida, Long> {
}
