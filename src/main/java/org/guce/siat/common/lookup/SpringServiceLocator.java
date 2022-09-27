package org.guce.siat.common.lookup;

import javax.naming.NamingException;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author tadzotsa
 */
public class SpringServiceLocator extends AbstractServiceLocator {

    ApplicationContext ctx;

    public SpringServiceLocator() {
        this.setAppContext();
    }

    @Override
    public Object lookup(String value) throws NamingException {
        return ctx.getBean(value);
    }

    private void setAppContext() {
        this.ctx = ApplicationContextHolder.getApplicationContext();
    }

}
