package edu.boun.cmpe451.group2.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import edu.boun.cmpe451.group2.client.Tag;
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
     * Returns all institutional users (i.e. restaurants).
     */
    public List<Map<String, Object>> getRestaurantsAll() {
        String sql = "SELECT * FROM users WHERE isInst = (1)";

        return this.jdbcTemplate.queryForList(sql);
    }

    /**
     * Returns all restaurants with the given name parameter.
     */
    public List<Map<String, Object>> searchRestaurants(String name) {
        String sql = "SELECT * FROM users WHERE isInst = (1) AND full_name LIKE ?";

        return this.jdbcTemplate.queryForList(sql, "%" + name + "%");
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

    public void updateUser(Long id, String username,String email){
        String sql = "UPDATE users SET email=?,username=? WHERE id=?";

        this.jdbcTemplate.update(sql,email,username,id);
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

    public boolean consume(Long userID,Long recipeID, Calendar calendar){
        String sql = "INSERT INTO dailyConsumption(day,userID,recipeID) VALUES(?,?,?)";
        String time = "";
        time = time + calendar.get(Calendar.YEAR) +"-";
        time = time + calendar.get(Calendar.MONTH)+"-";
        time = time + calendar.get(Calendar.DAY_OF_MONTH);
        this.jdbcTemplate.update(sql,time,userID,recipeID);

        String sqlCheck = "SELECT COUNT(*) FROM dailyConsumption WHERE userID=? AND day=? and recipeID=?";
        Map<String,Object> res = this.jdbcTemplate.queryForMap(sqlCheck,userID,time,recipeID);
        if(Integer.parseInt(res.get("COUNT(*)").toString()) == 0) return false;

        return true;
    }

    public List<Map<String,Object>> getDailyConsumption(Long userID,Calendar calendar){
        String sql = "SELECT * FROM dailyConsumption WHERE userID=? AND day=?";
        String day = ""+calendar.get(Calendar.YEAR)+"-";
        day += calendar.get(Calendar.MONTH)+"-";
        day += calendar.get(Calendar.DAY_OF_MONTH);
        day += " 00:00:00";
        return this.jdbcTemplate.queryForList(sql,userID,day);
    }

    /**
     * this method brings the likes of a user from the db
     * @param userID id of the user whom likes to be brought
     * @return returns arraylist of tags
     */
    public ArrayList<Tag> getLikes(String userID) {
        String sql = "SELECT * FROM userLikes WHERE userID = ?";
        List<Map<String,Object>> likes = this.jdbcTemplate.queryForList(sql,userID);
        ArrayList<Tag> result = new ArrayList<Tag>();
        //changing the return type into required type
        for(Map<String,Object> rows : likes){
            Tag t = new Tag();
            t.name = rows.get("name").toString();
            t.parentTag = rows.get("parentTag").toString();
            result.add(t);
        }
        return result;
    }

    public boolean setLikes(Long userID,ArrayList<Tag> likes){
        String sql = "INSERT INTO userLikes(userID,name,parentTag) VALUES(?,?,?)";
        for(Tag t : likes){
            this.jdbcTemplate.update(sql,userID,t.name,t.parentTag);
        }
        return true;
    }

    public boolean setDislikes(Long userID,ArrayList<Tag> dislikes){
        String sql = "INSERT INTO userDislikes(userID,name,parentTag) VALUES(?,?,?)";
        for(Tag t : dislikes){
            this.jdbcTemplate.update(sql,userID,t.name,t.parentTag);
        }
        return true;
    }

    public boolean setAllergies(Long userID,ArrayList<Tag> allergies){
        String sql = "INSERT INTO userAllergies(userID,name,parentTag) VALUES(?,?,?)";
        for(Tag t : allergies){
            this.jdbcTemplate.update(sql,userID,t.name,t.parentTag);
        }
        return true;
    }

    public ArrayList<Tag> getDislikes(String userID) {
        String sql = "SELECT * FROM userDislikes WHERE userID = ?";
        List<Map<String,Object>> dislikes = this.jdbcTemplate.queryForList(sql,userID);
        ArrayList<Tag> result = new ArrayList<Tag>();
        //changing the return type into required type
        for(Map<String,Object> rows : dislikes){
            Tag t = new Tag();
            t.name = rows.get("name").toString();
            t.parentTag = rows.get("parentTag").toString();
            result.add(t);
        }
        return result;
    }

    public ArrayList<Tag> getAllergies(String userID) {
        String sql = "SELECT * FROM userAllergies WHERE userID = ?";
        List<Map<String,Object>> allergies = this.jdbcTemplate.queryForList(sql,userID);
        ArrayList<Tag> result = new ArrayList<Tag>();
        //changing the return type into required type
        for(Map<String,Object> rows : allergies){
            Tag t = new Tag();
            t.name = rows.get("name").toString();
            t.parentTag = rows.get("parentTag").toString();
            result.add(t);
        }
        return result;
    }
}
