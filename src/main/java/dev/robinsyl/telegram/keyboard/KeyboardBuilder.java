package dev.robinsyl.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.LoginUrl;
import org.telegram.telegrambots.meta.api.objects.games.CallbackGame;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public final class KeyboardBuilder {

    private KeyboardBuilder() {
    }

    /**
     * Creates a builder for {@link InlineKeyboardMarkup}
     *
     * @return An instance of {@link InlineKeyboardBuilder}
     */
    public static InlineKeyboardBuilder inline() {
        return new InlineKeyboardBuilder();
    }

    /**
     * Creates a builder for {@link ReplyKeyboardMarkup}
     *
     * @return An instance of {@link ReplyKeyboardBuilder}
     */
    public static ReplyKeyboardBuilder reply() {
        return new ReplyKeyboardBuilder();
    }

    /**
     * A method to quickly create an {@link InlineKeyboardButton}.
     * Can be statically imported and used in conjunction with {@link #ofInline}.
     *
     * @param text         Display text of the button
     * @param callbackData Callback data of the button
     * @return An {@link InlineKeyboardButton} containing text and callback data
     */
    public static InlineKeyboardButton button(String text, String callbackData) {
        return InlineKeyboardButton.builder().text(text).callbackData(callbackData).build();
    }

    /**
     * A method to build an inline keyboard.
     * Designed to be statically imported and to be used in conjunction with {@link #button}.
     * This method works the same as {@link #ofInline(int, InlineKeyboardButton...)}, but defaults to 2 buttons per row.
     *
     * @param buttons Any number of {@link InlineKeyboardButton} to include in the keyboard
     * @return An instance of {@link InlineKeyboardMarkup} with the buttons
     * @see #ofInline(int, InlineKeyboardButton...)
     */
    public static InlineKeyboardMarkup ofInline(InlineKeyboardButton... buttons) {
        return ofInline(2, buttons);
    }

    /**
     * A method to build an inline keyboard.
     * Designed to be statically imported and to be used in conjunction with {@link #button}.
     *
     * @param rowWidth The number of buttons per row
     * @param buttons  Any number of {@link InlineKeyboardButton} to include in the keyboard
     * @return An instance of {@link InlineKeyboardMarkup} with the buttons
     * @see #ofInline(int, InlineKeyboardButton...)
     */
    public static InlineKeyboardMarkup ofInline(int rowWidth, InlineKeyboardButton... buttons) {
        InlineKeyboardBuilder builder = inline();
        for (int i = 0; i < buttons.length; i++) {
            if (i % rowWidth == 0) {
                builder.row();
            }
            builder.button(buttons[i]);
        }
        return builder.build();
    }

    /**
     * A method to build a reply keyboard. Designed to be statically imported.
     * This method works the same as {@link #ofReply(int, String...)}, but defaults to 2 buttons per row.
     *
     * @param buttons The text of the buttons, with each string being a button.
     * @return An instance of {@link ReplyKeyboardMarkup} with the buttons
     * @see #ofReply(int, String...)
     */
    public static ReplyKeyboardMarkup ofReply(String... buttons) {
        return ofReply(2, buttons);
    }

    /**
     * A method to build a reply keyboard. Designed to be statically imported.
     *
     * @param rowWidth The number of buttons per row
     * @param buttons  The text of the buttons, with each string being a button.
     * @return An instance of {@link ReplyKeyboardMarkup} with the buttons
     * @see #ofReply(String...)
     */
    public static ReplyKeyboardMarkup ofReply(int rowWidth, String... buttons) {
        ReplyKeyboardBuilder builder = reply();
        for (int i = 0; i < buttons.length; i++) {
            if (i % rowWidth == 0) {
                builder.row();
            }
            builder.button(buttons[i]);
        }
        return builder.build();
    }

    public static final class InlineKeyboardBuilder {

        private List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
        private List<InlineKeyboardButton> row = new LinkedList<>();

        private InlineKeyboardBuilder() {
        }

        public InlineKeyboardBuilder button(InlineKeyboardButton button) {
            row.add(button);
            return this;
        }

        public InlineKeyboardBuilder button(String text, String callbackData) {
            row.add(InlineKeyboardButton.builder().text(text).callbackData(callbackData).build());
            return this;
        }

        public InlineKeyboardBuilder buttonLogin(String text, String loginUrl) {
            row.add(InlineKeyboardButton.builder().text(text).loginUrl(new LoginUrl(loginUrl)).build());
            return this;
        }

        public InlineKeyboardBuilder buttonGame(String text) {
            row.add(InlineKeyboardButton.builder().text(text).callbackGame(new CallbackGame()).build());
            return this;
        }

        public InlineKeyboardBuilder buttonUrl(String text, String url) {
            row.add(InlineKeyboardButton.builder().text(text).url(url).build());
            return this;
        }

        public InlineKeyboardBuilder buttonSwitch(String text, String switchInlineQuery) {
            row.add(InlineKeyboardButton.builder().text(text).switchInlineQuery(switchInlineQuery).build());
            return this;
        }

        public InlineKeyboardBuilder buttonSwitchCurrent(String text, String switchInlineQueryCurrentChat) {
            row.add(InlineKeyboardButton.builder().text(text).switchInlineQueryCurrentChat(switchInlineQueryCurrentChat).build());
            return this;
        }

        public InlineKeyboardBuilder button(String text, CallbackGame callbackGame) {
            row.add(InlineKeyboardButton.builder().text(text).callbackGame(callbackGame).build());
            return this;
        }

        public InlineKeyboardBuilder button(String text, LoginUrl loginUrl) {
            row.add(InlineKeyboardButton.builder().text(text).loginUrl(loginUrl).build());
            return this;
        }

        public InlineKeyboardBuilder button(String text, boolean pay) {
            row.add(InlineKeyboardButton.builder().text(text).pay(pay).build());
            return this;
        }

        public InlineKeyboardBuilder row() {
            if (!row.isEmpty()) {
                keyboard.add(row);
                row = new LinkedList<>();
            }
            return this;
        }

        public InlineKeyboardMarkup build() {
            row();
            return new InlineKeyboardMarkup(keyboard);
        }
    }

    public static final class ReplyKeyboardBuilder {

        private List<KeyboardRow> keyboard = new LinkedList<>();
        private KeyboardRow row = new KeyboardRow();

        private ReplyKeyboardBuilder() {
        }

        public ReplyKeyboardBuilder button(String text) {
            row.add(text);
            return this;
        }

        public ReplyKeyboardBuilder row() {
            if (!row.isEmpty()) {
                keyboard.add(row);
                row = new KeyboardRow();
            }
            return this;
        }

        public ReplyKeyboardMarkup build() {
            row();
            return new ReplyKeyboardMarkup(keyboard);
        }
    }
}
