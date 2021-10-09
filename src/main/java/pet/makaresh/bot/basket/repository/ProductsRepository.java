package pet.makaresh.bot.basket.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pet.makaresh.bot.basket.model.BasketProduct;
import pet.makaresh.bot.basket.model.BasketRequestedProduct;
import pet.makaresh.bot.basket.model.QuantityProduct;
import pet.makaresh.bot.basket.model.SavedProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public BasketProduct getProduct(UUID id) {
        List<BasketProduct> basketProducts = jdbcTemplate.query(
                "select * from products_basket where product_id = ?",
                (resultSet, i) -> {
                    UUID productId = resultSet.getObject("product_id", UUID.class);
                    String productName = resultSet.getString("product_name");
                    Double quantity = resultSet.getDouble("quantity");
                    String comment = resultSet.getString("comment");
                    return new BasketProduct(productId, productName, quantity, comment);
                },
                id
        );
        return (!basketProducts.isEmpty())
                ? basketProducts.get(0)
                : null;
    }

    @Transactional
    public List<String> getSavedProducts() {
        return jdbcTemplate.queryForList("select product_name from saved_products", String.class);
    }

    @Transactional
    public List<String> getProductsFromBasket() {
        return jdbcTemplate.queryForList("select product_name from products_basket", String.class);
    }

    @Transactional
    public void addToProductList(BasketRequestedProduct basketRequestedProduct) {
        if (!containsInSaved(basketRequestedProduct)) {
            saveProduct(basketRequestedProduct);
        }
        BasketProduct basketProduct = new BasketProduct(basketRequestedProduct);
        jdbcTemplate.update(
                "insert into products_basket(product_id, product_name, quantity, comment) values (?, ?, ?, ?)",
                basketProduct.getProductId(),
                basketProduct.getProductName(),
                basketProduct.getQuantity(),
                basketProduct.getComment()
        );
    }

    @Transactional
    public boolean containsInSaved(BasketRequestedProduct basketRequestedProduct) {
        List<SavedProduct> query = jdbcTemplate.query(
                "select * from saved_products where product_id = (?)",
                (resultSet, i) -> {
                    SavedProduct saved = new SavedProduct();
                    saved.setProductId(resultSet.getObject("product_id", UUID.class));
                    saved.setProductName(resultSet.getString("product_name"));
                    return saved;
                },
                basketRequestedProduct.getProductId()
        );
        return !query.isEmpty();
    }

    @Transactional
    public void saveProduct(BasketRequestedProduct basketRequestedProduct) {
        jdbcTemplate.update(
                "insert into saved_products(product_id, product_name) values (?, ?)",
                basketRequestedProduct.getProductId(),
                basketRequestedProduct.getName()
        );
    }

    @Transactional
    public List<BasketProduct> getBasketList() {
        return jdbcTemplate.query("select * from products_basket", (resultSet, i) -> {
            UUID productId = resultSet.getObject("product_id", UUID.class);
            String productName = resultSet.getString("product_name");
            Double quantity = resultSet.getDouble("quantity");
            String comment = resultSet.getString("comment");
            return new BasketProduct(productId, productName, quantity, comment);
        });
    }

    @Transactional
    public Integer updateProductQuantity(QuantityProduct quantityProduct) {
        Integer count = 0;
        BasketProduct product = getProduct(quantityProduct.getProductId());
        if (product != null) {
            Double quantity = quantityProduct.getQuantity() + product.getQuantity();
            String sql = "update products_basket set quantity = " + quantity + " where product_id = " + quantityProduct.getProductId();
            count = jdbcTemplate.update(sql);
        }
        return count;
    }
}
