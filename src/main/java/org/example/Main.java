package org.example;



public class Main {
    public static void main(String[] args) throws Exception {
        Integer GROUP_ID = 221692633;
        String ACCESS_TOKEN_APP = "vk1.a.wpyJdJ9z9Agl7zuhfZyevbdrWZp5IcCacFQ2IsPE50C6DaB5iuWNNkKRNQqecWRAXhZrP4lMF_REnPRumkIXvzcW1B9LhcvS5sg-1f_87R1e_rIANxqprIrqiGqLWmSZnQg9hItdKiAT4_mi1UmbwCzdNvMoJF432FR_zveWH8yTO_8wVOZ_0h20jdSxoyjXuKsgqekJJSM4F1UaLRTX5g";
        String ACCESS_TOKEN = "vk1.a.Kw4y1E51D6SN-mX2VxSUGVj5VUf2bARNGpBTMjtN4I0Cm1v2LBtSgmnECfVt780NvsGyDZEfib5LYhqim_qcbTwvgqZdbo1HCjXOjVgJGEY4oJnhczrJ5R1lcTRCoge2M9ArjFRcFxuOqEXqFptBF6D4c7mMVRxTNmdnx9LOi4UZ-Rrc4ytZmzIFChT1PV9YKVDT465BvN2ECB6ADJLNpQ";

        String TG_TOKEN = "6059136593:AAH8zSDN6hrzRwdWZg5lYg3QE0x6wrVjFUc";
        String TG_CHAT_ID = "5294825064";

        // Постилка
        PostingOnSocial pos = new PostingOnSocial();

        // Отправка в ВК
        MessageVK postVK = (MessageVK) MessagePostFabrick.createPost(MessagePostFabrick.supportedTypeSocial.VK, "FabrickToVK!");
        boolean ok_responce =  pos.publishToVk(GROUP_ID, ACCESS_TOKEN, postVK);
        if (ok_responce) {
            System.out.println("Отправлено!");
        }

//        // Отправка на Телеграмм
//        MessageTG postTG = (MessageTG) MessagePostFabrick.createPost(MessagePostFabrick.supportedTypeSocial.TG, "Fabrick готова!");
//        System.out.println(pos.publishToTelegram(TG_TOKEN, TG_CHAT_ID, postTG));

    }
}