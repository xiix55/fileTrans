package com.grean.fileTrans.view;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;

import javax.annotation.PostConstruct;

@FXMLView(value = "/view/MainStage.fxml")
public class MainStageView extends AbstractFxmlView {
    @PostConstruct
    protected void initUI() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //monitorService.run();
            }
        }).start();
    }
}
