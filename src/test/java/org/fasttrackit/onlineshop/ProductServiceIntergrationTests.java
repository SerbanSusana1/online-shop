package org.fasttrackit.onlineshop;

import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.service.ProductService;
import org.fasttrackit.onlineshop.steps.ProductSteps;
import org.fasttrackit.onlineshop.transfer.product.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntergrationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSteps productSteps;
    @Test
     public void testCreateProduct_whenValidRequest_thenReurnCreateProduct(){

        productSteps.createProduct();


    }



    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenInvalidRequest_thenThrowException(){

        SaveProductRequest request =new SaveProductRequest();
        //we're not setting any value reguest,
        // because we want to send an invalid request
         productService.createProduct(request);
    }

    @Test
    public void testGetProduct_whenExistingProduct_thenReturnProduct(){
        Product createProduct = productSteps.createProduct();

        Product retriveProduct = productService.getProduct(productSteps.createProduct().getId());

        assertThat(retriveProduct, notNullValue());
        assertThat(retriveProduct.getId(), is(productSteps.createProduct().getId()));
        assertThat(retriveProduct.getName(), is(productSteps.createProduct().getName()));


    }
    @Test(expected = ResourceNotFoundException.class)
    public  void testGetProduct_whenNotExistingEntity_thenThrowNotFoundException(){
        productService.getProduct(999999);

    }
    @Test
    public  void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct(){

        Product  createProduct = productSteps.createProduct();
        SaveProductRequest request = new SaveProductRequest();
        request.setName(createProduct.getName()+ "Update");
        request.setPrice(createProduct.getPrice()+10);
        request.setQuantity(createProduct.getQuantity()+10);

        Product updateProduct = productService.updateProduct(createProduct.getId(), request);

        assertThat(updateProduct,notNullValue());
        assertThat(updateProduct.getId(),is(createProduct.getId()));
        assertThat(updateProduct.getName(),is(request.getName()));
        assertThat(updateProduct.getPrice(),is(request.getPrice()));
        assertThat(updateProduct.getQuantity(),is(request.getQuantity()));


    }





}
