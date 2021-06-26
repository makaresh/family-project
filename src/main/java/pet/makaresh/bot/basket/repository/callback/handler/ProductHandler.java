package pet.makaresh.bot.basket.repository.callback.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowCallbackHandler;
import pet.makaresh.bot.basket.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProductHandler implements RowCallbackHandler {

    private Map<Integer, String> seasonMap;
    private Map<Integer, String> userMap;
    private Map<Integer, String> necessityMap;
    private Map<String, Product> map;

    public ProductHandler(Map<Integer, String> seasonMap, Map<Integer, String> userMap, Map<Integer, String> necessityMap) {
        this.seasonMap = seasonMap;
        this.userMap = userMap;
        this.necessityMap = necessityMap;
        map = new HashMap<>();
    }

    @Override
    public void processRow(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getObject("product_id", UUID.class));
        String productName = resultSet.getString("product_name");
        product.setProductName(productName);
        product.setPrice(resultSet.getDouble("price"));
        product.setQuantity(resultSet.getDouble("quantity"));
        product.setComment(resultSet.getString("comment"));
        product.setNecessity(necessityMap.get(resultSet.getInt("necessity")));
        product.setUserName(userMap.get(resultSet.getInt("user_name")));
        product.setSeason(seasonMap.get(resultSet.getInt("season")));
        map.put(productName, product);
    }
}
