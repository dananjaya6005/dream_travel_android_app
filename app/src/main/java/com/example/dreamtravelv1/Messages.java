package com.example.dreamtravelv1;

public class Messages {
    //Model class

    String name;
    String imageUrl;

    String body;

    //construcror


    public Messages() {
    }

    public Messages(String name, String imageUrl, String body) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.body =body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

