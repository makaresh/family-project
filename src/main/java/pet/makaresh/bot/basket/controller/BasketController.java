package pet.makaresh.bot.basket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.makaresh.bot.basket.model.Product;
import pet.makaresh.bot.basket.model.SavedProduct;
import pet.makaresh.bot.basket.repository.ProductsRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final ProductsRepository productsRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToBasket(@RequestBody Product product) {
        SavedProduct savedProduct = productsRepository.getProduct(product);
        int addedProduct = productsRepository.addProductToBasket(product);
        return ResponseEntity.ok(product.getProductName() + " was added");
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> add(@RequestBody Product product) {
        productsRepository.deleteFromBasket(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change")
    public ResponseEntity<Void> changeQuantity(@RequestBody Product product) {
        productsRepository.updateQuantityProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/bought")
    public ResponseEntity<Void> setBought(@RequestBody Product product) {
        productsRepository.setBought(product);
        return ResponseEntity.ok().build();
    }
}
