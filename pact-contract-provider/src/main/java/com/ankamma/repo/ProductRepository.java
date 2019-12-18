package com.ankamma.repo;
import java.util.Optional;

import com.ankamma.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @author ankamma pallapu
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

    Optional<Product> findProductById(Integer productId);
}
