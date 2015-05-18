package ru.ifmo.javawebapp;

import java.util.Date;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Класс описывает контакт (JSON формат).
 * @author Anton Skshidlevsky
 * @see https://code.google.com/p/json-simple/
 */
public class Contact implements JSONAware {

    private Long id;
    private String nick;
    private String mess;

    /**
     * Конструктор контакта.
     * @param id идентификатор контакта.
     * @param fio ФИО.
     */
    public Contact(Long id, String nick, String mess) {
        this.id = id;
        this.nick = nick;
        this.mess = mess;
    }
    
    public Long getId() {
        return id;
    }

    public String getNICK() {
        return nick;
    }
    
    public String getMESS(){
        return mess;
    }

    /**
     * Преобразует данные контакта в JSON строку.
     * @return данные контакта в формате JSON.
     */
    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("\"").append(JSONObject.escape("id")).append("\"");
        sb.append(":");
        sb.append(id);

        sb.append(",");
        
        sb.append("\"").append("nick").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(nick)).append("\"");

        sb.append(",");
        
        sb.append("\"").append("mess").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(mess)).append("\"");
        
        sb.append("}");

        return sb.toString();
    }
}
