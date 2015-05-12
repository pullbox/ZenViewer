package net.bechtelus.standard;

import java.io.Serializable;
import java.util.Properties;
import net.bechtelus.common.ZendeskConfig;
import org.zendesk.client.v2.Zendesk;

public class APIAccessObject implements Serializable {

	private static final long serialVersionUID = 7778841766245989494L;

	private static Zendesk zd;
	private static Properties config;

	private APIAccessObject() {

	}

	public static Zendesk getAPIAccessObject() {

		// zd is null or closed
		if (zd == null || zd.isClosed()) {
			config = ZendeskConfig.load();

			Zendesk.Builder builder = new Zendesk.Builder(
					config.getProperty("url")).setUsername(config
					.getProperty("username"));
			if (config.getProperty("token") != null) {
				builder.setToken(config.getProperty("token"));
			} else if (config.getProperty("password") != null) {
				builder.setPassword(config.getProperty("password"));
			}
			zd = builder.build();
		}
		return zd;
	}

}
