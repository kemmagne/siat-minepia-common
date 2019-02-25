package org.guce.siat.common.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * The Class JobResendMessage.
 */
public class JobResendMessage extends QuartzJobBean {

    /**
     * The task message resend.
     */
    private TaskResendMessage taskResendMessage;

    /**
     * Sets the my task.
     *
     * @param taskResendMessage the new my task
     */
    public void setMyTask(final TaskResendMessage taskResendMessage) {
        this.taskResendMessage = taskResendMessage;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException {
        taskResendMessage.resendMessage();
    }

}

