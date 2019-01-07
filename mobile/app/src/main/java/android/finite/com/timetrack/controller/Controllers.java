package android.finite.com.timetrack.controller;

import java.util.HashMap;
import java.util.Map;

public class Controllers {
    private static Map<String, Controller> controllers = new HashMap<>();
    private static Controllers instance = null;

    private Controllers() {
        registerController(new TimeHandler());
    }

    public static Controllers get() {
        if(instance == null){
            instance = new Controllers();
        }
        return instance;
    }

    public static void registerController(Controller controller) {
        controllers.put(controller.getClass().getCanonicalName(), controller);
    }

    public <ControllerT extends Controller> ControllerT getController(Class<ControllerT> clazz) {
        return clazz.cast(controllers.get(clazz.getCanonicalName()));
    }
}
