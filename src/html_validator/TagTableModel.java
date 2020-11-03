package html_validator;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TagTableModel extends AbstractTableModel{
	
	private ArrayList<Tag> dados = new ArrayList();
    private String[] colunas ;
    

    public TagTableModel(ArrayList<Tag> dados) {
		super();
		this.dados = dados;
		this.colunas = new String[]{"Tags","Quantidade de ocorrências"};
	}

	@Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return dados.get(linha).getQtd();
            case 1: 
                return dados.get(linha).getValue();
        }
        
        return null;
        
    }
    
    public void addRow(Tag p){
        this.dados.add(p);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

}
