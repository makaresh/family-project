package pet.makaresh.bot.basket.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import pet.makaresh.bot.basket.model.SavedProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class ProductRowMapper implements RowMapper<SavedProduct> {
    @Override
    public SavedProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        return new SavedProduct(
                resultSet.getObject("product_id", UUID.class),
                resultSet.getString("product_name"),
                resultSet.getObject("average_price", List.class)
        );
    }
}
