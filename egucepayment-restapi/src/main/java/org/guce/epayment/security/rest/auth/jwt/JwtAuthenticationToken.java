package org.guce.epayment.security.rest.auth.jwt;

import java.util.Collection;
import java.util.Map;
import org.guce.epayment.security.rest.auth.models.RawAccessJwtToken;
import org.guce.epayment.security.rest.auth.models.UserContext;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author tadzotsa
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 4101666125695936636L;

    private RawAccessJwtToken rawAccessToken;
    private UserContext userContext;
    private final Map<String, Object> infos;

    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken, Map<String, Object> infos) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.infos = infos;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
        this.infos = null;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessToken = null;
    }

    @Override
    public Object getDetails() {
        return infos;
    }

}
