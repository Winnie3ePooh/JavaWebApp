package ru.ifmo.javawebapp;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Класс описывает контакт (JSON формат).
 * @author Anton Skshidlevsky
 * @see https://code.google.com/p/json-simple/
 */
public class Contact implements JSONAware {

    private Long id;
    private String fio;
    private String phone;
    private String email;

    /**
     * Конструктор контакта.
     * @param id идентификатор контакта.
     * @param fio ФИО.
     * @param phone телефон.
     * @param email электронная почта.
     */
    public Contact(Long id, String fio) {
        this.id = id;
        this.fio = fio;
    }
    
    public Long getId() {
        return id;
    }

    public String getFIO() {
        return fio;
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

        sb.append("\"").append("fio").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(fio)).append("\"");

        sb.append("}");

        return sb.toString();
    }
}
