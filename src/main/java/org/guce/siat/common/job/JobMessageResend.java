package org.guce.siat.common.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * The Class JobMessageResend.
 */
public class JobMessageResend extends QuartzJobBean {

    /**
     * The task message resend.
     */
    private TaskMessageResend taskMessageResend;

    /**
     * Sets the my task.
     *
     * @param taskMessageResend the new my task
     */
    public void setMyTask(final TaskMessageResend taskMessageResend) {
        this.taskMessageResend = taskMessageResend;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException {
        taskMessageResend.resendMessage();
    }

}

