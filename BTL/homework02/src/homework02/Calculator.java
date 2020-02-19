package homework02.src.homework02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private int firstNum, secondNum;
    protected String operator;

    protected JTextField txtFirstNumber = new JTextField();
    protected JTextField txtSecondNumber = new JTextField();
    protected JTextField txtResult = new JTextField();

    public Calculator() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(6, 2, 3, 3));

        txtFirstNumber.setHorizontalAlignment(JTextField.RIGHT);
        txtSecondNumber.setHorizontalAlignment(JTextField.RIGHT);

        txtResult.setHorizontalAlignment(JTextField.RIGHT);
        txtResult.setEnabled(false);
        txtResult.setDisabledTextColor(Color.BLACK);
        txtResult.setBackground(getBackground());

        container.add(new JLabel("First number: "));
        container.add(txtFirstNumber);
        container.add(new JLabel("Second number: "));
        container.add(txtSecondNumber);
        container.add(new JLabel("Result :"));
        container.add(txtResult);

        JButton btnPlus = new JButton("+");
        container.add(btnPlus);
        JButton btnMinus = new JButton("-");
        container.add(btnMinus);
        JButton btnMultiple = new JButton("*");
        container.add(btnMultiple);
        JButton btnDivide = new JButton("/");
        container.add(btnDivide);
        JButton btnModulo = new JButton("%");
        container.add(btnModulo);
        JButton btnClear = new JButton("CLEAR");
        container.add(btnClear);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setSize(500, 300);
        setVisible(true);

        btnClear.addActionListener(this);
        btnDivide.addActionListener(this);
        btnModulo.addActionListener(this);
        btnMinus.addActionListener(this);
        btnMultiple.addActionListener(this);
        btnPlus.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")){
            this.operator = s;
            if (txtFirstNumber.getText().equals("")) {
            	JOptionPane.showMessageDialog(txtResult, 
            			"PLEASE enter the first operand", 
            			"Error", 
            			JOptionPane.ERROR_MESSAGE);
            	return;
            } else if (txtSecondNumber.getText().equals("")) {
            	JOptionPane.showMessageDialog(txtResult, 
            			"PLEASE enter the second operand", 
            			"Error", 
            			JOptionPane.ERROR_MESSAGE);
            	return;
            }
            
            try {
            	this.firstNum = Integer.parseInt(txtFirstNumber.getText());
                this.secondNum = Integer.parseInt(txtSecondNumber.getText());
                this.txtResult.setText(evaluate(this.operator));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(txtResult, 
						"Wrong format input", 
						"Error", 
						JOptionPane.ERROR_MESSAGE);
			}
        } else if (s.equals("CLEAR")) {
        	txtFirstNumber.setText("");
        	txtSecondNumber.setText("");
        	txtResult.setText("");
        }
    }

	public String evaluate(String operator)
    {
		String result = "";
        if(operator.equals("+"))
            result += (this.firstNum + this.secondNum);
        else if(operator.equals("-"))
        	result += (this.firstNum - this.secondNum);
        else if(operator.equals("*"))
        	result += (this.firstNum * this.secondNum);
        else if(operator.equals("/")) {
        	if (secondNum == 0) {
        		JOptionPane.showMessageDialog(txtResult, 
        				"/ by zero", 
        				"Error", 
        				JOptionPane.ERROR_MESSAGE);
        		result += 0;
        	} else {
        		result += ((double)this.firstNum / this.secondNum);
        	}
        } else if(operator.equals("%"))
        	if (secondNum == 0) {
        		JOptionPane.showMessageDialog(txtResult, 
        				"/ by zero", 
        				"Error", 
        				JOptionPane.ERROR_MESSAGE);
        		result += 0;
        	} else {
        		result += (this.firstNum % this.secondNum);
        	}
        return result;
    }
}