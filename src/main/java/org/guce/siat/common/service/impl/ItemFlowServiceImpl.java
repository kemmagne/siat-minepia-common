package org.guce.siat.common.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AppointmentDao;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.FlowDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.dao.ItemFlowDataDao;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.StepDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.ItemFlowService;
import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.enums.AperakType;
import org.guce.siat.common.utils.enums.FlowCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ItemFlowServiceImpl.
 */
@Service("itemFlowService")
@Transactional(readOnly = true)
public class ItemFlowServiceImpl extends AbstractServiceImpl<ItemFlow> implements ItemFlowService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The item flow dao.
     */
    @Autowired
    private ItemFlowDao itemFlowDao;

    /**
     * The item flow data dao.
     */
    @Autowired
    private ItemFlowDataDao itemFlowDataDao;

    /**
     * The file item dao.
     */
    @Autowired
    private FileItemDao fileItemDao;

    /**
     * The file dao.
     */
    @Autowired
    private FileDao fileDao;

    /**
     * The appointment dao.
     */
    @Autowired
    private AppointmentDao appointmentDao;

    /**
     * The flow dao.
     */
    @Autowired
    private FlowDao flowDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * The step dao.
     */
    @Autowired
    private StepDao stepDao;

    /**
     * the params dao
     */
    @Autowired
    private ParamsDao paramsDao;

    /**
     * Instantiates a new item flow service impl.
     */
    public ItemFlowServiceImpl() {
        super();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<ItemFlow> getJpaDao() {
        return itemFlowDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<ItemFlow> jpaDao) {
        this.itemFlowDao = (ItemFlowDao) jpaDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#findLastItemFlowByFileItem(java.lang.String)
     */
    @Override
    public ItemFlow findLastItemFlowByFileItem(final FileItem fileItem) {
        return itemFlowDao.findLastItemFlowByFileItem(fileItem);
    }
    
    @Override
    public ItemFlow findLastItemFlowByFileItemAndFlow(FileItem fileItem, FlowCode flowCode) {
        return itemFlowDao.findLastItemFlowByFileItemAndFlow(fileItem, flowCode);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#findLastItemFlowsByFileItemList(java.util.List)
     */
    @Override
    public List<ItemFlow> findLastItemFlowsByFileItemList(final List<FileItem> fileItemList) {
        final List<ItemFlow> returnedItemFlowList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(fileItemList)) {
            for (final FileItem fileItem : fileItemList) {
                final ItemFlow itemFlow = itemFlowDao.findLastItemFlowByFileItem(fileItem);
                if (itemFlow != null) {
                    returnedItemFlowList.add(itemFlow);
                }
            }
        }

        return returnedItemFlowList;
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#findLastSentItemFlowByFileItem(java.lang.Long)
     */
    @Override
    public ItemFlow findLastSentItemFlowByFileItem(final FileItem fileItem) {
        return itemFlowDao.findLastSentItemFlowByFileItem(fileItem);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#takeDecision(java.util.List, java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public List<ItemFlow> takeDecision(final List<ItemFlow> itemFlowList, final List<ItemFlowData> flowDatas) {

        List<ItemFlow> toReturn = new ArrayList<>();

        final List<FileItem> fileItemList = new ArrayList<>();

        for (final ItemFlow itemFlow : itemFlowList) {
            final ItemFlow item = itemFlowDao.save(itemFlow);
            toReturn.add(item);
            if (CollectionUtils.isNotEmpty(flowDatas)) {
                final List<ItemFlowData> itemFlowDatas = new ArrayList<>();
                for (final ItemFlowData flowData : flowDatas) {
                    final ItemFlowData itemFlowData = new ItemFlowData();
                    itemFlowData.setDataType(flowData.getDataType());
                    itemFlowData.setValue(flowData.getValue());
                    itemFlowData.setItemFlow(item);
                    itemFlowDatas.add(itemFlowData);
                }
                itemFlowDataDao.saveOrUpdateList(itemFlowDatas);
            }

            // Set draft = true to be updated
            itemFlow.getFileItem().setDraft(Boolean.TRUE);
            fileItemList.add(itemFlow.getFileItem());
        }

        // Update fileItems : Set draft = true
        fileItemDao.saveOrUpdateList(fileItemList);

        return toReturn;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#rollBackDecisionForDispatchFile(java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public void rollBackDecisionForDispatchFile(final List<Long> fileItems) {
        final List<ItemFlow> itemFlows = itemFlowDao.findItemFlowsByFileItemList(fileItems);

        itemFlowDataDao.deleteList(itemFlowDataDao.findByItemFlows(itemFlows));

        itemFlowDao.deleteList(itemFlows);

        final List<FileItem> fileItemList = new ArrayList<>();

        for (final ItemFlow itemFlow : itemFlows) {
            // Set draft = false to be updated
            itemFlow.getFileItem().setDraft(Boolean.FALSE);
            fileItemList.add(itemFlow.getFileItem());
        }
        // Update fileItems : Set draft = false
        fileItemDao.saveOrUpdateList(fileItemList);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#sendDecisions(org.guce.siat.core.ct.model.File, java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public Map<FileItem, Flow> sendDecisions(final File file, final List<FileItem> fileItems) {
        final Map<FileItem, Flow> returnedMap = new HashMap<>();
        final List<FileItem> items = new ArrayList<>();
        final List<ItemFlow> draftItemFlows = new ArrayList<>();
        Step step = null;
        for (final FileItem fileItem : fileItems) {
            if (fileItem.getDraft()) {
                final ItemFlow draftItemFlow = itemFlowDao.findDraftByFileItem(fileItem);
                final Flow decision = draftItemFlow.getFlow();
                final FileItem fItem = fileItemDao.find(fileItem.getId());

                if (decision != null && decision.getToStep() != null) {
                    step = decision.getToStep();
                } else {
                    // If the executed flow is "Rejet Annulation" (FL_CT_63) --> his toStep is null so we must return the fileItem step to the last step before "Etude demande d'annulation"
                    step = findLastStepBeforeCancellingRequest(fItem);
                }
                fItem.setStep(step);

                fItem.setDraft(Boolean.FALSE);
                items.add(fItem);

                if (decision != null && (decision.getOutgoing() == 1 || (decision.getOutgoing() == 0 && CollectionUtils.isNotEmpty(decision.getCopyRecipientsList())))) {
                    returnedMap.put(fItem, decision);
                }
                draftItemFlow.setSent(Boolean.TRUE);
                draftItemFlow.setReceived(AperakType.APERAK_D.getCharCode());
                draftItemFlows.add(draftItemFlow);
            }

        }
        fileItemDao.saveOrUpdateList(items);
        itemFlowDao.saveOrUpdateList(draftItemFlows);

        Map<FileItem, Flow> map;
        if (step != null && (map = checkIfSystemDecisionMustBeTakenAndTakeIt(file, fileItems, step)) != null) {
            return map;
        }

        return returnedMap;
    }

    /**
     * Find last step before cancelling request.
     *
     * @param fileItem the file item
     * @return the step
     */
    private Step findLastStepBeforeCancellingRequest(final FileItem fileItem) {
        final List<ItemFlow> itemFlowList = itemFlowDao.findItemFlowByFileItem(fileItem);

        Integer lastCancellingRequestIndex = null;

        for (int i = 0; i < itemFlowList.size(); i++) {
            // Le flux "Demande d'annulation" : CCT (FL_CT_61), AP (FL_AP_147), CO (FL_CO_147), AM (FL_AM_147) CC (FL_CC_147) et SF (FL_SF_147)
            if ((FlowCode.FL_CT_61.toString().equals(itemFlowList.get(i).getFlow().getCode())
                    || FlowCode.FL_AP_147.toString().equals(itemFlowList.get(i).getFlow().getCode())
                    || FlowCode.FL_CO_147.toString().equals(itemFlowList.get(i).getFlow().getCode())
                    || FlowCode.FL_AM_147.toString().equals(itemFlowList.get(i).getFlow().getCode())
                    || FlowCode.FL_SF_147.toString().equals(itemFlowList.get(i).getFlow().getCode())
                    || FlowCode.FL_CC_147.toString().equals(itemFlowList.get(i).getFlow().getCode()))
                    && fileItem.getId().equals(itemFlowList.get(i).getFileItem().getId())) {
                lastCancellingRequestIndex = i;
            }
        }

        if (!Objects.equals(lastCancellingRequestIndex, null)) {
            for (int i = lastCancellingRequestIndex; i > 0; i--) {
                if (!FlowCode.FL_AP_147.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AP_151.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AP_152.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AP_153.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CO_147.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CO_151.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CO_152.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CO_153.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AM_147.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AM_151.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AM_152.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_AM_153.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_SF_147.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_SF_151.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_SF_152.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_SF_153.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CC_147.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CC_151.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CC_152.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CC_153.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CT_61.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CT_62.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CT_63.name().equals(itemFlowList.get(i - 1).getFlow().getCode())
                        && !FlowCode.FL_CT_87.name().equals(itemFlowList.get(i - 1).getFlow().getCode())) {
                    return itemFlowList.get(i - 1).getFlow().getToStep();
                }
            }
        }

        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#sendDecisionsToDispatchFile(org.guce.siat.core.ct.model.File)
     */
    @Transactional(readOnly = false)
    @Override
    public void sendDecisionsToDispatchFile(final File file) {
        final List<ItemFlow> draftItemFlows = new ArrayList<>();
        final List<FileItem> fileItems = new ArrayList<>();
        for (final FileItem fileItem : file.getFileItemsList()) {
            if (fileItem.getDraft()) {
                final ItemFlow draftItemFlow = itemFlowDao.findDraftByFileItem(fileItem);
                fileItem.setStep(draftItemFlow.getFlow().getToStep());
                fileItem.setDraft(Boolean.FALSE);
                fileItems.add(fileItem);
                draftItemFlow.setSent(Boolean.TRUE);
                draftItemFlows.add(draftItemFlow);
            }
        }
        fileItemDao.saveOrUpdateList(fileItems);
        itemFlowDao.saveOrUpdateList(draftItemFlows);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemFlowService#sendDecisionsToDispatchCctFile(org.guce.siat.common.model.File,
	 * java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public void sendDecisionsToDispatchCctFile(final File file, final List<FileItem> fileItems) {
        final List<ItemFlow> draftItemFlows = new ArrayList<>();
        final List<FileItem> items = new ArrayList<>();
        for (final FileItem fileItem : fileItems) {
            if (fileItem.getDraft()) {
                final ItemFlow draftItemFlow = itemFlowDao.findDraftByFileItem(fileItem);
                fileItem.setStep(draftItemFlow.getFlow().getToStep());
                fileItem.setDraft(Boolean.FALSE);
                items.add(fileItem);
                draftItemFlow.setSent(Boolean.TRUE);
                draftItemFlows.add(draftItemFlow);
            }
        }
        fileItemDao.saveOrUpdateList(items);
        itemFlowDao.saveOrUpdateList(draftItemFlows);
    }

    /**
     * Gets the item flow data dao.
     *
     * @return the item flow data dao
     */
    public ItemFlowDataDao getItemFlowDataDao() {
        return itemFlowDataDao;
    }

    /**
     * Sets the item flow data dao.
     *
     * @param itemFlowDataDao the new item flow data dao
     */
    public void setItemFlowDataDao(final ItemFlowDataDao itemFlowDataDao) {
        this.itemFlowDataDao = itemFlowDataDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ItemFlowService#findItemFlowByFileItem(org.guce.siat.core.ct.model.FileItem)
     */
    @Override
    public List<ItemFlow> findItemFlowByFileItem(final FileItem fileItem) {
        return itemFlowDao.findItemFlowByFileItem(fileItem);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.ItemFlowService#findItemFlowByFileItemAndFlow(org.guce.siat.common.model.FileItem,
	 * org.guce.siat.common.utils.enums.FlowCode)
     */
    @Override
    public ItemFlow findItemFlowByFileItemAndFlow(final FileItem fileItem, final FlowCode flowCode) {
        return itemFlowDao.findItemFlowByFileItemAndFlow(fileItem, flowCode);
    }

    @Override
    public ItemFlow findItemFlowByFileItemAndFlow2(final FileItem fileItem, final FlowCode flowCode) {
        return itemFlowDao.findItemFlowByFileItemAndFlow2(fileItem, flowCode);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemFlowService#findLastOutgoingItemFlowByFileItem(org.guce.siat.common.model.
	 * FileItem )
     */
    @Override
    public ItemFlow findLastOutgoingItemFlowByFileItem(final FileItem fileItem) {
        return itemFlowDao.findLastOutgoingItemFlowByFileItem(fileItem);
    }

    /**
     * Gets the item flow dao.
     *
     * @return the itemFlowDao
     */
    public ItemFlowDao getItemFlowDao() {
        return itemFlowDao;
    }

    /**
     * Sets the item flow dao.
     *
     * @param itemFlowDao the itemFlowDao to set
     */
    public void setItemFlowDao(final ItemFlowDao itemFlowDao) {
        this.itemFlowDao = itemFlowDao;
    }

    /**
     * Gets the file item dao.
     *
     * @return the fileItemDao
     */
    public FileItemDao getFileItemDao() {
        return fileItemDao;
    }

    /**
     * Sets the file item dao.
     *
     * @param fileItemDao the fileItemDao to set
     */
    public void setFileItemDao(final FileItemDao fileItemDao) {
        this.fileItemDao = fileItemDao;
    }

    /**
     * Gets the file dao.
     *
     * @return the fileDao
     */
    public FileDao getFileDao() {
        return fileDao;
    }

    /**
     * Sets the file dao.
     *
     * @param fileDao the fileDao to set
     */
    public void setFileDao(final FileDao fileDao) {
        this.fileDao = fileDao;
    }

    /**
     * Gets the appointment dao.
     *
     * @return the appointmentDao
     */
    public AppointmentDao getAppointmentDao() {
        return appointmentDao;
    }

    /**
     * Sets the appointment dao.
     *
     * @param appointmentDao the appointmentDao to set
     */
    public void setAppointmentDao(final AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    /**
     * Gets the flow dao.
     *
     * @return the flowDao
     */
    public FlowDao getFlowDao() {
        return flowDao;
    }

    /**
     * Sets the flow dao.
     *
     * @param flowDao the flowDao to set
     */
    public void setFlowDao(final FlowDao flowDao) {
        this.flowDao = flowDao;
    }

    /**
     * Gets the user dao.
     *
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Sets the user dao.
     *
     * @param userDao the userDao to set
     */
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Gets the step dao.
     *
     * @return the stepDao
     */
    public StepDao getStepDao() {
        return stepDao;
    }

    /**
     * Sets the step dao.
     *
     * @param stepDao the stepDao to set
     */
    public void setStepDao(final StepDao stepDao) {
        this.stepDao = stepDao;
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemFlowService#findNbrDecisionByFileItemHistory(java.util.List,
	 * org.guce.siat.common.model.FileItem)
     */
    @Override
    public Long findNbrDecisionByFileItemHistory(final List<String> flowCodes, final FileItem fileItem) {
        return itemFlowDao.findNbrDecisionByFileItemHistory(flowCodes, fileItem);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemFlowService#findFileItemsHistoryByNegativeDecisionsAndCompany(org.guce.siat.
	 * common .model.FileItem, java.util.List)
     */
    @Override
    public List<FileItem> findFileItemsHistoryByNegativeDecisionsAndCompany(final FileItem fileItem, final List<StepCode> stepCodes) {
        return itemFlowDao.findFileItemsHistoryByNegativeDecisionsAndCompany(fileItem, stepCodes);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemFlowService#findFileItemsHistoryByNegativeDecisionsAndProduct(org.guce.siat.
	 * common .model.FileItem, java.util.List)
     */
    @Override
    public List<FileItem> findFileItemsHistoryByNegativeDecisionsAndProduct(final FileItem fileItem, final List<StepCode> stepCodes) {
        return itemFlowDao.findFileItemsHistoryByNegativeDecisionsAndProduct(fileItem, stepCodes);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemFlowService#findDraftByFileItem(org.guce.siat.common.model.FileItem)
     */
    @Override
    public ItemFlow findDraftByFileItem(final FileItem fileItem) {
        return itemFlowDao.findDraftByFileItem(fileItem);
    }

    @Override
    public ItemFlow findPreviousItemFlow(ItemFlow itemFlow) {
        return itemFlowDao.findPreviousItemFlow(itemFlow);
    }

    @Override
    public ItemFlow findNextItemFlow(ItemFlow itemFlow) {
        return itemFlowDao.findNextItemFlow(itemFlow);
    }

    private Map<FileItem, Flow> checkIfSystemDecisionMustBeTakenAndTakeIt(File currentFile, List<FileItem> fileItems, Step currentStep) {

        String paramsName = MessageFormat.format("system.automatic.decison.{0}.{1}.{2}", currentFile.getBureau().getCode(),
                currentFile.getFileType().getCode(), currentStep.getStepCode());
        Params params = paramsDao.findParamsByName(paramsName);
        if (params == null) {
            return null;
        }

        Flow flow;
        try {
            FlowCode flowCode = Enum.valueOf(FlowCode.class, params.getValue());
            flow = flowDao.findFlowByCode(flowCode.name());
        } catch (IllegalArgumentException | NullPointerException ex) {
            logger.error("The value {} at the params {} is not a valid flow", params.getValue(), params);
            return null;
        }

        return saveSystemDecisions(fileItems, flow);
    }

    private Map<FileItem, Flow> saveSystemDecisions(List<FileItem> fileItems, Flow flow) {

        Map<FileItem, Flow> returnedMap = new HashMap<>();
        User systemUser = userDao.getUserByLogin(Constants.SYSTEM_USER_LOGIN);
        List<ItemFlow> itemFlows = new ArrayList<>();
        for (FileItem fileItem : fileItems) {
            ItemFlow itemFlow = new ItemFlow();

            itemFlow.setFileItem(fileItem);
            itemFlow.setFlow(flow);
            itemFlow.setReceived(AperakType.APERAK_D.getCharCode());
            itemFlow.setSender(systemUser);
            itemFlow.setSent(Boolean.TRUE);
            itemFlow.setUnread(Boolean.TRUE);

            fileItem.setStep(flow.getToStep());
            fileItem.setDraft(Boolean.FALSE);

            if (CollectionUtils.isNotEmpty(flow.getCopyRecipientsList())) {
                returnedMap.put(fileItem, flow);
            }

            itemFlows.add(itemFlow);
        }

        itemFlowDao.saveList(itemFlows);
        fileItemDao.saveOrUpdateList(fileItems);

        return returnedMap;
    }

    @Override
    public List<ItemFlow> findLastItemFlowsByFileAndFlow(File file, FlowCode flowCode) {
        return itemFlowDao.findLastItemFlowsByFileAndFlow(file, flowCode);
    }

    @Override
    public List<ItemFlow> findItemFlowsByFileItemListAndFlow(List<Long> fileItems, FlowCode flowCode) {
        return itemFlowDao.findItemFlowsByFileItemListAndFlow(fileItems, flowCode);
    }

    @Override
    public List<ItemFlow> findLastItemFlowsByFileItemListAndFlow(List<FileItem> fileItems, FlowCode flowCode) {
        final List<ItemFlow> returnedItemFlowList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(fileItems)) {
            for (final FileItem fileItem : fileItems) {
                final ItemFlow itemFlow = itemFlowDao.findLastItemFlowByFileItemAndFlow(fileItem, flowCode);
                if (itemFlow != null) {
                    returnedItemFlowList.add(itemFlow);
                }
            }
        }

        return returnedItemFlowList;
    }
}
