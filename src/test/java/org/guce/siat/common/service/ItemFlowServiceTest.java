package org.guce.siat.common.service;

import java.util.Arrays;
import java.util.Map;
import junit.framework.TestCase;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author ht
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemFlowServiceTest extends TestCase {

    @Mock
    private ItemFlowService itemFlowService;

    @Mock
    private ParamsDao paramsDao;
    @Mock
    private UserDao userDao;
    @Mock
    private FileItemDao fileItemDao;
    @Mock
    private ItemFlowDao itemFlowDao;

    private Flow decision;
    private File file;
    private FileItem fileItem;
    private FileItem fileItemDb;
    private ItemFlow draftItemFlow;

    @Before
    @Override
    public void setUp() throws Exception {
        Assert.assertNotNull(paramsDao);
        Assert.assertNotNull(userDao);
        Assert.assertNotNull(fileItemDao);
        Assert.assertNotNull(itemFlowDao);

        decision = new Flow();
        decision.setToStep(new Step());

        file = new File();

        fileItem = new FileItem();
        fileItem.setFile(file);

        draftItemFlow = new ItemFlow();
        draftItemFlow.setFileItem(fileItem);

        fileItemDb = new FileItem();
        BeanUtils.copyProperties(fileItem, fileItemDb);
    }

    /**
     * Test of findLastItemFlowByFileItem method, of class ItemFlowService.
     */
    @Test
    public void testFindLastItemFlowByFileItem() {
    }

    /**
     * Test of findLastOutgoingItemFlowByFileItem method, of class
     * ItemFlowService.
     */
    @Test
    public void testFindLastOutgoingItemFlowByFileItem() {
    }

    /**
     * Test of findLastItemFlowsByFileItemList method, of class ItemFlowService.
     */
    @Test
    public void testFindLastItemFlowsByFileItemList() {
    }

    /**
     * Test of findLastSentItemFlowByFileItem method, of class ItemFlowService.
     */
    @Test
    public void testFindLastSentItemFlowByFileItem() {
    }

    /**
     * Test of takeDecision method, of class ItemFlowService.
     */
    @Test
    public void testTakeDecision() {
    }

    /**
     * Test of sendDecisions method, of class ItemFlowService.
     */
    @Test
    public void testSendDecisions() {
//        Mockito.when(itemFlowDao.findDraftByFileItem(fileItem)).thenReturn(draftItemFlow);
//        Mockito.when(fileItemDao.find(fileItem.getId())).thenReturn(fileItemDb);
        Map<FileItem, Flow> returnedMap = itemFlowService.sendDecisions(file, Arrays.asList(fileItem));
    }

    /**
     * Test of findItemFlowByFileItem method, of class ItemFlowService.
     */
    @Test
    public void testFindItemFlowByFileItem() {
    }

    /**
     * Test of findItemFlowByFileItemAndFlow method, of class ItemFlowService.
     */
    @Test
    public void testFindItemFlowByFileItemAndFlow() {
    }

    /**
     * Test of findItemFlowByFileItemAndFlow2 method, of class ItemFlowService.
     */
    @Test
    public void testFindItemFlowByFileItemAndFlow2() {
    }

    /**
     * Test of sendDecisionsToDispatchFile method, of class ItemFlowService.
     */
    @Test
    public void testSendDecisionsToDispatchFile() {
    }

    /**
     * Test of rollBackDecisionForDispatchFile method, of class ItemFlowService.
     */
    @Test
    public void testRollBackDecisionForDispatchFile() {
    }

    /**
     * Test of sendDecisionsToDispatchCctFile method, of class ItemFlowService.
     */
    @Test
    public void testSendDecisionsToDispatchCctFile() {
    }

    /**
     * Test of findNbrDecisionByFileItemHistory method, of class
     * ItemFlowService.
     */
    @Test
    public void testFindNbrDecisionByFileItemHistory() {
    }

    /**
     * Test of findFileItemsHistoryByNegativeDecisionsAndCompany method, of
     * class ItemFlowService.
     */
    @Test
    public void testFindFileItemsHistoryByNegativeDecisionsAndCompany() {
    }

    /**
     * Test of findFileItemsHistoryByNegativeDecisionsAndProduct method, of
     * class ItemFlowService.
     */
    @Test
    public void testFindFileItemsHistoryByNegativeDecisionsAndProduct() {
    }

    /**
     * Test of findDraftByFileItem method, of class ItemFlowService.
     */
    @Test
    public void testFindDraftByFileItem() {
    }

    /**
     * Test of findPreviousItemFlow method, of class ItemFlowService.
     */
    @Test
    public void testFindPreviousItemFlow() {
    }

    /**
     * Test of findNextItemFlow method, of class ItemFlowService.
     */
    @Test
    public void testFindNextItemFlow() {
    }

}
