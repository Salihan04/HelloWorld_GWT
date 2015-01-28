package com.han.client;

import org.apache.tools.ant.types.Assertions.EnabledAssertion;

import com.gargoylesoftware.htmlunit.javascript.host.Text;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloWorld_GWT implements EntryPoint {
	
	private static class OptionalTextBox extends Composite implements ClickHandler {
		
		private TextBox textbox = new TextBox();
		private CheckBox checkbox = new CheckBox();
		private boolean enabled = true;
		
		public boolean isEnabled() {
			return enabled;
		}
		
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		
		public OptionalTextBox(String caption) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(10);
			panel.add(checkbox);
			panel.add(textbox);
			
			initWidget(panel);
			
			setStyleName("optionalTextWidget");
			
			textbox.setStyleName("optionalTextBox");
			
			checkbox.setStyleName("optionalCheckBox");
			textbox.setWidth("200");
			
			checkbox.setText(caption);
			checkbox.setValue(enabled);
			checkbox.addClickHandler(this);
			enableTextBox(enabled, checkbox.getValue());
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource() == checkbox) {
				enableTextBox(enabled, checkbox.getValue());
			}
		}
		
		private void enableTextBox(boolean enable, boolean isChecked) {
			enable = (enabled && isChecked) || (!enable && !isChecked);
			textbox.setStyleDependentName("disabled", !enable);
			textbox.setEnabled(enable);
		}
	}
	
	public void onModuleLoad() {
		
		OptionalTextBox otb = new OptionalTextBox("Want to explain the solution?");
		otb.setEnabled(true);
		RootPanel.get("gwtContainer").add(otb);
	}
}
