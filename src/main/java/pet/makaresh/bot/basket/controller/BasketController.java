package pet.makaresh.bot.basket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.makaresh.bot.basket.model.BasketProduct;
import pet.makaresh.bot.basket.model.BasketRequestedProduct;
import pet.makaresh.bot.basket.model.QuantityProduct;
import pet.makaresh.bot.basket.repository.ProductsRepository;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final ProductsRepository productsRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToBasket(@RequestBody BasketRequestedProduct basketRequestedProduct) {
        productsRepository.addToProductList(basketRequestedProduct);
        return ResponseEntity.ok(basketRequestedProduct.getName() + " was added");
    }

    @GetMapping("/existed")
    public ResponseEntity<List<String>> getExistedProductsInBasket() {
        List<String> productsFromBasket = productsRepository.getSavedProducts();
        return ResponseEntity.ok(productsFromBasket);
    }

    @GetMapping("/show_list")
    public ResponseEntity<List<BasketProduct>> showBasketList() {
        List<BasketProduct> basketList = productsRepository.getBasketList();
        return ResponseEntity.ok(basketList);
    }

    @GetMapping("/basket_products")
    public ResponseEntity<List<String>> getNamesProductsInBasket() {
        List<String> productsFromBasket = productsRepository.getProductsFromBasket();
        return ResponseEntity.ok(productsFromBasket);
    }

    @PostMapping("/update_quantity")
    public ResponseEntity<Integer> updateProductQuantity(@RequestBody QuantityProduct quantityProduct) {
        Integer count = productsRepository.updateProductQuantity(quantityProduct);
        return ResponseEntity.ok(count);
    }
}
