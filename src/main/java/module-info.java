module me.masterdash5.unoproject {

    requires javafx.controls;
    requires javafx.fxml;


    opens me.masterdash5.unoproject to javafx.fxml;

    exports me.masterdash5.unoproject;
    exports me.masterdash5.unoproject.controller;
    exports me.masterdash5.unoproject.view;
    opens me.masterdash5.unoproject.controller to javafx.fxml;



}