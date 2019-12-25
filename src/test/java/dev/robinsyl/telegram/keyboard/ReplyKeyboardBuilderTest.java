package dev.robinsyl.telegram.keyboard;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplyKeyboardBuilderTest {

    @Test
    void testCreateReplyKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add("hello");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup().setKeyboard(Collections.singletonList(row));

        ReplyKeyboardMarkup keyboardMarkup = KeyboardBuilder.reply()
                .button("hello")
                .build();
        assertEquals(keyboard, keyboardMarkup);
    }

    @Test
    void testCreateReplyKeyboardWithEmptyRow() {
        KeyboardRow row = new KeyboardRow();
        row.add("hello");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup().setKeyboard(Collections.singletonList(row));

        ReplyKeyboardMarkup keyboardMarkup = KeyboardBuilder.reply()
                .button("hello")
                .row()
                .build();
        assertEquals(keyboard, keyboardMarkup);
    }

    @Test
    void testCreateReplyKeyboardHorizontal() {
        KeyboardRow row = new KeyboardRow();
        row.add("text1");
        row.add("text2");
        ReplyKeyboardMarkup horizontalKeyboard = new ReplyKeyboardMarkup().setKeyboard(Collections.singletonList(row));

        ReplyKeyboardMarkup keyboardMarkup = KeyboardBuilder.ofReply("text1", "text2");

        assertEquals(horizontalKeyboard, keyboardMarkup);
    }

    @Test
    void testCreateReplyKeyboardVertical() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add("text1");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("text2");
        KeyboardRow row3 = new KeyboardRow();
        row3.add("text3");
        List<KeyboardRow> keyboardList = new LinkedList<>();
        keyboardList.add(row1);
        keyboardList.add(row2);
        keyboardList.add(row3);
        ReplyKeyboardMarkup verticalKeyboard = new ReplyKeyboardMarkup().setKeyboard(keyboardList);

        ReplyKeyboardMarkup keyboardMarkup = KeyboardBuilder.ofReply(1, "text1", "text2", "text3");

        assertEquals(verticalKeyboard, keyboardMarkup);
    }
}