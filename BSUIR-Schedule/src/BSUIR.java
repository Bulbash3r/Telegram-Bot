import jdk.nashorn.internal.parser.JSONParser;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileReader;
import org.json.*;


/**
 * Небольшой бот для Телеграма, взаимодействие с которым происходит при помощи /команд.
 */
public class BSUIR extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = "515118500:AAG8XBvlgIOpbH6C9sTxURg39QcZEtiOiqg"; //Токен бота
    private static final String BOT_USERNAME = "BSUIR Schedule"; //Имя бота
    /**
     * @return Имя бота
     */
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    /**
     * Обработка полученного сообщения и совершение какого-либо действия
     * @param e для получения текста сообщения
     */
    @Override
    public void onUpdateReceived (Update e) {
        Message msg = e.getMessage();

        if (msg.getText().equals("/start")) {
            sendMsg (msg, "Hello!");
        }
        else if (msg.getText().equals("/group")) {
            getJSON();
        }
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
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getJSON() {
        JSONObject obj = new JSONObject("/src/schedule.json");
        int group = obj.getJSONObject("studentGroup").getInt("name");
        System.out.println(group);
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
        // Create send method
        SendSticker sendStickerRequest = new SendSticker();
        // Set destination chat id
        sendStickerRequest.setChatId(chatId);
        // Set the photo url as a simple photo
        sendStickerRequest.setSticker(url);
        try {
            // Execute the method
            sendSticker(sendStickerRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

/*    void addToLog(String txt) {
        try(FileWriter writer = new FileWriter("C:\\Users\\yurev\\Documents\\GitHub\\Java-Labs\\Telegram Bot\\src\\data\\log.txt", true)) {
            writer.write(Integer.toString(log_count));
            writer.write(" " + txt);
            writer.append('\n');
            log_count++;
            writer.flush();
        } catch (IOException ex) {
            addToLog(ex.getMessage());
        }
    }
*/
   /* private synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/random"));
        keyboardFirstRow.add(new KeyboardButton("/quote"));


        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("/flip"));
        keyboardSecondRow.add(new KeyboardButton("/memes"));
        keyboardSecondRow.add(new KeyboardButton("/sticker"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("/music"));
        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }*/
}