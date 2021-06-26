package pet.makaresh.bot.basket.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pet.makaresh.bot.basket.model.Product;
import pet.makaresh.bot.basket.model.SavedProduct;
import pet.makaresh.bot.basket.repository.mapper.ProductRowMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final Map<Integer, String> seasonMap;
    private final Map<Integer, String> userMap;
    private final Map<Integer, String> necessityMap;

    @Transactional
    public int addProductToBasket(Product product) {
        return jdbcTemplate.update(
                "insert into products_basket (product_id, name, price, quantity, comment, necessity, user_name, season)" +
                        " values(?, ?, ?, ?, ?, ?, ?, ?) on conflict (product_id) do nothing",
                product.getProductId(),
                product.getProductName().toLowerCase(),
                product.getPrice(),
                product.getQuantity(),
                product.getComment(),
                necessityMap.entrySet().stream().filter(val -> val.getValue().equals(product.getNecessity())).map(Map.Entry::getKey).collect(Collectors.toList()).get(0),
                userMap.entrySet().stream().filter(val -> val.getValue().equals(product.getUserName())).map(Map.Entry::getKey).collect(Collectors.toList()).get(0),
                seasonMap.entrySet().stream().filter(val -> val.getValue().equals(product.getSeason())).map(Map.Entry::getKey).collect(Collectors.toList()).get(0)
        );
    }

    @Transactional
    public void addProduct(Product product, SavedProduct savedProduct) {
        List<Double> prices = (savedProduct.getAllPrices().isEmpty()) ? savedProduct.getAllPrices() : new ArrayList<>();
        prices.add(product.getPrice());
        jdbcTemplate.update(
                "insert into saved_products (product_id, produc_name, all_prices) " +
                        "values (?, ?, ?) on conflict (product_id) do update set all_prices = ?",
                product.getProductId(),
                product.getProductName().toLowerCase(),
                product.getPrice(),
                prices
        );
    }

    @Transactional
    public SavedProduct getProduct(Product product) {
        if (product.getProductId() == null) {
            UUID productId = UUID.nameUUIDFromBytes(product.getProductName().getBytes());
            addProduct(product, new SavedProduct(productId, product.getProductName(), new ArrayList<Double>()));
            product.setProductId(productId);
        }
        RowMapper<SavedProduct> rowMapper = new ProductRowMapper();
        return jdbcTemplate.queryForObject("select * from saved_products where produc_id = ?",
                rowMapper,
                product.getProductId()
        );
    }

    @Transactional
    public void deleteFromBasket(Product product) {
        jdbcTemplate.update("delete from products_basket where product_id = ?",
                product.getProductId()
        );
    }

    @Transactional
    public void updateQuantityProduct(Product product) {
        if (product.getQuantity() == 0) {
            deleteFromBasket(product);
        } else {
            jdbcTemplate.update("update products_basket set quantity = ? where product_id = ?",
                    product.getProductId(),
                    product.getQuantity()
            );
        }
    }

    @Transactional
    public void setBought(Product product) {
        jdbcTemplate.update("insert into bought_products (product_id, price, month) values (?, ?, ?)",
                product.getProductId(),
                product.getPrice(),
                LocalDate.now().getMonth().getValue()
        );
    }
}
