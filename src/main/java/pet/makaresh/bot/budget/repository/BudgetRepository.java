package pet.makaresh.bot.budget.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BudgetRepository {

    private final JdbcTemplate jdbcTemplate;

}
