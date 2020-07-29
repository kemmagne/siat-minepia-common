package org.guce.siat.common.dao;

import org.guce.siat.common.dao.config.DataSourceConfig;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.StepCode;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ht
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class})
public class FlowDaoTest {

    @Autowired
    private StepDao stepDao;
    @Autowired
    private FlowDao flowDao;

    /**
     * Test of findFlowsByFromStepAndFileType method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowsByFromStepAndFileType() {
    }

    /**
     * Test of findFlowsByFromStepAndFileType2 method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowsByFromStepAndFileType2() {
    }

    /**
     * Test of findFlowBySentFileItem method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowBySentFileItem() {
    }

    /**
     * Test of findByStepsIDs method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindByStepsIDs() {
    }

    /**
     * Test of findFlowByLabelSourceAndTarget method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowByLabelSourceAndTarget() {
    }

    /**
     * Test of findFlowByCurrentStep method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowByCurrentStep() {
    }

    /**
     * Test of findCotationFlowByCurrentStep method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindCotationFlowByCurrentStep() {
        System.out.println("org.guce.siat.common.dao.FlowDaoTest.testFindCotationFlowByCurrentStep()");
        Step step = stepDao.findByStepCode(StepCode.ST_CC_47);
        Assert.assertNotNull(step);
        Flow flow = flowDao.findCotationFlowByCurrentStep(step);
        Assert.assertNotNull(flow);
    }

    /**
     * Test of findFlowByCode method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowByCode() {
    }

    /**
     * Test of findFlowListByFlowCodeList method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowListByFlowCodeList() {
    }

    /**
     * Test of findCiResponseFlow method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindCiResponseFlow() {
    }

    /**
     * Test of findFinalDecisions method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFinalDecisions() {
    }

    /**
     * Test of findFlowByFileType method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowByFileType() {
    }

    /**
     * Test of findFlowByFileTypes method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindFlowByFileTypes() {
    }

    /**
     * Test of findByToStep method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindByToStep_Step() {
    }

    /**
     * Test of findByToStep method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindByToStep_Step_FileType() {
    }

    /**
     * Test of findBeforeCotationStepFlows method, of class FlowDao.
     */
    @Ignore
    @Test
    public void testFindBeforeCotationStepFlows() {
    }

}
