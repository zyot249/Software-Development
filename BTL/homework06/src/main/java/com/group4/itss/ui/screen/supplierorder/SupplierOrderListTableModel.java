package com.group4.itss.ui.screen.supplierorder;

import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.SupplierOrder;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderListTableModel extends AbstractTableModel {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private String[] columnNames = {"No.", "Order Code", "Creator", "Create Date","Action"};
    private List<SupplierOrder> supplierOrderList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    public SupplierOrderListTableModel(List<SupplierOrder> supplierOrderList,
                                       List<Employee> employeeList) {
        this.supplierOrderList = supplierOrderList;
        this.employeeList = employeeList;
        System.out.println(employeeList);
    }

    @Override
    public int getRowCount() {
        return supplierOrderList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SupplierOrder supplierOrder = supplierOrderList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return supplierOrder.getCode();
            case 2:
                if (supplierOrder.getCreator() == null) {
                    return "";
                }
                return findEmployeeById(Integer.parseInt(supplierOrder.getCreator())).getName();
            case 3:
                return formatter.format(supplierOrder.getCreatedDate());
            case 4:
                return "Detail";
            default:
                return "";
        }
    }

    private Employee findEmployeeById(int id) {
        for (Employee theEmployee: employeeList) {
            if (theEmployee.getId() == id) {
                return theEmployee;
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
