package ru.ifmo.javawebapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * В классе реализованы основные операции с контакт листом.
 * @author Anton Skshidlevsky
 * @see http://www.h2database.com/html/quickstart.html 
 */
public class UsersFunc {
    
    private static final String dbUrl = "jdbc:h2:~/test1";

    /**
     * Конструктор класса, регистрирует драйвер БД.
     */
    public UsersFunc() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Вспомогательный метод для закрытия соединений с БД.
     * @param closeable 
     */
    private static void closeQuietly(Connection closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (SQLException ex) {
                // ignore
            }
        }
    }
    
    /**
     * Создать БД.
     * @return в случае успеха возвражает пустой JSON объект, иначе null.
     */
    public JSONObject createDB() {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            Statement st = conn.createStatement();
            st.execute("create table users(id INT PRIMARY KEY AUTO_INCREMENT, login varchar(255),"
                    + "pass varchar(255))");
            
            result = new JSONObject();
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }

    public boolean checking(String loginCheck, String passCheck) {
        boolean check = false;
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM USERS");
            while (result.next()) {
                if(result.getString("LOGIN").equals(loginCheck) && result.getString("PASS").equals(passCheck)){
                    check = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return check;
    }

    public JSONObject add(Users c) {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            String q = "INSERT INTO USERS(login,pass) VALUES(?,?)";
            PreparedStatement st = conn.prepareStatement(q);

            st.setString(1, c.getLOGIN());
            st.setString(2, c.getPASS());
            st.execute();
            
            long id = -1;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            result = new JSONObject();
            result.put("id", id);
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    } 
    
}
