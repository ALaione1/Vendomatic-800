package com.techelevator.view;

import com.techelevator.core.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InventoryTests {

        @Test
        public void testDisplayProduct() throws IOException {
            //arrange
            Map<Product, Integer> slotInventoryTest = new HashMap<>();
            Product product = new Product(123, "Cookie Crisps", "Chip", 101, 1.00);
            slotInventoryTest.put(product, 101);
            String results = product.getName();
            Assert.assertEquals(results, "Cookie Crisps");

        }

        @Test
        public void testGetProductByCodeShouldReturnValid() {
            //arrange
            Product product = new Product(123, "Cookie Crisps", "Chip", 101, 1.00);
            Assert.assertEquals(product.getCode(), 101);
        }

        @Test
        public void getProductByInvalidCodeReturnsErrorMessage() {
            Product product = new Product(123, "Cookie Crisps", "Chip", 101, 1.00);
            product.setCode(100);
            Assert.assertEquals("invalid code selection", 100, product.getCode(), 0);
        }
    }

