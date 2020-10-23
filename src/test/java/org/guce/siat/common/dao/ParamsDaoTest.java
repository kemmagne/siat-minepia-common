package org.guce.siat.common.dao;

import java.util.List;
import junit.framework.TestCase;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.utils.enums.ParamsCategory;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class ParamsDaoTest extends TestCase {

    @Autowired
    private ParamsDao paramsDao;

    @Transactional
    @Test
    public void test_findParamsByName_notNull() {
        String paramsName = "params.name";
        String paramsValue = "params.value";
        Params params = new Params();
        params.setName(paramsName);
        params.setValue(paramsValue);
        paramsDao.save(params);
        Params params1 = paramsDao.findParamsByName(paramsName);
        Assert.assertNotNull(params1);
        Assert.assertEquals(paramsName, params1.getName());
        Assert.assertEquals(paramsValue, params1.getValue());
    }

    @Transactional(readOnly = true)
    @Test
    public void test_findParamsByName_Null() {
        String paramsName = "params.name2";
        Params params = paramsDao.findParamsByName(paramsName);
        Assert.assertNull(params);
    }

    @Transactional
    @Test
    public void test_findParamsByCategory() {
        String paramsName = "params.name";
        String paramsValue = "params.value";
        ParamsCategory paramsCategory = ParamsCategory.GR;
        Params params = new Params();
        params.setName(paramsName);
        params.setValue(paramsValue);
        params.setParamsCategory(paramsCategory);
        paramsDao.save(params);
        List<Params> paramses = paramsDao.findParamsByCategory(paramsCategory);
        Assert.assertFalse(paramses.isEmpty());
        Params params1 = paramsDao.findParamsByName(paramsName);
        Assert.assertEquals(paramsCategory, params1.getParamsCategory());
        Assert.assertTrue(paramsDao.findParamsByCategory(null).isEmpty());
    }

}
