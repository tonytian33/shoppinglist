package com.wicket_projects;

import junit.framework.TestCase;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}
	
	@Test
	public void testRenderMyPage()
	{
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");
	}
}
