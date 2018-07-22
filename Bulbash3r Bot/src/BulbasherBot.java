import Constants.Const;
import Modules.MRandom;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static Constants.Const.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Небольшой бот для Телеграма, взаимодействие с которым происходит при помощи /команд.
 */
public class BulbasherBot extends TelegramLongPollingBot {

    private int log_count = 0;

    /**
     * @return Имя бота
     */
    @Override
    public String getBotUsername() {
        return Const.BOT_USERNAME;
    }

    /**
     * Обработка полученного сообщения и совершение какого-либо действия
     * @param e для получения текста сообщения
     */
    @Override
    public void onUpdateReceived (Update e) {
        Message msg = e.getMessage();
        addToLog(msg.getFrom().getUserName() + ": "+ msg.getText());
        String source = msg.getText().split(" ")[0];
        String result;

        if (source.equals("/random")) {
            sendMsg(msg, MRandom.getRandom(msg));
        }
        else if (source.equals("/quote")) {

        }
        else if (source.equals("/memes")) {

        }
        else if (source.equals("/sticker")) {

        }
        else if (source.equals("/hello") || source.equals("/start")) {

        }
        else if (source.equals("/help")) {

        }
        else if (source.equals("/music")) {

        }
        else if (source.equals("/flip") || source.equals("/coin") || source.equals("/flipcoin")) {

        }


       /*
        if (msg.getText().equals ("/random")) {
            sendMsg (msg, "Введите максимальное случайное число");
            random = true;
        }
        else if (msg.getText().equals("/quote")) {
            result = generateQuote()+ " (с) Евгений Степанцов";
            sendMsg (msg, result);
            addToLog("Bot: "+ result);
        }
        else if (msg.getText().equals("/memes")) {
            int i = new MRandom().nextInt(MEMES.length);
            sendImageFromUrl(MEMES[i], Long.toString(msg.getChatId()));
            if (i==3) sendMsg (msg, "Этот мем сделала Настя, вьювер дискрешн из эдвайст");
            addToLog ("Мем отправлен");
        }
        else if (msg.getText().equals("/sticker")) {
            sendStickerFromURL(STICKER[new MRandom().nextInt(STICKER.length)],Long.toString(msg.getChatId()));
            addToLog("Стикер отправлен");
        }
        else if (msg.getText().equals("/hello")||msg.getText().equals("/start")) {
            result = generateHello()+", "+msg.getFrom().getFirstName()+"!\nТыкай /help чтобы начать";
            sendMsg (msg, result);
            addToLog("Bot: "+ result);
        }
        else if (msg.getText().equals("/help")) {
            sendMsg (msg, "Список команд:\n" +
                    "/start или /hello - приветствие\n" +
                    "/help - помощь\n" +
                    "/random - рандомит число\n" +
                    "/quote - выводит цитату Степанцова\n" +
                    "/flip или /coin - бросить монетку\n" +
                    "/memes - присылает мем\n" +
                    "/sticker - отправляет стикер\n" +
                    "/music - отправляет песню");
            addToLog("Помощь выведена");
        }
        else if (msg.getText().equals("/music")) {
            sendMsg(msg, "5 минут назад");
            sendVoiceFromURL(Long.toString(msg.getChatId()));
        }
        else if (msg.getText().equals ("/flip") || msg.getText().equals("/coin")) {
            sendMsg(msg, "Бросаем монетку...");
            result = randomCoin();
            sendMsg(msg, result);
            addToLog(result);
        }*/
    }

    /**
     * @return Токен бота
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN; //токен бота
    }

    @SuppressWarnings("deprecation")
    private void sendMsg (Message msg, String text) {
        SendMessage s = new SendMessage();
        setButtons(s);
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод генерирует приветственное сообщение
     * @return сообщение приветствия
     */
    private String generateHello () {
        return HELLO[new Random().nextInt(HELLO.length)];
    }

    private String randomCoin() {
        String coin ="";
        switch (new Random().nextInt(2)) {
            case 0: coin= "Орёл"; break;
            case 1: coin= "Решка"; break;
        }
        return coin;
    }

    private String generateQuote() {
        return QUOTE[new Random().nextInt(QUOTE.length)];
    }

    /**
     * Метод генерирует случайное число
     * @param str строка, из которой выбирается подстрока с числом-областью рандома
     * @return строка, являющаяся случайным числом
     */
    private String getRand(String str) {
        return Integer.toString(new Random().nextInt(Integer.parseInt(str)));
    }

    private void sendImageFromUrl(String url, String chatId) {
        // Create send method
        SendPhoto sendPhotoRequest = new SendPhoto();
        // Set destination chat id
        sendPhotoRequest.setChatId(chatId);
        // Set the photo url as a simple photo
        sendPhotoRequest.setPhoto(url);
        try {
            // Execute the method
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendStickerFromURL(String url, String chatId) {
        SendSticker sendStickerRequest = new SendSticker();
        sendStickerRequest.setChatId(chatId);
        sendStickerRequest.setSticker(url);
        try {
            sendSticker(sendStickerRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendVoiceFromURL(String chatId) {
        SendVoice sendVoiceRequest = new SendVoice();
        sendVoiceRequest.setChatId(chatId);
        sendVoiceRequest.setVoice("https://psv4.vkuseraudio.net/c815620/u70306077/audios/e1f9edd884a8.mp3?extra=nY8SV_zRr3K5Q_JzaNo3hATKaRtnCbuKOkjTbObuYeLX6HWFLXFtZdWeXser7br9Rj1wQqQN6tuHFYVNxqOywe4SSkPemewU0ArWfVu-PftqaCOYpSC8oDTkWePmK-AzGapiJFVwAXw");
        try {
            sendVoice(sendVoiceRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    private void addToLog(String txt) {
        try(FileWriter writer = new FileWriter("C:\\Users\\yurev\\Documents\\GitHub\\Telegram-Bot\\Bulbash3r Bot\\src\\data\\log.txt", true)) {
            writer.write(Integer.toString(log_count));
            writer.write(" " + txt);
            writer.append('\n');
            log_count++;
            writer.flush();
        } catch (IOException ex) {
            addToLog(ex.getMessage());
        }
    }

    private synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/random"));
        keyboardFirstRow.add(new KeyboardButton("/quote"));


        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/flip"));
        keyboardSecondRow.add(new KeyboardButton("/memes"));
        keyboardSecondRow.add(new KeyboardButton("/sticker"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("/music"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}