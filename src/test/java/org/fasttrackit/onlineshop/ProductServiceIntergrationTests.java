package org.fasttrackit.onlineshop;

import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.service.ProductService;
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
    @Test
     public void testCreateProduct_whenValidRequest_thenReurnCreateProduct(){

        createProduct();


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
        Product createProduct = createProduct();

        Product retriveProduct = productService.getProduct(createProduct.getId());

        assertThat(retriveProduct, notNullValue());
        assertThat(retriveProduct.getId(), is(createProduct.getId()));
        assertThat(retriveProduct.getName(), is(createProduct.getName()));


    }
    @Test(expected = ResourceNotFoundException.class)
    public  void testGetProduct_whenNotExistingEntity_thenThrowNotFoundException(){
        productService.getProduct(999999);

    }
    private Product createProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Computer");
        request.setDescription("Some description");
        request.setPrice(2000);
        request.setQuantity(100);

        Product product = productService.createProduct(request);

        assertThat(product,notNullValue());
        assertThat(product.getId(),notNullValue());
        //assertThat(product.getId(), greaterThat((0L)));
        assertThat(product.getName(), is(request.getName()));
        assertThat(product.getDescription(), is(request.getDescription()));
        assertThat(product.getPrice(), is(request.getPrice()));
        assertThat(product.getQuantity(), is(request.getQuantity()));

        return product;
    }

}
