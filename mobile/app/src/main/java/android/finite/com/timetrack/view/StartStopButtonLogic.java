package android.finite.com.timetrack.view;

import android.app.Activity;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.model.Model;
import android.finite.com.timetrack.view.wrapper.ViewWrapper;
import android.view.View;
import android.widget.Button;

public class StartStopButtonLogic extends ViewWrapper implements View.OnClickListener {
    private Activity parent;
    private TimeHandler timeHandler;
    private Button btn;

    public StartStopButtonLogic(Button start, TimeHandler handler, Activity parent) {
        this.btn = start;
        this.timeHandler = handler;
        this.parent = parent;

        this.btn.setOnClickListener(this);
        handler.registerToAllModels(this);
    }

    @Override
    public void notifyModelChanged(Model model) {

    }

    @Override
    public void onClick(View view) {
        if(!this.timeHandler.timeAquisitionOngoing()){
            this.btn.setText("Stop");
            this.timeHandler.startTracking(this.parent);
        } else {
            this.btn.setText("Start");
            this.timeHandler.stopTracking(this.parent);
        }
    }
}
