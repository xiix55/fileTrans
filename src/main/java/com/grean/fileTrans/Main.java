package com.grean.fileTrans;

import com.grean.fileTrans.controller.MainStageController;
import com.grean.fileTrans.view.MainStageView;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.controlsfx.dialog.Wizard;
import io.datafx.controller.flow.Flow;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {

    public static Stage primaryStage;
    public static void main(String[] args){
        launch(Main.class, MainStageView.class, args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("文件提取软件");
        primaryStage.setUserData(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.print("Exist");
                System.exit(0);
            }
        });

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        try {
            Flow flow = new Flow(MainStageController.class);
            primaryStage.setTitle("测量模块控制");
            //透明背景，没有操作系统平台装饰
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //窗口最大化
            primaryStage.setX(primaryScreenBounds.getWidth() / 3);
            primaryStage.setY(primaryScreenBounds.getHeight() / 5);
            //primaryStage.setWidth(primaryScreenBounds.getWidth() / 3);
            //primaryStage.setHeight(primaryScreenBounds.getHeight() / 3);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            //窗口始终显示在其他窗口之上
            primaryStage.setAlwaysOnTop(false);
            primaryStage.setResizable(true);
            primaryStage.setFullScreen(false);
            Scene mScene = new Scene(flow.start(), primaryScreenBounds.getMinX(), primaryScreenBounds.getMinY());
            //设置 舞台最大化 true
            primaryStage.setMaximized(false);
            mScene.getStylesheets().addAll(Main.class.getResource("/css/main-stage.css").toExternalForm());
            primaryStage.setScene(mScene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
