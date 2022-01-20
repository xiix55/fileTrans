package com.grean.fileTrans.controller;

import com.jfoenix.controls.JFXAlert;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2021-06-02.
 */
public class BaseDialog {

    private String title;
    private Pane pane;
    private Control control;
    private Object object;
    private JFXAlert dialog;
    private int width = 600;

    //private OnClickListener positiveBtnOnclickListener;

    public BaseDialog(String title, Control control, Object object, Pane pane) {
        this.title = title;
        this.pane = pane;
        this.control = control;
        this.object = object;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void show(){
        dialog = new JFXAlert((Stage)control.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setOverlayClose(false);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/BaseDialog.fxml"));
            BorderPane borderPane = (BorderPane) fxmlLoader.load();
            borderPane.setPrefWidth(width);
            Label titleLabel = (Label) borderPane.lookup("#titleLabel");
            titleLabel.setText(title);
            Button closeBtn = (Button) borderPane.lookup("#closeBtn");
            closeBtn.setOnAction(event -> {
                dialog.hideWithAnimation();
            });
            Button positiveBtn = (Button) borderPane.lookup("#positiveBtn");
            positiveBtn.setOnAction(event -> {
                /*JFXAlert alert = new JFXAlert((Stage) control.getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setOverlayClose(false);
                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setHeading(new Label("Modal Dialog using JFXAlert"));
                layout.setBody(new Label("This is a Modal Dialog using JFXAlert."));
                JFXButton closeButton = new JFXButton("ACCEPT");
                closeButton.getStyleClass().add("dialog-accept");
                closeButton.setOnAction(event1 -> {
                    alert.hideWithAnimation();
                    dialog.show();
                    dialog.close();
                });
                layout.setActions(closeButton);
                alert.setContent(layout);
                alert.show();*/
                dialog.hideWithAnimation();
            });
            Button negativeBtn = (Button) borderPane.lookup("#negativeBtn");
            negativeBtn.setOnAction(event -> {
                dialog.hideWithAnimation();
            });

            HBox contentBox = (HBox) borderPane.lookup("#contentBox");
            pane.prefWidthProperty().bind(contentBox.widthProperty());
            if(pane.getPrefHeight()<600) {
                contentBox.setPrefHeight(pane.getPrefHeight());
            }
            else{
                contentBox.setPrefHeight(600);
            }
            contentBox.getChildren().add(pane);

            dialog.setContent(borderPane);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
        //dialog.setContent(layout);

    }
}
