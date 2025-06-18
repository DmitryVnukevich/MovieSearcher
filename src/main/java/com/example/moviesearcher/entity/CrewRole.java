package com.example.moviesearcher.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CrewRole {
    DIRECTOR,                // Режиссер
    ACTOR,                   // Актер
    PRODUCER,                // Продюсер
    WRITER,                  // Сценарист
    CINEMATOGRAPHER,         // Оператор-постановщик
    COMPOSER,                // Композитор
    EDITOR,                  // Монтажер
    PRODUCTION_DESIGNER,     // Художник-постановщик
    COSTUME_DESIGNER,        // Художник по костюмам
    MAKEUP_ARTIST,           // Гример
    SOUND_DESIGNER,          // Звукорежиссер
    STUNT_COORDINATOR,       // Координатор трюков
    CHOREOGRAPHER,           // Хореограф
    VISUAL_EFFECTS_SUPERVISOR, // Супервайзер визуальных эффектов
    CASTING_DIRECTOR,        // Кастинг-директор
    EXECUTIVE_PRODUCER,      // Исполнительный продюсер
    ANIMATOR,                // Аниматор
    VOICE_ACTOR,             // Актер озвучки
    PUPPETEER,               // Кукловод
    ANIMAL_TRAINER;           // Дрессировщик животных

    @JsonCreator
    public static CrewRole fromValue(String value) {
        return valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
