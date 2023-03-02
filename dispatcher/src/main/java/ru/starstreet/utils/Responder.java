package ru.starstreet.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Responder {
    private final JokeGenerator jokeGenerator;

    public String answer(String message, String id)  {
        if (message.toLowerCase().contains("шутка")) {
            return jokeGenerator.generate();
        }
        if (message.toLowerCase().contains("привет")) {
            return switch (id) {
                case "427211895" -> "Оля-оля,давай бухать?";
                case "346109889" -> "Привеееет, Котя! Как твои улики?";
                case "5096225073" -> "Привеееет, Серёжа! Как твой попугай?";
                case "5016572852" -> "Привеееет, Лилек! А сколько Лилек надо, чтобы закрутить лампочку?";
                case "757436040" -> "Приветсвую хозяин!";
                default -> "Приветствую тебя странник, если хочешь анекдот, то набери слово \"шутка\"";
            };
        }
        return "Если хочешь анекдот, то набери слово \"шутка\"";
    }
}
