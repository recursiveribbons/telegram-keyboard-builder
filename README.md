# Telegram Keyboard Builder
This is a collection of builders that make creating inline and reply keyboards in Telegram Bots much easier.

## Dependencies
This library requires [rubenlagus/TelegramBots](https://github.com/rubenlagus/TelegramBots) on GitHub.

## Usage
First, it is recommended that you static import these static functions, to reduce boilerplate code.

### Builders

#### Inline Keyboard
Start with `KeyboardBuilder.inline()` to create a builder.
The builder has multiple methods for buttons, such as `buttonUrl` for a link button.
Each call to a button method creates a new button of that type in the row.
The `row` method starts a new row.
Remember to finish with `build`.

**Example**
```
KeyboardBuilder
    .inline()
    .button("hello", "world")
    .row()
    .buttonUrl("Visit", "example.com")
    .build();
```

#### Reply Keyboard
Start with `KeyboardBuilder.reply()` to create a builder.
Each call to the `button` method creates a new button in the row.
The `row` method starts a new row.
Remember to finish with `build`.

**Example**
```
KeyboardBuilder
    .reply()
    .button("yes")
    .row()
    .button("no")
    .build();
```

### Functions
For simple keyboards, you can also use the of-methods to quickly create keyboards without use of a builder.

#### Inline Keyboard
Start by statically importing the `button` method.
This method creates an inline button with a callback query.
The `ofInline` method receives a varargs list of buttons for the keyboard, optionally with the number of buttons per row.
The default is 2.

**Example**
```
KeyboardBuilder.ofInline(2,
    button("hello", "world"),
    button("foo", "bar")
);
```

#### Reply Keyboard
The `ofInline` method receives a varargs list of strings, each of which creates a button for the keyboard, optionally with the number of buttons per row.
The default is 2.

**Example**
```
KeyboardBuilder.ofReply(2, "yes", "no");
```
