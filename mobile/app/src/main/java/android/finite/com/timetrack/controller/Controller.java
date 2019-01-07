package android.finite.com.timetrack.controller;

import android.finite.com.timetrack.view.wrapper.ViewWrapper;

public interface Controller<ModelType> {

    void registerToAllModels(ViewWrapper viewWrapper);

    ModelType getModel();

}
