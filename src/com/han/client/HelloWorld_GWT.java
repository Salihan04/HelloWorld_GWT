package com.han.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloWorld_GWT implements EntryPoint {
	
	private TabPanel tabPanel;
	
	//Create Root Logger
	private static Logger rootLogger = Logger.getLogger("");
	private VerticalPanel customLogArea;
	
	private void selectTab(String historyToken) {
		//Parse the history token
		try {
			if(historyToken.substring(0, 9).equals("pageIndex")) {
				String tabIndexToken = historyToken.substring(9, 10);
				int tabIndex = Integer.parseInt(tabIndexToken);
				
				//Select the selected tab panel
				tabPanel.selectTab(tabIndex);
			}
			else {
				tabPanel.selectTab(0);
			}
		}
		catch(IndexOutOfBoundsException e) {
			tabPanel.selectTab(0);
		}
	}

	public void onModuleLoad() {
		//Create a tab panel to carry multiple pages
		tabPanel = new TabPanel();
		
		//Create pages
		HTML firstPage = new HTML("<h1>We are on the first page.</h1>");
		HTML secondPage = new HTML("<h1>We are on the second page.</h1>");
		HTML thirdPage = new HTML("<h1>We are on the third page.</h1>");
		
		String firstPageTitle = "First Page";
		String secondPageTitle = "Second Page";
		String thirdPageTitle = "Third Page";
		
		Hyperlink firstPageLink = new Hyperlink("1", "pageIndex0");
		Hyperlink secondPageLink = new Hyperlink("2", "pageIndex1");
		Hyperlink thirdPageLink = new Hyperlink("3", "pageIndex2");
		
		HorizontalPanel linksHPanel = new HorizontalPanel();
		linksHPanel.setSpacing(10);
		linksHPanel.add(firstPageLink);
		linksHPanel.add(secondPageLink);
		linksHPanel.add(thirdPageLink);
		
		//If application starts with no history token, redirect to a pageIndex0
		String initToken = History.getToken();
		if(initToken.length() == 0) {
			History.newItem("pageIndex0");
			initToken = "pageIndex0";
		}
		
		tabPanel.setWidth("400");
		//Add pages to tabPanel
		tabPanel.add(firstPage, firstPageTitle);
		tabPanel.add(secondPage, secondPageTitle);
		tabPanel.add(thirdPage, thirdPageTitle);
		
		/*Add value change handler to History
		 * this method will be called when browser's
		 * Back button or Forward button are clicked 
		 * and URL of application changes
		 */
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				selectTab(event.getValue());
				rootLogger.log(Level.SEVERE, "pageIndex selected: " + event.getValue());
			}
		});
		
		selectTab(initToken);
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(10);
		vPanel.add(tabPanel);
		vPanel.add(linksHPanel);
		
		customLogArea = new VerticalPanel();
		vPanel.add(customLogArea);
		
		//An example of using own custom logging area
		rootLogger.addHandler(new HasWidgetsLogHandler(customLogArea));
		
		//Add widgets to root panel
		RootPanel.get("gwtContainer").add(vPanel);
	}
}
