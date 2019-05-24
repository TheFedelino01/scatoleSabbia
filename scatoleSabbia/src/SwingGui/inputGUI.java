package SwingGui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri May 24 23:56:20 CEST 2019
 */



/**
 * @author gdfgfdgdfg
 */
public class inputGUI extends JPanel {
	public inputGUI() {
		initComponents();
	}

	private void sliderStateChanged(ChangeEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - gdfgfdgdfg
		label3 = new JLabel();
		separator1 = new JSeparator();
		label1 = new JLabel();
		panel3 = new JPanel();
		button2 = new JButton();
		button5 = new JButton();
		panel4 = new JPanel();
		label4 = new JLabel();
		asseXdisplay2 = new JLabel();
		sliderX2 = new JSlider();
		separator2 = new JSeparator();
		label5 = new JLabel();
		panel6 = new JPanel();
		label6 = new JLabel();
		asseXdisplay3 = new JLabel();
		panel5 = new JPanel();
		button6 = new JButton();
		button4 = new JButton();
		panel1 = new JPanel();
		sliderX = new JSlider();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[grow,fill]" +
			"[grow,fill]" +
			"[grow,fill]",
			// rows
			"[43,top]0" +
			"[]0" +
			"[]0" +
			"[300!,grow,center]0" +
			"[]0" +
			"[]0" +
			"[grow]0" +
			"[grow,fill]"));

		//---- label3 ----
		label3.setText("Strumento di gestione Input");
		label3.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(label3, "cell 0 0");
		add(separator1, "cell 0 1 3 1");

		//---- label1 ----
		label1.setText("INPUT X:");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(label1, "cell 0 2 3 1");

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

			//---- button2 ----
			button2.setText("\u25c4");
			button2.setFont(new Font("Tahoma", Font.PLAIN, 24));
			panel3.add(button2, "cell 1 0,growy");

			//---- button5 ----
			button5.setText("\u25ba");
			button5.setFont(new Font("Tahoma", Font.PLAIN, 24));
			panel3.add(button5, "cell 3 0,growy");

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

				//---- asseXdisplay2 ----
				asseXdisplay2.setText("0");
				asseXdisplay2.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel4.add(asseXdisplay2, "cell 1 0");
			}
			panel3.add(panel4, "cell 1 1 3 1");

			//---- sliderX2 ----
			sliderX2.setMajorTickSpacing(10);
			sliderX2.setMinimum(-100);
			sliderX2.setMinorTickSpacing(10);
			sliderX2.setPaintLabels(true);
			sliderX2.setPaintTicks(true);
			sliderX2.setValue(0);
			sliderX2.addChangeListener(e -> sliderStateChanged(e));
			panel3.add(sliderX2, "cell 0 2 5 1,aligny bottom,growy 0");
		}
		add(panel3, "cell 0 3 3 1");

		//---- separator2 ----
		separator2.setForeground(Color.red);
		add(separator2, "cell 0 4 3 1");

		//---- label5 ----
		label5.setText("INPUT Y:");
		label5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(label5, "cell 0 5");

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
			label6.setText("Inclinazione Asse X:");
			label6.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel6.add(label6, "cell 0 0 2 1");

			//---- asseXdisplay3 ----
			asseXdisplay3.setText("0");
			asseXdisplay3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel6.add(asseXdisplay3, "cell 1 0,alignx right,growx 0");
		}
		add(panel6, "cell 1 5 2 1");

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

			//---- button6 ----
			button6.setText("\u25b2");
			button6.setFont(new Font("Tahoma", Font.PLAIN, 24));
			panel5.add(button6, "cell 1 0,growy");

			//---- button4 ----
			button4.setText("\u25bc");
			button4.setFont(new Font("Tahoma", Font.PLAIN, 24));
			panel5.add(button4, "cell 1 2,growy");
		}
		add(panel5, "cell 0 6 1 2,align right center,grow 0 0");

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

			//---- sliderX ----
			sliderX.setMajorTickSpacing(10);
			sliderX.setMinimum(-100);
			sliderX.setMinorTickSpacing(10);
			sliderX.setPaintLabels(true);
			sliderX.setPaintTicks(true);
			sliderX.setValue(0);
			sliderX.setOrientation(SwingConstants.VERTICAL);
			sliderX.addChangeListener(e -> sliderStateChanged(e));
			panel1.add(sliderX, "cell 0 0 5 3,growy");
		}
		add(panel1, "cell 1 6 2 2,growy");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - gdfgfdgdfg
	private JLabel label3;
	private JSeparator separator1;
	private JLabel label1;
	private JPanel panel3;
	private JButton button2;
	private JButton button5;
	private JPanel panel4;
	private JLabel label4;
	private JLabel asseXdisplay2;
	private JSlider sliderX2;
	private JSeparator separator2;
	private JLabel label5;
	private JPanel panel6;
	private JLabel label6;
	private JLabel asseXdisplay3;
	private JPanel panel5;
	private JButton button6;
	private JButton button4;
	private JPanel panel1;
	private JSlider sliderX;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
