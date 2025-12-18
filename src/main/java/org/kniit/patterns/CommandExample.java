package org.kniit.patterns;

public class CommandExample {
    public static void main(String[] args) {
        Document doc = new Document();

        Button openButton  = new Button("Открыть");
        Button saveButton  = new Button("Сохранить");
        Button closeButton = new Button("Закрыть");

        openButton.setCommand(doc::open);
        saveButton.setCommand(doc::save);
        closeButton.setCommand(doc::close);

        openButton.click();
        saveButton.click();
        closeButton.click();
    }
}

class Button {
    private final String title;

    private Runnable command;

    public Button(String title) {
        this.title = title;
    }

    public void setCommand(Runnable command) {
        this.command = command;
    }
    public void click() {
        System.out.println("Нажата кнопка: " + title);
        if (command != null) {
            command.run();
        }
    }

}
class Document {
    public void open()  { System.out.println("Документ открыт"); }
    public void save()  { System.out.println("Документ сохранён"); }
    public void close() { System.out.println("Документ закрыт"); }

}
