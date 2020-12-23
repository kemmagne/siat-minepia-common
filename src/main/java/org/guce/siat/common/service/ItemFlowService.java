package org.guce.siat.common.service;

import java.util.List;
import java.util.Map;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;
import org.guce.siat.common.utils.enums.FlowCode;
import org.guce.siat.common.utils.enums.StepCode;

/**
 * The Interface ItemFlowService.
 */
public interface ItemFlowService extends AbstractService<ItemFlow> {

    /**
     * Find last item flow by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findLastItemFlowByFileItem(final FileItem fileItem);

    /**
     * Find last item flow by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findLastOutgoingItemFlowByFileItem(final FileItem fileItem);

    /**
     * Find last item flows by file item list.
     *
     * @param fileItemList the file item list
     * @return the list
     */
    List<ItemFlow> findLastItemFlowsByFileItemList(final List<FileItem> fileItemList);

    /**
     * Find last sent item flow by file item.
     *
     * @param fileItem the file item id
     * @return the item flow
     */
    ItemFlow findLastSentItemFlowByFileItem(final FileItem fileItem);

    /**
     * Take decision.
     *
     * @param itemFlowList the item flow list
     * @param flowDatas the flow datas
     * @return
     */
    List<ItemFlow> takeDecision(List<ItemFlow> itemFlowList, List<ItemFlowData> flowDatas);

    /**
     * Send decisions.
     *
     * @param file the file
     * @param fileItems the file items
     * @return the map
     */
    Map<FileItem, Flow> sendDecisions(File file, List<FileItem> fileItems);

    /**
     * Find item flow by file item.
     *
     * @param fileItem the file item
     * @return the list
     */
    List<ItemFlow> findItemFlowByFileItem(final FileItem fileItem);

    /**
     * Find item flow by file item and flow.
     *
     * @param fileItem the file item
     * @param flowCode the flow code
     * @return the list
     */
    ItemFlow findItemFlowByFileItemAndFlow(final FileItem fileItem, FlowCode flowCode);

    ItemFlow findItemFlowByFileItemAndFlow2(final FileItem fileItem, FlowCode flowCode);

    /**
     * Send decisions to dispatch file.
     *
     * @param file the file
     */
    void sendDecisionsToDispatchFile(File file);

    /**
     * Roll back decision for dispatch file.
     *
     * @param chckedProductInfoChecksList the chcked product info checks list
     */
    void rollBackDecisionForDispatchFile(List<Long> chckedProductInfoChecksList);

    /**
     * Send decisions to dispatch cct file.
     *
     * @param currentFile the current file
     * @param productInfoItems the product info items
     */
    void sendDecisionsToDispatchCctFile(File currentFile, List<FileItem> productInfoItems);

    /**
     * Find nbr decision by file item history.
     *
     * @param flowCodes the flow codes
     * @param fileItem the file item
     * @return the long
     */
    Long findNbrDecisionByFileItemHistory(final List<String> flowCodes, final FileItem fileItem);

    /**
     * Find file items history by negative decisions and company.
     *
     * @param fileItem the file item
     * @param stepCodes the step codes
     * @return the list
     */
    List<FileItem> findFileItemsHistoryByNegativeDecisionsAndCompany(final FileItem fileItem, final List<StepCode> stepCodes);

    /**
     * Find file items history by negative decisions and product.
     *
     * @param fileItem the file item
     * @param stepCodes the step codes
     * @return the list
     */
    List<FileItem> findFileItemsHistoryByNegativeDecisionsAndProduct(final FileItem fileItem, final List<StepCode> stepCodes);

    /**
     * Find draft by file item.
     *
     * @param fileItem the file item
     * @return the item flow
     */
    ItemFlow findDraftByFileItem(FileItem fileItem);

    ItemFlow findPreviousItemFlow(ItemFlow itemFlow);

    ItemFlow findNextItemFlow(ItemFlow itemFlow);
    
    List<ItemFlow> findLastItemFlowsByFileAndFlow(final File file, final FlowCode flowCode);

}
