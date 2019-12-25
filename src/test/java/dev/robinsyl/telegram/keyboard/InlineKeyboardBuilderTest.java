package dev.robinsyl.telegram.keyboard;

import org.junit.jupiter.api.Test;
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
                        new InlineKeyboardButton("hello").setCallbackData("world"))
        );
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup().setKeyboard(keyboardList);

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.inline()
                .button("hello", "world")
                .build();
        assertEquals(keyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardWithEmptyRow() {
        List<List<InlineKeyboardButton>> keyboardList = Collections.singletonList(
                Collections.singletonList(
                        new InlineKeyboardButton("hello").setCallbackData("world"))
        );
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup().setKeyboard(keyboardList);

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.inline()
                .button("hello", "world")
                .row()
                .build();
        assertEquals(keyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardStaticDefault() {
        List<InlineKeyboardButton> row = new ArrayList<>(2);
        row.add(new InlineKeyboardButton("text1").setCallbackData("callback1"));
        row.add(new InlineKeyboardButton("text2").setCallbackData("callback2"));
        InlineKeyboardMarkup horizontalKeyboard = new InlineKeyboardMarkup().setKeyboard(Collections.singletonList(row));

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.ofInline(
                KeyboardBuilder.button("text1", "callback1"),
                KeyboardBuilder.button("text2", "callback2")
        );

        assertEquals(horizontalKeyboard, keyboardMarkup);
    }

    @Test
    void testCreateInlineKeyboardStatic() {
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>(3);
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("text1").setCallbackData("callback1")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("text2").setCallbackData("callback2")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("text3").setCallbackData("callback3")));
        InlineKeyboardMarkup verticalKeyboard = new InlineKeyboardMarkup().setKeyboard(keyboardList);

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
        row.add(new InlineKeyboardButton("pay").setPay(true));
        //row.add(new InlineKeyboardButton("loginUrl").setLoginUrl(new LoginUrl("https://example.com")));
        //row.add(new InlineKeyboardButton("callbackGame").setCallbackGame(new CallbackGame()));
        //row.add(new InlineKeyboardButton("login").setLoginUrl(new LoginUrl("https://example.com")));
        //row.add(new InlineKeyboardButton("game").setCallbackGame(new CallbackGame()));
        row.add(new InlineKeyboardButton("url").setUrl("https://example.com"));
        row.add(new InlineKeyboardButton("switchInline").setSwitchInlineQuery("switchInlineQuery"));
        row.add(new InlineKeyboardButton("switchInlineCurrent").setSwitchInlineQueryCurrentChat("switchInlineQueryCurrent"));
        InlineKeyboardMarkup varietyKeyboard = new InlineKeyboardMarkup().setKeyboard(Collections.singletonList(row));

        InlineKeyboardMarkup keyboardMarkup = KeyboardBuilder.inline()
                .button("pay", true)
                //.button("loginUrl", new LoginUrl("https://example.com"))
                //.button("callbackGame", new CallbackGame())
                //.buttonLogin("login", "https://example.com")
                //.buttonGame("game")
                .buttonUrl("url", "https://example.com")
                .buttonSwitch("switchInline", "switchInlineQuery")
                .buttonSwitchCurrent("switchInlineCurrent", "switchInlineQueryCurrent")
                .build();

        assertEquals(varietyKeyboard, keyboardMarkup);
    }

    @Test
    void inlineButton() {
        InlineKeyboardButton exampleButton = new InlineKeyboardButton("hello").setCallbackData("world");
        InlineKeyboardButton testButton = KeyboardBuilder.button("hello", "world");
        assertEquals(exampleButton, testButton);
    }
}