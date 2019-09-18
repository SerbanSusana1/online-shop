package org.fasttrackit.onlineshop.persistance;

import org.fasttrackit.onlineshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

  Page<Product> findByNameContaining(String partialName, Pageable pageable);
  Page<Product> findByNameContainingAndQuantityGreaterThanEqual(
          String partialName,int minimumQuantity , Pageable pageable);

  //jpql syntax (JAVA
  //@Query(value = "SELECT  product  from  Product product where '%:partialName'", nativeQuery = true)
    @Query(value = "SELECT  * from product where  name like '%?1%'", nativeQuery = true)
  List<Product> findByPartialName(String partialName);



}
