import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class Address implements ISetValue{
    private static final Logger log = Logger.getLogger(Address.class);
    private String street;
    private Integer house;

    //region getters setters
    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    //endregion

    //метод для установки значений полей класса из JSON
    public void SetValueField(JsonObject jsonObject) {
        Field[] fields = this.getClass().getDeclaredFields();
        //получаем множество пар ключ-значение из JSON
        Set<Map.Entry<String, JsonElement>> pairJSON = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> pair : pairJSON) {
            for (Field field : fields) {
                if (field.getName().equals(pair.getKey())) {
                    String value = jsonObject.get(field.getName()).toString();
                    try {
                        field.setAccessible(true);
                        SetValue(field, value);
                    } catch (IllegalAccessException e) {
                        try {
                            SetValue(field, null);
                            log.log(Level.ERROR, e);
                        } catch (IllegalAccessException ex) {
                            System.out.println("В настоящее время исполняемый метод не имеет доступа к определению указанного класса, поля, метода или конструктора. Подробнее см. log-файл");
                            log.log(Level.ERROR, ex);
                        }
                    }
                }
            }
        }
    }
}