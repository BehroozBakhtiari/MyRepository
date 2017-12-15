package io.github.ehsanmody.maktab4ver2;

import java.util.Date;
import java.util.UUID;

public class Data {
    private UUID id;
    private String name;
    private String text;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Data() {
        id = UUID.randomUUID();
    }
}
