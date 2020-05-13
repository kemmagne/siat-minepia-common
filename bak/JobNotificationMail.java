package org.guce.siat.common.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * The Class JobNotificationMail.
 */
public class JobNotificationMail extends QuartzJobBean
{

	/** The task notification mail. */
	private TaskNotificationMail taskNotificationMail;

	/**
	 * Sets the my task.
	 *
	 * @param taskNotificationMail
	 *           the new my task
	 */
	public void setMyTask(final TaskNotificationMail taskNotificationMail)
	{
		this.taskNotificationMail = taskNotificationMail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException
	{
		taskNotificationMail.sendMailNotification();
	}

}
