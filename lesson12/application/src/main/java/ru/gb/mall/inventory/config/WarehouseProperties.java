package ru.gb.mall.inventory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("warehouse")
@PropertySource(value = "file:C:\\Users\\obelix\\IdeaProjects\\GeekbrainsSpringShop\\lesson12\\application\\config\\warehouse.properties")
public class WarehouseProperties {

    private String prefix;
    private int number;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public WarehouseProperties() {
    }

}
