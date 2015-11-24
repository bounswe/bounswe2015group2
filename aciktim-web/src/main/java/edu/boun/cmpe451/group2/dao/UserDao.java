package edu.boun.cmpe451.group2.dao;

import java.util.List;
import java.util.Map;

import edu.boun.cmpe451.group2.client.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import edu.boun.cmpe451.group2.utils.Security;

/**
 * User Dao class for User's Database Related operation.
 */
@Repository
@Scope("request")
public class UserDao extends BaseDao {

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
     * adds user to the db
     * @param user user to be added
     */
    public void addUser(User user) {
        String sql = "INSERT INTO users(email, passwd,full_name,username,isInst,api_key) VALUES(?,?,?,?,?,?)";

        this.jdbcTemplate.update(sql, user.email, Security.md5(user.passwd),user.full_name,user.username,user.isInst, Security.randomKey());
    }


    /**
     * Updates user
     *
     * @param id     - user's id to be updated
     * @param email  - user's email address
     * @param passwd - user's password
     */
    public void updateUser(Long id, String email, String passwd, String full_name, String username) {
        String sql = "UPDATE users SET email = ?, passwd = ?, full_name = ?, username = ? WHERE id = ?";

        this.jdbcTemplate.update(sql, email, Security.md5(passwd), full_name, username, id);
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

    /**
     * get user information who has the specified email
     *
     * @param email email address to search
     * @return user information
     */
    public Map<String, Object> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return this.jdbcTemplate.queryForMap(sql, email);
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> getUserByApiKey(String api_key) {
        String sql = "SELECT * FROM users WHERE api_key = ?";
        try {
            return this.jdbcTemplate.queryForMap(sql, api_key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks users email and password
     *
     * @param email
     * @param password
     * @return boolean
     */
    public boolean login(String email, String password) {
        String sql = "SELECT COUNT(*) AS cnt FROM users WHERE email = ? AND passwd = ?";

        Map<String, Object> user = this.jdbcTemplate.queryForMap(sql, email, Security.md5(password));

        if (user.get("cnt").equals("0"))
            return false;
        else
            return true;
    }
}
