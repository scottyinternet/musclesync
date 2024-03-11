/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.scottgriffin.musclesync;

import com.scottgriffin.musclesync.dynamics.ContextData;
import com.scottgriffin.musclesync.model.Assessment;
import com.scottgriffin.musclesync.model.enums.PregnancyStatus;
import com.scottgriffin.musclesync.utility.DemoDataProvider;
import com.scottgriffin.musclesync.utility.Input;


public class App {

    public static void main(String[] args) {
        DemoDataProvider demoDataProvider = new DemoDataProvider();
        Assessment assessment = demoDataProvider.getAssessment();
        ContextData contextData = new ContextData();
        contextData.setPregnancyStatus(PregnancyStatus.POSTPARTUM);
        assessment.setContextData(contextData);
        assessment.runCLI();
        Input.closeScanner();
    }
}
