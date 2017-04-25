package com.liferay.support.portal.security.ldap;

import com.liferay.portal.security.ldap.authenticator.configuration.LDAPAuthConfiguration;
import com.liferay.portal.security.ldap.configuration.ConfigurationProvider;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tibor Lipusz
 */
@Component
public class LDAPAuthConfigurationCleaner {

	//TODO
	@Activate
	protected void activate(Map<String, Object> properties) {
		System.out.println("Actived");

		System.out.println(
			"_ldapAuthConfigurationProvider.getMetatypeId() = " +
				_ldapAuthConfigurationProvider.getMetatype());
	}

	@Reference(
		target = "(factoryPid=com.liferay.portal.security.ldap.authenticator.configuration.LDAPAuthConfiguration)",
		unbind = "-"
	)
	protected void setConfigurationProvider(
		ConfigurationProvider<LDAPAuthConfiguration>
			ldapAuthConfigurationProvider) {

		_ldapAuthConfigurationProvider = ldapAuthConfigurationProvider;
	}

	private ConfigurationProvider<LDAPAuthConfiguration>
		_ldapAuthConfigurationProvider;

}