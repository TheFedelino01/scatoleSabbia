package SwingGui;
/*
 * Created by JFormDesigner on Tue May 28 15:46:34 CEST 2019
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import net.miginfocom.swing.MigLayout;
import scatolesabbia.DatiCondivisi;



/**
 * @author Saccani Federico
 * @version 1.0
 */
public class inputManagerGUI extends JFrame {
    
        private DatiCondivisi ptrDati;
        private BallColorChooser guiSceltaColore;
        
	public inputManagerGUI(DatiCondivisi dati) {
		initComponents();
                
                ptrDati=dati;
                
                asseXLabel.setText("0");//LABEL ASSE X
                asseYLabel.setText("0");//LABEL ASSE Y
                
                guiSceltaColore = new SwingGui.BallColorChooser(ptrDati);
	}


	

	private void btnColoreActionPerformed(ActionEvent e) {
            if(guiSceltaColore.isActive()==false){
                guiSceltaColore.setVisible(true);
            }
            
	}

	

	private void sliderXStateChanged(ChangeEvent e) {
            int value = sliderX.getValue();//MAX 100 min 0   MAX -100 min 0
            //System.out.println(value);
            ptrDati.setInclinazioneTavoloDiGiocoX(value);
            asseXLabel.setText(Integer.toString(ptrDati.getInclinazioneTavoloDiGiocoX()));
	}

	private void btnDownXActionPerformed(ActionEvent e) {
		sliderX.setValue(sliderX.getValue()-2);
	}

	private void btnUpXActionPerformed(ActionEvent e) {
		sliderX.setValue(sliderX.getValue()+2);
	}

	private void btnUpYActionPerformed(ActionEvent e) {
		sliderY.setValue(sliderY.getValue()+2);
	}

	private void btnDownYActionPerformed(ActionEvent e) {
		sliderY.setValue(sliderY.getValue()-2);
	}

	private void sliderYStateChanged(ChangeEvent e) {
            int value = sliderY.getValue();//MAX 100 min 0   MAX -100 min 0
            //System.out.println(value);
            ptrDati.setInclinazioneTavoloDiGiocoY(value);
            asseYLabel.setText(Integer.toString(ptrDati.getInclinazioneTavoloDiGiocoY()));
	}
        
        
        
        //eventi non utilizzati
        private void button2ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void button5ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void sliderX2StateChanged(ChangeEvent e) {
		// TODO add your code here
	}

	private void button6ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void button4ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - unknown
		label3 = new JLabel();
		btnColore = new JButton();
		separator1 = new JSeparator();
		label1 = new JLabel();
		panel4 = new JPanel();
		label4 = new JLabel();
		asseXLabel = new JLabel();
		panel3 = new JPanel();
		btnDownX = new JButton();
		btnUpX = new JButton();
		sliderX = new JSlider();
		separator2 = new JSeparator();
		label5 = new JLabel();
		panel6 = new JPanel();
		label6 = new JLabel();
		asseYLabel = new JLabel();
		panel5 = new JPanel();
		btnUpY = new JButton();
		btnDownY = new JButton();
		panel1 = new JPanel();
		sliderY = new JSlider();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[50!,grow]" +
			"[]0" +
			"[]" +
			"[]" +
			"[]0" +
			"[]" +
			"[grow]"));

		//---- label3 ----
		label3.setText("Strumento di gestione Input");
		label3.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(label3, "cell 0 0");

		//---- btnColore ----
		btnColore.setText("CAMBIA COLORE PALLINA");
		btnColore.addActionListener(e -> {
			btnColoreActionPerformed(e);
			btnColoreActionPerformed(e);
		});
		contentPane.add(btnColore, "cell 0 1,growy");

		//---- separator1 ----
		separator1.setForeground(Color.black);
		contentPane.add(separator1, "cell 0 2 2 1");

		//---- label1 ----
		label1.setText("INPUT X:");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(label1, "cell 0 3");

		//======== panel4 ========
		{
			panel4.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]0" +
				"[grow,fill]",
				// rows
				"[grow]"));

			//---- label4 ----
			label4.setText("Inclinazione Asse X:");
			label4.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel4.add(label4, "cell 0 0");

			//---- asseXLabel ----
			asseXLabel.setText("0");
			asseXLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel4.add(asseXLabel, "cell 1 0");
		}
		contentPane.add(panel4, "cell 1 3");

		//======== panel3 ========
		{
			panel3.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[100!,grow,fill]" +
				"[70!,grow,fill]" +
				"[100!,grow,fill]" +
				"[grow,fill]",
				// rows
				"[100!,grow]" +
				"[]para" +
				"[grow]" +
				"[]"));

			//---- btnDownX ----
			btnDownX.setText("\u25c4");
			btnDownX.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnDownX.addActionListener(e -> {
			button2ActionPerformed(e);
			btnDownXActionPerformed(e);
		});
			panel3.add(btnDownX, "cell 1 0,growy");

			//---- btnUpX ----
			btnUpX.setText("\u25ba");
			btnUpX.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnUpX.addActionListener(e -> {
			button5ActionPerformed(e);
			btnUpXActionPerformed(e);
		});
			panel3.add(btnUpX, "cell 3 0,growy");

			//---- sliderX ----
			sliderX.setMajorTickSpacing(10);
			sliderX.setMinimum(-100);
			sliderX.setMinorTickSpacing(10);
			sliderX.setPaintLabels(true);
			sliderX.setPaintTicks(true);
			sliderX.setValue(0);
			sliderX.addChangeListener(e -> {
			sliderStateChanged(e);
			sliderX2StateChanged(e);
			sliderXStateChanged(e);
			sliderXStateChanged(e);
		});
			panel3.add(sliderX, "cell 0 2 5 1,aligny bottom,growy 0");
		}
		contentPane.add(panel3, "cell 0 4 2 1");

		//---- separator2 ----
		separator2.setForeground(Color.black);
		contentPane.add(separator2, "cell 0 5 2 1");

		//---- label5 ----
		label5.setText("INPUT Y:");
		label5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(label5, "cell 0 6");

		//======== panel6 ========
		{
			panel6.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[200!,grow,fill]0" +
				"[20!,grow,fill]",
				// rows
				"[grow]"));

			//---- label6 ----
			label6.setText("Inclinazione Asse Y:");
			label6.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel6.add(label6, "cell 0 0 2 1");

			//---- asseYLabel ----
			asseYLabel.setText("0");
			asseYLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel6.add(asseYLabel, "cell 1 0,alignx right,growx 0");
		}
		contentPane.add(panel6, "cell 1 6");

		//======== panel5 ========
		{
			panel5.setLayout(new MigLayout(
				"hidemode 3,alignx center",
				// columns
				"[20!,fill]" +
				"[100!,grow,fill]" +
				"[20!,fill]",
				// rows
				"[100!,grow]" +
				"[70!,grow]" +
				"[100!,grow]0"));

			//---- btnUpY ----
			btnUpY.setText("\u25b2");
			btnUpY.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnUpY.addActionListener(e -> {
			button6ActionPerformed(e);
			btnUpYActionPerformed(e);
		});
			panel5.add(btnUpY, "cell 1 0,growy");

			//---- btnDownY ----
			btnDownY.setText("\u25bc");
			btnDownY.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnDownY.addActionListener(e -> {
			button4ActionPerformed(e);
			btnDownYActionPerformed(e);
		});
			panel5.add(btnDownY, "cell 1 2,growy");
		}
		contentPane.add(panel5, "cell 0 7,align right center,grow 0 0");

		//======== panel1 ========
		{
			panel1.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[100!,grow,fill]" +
				"[70!,grow,fill]" +
				"[100!,grow,fill]" +
				"[grow,fill]",
				// rows
				"[]0" +
				"[10!]0" +
				"[grow]"));

			//---- sliderY ----
			sliderY.setMajorTickSpacing(10);
			sliderY.setMinimum(-100);
			sliderY.setMinorTickSpacing(10);
			sliderY.setPaintLabels(true);
			sliderY.setPaintTicks(true);
			sliderY.setValue(0);
			sliderY.setOrientation(SwingConstants.VERTICAL);
			sliderY.addChangeListener(e -> {
			sliderStateChanged(e);
			sliderXStateChanged(e);
			sliderYStateChanged(e);
		});
			panel1.add(sliderY, "cell 0 0 5 3,growy");
		}
		contentPane.add(panel1, "cell 1 7,growy");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - unknown
	private JLabel label3;
	private JButton btnColore;
	private JSeparator separator1;
	private JLabel label1;
	private JPanel panel4;
	private JLabel label4;
	private JLabel asseXLabel;
	private JPanel panel3;
	private JButton btnDownX;
	private JButton btnUpX;
	private JSlider sliderX;
	private JSeparator separator2;
	private JLabel label5;
	private JPanel panel6;
	private JLabel label6;
	private JLabel asseYLabel;
	private JPanel panel5;
	private JButton btnUpY;
	private JButton btnDownY;
	private JPanel panel1;
	private JSlider sliderY;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

    private void sliderStateChanged(ChangeEvent e) {
        
    }
}



