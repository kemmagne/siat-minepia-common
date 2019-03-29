package org.guce.siat.common.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.AdministrationService;
import org.guce.siat.common.service.FileItemService;
import org.guce.siat.common.service.FileService;
import org.guce.siat.common.service.FileTypeService;
import org.guce.siat.common.service.ItemFlowService;
import org.guce.siat.common.service.MailService;
import org.guce.siat.common.service.StepService;
import org.guce.siat.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class TaskNotificationMail.
 */
public class TaskNotificationMail {

    /**
     * The item flow service.
     */
    @Autowired
    private ItemFlowService itemFlowService;

    /**
     * The file item service.
     */
    @Autowired
    private FileItemService fileItemService;

    /**
     * The step service.
     */
    @Autowired
    private StepService stepService;

    /**
     * The file type service.
     */
    @Autowired
    private FileTypeService fileTypeService;

    /**
     * The file service.
     */
    @Autowired
    private FileService fileService;

    /**
     * The administration service.
     */
    @Autowired
    private AdministrationService administrationService;

    /**
     * The user service.
     */
    @Autowired
    private UserService userService;

    /**
     * The mail service.
     */
    @Autowired
    private MailService mailService;

    /**
     * The Constant EMAIL_BODY_LATE_NOTIFICATION_FR.
     */
    private static final String EMAIL_BODY_LATE_NOTIFICATION_FR = "emailBodyLateNotification_fr.vm";

    /**
     * The Constant EMAIL_BODY_LATE_NOTIFICATION_EN.
     */
    private static final String EMAIL_BODY_LATE_NOTIFICATION_EN = "emailBodyLateNotification_en.vm";

    /**
     * Send mail notification.
     */
    @Deprecated
    public void sendMailNotification1() {

        final List<File> files = fileService.findAll();

        for (final File file : files) {

            final List<FileItem> fileItemList = fileItemService.findFileItemsByFile(file);
            final FileItem fileItem = fileItemList.get(0);
            final ItemFlow itemFlow = itemFlowService.findLastItemFlowByFileItem(fileItem);

            final Flow flow = itemFlow.getFlow();

            final Step step = stepService.find(flow.getToStep().getId());

            if (step.getIsFinal() && differenceBetweenTwoDates(new Date(), itemFlow.getCreated()) > flow.getDuration()) {

                final List<User> users = userService.findSuperUserByFileType(file.getFileType().getCode(), file.getBureau().getId());

                final Map<String, String> map = new HashMap<>();
                String object;

                for (final User usr : users) {

                    if ("FR".equals(usr.getPreferedLanguage())) {
//							object = ResourceBundle.getBundle(ControllerConstants.Bundle.LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
//									ControllerConstants.Bundle.Messages.OBJECT_MAIL_NOTIFICATION_RECEPT_FOLDER);
                        object = "Notification de retard de traitement d'un dossier";
                        map.put("subject", object);
                        map.put("vmf", EMAIL_BODY_LATE_NOTIFICATION_FR);
                    } else if ("EN".equals(usr.getPreferedLanguage())) {
//							object = ResourceBundle.getBundle(ControllerConstants.Bundle.LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
//									ControllerConstants.Bundle.Messages.OBJECT_MAIL_NOTIFICATION_RECEPT_FOLDER);
                        object = "Processing delay notification of a folder";
                        map.put("subject", object);
                        map.put("vmf", EMAIL_BODY_LATE_NOTIFICATION_EN);
                    }

                    map.put("firstName", usr.getFirstName());
                    map.put("from", mailService.getFromValue());
                    map.put("email", usr.getEmail());
                    map.put("referenceSiat", file.getReferenceSiat());

                    mailService.sendMail(map);

                }
            }

        }

    }

    public void sendMailNotification() {
    }

    /**
     * Difference entre deux dates.
     *
     * @param date1 the date1
     * @param date2 the date2
     * @return the long
     */
    private long differenceBetweenTwoDates(final Date date1, final Date date2) {

        final long diff = date1.getTime() - date2.getTime();

        return (diff / (1000 * 60 * 60));
    }

}

