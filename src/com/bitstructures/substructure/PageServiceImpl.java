package com.bitstructures.substructure;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PageServiceImpl implements PageService {
	
	private String pageSourceDir;

	@Inject
	public PageServiceImpl(@Named("PageSourceDir") String pageSourceDir) {
		super();
		this.pageSourceDir = pageSourceDir;
	}

	@Override
	public Page getPage() {
		System.out.println("getPage() called with pageSourceDir=" + pageSourceDir);
		return null;
	}

}
