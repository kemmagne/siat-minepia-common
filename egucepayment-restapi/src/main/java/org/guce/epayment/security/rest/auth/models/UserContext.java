package org.guce.epayment.security.rest.auth.models;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author tadzotsa
 */
@Data
@Setter(AccessLevel.NONE)
public class UserContext {

    private final String login;
    private final List<GrantedAuthority> authorities;
    private final Map<String, Object> infos;

    private UserContext(final String login, final List<GrantedAuthority> authorities, final Map<String, Object> infos) {
        this.login = login;
        this.authorities = authorities;
        this.infos = infos;
    }

    public static UserContext create(final String login, final List<GrantedAuthority> authorities, Map<String, Object> infos) {

        if (StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("Username is blank: " + login);
        }

        return new UserContext(login, authorities, infos);
    }

}
