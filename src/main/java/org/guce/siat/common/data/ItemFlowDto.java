package org.guce.siat.common.data;

import org.guce.siat.common.model.ItemFlow;

/**
 * The Class ItemFlowHistoryDto.
 */
public class ItemFlowDto {

    /**
     * The item flow.
     */
    private ItemFlow itemFlow;

    /**
     * The duration.
     */
    private String duration;

    /**
     * Gets the item flow.
     *
     * @return the item flow
     */
    public ItemFlow getItemFlow() {
        return itemFlow;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the new duration
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     * Sets the item flow.
     *
     * @param itemFlow the new item flow
     */
    public void setItemFlow(final ItemFlow itemFlow) {
        this.itemFlow = itemFlow;
    }
}
