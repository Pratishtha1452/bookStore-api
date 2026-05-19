package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.AuthorDao;
import com.bookstore.library.api.domain.Authors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuthorDaoimpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoimpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Authors authors) {
        jdbcTemplate.update("INSERT into authors(id, name, age) VALUES(?, ?, ?)",
        authors.getId(), authors.getName(), authors.getAge()
        );
    }

    @Override
    public Optional<Authors> findOne(long authorId) {
        List<Authors> results = jdbcTemplate.query("SELECT id, name, age FROM authors where id = ? LIMIT 1",
                new AuthorRowMapper(), authorId);
        return results.stream().findFirst();
    }

    public static class AuthorRowMapper implements RowMapper<Authors>{

        @Override
        public Authors mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Authors.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
