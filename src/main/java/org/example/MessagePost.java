package org.example;

public abstract class MessagePost {
    public String text;

    //...прочие общие свойства/параметры публикации
    public MessagePost(String text) {
        this.text = text;
    }
}
