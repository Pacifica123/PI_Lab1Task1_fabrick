package org.example;


public class MessagePostFabrick {
    enum supportedTypeSocial{
        VK, TG
    }
    public static MessagePost createPost(supportedTypeSocial typeSocial, String contentText /*класс с доп. параметрами конкретного указанного поста*/) throws Exception {
        //TODO: предпологается что на каждый тип поста есть свой класс-набор параметров этого поста
        return switch (typeSocial) {
            case TG -> new MessageTG(contentText);
            case VK -> new MessageVK(contentText);
            default -> throw new Exception("Неподдерживаемый тип поста");
        };
    }
}