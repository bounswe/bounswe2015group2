package edu.boun.cmpe451.group2.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Base DAO class
 * All Dao classes should inherit this class.
 *
 *
 */
@Repository
@Scope("request")
public class BaseDao {
    /**
     * Database connection object
     */
    private DataSource dataSource;

    /**
     * Spring's database middleware object
     */
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
