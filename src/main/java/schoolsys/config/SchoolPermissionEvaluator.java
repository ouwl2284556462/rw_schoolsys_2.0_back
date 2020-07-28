package schoolsys.config;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限判断 
 */
@Configuration
public class SchoolPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		String privilege = targetDomainObject + "-" + permission;
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (privilege.equals(authority.getAuthority())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

}
