package com.grean.fileTrans.controller;

import com.grean.fileTrans.Main;
import de.felixroske.jfxsupport.FXMLController;
import io.datafx.controller.ViewController;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.tools.Borders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;


//@ViewController(value = "/view/MainStage.fxml", title = "Material Design Example")
@FXMLController
public class MainStageController extends BaseDialogController implements Initializable {

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    BorderPane headPane;

    @FXML
    HiddenSidesPane centerPane;

    TabPane tabPane;
    Stage stageCustom;

    Timeline timeline;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        tabPane = createTabPane();
        VBox vBox = new VBox();
        Label label = new Label("", new ImageView("image/close.png"));
        label.setPadding(new Insets(0,3,0,3));
        Node wrapedLabel = Borders.wrap(label).lineBorder().color(Color.LIGHTGRAY).outerPadding(1,1,1,1).innerPadding(1,1,1,1).build().build();

        vBox.getChildren().addAll(tabPane, wrapedLabel);
        tabPane.prefHeightProperty().bind(vBox.heightProperty().multiply(0.98));
        centerPane.setContent(vBox);

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        //URL path = getClass().getResource("/view/SysInfo.fxml");
//        try{
//            fxmlLoader.setLocation(path);
//            BorderPane sysInfoPane = (BorderPane) fxmlLoader.load();
//            centerPane.setBottom(sysInfoPane);
//        }catch (Exception e){
//            System.out.println("Not found: " + path);
//            e.printStackTrace();
//        }

    }

    private void createQueryHistoryAction(MenuItem menuItem, String tabResource){
        menuItem.setOnAction(event -> {
            createQueryHistoryActionTab(tabPane, menuItem, tabResource);
        });
    }

    private TabPane createTabPane(){
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color:#fffff0");
        tabPane.setPrefWidth(300);
        tabPane.setPrefHeight(300);

        //tabPane.setRotateGraphic(false);//图片方向跟随
        //设置朝向(位置)
        //tabPane.setSide(Side.RIGHT);
        //tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);//都不允许关闭
        //监听tabpane组件的改变
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                System.out.println("select tab " + newValue.getText());
            }
        });

        return tabPane;
    }

    public void close() {

        System.exit(0);
        if (Main.primaryStage != null) {
            Main.primaryStage.close();//注意!相当于stage.hide(),舞台并没有销毁。
            System.exit(0);
        }
    }
}
