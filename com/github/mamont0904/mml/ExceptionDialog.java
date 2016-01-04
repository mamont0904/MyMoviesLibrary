package com.github.mamont0904.mml;


import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog {
    private Alert exceptionAlert;
    private TextArea stackTraceTextArea;

    public ExceptionDialog() {
        exceptionAlert = new Alert(Alert.AlertType.ERROR);
        exceptionAlert.setTitle("Ошибка");
        exceptionAlert.setHeaderText("Возникло исключение");

        Label label = new Label("Стектрейс:");

        stackTraceTextArea = new TextArea();
        stackTraceTextArea.setEditable(false);
        stackTraceTextArea.setWrapText(true);

        stackTraceTextArea.setMaxWidth(Double.MAX_VALUE);
        stackTraceTextArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(stackTraceTextArea, Priority.ALWAYS);
        GridPane.setHgrow(stackTraceTextArea, Priority.ALWAYS);

        GridPane сontent = new GridPane();
        сontent.setMaxWidth(Double.MAX_VALUE);
        сontent.add(label, 0, 0);
        сontent.add(stackTraceTextArea, 0, 1);

        exceptionAlert.getDialogPane().setContent(сontent);
    }

    void setException(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String exceptionText = stringWriter.toString();

        stackTraceTextArea.setText(exceptionText);
    }

    public void showAndWait() {
        exceptionAlert.showAndWait();
    }
}
