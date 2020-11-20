package dev.robinsyl.telegram.keyboard;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.LoginUrl;
import org.telegram.telegrambots.meta.api.objects.games.CallbackGame;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InlineKeyboardBuilderTest {

    @Test
    void testCreateInlineKeyboard() {
        List<List<InlineKeyboardButton>> keyboardList = Collections.singletonList(
                Collections.singletonList(
                        InlineKeyboardButton.builder().text("hello").callbackData("world").build())
        );
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(keyboardList);

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.inline()
                .button("hello", "world")
                .build();
        assertEquals(keyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardWithEmptyRow() {
        List<List<InlineKeyboardButton>> keyboardList = Collections.singletonList(
                Collections.singletonList(
                        InlineKeyboardButton.builder().text("hello").callbackData("world").build())
        );
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(keyboardList);

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.inline()
                .button("hello", "world")
                .row()
                .build();
        assertEquals(keyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardStaticDefault() {
        List<InlineKeyboardButton> row = new ArrayList<>(2);
        row.add(InlineKeyboardButton.builder().text("text1").callbackData("callback1").build());
        row.add(InlineKeyboardButton.builder().text("text2").callbackData("callback2").build());
        InlineKeyboardMarkup horizontalKeyboard = new InlineKeyboardMarkup(Collections.singletonList(row));

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.ofInline(
                KeyboardBuilder.button("text1", "callback1"),
                KeyboardBuilder.button("text2", "callback2")
        );

        assertEquals(horizontalKeyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardStatic() {
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>(3);
        keyboardList.add(Collections.singletonList(InlineKeyboardButton.builder().text("text1").callbackData("callback1").build()));
        keyboardList.add(Collections.singletonList(InlineKeyboardButton.builder().text("text2").callbackData("callback2").build()));
        keyboardList.add(Collections.singletonList(InlineKeyboardButton.builder().text("text3").callbackData("callback3").build()));
        InlineKeyboardMarkup verticalKeyboard = new InlineKeyboardMarkup(keyboardList);

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.ofInline(1,
                KeyboardBuilder.button("text1", "callback1"),
                KeyboardBuilder.button("text2", "callback2"),
                KeyboardBuilder.button("text3", "callback3")
        );

        assertEquals(verticalKeyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardVariety() {
        // Waiting for LoginUrl and CallbackGame to implement equals()
        List<InlineKeyboardButton> row = new ArrayList<>(6);
        row.add(InlineKeyboardButton.builder().text("pay").pay(true).build());
        row.add(InlineKeyboardButton.builder().text("loginUrl").loginUrl(new LoginUrl("https://example.com")).build());
        row.add(InlineKeyboardButton.builder().text("callbackGame").callbackGame(new CallbackGame()).build());
        row.add(InlineKeyboardButton.builder().text("login").loginUrl(new LoginUrl("https://example.com")).build());
        row.add(InlineKeyboardButton.builder().text("game").callbackGame(new CallbackGame()).build());
        row.add(InlineKeyboardButton.builder().text("url").url("https://example.com").build());
        row.add(InlineKeyboardButton.builder().text("switchInline").switchInlineQuery("switchInlineQuery").build());
        row.add(InlineKeyboardButton.builder().text("switchInlineCurrent").switchInlineQueryCurrentChat("switchInlineQueryCurrent").build());
        InlineKeyboardMarkup varietyKeyboard = new InlineKeyboardMarkup(Collections.singletonList(row));

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.inline()
                .button("pay", true)
                .button("loginUrl", new LoginUrl("https://example.com"))
                .button("callbackGame", new CallbackGame())
                .buttonLogin("login", "https://example.com")
                .buttonGame("game")
                .buttonUrl("url", "https://example.com")
                .buttonSwitch("switchInline", "switchInlineQuery")
                .buttonSwitchCurrent("switchInlineCurrent", "switchInlineQueryCurrent")
                .build();

        assertEquals(varietyKeyboard, keyboardMarkup);
    }

    @Test
    void inlineButton() {
        InlineKeyboardButton exampleButton = InlineKeyboardButton.builder().text("hello").callbackData("world").build();
        InlineKeyboardButton testButton = KeyboardBuilder.button("hello", "world");
        assertEquals(exampleButton, testButton);
    }
}