package demo.gui.examples.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AirLineTableData extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final List<AirLineData> data;

    public AirLineTableData() {
        data = populateAirLineData();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String returnValue = null;
        switch (columnIndex) {
            case 0:
                returnValue = data.get(rowIndex).getFlightNumber();
                break;
            case 1:
                returnValue = data.get(rowIndex).getDepartCity();
                break;
            case 2:
                returnValue = data.get(rowIndex).getDestCity();
                break;
            case 3:
                returnValue = data.get(rowIndex).getTime();
                break;
            case 4:
                returnValue = Boolean.toString(data.get(rowIndex).isOnTime());
                break;
        }

        return returnValue;
    }

    @Override
    public String getColumnName(int column) {
        String returnValue = null;
        switch (column) {
            case 0:
                returnValue = "Flight";
                break;
            case 1:
                returnValue = "Depart";
                break;
            case 2:
                returnValue = "Dest";
                break;
            case 3:
                returnValue = "Depart Time";
                break;
            case 4:
                returnValue = "OnTime?";
                break;
        }
        return returnValue;
    }

    private static List<AirLineData> populateAirLineData() {
        List<AirLineData> data = new ArrayList<>();
        AirLineData item;

        item = new AirLineData();
        item.setDepartCity("Boston");
        item.setDestCity("New York");
        item.setFlightNumber("1111");
        item.setOnTime(true);
        item.setTime("1:45");
        data.add(item);

        item = new AirLineData();
        item.setDepartCity("Miami");
        item.setDestCity("New York");
        item.setFlightNumber("2222");
        item.setOnTime(false);
        item.setTime("3:15");
        data.add(item);

        item = new AirLineData();
        item.setDepartCity("Atlanta");
        item.setDestCity("Los Angeles");
        item.setFlightNumber("3333");
        item.setOnTime(true);
        item.setTime("5:45");
        data.add(item);

        return data;
    }
}
