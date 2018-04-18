package com.harri.harriinterviewtask.base;

public interface BaseView<T> {

    /**
     * Set the presenter for a view
     *
     * @param presenter the presenter to be set
     */
    void setPresenter(T presenter);
}
