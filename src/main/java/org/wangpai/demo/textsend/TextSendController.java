package org.wangpai.demo.textsend;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TextSendController {
    @FXML
    private TextArea textArea;

    @FXML
    public void onKeyPressedTextArea(KeyEvent keyEvent) {
        // 如果按下了回车键
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // 获得此时的光标位置。此位置为刚刚输入的换行符之后
            var caretPosition = this.textArea.getCaretPosition();

            // 如果已经按下的按键中包含 Control 键
            if (!keyEvent.isControlDown()) { // 如果输入的不是组合键 `Ctrl+Enter`，去掉换行符，然后将文本发送
                // 获得输入文本，此文本包含刚刚输入的换行符
                var text = this.textArea.getText();
                // 获得换行符两边的文本
                var front = text.substring(0, caretPosition - 1);
                var end = text.substring(caretPosition);
                this.textArea.setText(front + end);
                this.onActionButton(null); // 模拟发送

                /*----- 如果希望发送后保留输入框文本，需要只使用下面这行代码，然后去掉清除文本框的代码 -------*/
                // this.textArea.positionCaret(caretPosition - 1);
            } else {
                // 获得输入文本，此文本不包含刚刚输入的换行符
                var text = this.textArea.getText();
                // 获得光标两边的文本
                var front = text.substring(0, caretPosition);
                var end = text.substring(caretPosition);
                // 在光标处插入换行符
                this.textArea.setText(front + System.lineSeparator() + end);
                // 将光标移至换行符
                this.textArea.positionCaret(caretPosition + 1);
            }
        }
    }

    /**
     * 模拟的发送方法
     */
    @FXML
    public void onActionButton(ActionEvent event) {
        System.out.println("正在发送信息...");
        System.out.println(this.textArea.getText());

        this.textArea.requestFocus();
        /*----- 如果希望发送后清除输入框文本，使用下面这行代码 -------*/
        this.textArea.clear();
    }
}