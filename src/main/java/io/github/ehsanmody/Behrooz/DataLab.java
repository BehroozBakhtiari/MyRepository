package io.github.ehsanmody.maktab4ver2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataLab {
    private static DataLab dataLab = new DataLab();

    private List<Data> dataList;

    private DataLab() {
        dataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Data data = new Data();
            data.setName("Data " + i);
            data.setText("This is Data " + i + " Text");

            dataList.add(data);
        }
    }

    public static DataLab getInstance() {
        return dataLab;
    }

    public List<Data> getAllData() {
        return dataList;
    }

    public Data getDataById(UUID dataId) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getId().equals(dataId)) return dataList.get(i);
        }
        return null;
    }

    public void deleteDataById(UUID id) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getId().equals(id))
                dataList.remove(i);
        }
    }
}
