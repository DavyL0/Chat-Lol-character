package com.chatlol.adapters.out;

import com.chatlol.domain.model.Champions;
import com.chatlol.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    private  final JdbcTemplate jdbcTemplate;
    private final RowMapper<Champions> championsRowMapper;

//Busca no banco de Dados
    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.championsRowMapper = ((rs, rowNum) -> new Champions(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("lore"),
                rs.getString("image_url")

            )
        );
    }

    @Override
    public List<Champions> findAll(){

        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", championsRowMapper);
    }


    public Optional<Champions> findByID(Long id){
        String sql = "SELECT * FROM CHAMPIONS WHERE ID = ?";
        List<Champions> champions = jdbcTemplate.query(sql, championsRowMapper, id);
        return champions.stream().findFirst();
    }
}
