package Modules;

import org.telegram.telegrambots.api.objects.Message;

import java.util.Random;

public class MRandom {

    public static String getRandom(Message msg) {
        String[] argv = msg.getText().split(" ");
        Random rand = new Random();
        switch (argv.length) {
            case 1: return "Ваше случайное число: " + rand.nextInt();
            case 2: {
                try {
                    return "Ваше случайное число от 1 до " + argv[1] + ": " + (1 + rand.nextInt(Integer.parseInt(argv[1])));
                } catch (NumberFormatException e) {
                    return "Неверный формат данных";
                }
            }
            case 3: {
                try {
                    return "Ваше случайное число от " + argv[1] + " до " + argv[2] + ": " +
                            (rand.nextInt((Integer.parseInt(argv[2]) - Integer.parseInt(argv[1]))+1) + Integer.parseInt (argv[1]));
                } catch (NumberFormatException e) {
                    return "Неверный формат данных";
                }
            }
            default: return "Неверный формат команды";
        }
    }
}
