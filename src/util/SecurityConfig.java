package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {

	// DEFINIZIONE RUOLI (E-COMMERCE MULTIROLES)
	public static final String ROLE_USER = "USER";
	public static final String ROLE_ADMIN = "ADMIN";
	private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

	static {
		System.out.println("Security Config Init");

		// URL USER
		List<String> userUrlPatterns = new LinkedList<String>();
		userUrlPatterns.add("/user");
		userUrlPatterns.add("/checkout");
		userUrlPatterns.add("/song");
		mapConfig.put(ROLE_USER, userUrlPatterns);

		// URL ADMIN
		List<String> adminUrlPatterns = new LinkedList<String>();
		adminUrlPatterns.add("/admin");
		mapConfig.put(ROLE_ADMIN, adminUrlPatterns);
	}

	public static Set<String> getAllAppRoles() {
		return mapConfig.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return mapConfig.get(role);
	}
}
