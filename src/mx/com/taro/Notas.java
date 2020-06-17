package mx.com.taro;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Notas {
	class ListSelectionListenerCustom implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
	class MouseClick implements MouseListener{
		
//		public getSelectedData(JTable table) {
//		int selectedRow = table.getSelectedRow();
//		String selected = new String();
//		selected = table.getValueAt(selectedRow, 0);
//		textf1.setText(selected);
	//}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		//Forma 1
				ListSelectionListenerCustom custom = new ListSelectionListenerCustom();
				model.addListSelectionListener(custom);
				
				//primero se crea una clase necesaria(ListSelectionListenerCustom) 
				//que implenta la inteface (ListSelectionListener) 
				//como es una inferface, se tienen que sobreescribir sus metodos.
				//en el lugar en donde va a ser usado , se crea una instancia de la clase
				//y se asigna como parametro del metodo que se quiere usar. 
				
				//Forma 2
//				model.addListSelectionListener(new ListSelectionListener() {
//					@Override
//					public void valueChanged(ListSelectionEvent e) {
//						// TODO Auto-generated method stub
//						if(! model.isSelectionEmpty()){
//							int selectedRow = model.getMinSelectionIndex();
//							JOptionPane.showMessageDialog(null, "Selected Row"+selectedRow);
//						}
//						
//					}
//				});
				//todo dentro del parametro, se puede hacer la implementacion
				//de la interface sin crear una clase.

}
