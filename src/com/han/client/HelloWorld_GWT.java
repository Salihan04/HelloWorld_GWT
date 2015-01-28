package com.han.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloWorld_GWT implements EntryPoint {
	
	public void onModuleLoad() {
		
		//Add button to change font to big when clicked
		Button btn1 = new Button("Big Text");
		btn1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("mytext").setStyleName("gwt-Big-Text");
			}
		});
		
		//Add button to change font to small when clicked
		Button btn2 = new Button("Small Text");
		btn2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("mytext").setStyleName("gwt-Small-Text");
			}
		});
		
		RootPanel.get("gwtGreenButton").add(btn1);
		RootPanel.get("gwtRedButton").add(btn2);
	}
}
