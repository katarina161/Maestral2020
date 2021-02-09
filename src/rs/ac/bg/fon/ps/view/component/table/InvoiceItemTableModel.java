/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.component.table;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class InvoiceItemTableModel extends AbstractTableModel{
    
    private final String[] columnNames = new String[]{"Order No.", "Product", "Size", "Price", "Quantity"};
    private final Class[] columnClasses = new Class[]{Integer.class, Product.class, Size.class, BigDecimal.class, Integer.class};
    private List<InvoiceItem> items;

    public InvoiceItemTableModel() {
        items = new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem item = items.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return item.getOrderNumber();
            case 1:
                return item.getProduct();
            case 2:
                return item.getSize();
            case 3:
                return item.getPrice().setScale(2, RoundingMode.HALF_UP).doubleValue();
            case 4:
                return item.getQuantity();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        InvoiceItem item = items.get(rowIndex);
        
        switch (columnIndex) {
            case 2:
                item.setSize((Size) value);
                break;
            case 4:
                int quantity = (int) value;
                if (quantity > 0) {
                    item.setQuantity(quantity);
                }
        }
    }

    public List<InvoiceItem> getItems() {
        return items;
    }
    
}
