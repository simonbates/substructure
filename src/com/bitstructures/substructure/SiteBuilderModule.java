package com.bitstructures.substructure;

import com.google.inject.AbstractModule;

public class SiteBuilderModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PageService.class).to(PageServiceImpl.class);
		bind(PageRenderingService.class).to(PageRenderingServiceImpl.class);
		bind(OutputService.class).to(OutputServiceImpl.class);
	}

}
