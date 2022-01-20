package com.grean.fileTrans.controller;

import de.felixroske.jfxsupport.FXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.cell.ColorGridCell;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Random;


/**
 * Created by Administrator on 2020-04-09.
 */
@Component
@FXMLView(value = "/view/BaseDialog.fxml")
public class BaseDialogController {

    private Object controllerObject;

    @FXML
    private void initialize() {

    }

    protected void createQueryHistoryActionTab(TabPane tabPane, MenuItem menuItem, String tabResource){
        System.out.println(menuItem.getText() + "tab create");
        URL path = getClass().getResource(tabResource);
        FXMLLoader fxmlLoader = getFXMLLoader(path);
        AnchorPane borderPane = null;
        try{
            fxmlLoader.setLocation(path);
            borderPane = (AnchorPane) fxmlLoader.load();
            fxmlLoader.getController();
        }catch (Exception e){
            System.out.println("Not found: " + path);
            e.printStackTrace();
        }
    }

    public void showGeneralDialog(Stage stage, String title, String tabResource, double width, double height) {

        try {
            FXMLLoader fxmlLoader = getFXMLLoader(getClass().getResource(tabResource));
            BorderPane mPane = fxmlLoader.load();
            Object controller = fxmlLoader.getController();

            Scene sceneCustom = new Scene(mPane, width, height, Color.web("lightgray"));
            Stage stageCustom = new Stage();
            stageCustom.initOwner(stage);
            stageCustom.setResizable(false);

            stageCustom.setTitle(title);
            stageCustom.initStyle(StageStyle.DECORATED);
            stageCustom.initModality(Modality.APPLICATION_MODAL);
            sceneCustom.setFill(Color.TRANSPARENT);
            stageCustom.setScene(sceneCustom);

            stageCustom.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setHeight(400);
            alert.setWidth(400);
            alert.setContentText("Couldn't load the dialog");
            alert.showAndWait();
            e.printStackTrace();
        }
    }


    private FXMLLoader getFXMLLoader(URL fxmlPath){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlPath);
        fxmlLoader.setControllerFactory(type ->{
            try {
                for (Constructor<?> c : type.getConstructors()){
                    if (c.getParameterCount() == 1) {
                        /**
                        if (c.getParameterTypes()[0]== ViewService.class) {
                            return c.newInstance(viewService);
                        } else if(c.getParameterTypes()[0]== RealUIService.class) {
                            return c.newInstance(realUIService);
                        }**/
                    }
                }
                return type.newInstance();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
        return  fxmlLoader;
    }

    public void notification(String title, String text, Node graphic, Pos pos) {
        String graphicMode = "Custom graphic";

        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(graphic)
                .hideAfter(Duration.seconds(20))
                .position(pos)
                .onAction(e -> System.out.println("Notification clicked on!"))
                .threshold(10,
                        Notifications.create().title("Threshold Notification"));

        /*notificationBuilder.owner(stage);
        notificationBuilder.hideCloseButton();
        notificationBuilder.darkStyle();*/

        switch (graphicMode) {
            case "Warning graphic":     notificationBuilder.showWarning(); break;
            case "Information graphic": notificationBuilder.showInformation(); break;
            case "Confirm graphic":     notificationBuilder.showConfirm(); break;
            case "Error graphic":       notificationBuilder.showError(); break;
            default: notificationBuilder.show();
        }
    }

    private Node buildTotalReplacementGraphic() {
        final ObservableList<Color> list = FXCollections.<Color>observableArrayList();

        GridView<Color> colorGrid = new GridView<>(list);
        colorGrid.setPrefSize(300, 300);
        colorGrid.setMaxSize(300, 300);

        colorGrid.setCellFactory(new Callback<GridView<Color>, GridCell<Color>>() {
            @Override public GridCell<Color> call(GridView<Color> arg0) {
                return new ColorGridCell();
            }
        });
        Random r = new Random(System.currentTimeMillis());
        for(int i = 0; i < 500; i++) {
            list.add(new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1.0));
        }
        return colorGrid;
    }

    public Object getControllerObject() {
        return this.controllerObject;
    }

    public void setControllerObject(Object controllerObject) {
        this.controllerObject = controllerObject;
    }
}
