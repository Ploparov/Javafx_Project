package item.component;

import Interface.activeAble;
import Interface.taskAble;
import item.GroupObjectActivable;

public class GasStove extends GroupObjectActivable implements taskAble, activeAble {
    public GasStove() {
        super("Component/GasStove/GasStove1.png");
    }

    @Override
    public void Active() {

    }

    @Override
    public void taskAlert() {

    }
}
