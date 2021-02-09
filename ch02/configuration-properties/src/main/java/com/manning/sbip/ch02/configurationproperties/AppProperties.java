package com.manning.sbip.ch02.configurationproperties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConstructorBinding
@ConfigurationProperties("app.sbip.ct")
public class AppProperties {
	
	/**
	 * Application Name
	 */
	private final String name;
	
	/**
	 * Application IP
	 */
	private final String ip;

	/**
	 * Application IP
	 */
	private final int port;
	
	/**
	 * Application Security configuration
	 */
	private final Security security;

	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public Security getSecurity() {
		return security;
	}

	public AppProperties(String name, String ip, @DefaultValue("8080") int port, Security security) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.security = security;
	}

	@Override
	public String toString() {
		return "AppProperties{" +
				"name='" + name + '\'' +
				", ip='" + ip + '\'' +
				", port='" + port + '\'' +
				", security=" + security +
				'}';
	}

	public static class Security {
		
		/**
		 * Enable Security. Possible values true/false
		 */
		private boolean enabled;
		
		/**
		 * Token Value
		 */
		private final String token;
		
		/**
		 * Available roles
		 */
		private final List<String> roles;
		
		public Security(boolean enabled, String token, List<String> roles) {
			this.enabled = enabled;
			this.token = token;
			this.roles = roles;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public String getToken() {
			return token;
		}

		public List<String> getRoles() {
			return roles;
		}

		@Override
		public String toString() {
			return "Security{" +
					"enabled=" + enabled +
					", token='" + token + '\'' +
					", roles=" + roles +
					'}';
		}
	}

}
