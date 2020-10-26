package org.guce.siat.common.dao;

import java.util.List;
import junit.framework.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.Params;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class ParamsDaoTest extends TestCase {

    @Autowired
    private TestDataBuilder builder;

    @Test
    public void test_findParamsByName_notNull() {
        Params params = builder.getParams();
        Params params1 = builder.getParamsDao().findParamsByName(params.getName());
        Assert.assertNotNull(params1);
        Assert.assertEquals(params.getName(), params1.getName());
        Assert.assertEquals(params.getValue(), params1.getValue());
    }

    @Test
    public void test_findParamsByName_Null() {
        String paramsName = RandomStringUtils.randomAlphabetic(5);
        Params params = builder.getParamsDao().findParamsByName(paramsName);
        Assert.assertNull(params);
    }

    @Test
    public void test_findParamsByCategory() {
        Params params = builder.getParams();
        List<Params> paramses = builder.getParamsDao().findParamsByCategory(params.getParamsCategory());
        Assert.assertFalse(paramses.isEmpty());
        Params params1 = builder.getParamsDao().findParamsByName(params.getName());
        Assert.assertEquals(params.getParamsCategory(), params1.getParamsCategory());
        Assert.assertTrue(builder.getParamsDao().findParamsByCategory(null).isEmpty());
    }

}
