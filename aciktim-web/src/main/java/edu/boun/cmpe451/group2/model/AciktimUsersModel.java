package edu.boun.cmpe451.group2.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Scope("request")
public class AciktimUsersModel {
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

    /**
     * Returns all of users.
     */
    public List<Map<String, Object>> getUsers() {
        String sql = "SELECT * FROM users";

        return this.jdbcTemplate.queryForList(sql);
    }

    /**
     * Selects one user from database.
     *
     * @param id - user's id
     */
    public Map<String, Object> getUser(Long id) {
        String sql = "SELECT * FROM users WHERE id = ? ";

        return this.jdbcTemplate.queryForMap(sql, id);
    }

    /**
     * Adds a new user
     *
     * @param email  - user's email address
     * @param passwd - users's password
     */
    public void addUser(String email, String passwd) {
        String sql = "INSERT INTO users(email, passwd) VALUES(?, ?)";

        this.jdbcTemplate.update(sql, email, passwd);
    }

    /**
     * Updates user
     *
     * @param id     - user's id to be updated
     * @param email  - user's email address
     * @param passwd - user's password
     */
    public void updateUser(Long id, String email, String passwd) {
        String sql = "UPDATE users SET email = ?, passwd = ? WHERE id = ?";

        this.jdbcTemplate.update(sql, email, passwd, id);
    }

    /**
     * Deletes a user from database
     *
     * @param id - user's id to be deleted
     */
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        this.jdbcTemplate.update(sql, id);
    }
}
