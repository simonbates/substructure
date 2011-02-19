package com.bitstructures.substructure;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class SiteBuilderModule extends AbstractModule {
	
	private String pageSourceDir;

	public SiteBuilderModule(String pageSourceDir) {
		super();
		this.pageSourceDir = pageSourceDir;
	}

	@Override
	protected void configure() {
		bind(PageService.class).to(PageServiceImpl.class);
		bindConstant().annotatedWith(Names.named("PageSourceDir")).to(pageSourceDir);
		bind(PageRenderingService.class).to(PageRenderingServiceImpl.class);
		bind(OutputService.class).to(OutputServiceImpl.class);
	}

}
