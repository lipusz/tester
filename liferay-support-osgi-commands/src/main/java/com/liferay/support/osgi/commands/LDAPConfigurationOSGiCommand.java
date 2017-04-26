package com.liferay.support.osgi.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.ldap.authenticator.configuration.LDAPAuthConfiguration;
import com.liferay.portal.security.ldap.configuration.ConfigurationProvider;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tibor Lipusz
 *<br>
 * @see <a href="https://issues.liferay.com/browse/LPS-68834">
 * https://issues.liferay.com/browse/LPS-68834</a>
 */
@Component (
	immediate = true,
	property = {
		"osgi.command.function=deleteLDAPAuthConfigurationCompany",
		"osgi.command.function=listCompanyIds", "osgi.command.scope=support"
	},
	service = LDAPConfigurationOSGiCommand.class
)
public class LDAPConfigurationOSGiCommand {

	public void deleteLDAPAuthConfigurationCompany(long companyId) {
		Dictionary<String, Object> ldapAuthConfigurationProperties =
			_ldapAuthConfigurationProvider.getConfigurationProperties(
				companyId);

		if ((ldapAuthConfigurationProperties != null) &&
			!ldapAuthConfigurationProperties.isEmpty()) {

			_ldapAuthConfigurationProvider.delete(companyId);

			System.out.println(
				"Deleted " + LDAPAuthConfiguration.class.getName() +
					" instance for company " + companyId);
		}
		else {
			System.out.println(
				"No such " + LDAPAuthConfiguration.class.getName() +
					" exists for company " + companyId);
		}
	}

	public void listCompanyIds() {
		List<Company> companies = _companyLocalService.getCompanies();

		StringBundler sb = new StringBundler();

		companies.forEach(company -> sb.append(company.getCompanyId() + " "));

		System.out.println(sb.toString());
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		if (log.isInfoEnabled()) {
			log.info("Activated");
		}
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
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

	protected final Log log = LogFactoryUtil.getLog(getClass());

	private CompanyLocalService _companyLocalService;
	private ConfigurationProvider<LDAPAuthConfiguration>
		_ldapAuthConfigurationProvider;

}