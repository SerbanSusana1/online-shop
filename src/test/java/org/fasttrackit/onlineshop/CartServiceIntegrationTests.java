package org.fasttrackit.onlineshop;

//import com.sun.org.apache.xerces.internal.util.PropertyState;
//import net.bytebuddy.matcher.ElementMatcher;
import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Customer;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.service.CartService;
import org.fasttrackit.onlineshop.steps.CustomerSteps;
import org.fasttrackit.onlineshop.steps.ProductSteps;
import org.fasttrackit.onlineshop.transfer.cart.AddProducttoCartRequest;
import org.fasttrackit.onlineshop.transfer.cart.CartResponse;
import org.fasttrackit.onlineshop.transfer.product.ProductInCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.Is.is;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
//import  static  org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegrationTests {

    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerSteps customerSteps;
    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testAddToCart_whenNewCart_thenCreateCart(){


    Customer customer = customerSteps.createCustomer();

    Product product = productSteps.createProduct();
        AddProducttoCartRequest request = new AddProducttoCartRequest();
        request.setCustomerId(customer.getId());
        //TODO: replace this after mapping Cart-Product relationship
        request.setProductId(product.getId());
    cartService.addProductToCart(request);

        CartResponse cart = cartService.getCard(customer.getId());

        assertThat(cart,notNullValue());
        assertThat(cart.getId(), is(customer.getId()));
        assertThat(cart.getProducts(), notNullValue());
        assertThat(cart.getProducts(), hasSize(1));

        ProductInCartResponse productFromCart = cart.getProducts().iterator().next();

        assertThat(productFromCart, notNullValue());
        assertThat(productFromCart.getId(), is(request.getProductId()));
        assertThat(productFromCart.getName(), is(product.getName()));
        assertThat(productFromCart.getPrice(), is(product.getPrice()));
    }




}
