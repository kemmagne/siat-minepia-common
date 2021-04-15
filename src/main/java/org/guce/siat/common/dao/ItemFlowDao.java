package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.FlowCode;
import org.guce.siat.common.utils.enums.StepCode;

/**
 * The Interface ItemFlowDao.
 */
public interface ItemFlowDao extends AbstractJpaDao<ItemFlow> {

    /**
     * Find last item flow by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findLastItemFlowByFileItem(FileItem fileItem);
    
    /**
     * Find last item flow by file item And Flow.
     *
     * @param fileItem the file item
     * @param flowCode
     * @return the item flow
     */
    ItemFlow findLastItemFlowByFileItemAndFlow(FileItem fileItem, FlowCode flowCode);

    /**
     * Find last o utgoing item flow by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findLastOutgoingItemFlowByFileItem(FileItem fileItem);

    /**
     * Find last sent item flow by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findLastSentItemFlowByFileItem(final FileItem fileItem);
    
    /**
     * Find item flows by file item list.
     *
     * @param fileItems the file items
     * @return the list
     */
    List<ItemFlow> findItemFlowsByFileItemList(List<Long> fileItems);
    
    /**
     * Find item flows by file item list and Flow.
     *
     * @param fileItems the file items
     * @param flowCode
     * @return the list
     */
    List<ItemFlow> findItemFlowsByFileItemListAndFlow(List<Long> fileItems, FlowCode flowCode);

    /**
     * Find item flow by file item.
     *
     * @param fileItem the file item
     * @return the list
     */
    List<ItemFlow> findItemFlowByFileItem(FileItem fileItem);

    /**
     * Find item flow by file item and flow.
     *
     * @param fileItem the file item
     * @param flowCode the flow code
     * @return the list
     */
    ItemFlow findItemFlowByFileItemAndFlow(final FileItem fileItem, final FlowCode flowCode);

    ItemFlow findItemFlowByFileItemAndFlow2(final FileItem fileItem, final FlowCode flowCode);

    /**
     * Find item flow by file item and flow.
     *
     * @param fileItem the file item
     * @param flowCodeList the flow code list
     * @return the item flow
     */
    ItemFlow findItemFlowByFileItemAndFlow(final FileItem fileItem, final List<String> flowCodeList);

    /**
     * Find nbr decision by file item history.
     *
     * @param flowCodes the flow codes
     * @param fileItem the file item
     * @return the long
     */
    Long findNbrDecisionByFileItemHistory(List<String> flowCodes, FileItem fileItem);

    /**
     * Find draft by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findDraftByFileItem(FileItem fileItem);

    /**
     * Find last sent item flow by file item.
     *
     * @param fileItemId the file item id
     * @return the item flow
     */
    ItemFlow findLastSentItemFlowByFileItem(Long fileItemId);

    /**
     * Find file items history by negative decisions and company.
     *
     * @param fileItem the file item
     * @param stepCodes the step codes
     * @return the list
     */
    List<FileItem> findFileItemsHistoryByNegativeDecisionsAndCompany(FileItem fileItem, List<StepCode> stepCodes);

    /**
     * Find file items history by negative decisions and product.
     *
     * @param fileItem the file item
     * @param stepCodes the step codes
     * @return the list
     */
    List<FileItem> findFileItemsHistoryByNegativeDecisionsAndProduct(FileItem fileItem, List<StepCode> stepCodes);

    /**
     * Retrive item flow data databy file type and value.
     *
     * @param fileTypeCode the file type code
     * @param value the value
     * @return the item flow data
     */
    ItemFlowData retriveItemFlowDataDatabyFileTypeAndValue(FileTypeCode fileTypeCode, String value);

    /**
     * Retrive item flow given message id
     *
     * @param messageId
     * @return
     */
    ItemFlow findByMessageId(String messageId);

    ItemFlow findPreviousItemFlow(ItemFlow itemFlow);

    ItemFlow findNextItemFlow(ItemFlow itemFlow);

    ItemFlow findLastDecisionByFile(File file);

    List<ItemFlow> findLastItemFlowsByFileAndFlow(final File file, final FlowCode flowCode);

}
