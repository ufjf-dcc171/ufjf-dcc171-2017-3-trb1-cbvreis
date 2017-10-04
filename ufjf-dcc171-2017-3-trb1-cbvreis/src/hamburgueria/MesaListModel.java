package hamburgueria;


import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class MesaListModel implements ListModel<Mesa> {
    private final List<Mesa> mesas;
    private final List<ListDataListener> dataListeners;

    public MesaListModel(List<Mesa> mesas) {
        this.mesas = mesas;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return mesas.size();
    }

    @Override
    public Mesa getElementAt(int index) {
        return mesas.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListeners.remove(l);
    }
    
    
}






















