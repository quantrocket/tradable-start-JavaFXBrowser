package com.tradable.exampleApps.JavaFXBrowser;

import com.tradable.api.component.WorkspaceModule;
import com.tradable.api.component.WorkspaceModuleCategory;
import com.tradable.api.component.WorkspaceModuleFactory;

public class JavaFXBrowserFactory implements WorkspaceModuleFactory {

	@Override
	public WorkspaceModuleCategory getCategory() {
		return WorkspaceModuleCategory.MISCELLANEOUS;
	}

	@Override
	public String getDisplayName() {
		return "JavaFX Browser";
	}

	@Override
	public String getFactoryId() {
		return "com.tradable.exampleApps.JavaFXBrowser";
	}

	@Override
	public WorkspaceModule createModule() {
		return new JavaFXBrowserModule();
	}

}
