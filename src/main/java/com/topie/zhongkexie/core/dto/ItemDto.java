package com.topie.zhongkexie.core.dto;

import java.math.BigDecimal;
import java.util.List;

public class ItemDto {

    private Integer id;

    private String title;

    private String type = "item";

    private BigDecimal score;

    private List<OptionDto> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionDto> getItems() {
        return items;
    }

    public void setItems(List<OptionDto> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
