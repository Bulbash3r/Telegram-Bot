import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.api.objects.stickers.Sticker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.*;

/**
 * Небольшой бот для Телеграма, взаимодействие с которым происходит при помощи /команд.
 */
public class BulbasherBot extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = "595245589:AAFh1CMCGwO9iBgXW5w51ZhqP_D3n0Mzfv0"; //Токен бота
    private static final String BOT_USERNAME = "Sticker ID Helper Bot"; //Имя бота

    /**
     * @return Имя бота
     */
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    /**
     * Обработка полученного сообщения и совершение какого-либо действия
     * @param e сообщение пользователя
     */
    @Override
    public void onUpdateReceived (Update e) {
        Message msg = e.getMessage();
        sendMsg (msg, e.getMessage().getSticker());
    }

    /**
     * @return Токен бота
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN; //токен бота
    }


    @SuppressWarnings("deprecation")
    private void sendMsg (Message msg, Sticker sticker) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        addToLog(sticker.getFileId());

        s.setText("Название стикерпака: " + sticker.getSetName() + "\n" +
                "ID стикера: " + sticker.getFileId() + "\n" +
                "Emoji: " + sticker.getEmoji() + "\n");
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    void addToLog(String txt) {
        try(FileWriter writer = new FileWriter("C:\\Users\\yurev\\Documents\\GitHub\\Telegram-Bot\\Sticker Bot\\src\\data\\sticker_data.txt", true)) {
            writer.write(" " + txt);
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            addToLog(ex.getMessage());
        }
    }
}