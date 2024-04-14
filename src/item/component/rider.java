package item.component;

import Interface.activeAble;
import Interface.taskAble;
import item.GroupObjectActivable;

public class rider extends GroupObjectActivable implements taskAble, activeAble {
    public rider() {
        super("Component/Rider/foodcat.png");
    }

    @Override
    public void Active() {

    }

    @Override
    public void taskAlert() {

    }
}
