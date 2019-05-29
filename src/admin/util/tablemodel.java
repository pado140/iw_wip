/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import observateurs.Observateurs;
import view.ListObservable;

/**
 *
 * @author Padovano
 */
public class tablemodel<T> extends AbstractTableModel implements Observateurs{
    public ListObservable<T> observable;
    public List<String> title;
    private tableDataAction tda; 
    public tablemodel() {
    }

    public tablemodel(ListObservable<T> observable) {
        this.observable = observable;
    }

    public tablemodel(ListObservable<T> observable, List<String> title) {
        this.observable = observable;
        this.title = title;
    }
    
    
    @Override
    public int getRowCount() {
        return observable.size();
    }

    @Override
    public int getColumnCount() {
        return title.size();
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] data=(Object[])observable.get(rowIndex);
        return data[columnIndex];
    }
    
    public Object getData(int row){
        return observable.get(row);
    }
    @Override
    public void executer(Object... obs) {
        if(obs[0] instanceof tableDataAction){
        tableDataAction key=(tableDataAction)obs[0];
        switch(key){
            case ADD:
                    this.fireTableDataChanged();
             break;
            case REMOVE:
                this.fireTableRowsDeleted((Integer)obs[1], (Integer)obs[1]);
            break;
            case UPDATE:
                this.fireTableRowsUpdated((Integer)obs[1], (Integer)obs[1]);
                break;
        }
        }
    }
    
}
