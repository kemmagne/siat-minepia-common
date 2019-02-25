package org.guce.siat.common.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * The Class JobResendMessage.
 */
public class JobResendMail extends QuartzJobBean {

    /**
     * The task message resend.
     */
    private TaskResendMail taskResendMail;

    /**
     * Sets the my task.
     *
     * @param taskResendMail
     */
    public void setMyTask(final TaskResendMail taskResendMail) {
        this.taskResendMail = taskResendMail;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException {
        taskResendMail.resendMail();
    }

}

