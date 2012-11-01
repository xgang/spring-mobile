package org.springframework.mobile.device.site;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mobile.device.DeviceType;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.StubDevice;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class StandardSitePreferenceHandlerTest {

	private StandardSitePreferenceHandler sitePreferenceHandler;

	private MockHttpServletRequest request = new MockHttpServletRequest();

	private MockHttpServletResponse response = new MockHttpServletResponse();

	private StubSitePreferenceRepository sitePreferenceRepository = new StubSitePreferenceRepository();

	@Before
	public void setup() throws Exception {
		sitePreferenceHandler = new StandardSitePreferenceHandler(sitePreferenceRepository);
	}

	@Test
	public void saveInvalidSitePreference() throws Exception {
		request.addParameter("site_preference", "invalid");
		assertNull(sitePreferenceHandler.handleSitePreference(request, response));
		assertNull(sitePreferenceRepository.getSitePreference());
		assertNull(SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void saveSitePreferenceNormal() throws Exception {
		request.addParameter("site_preference", "normal");
		assertEquals(SitePreference.NORMAL, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.NORMAL, sitePreferenceRepository.getSitePreference());
		assertEquals(SitePreference.NORMAL, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void saveSitePreferenceMobile() throws Exception {
		request.addParameter("site_preference", "mobile");
		assertEquals(SitePreference.MOBILE, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.MOBILE, sitePreferenceRepository.getSitePreference());
		assertEquals(SitePreference.MOBILE, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void saveSitePreferenceTablet() throws Exception {
		request.addParameter("site_preference", "tablet");
		assertEquals(SitePreference.TABLET, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.TABLET, sitePreferenceRepository.getSitePreference());
		assertEquals(SitePreference.TABLET, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void loadSitePreferenceNormal() throws Exception {
		sitePreferenceRepository.setSitePreference(SitePreference.NORMAL);
		assertEquals(SitePreference.NORMAL, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.NORMAL, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void loadSitePreferenceMobile() throws Exception {
		sitePreferenceRepository.setSitePreference(SitePreference.MOBILE);
		assertEquals(SitePreference.MOBILE, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.MOBILE, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void loadSitePreferenceTablet() throws Exception {
		sitePreferenceRepository.setSitePreference(SitePreference.TABLET);
		assertEquals(SitePreference.TABLET, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.TABLET, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void defaultSitePreference() throws Exception {
		assertNull(sitePreferenceHandler.handleSitePreference(request, response));
		assertNull(SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void defaultSitePreferenceNormalDevice() throws Exception {
		request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE, new StubDevice(DeviceType.NORMAL));
		assertEquals(SitePreference.NORMAL, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.NORMAL, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void defaultSitePreferenceMobileDevice() throws Exception {
		request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE, new StubDevice(DeviceType.MOBILE));
		assertEquals(SitePreference.MOBILE, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.MOBILE, SitePreferenceUtils.getCurrentSitePreference(request));
	}

	@Test
	public void defaultSitePreferenceTabletDevice() throws Exception {
		request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE, new StubDevice(DeviceType.TABLET));
		assertEquals(SitePreference.TABLET, sitePreferenceHandler.handleSitePreference(request, response));
		assertEquals(SitePreference.TABLET, SitePreferenceUtils.getCurrentSitePreference(request));
	}

}