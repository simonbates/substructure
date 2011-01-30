package com.bitstructures.substructure;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class SiteBuilder {

	private PageService pageService;
	private PageRenderingService pageRenderingService;
	private OutputService outputService;
	
	@Inject
	public SiteBuilder(PageService pageService,
			PageRenderingService pageRenderingService,
			OutputService outputService) {
		super();
		this.pageService = pageService;
		this.pageRenderingService = pageRenderingService;
		this.outputService = outputService;
	}

	private void buildSite() {
		Page page = pageService.getPage();
		String html = pageRenderingService.renderPage(page);
		outputService.writeHtml(html);
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new SiteBuilderModule());
		SiteBuilder siteBuilder = injector.getInstance(SiteBuilder.class);
		siteBuilder.buildSite();
	}

}
