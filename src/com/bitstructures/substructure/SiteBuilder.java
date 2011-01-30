package com.bitstructures.substructure;

public class SiteBuilder {

	private PageService pageService;
	private PageRenderingService pageRenderingService;
	private OutputService outputService;
	
	private void buildSite() {
		Page page = pageService.getPage();
		String html = pageRenderingService.renderPage(page);
		outputService.writeHtml(html);
	}
	
	public static void main(String[] args) {
		SiteBuilder siteBuilder = new SiteBuilder();
		siteBuilder.buildSite();
	}

}
