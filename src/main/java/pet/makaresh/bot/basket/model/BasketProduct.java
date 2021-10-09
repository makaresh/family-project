package pet.makaresh.bot.basket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketProduct {
    private UUID productId;
    private String productName;
    private Double quantity;
    private String comment;

    public BasketProduct(BasketRequestedProduct basketRequestedProduct) {
        this.productId = basketRequestedProduct.getProductId();
        this.productName = basketRequestedProduct.getName();
        this.quantity = 1.0;
        this.comment = basketRequestedProduct.getComment();
    }
}
