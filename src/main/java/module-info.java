module org.wangpai.demo.textsend {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.wangpai.demo.textsend to javafx.fxml;
    exports org.wangpai.demo.textsend;
}