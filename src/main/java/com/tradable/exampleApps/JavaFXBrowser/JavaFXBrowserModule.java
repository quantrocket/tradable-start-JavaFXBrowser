package com.tradable.exampleApps.JavaFXBrowser;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.tradable.api.component.WorkspaceModule;
import com.tradable.api.component.WorkspaceModuleProperties;
import com.tradable.api.component.state.PersistedStateHolder;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class JavaFXBrowserModule extends JPanel implements WorkspaceModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //change this number to something more sensible
	
	public JavaFXBrowserModule() {
		setLayout(null);
		setSize(500, 300);
		
		setModuleTitle("JavaFX Browser");
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	initAndShowGUI();
     
            }
		});
	}

	private void initAndShowGUI() {
        // This method is invoked on the EDT thread
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
        final JFXPanel fxPanel = new JFXPanel();
        fxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fxPanel.setPreferredSize(new Dimension(4096, 4096));
        add(fxPanel);
        fxPanel.setLocation(0, 0);
        setMaximumSize(new Dimension(4096, 4096));
        setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	initFX(fxPanel);
            }
       });
    }

    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
    
    private Scene createScene() {
        GridPane layout = new GridPane();
        layout.setVgap(1);
        layout.setHgap(0);
        Scene  scene  =  new  Scene(layout, Color.BLACK);

        final WebView browser = new WebView();
        
        final TextField url = new TextField();
        url.setPrefWidth(4096);
        url.setMinHeight(20);
        layout.addRow(0, url);
        url.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	browser.getEngine().load(url.getText());
            }     
        });
        
        browser.setPrefSize(4096, 4096);
        WebEngine engine = browser.getEngine();
        engine.locationProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                url.setText(newValue);
            }
        });
        engine.load("http://tradable.com");
        layout.addRow(1, browser);
        
        return (scene);
    }

	
	@Override
	public JComponent getVisualComponent() {
		return this;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersistedStateHolder getPersistedState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadPersistedState(PersistedStateHolder state) {
		// TODO Auto-generated method stub
		
	}
	

    protected void setModuleTitle(String title) {
        putClientProperty(WorkspaceModuleProperties.COMPONENT_TITLE, title);
    }

}
