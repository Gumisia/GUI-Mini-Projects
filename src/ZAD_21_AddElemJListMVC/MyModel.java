package ZAD_21_AddElemJListMVC;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MyModel extends AbstractListModel<String> {


    List<String> stringList;

    MyModel(){
        stringList = new ArrayList<>();
    }

    public void addElementList(String text){
        stringList.add(text);
        fireContentsChanged(this, 0, stringList.size());
    }

    @Override
    public int getSize() {
        return stringList.size();
    }

    @Override
    public String getElementAt(int index) {
        return stringList.get(index);
    }
}
