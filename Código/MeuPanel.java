import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class MeuPanel extends JPanel{
	
	List<MeuObjeto> lista; 
	
	public MeuPanel() {
		lista = new LinkedList<MeuObjeto>();
		setBackground(Color.lightGray);
	}
	
	public List<MeuObjeto> getLista(){
		return lista;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (MeuObjeto obj: lista) {
			obj.desenha(g);
		}
	}

	

}