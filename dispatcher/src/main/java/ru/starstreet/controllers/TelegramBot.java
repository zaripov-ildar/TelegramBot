package ru.starstreet.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.starstreet.utils.JokeGenerator;
import ru.starstreet.utils.Responder;

@Component
@Log4j
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final Responder responder;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        log.debug(message);
        var response = new SendMessage();
        response.setChatId(message.getChatId().toString());
        String user = message.toString();
        String id = user.split("id=")[1]
                .split(",")[0];
        String responseMessage = responder.answer(message.getText(), id);
        response.setText(responseMessage);
        sendAnswerMessage(response);
    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException tae) {
                log.error(tae);
            }
        }
    }
}
