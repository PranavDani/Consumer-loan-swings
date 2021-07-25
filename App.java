import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener {
    JLabel lbl,irl,nopl,mpl,la;
    JTextField loanBalanceTf,interstRateTf,noOfPaymentTf,monthlyPaymentTf;
    JButton b1,b2,x1,x2,exit;
    JTextArea textArea;
    Font flabel,fbutton;
    Boolean tf3enabled=false,tf4enabled=true;

    App(){
        super("Loan Assistant");

        textArea=new JTextArea();
        textArea.setBounds(400,40,300,150);
        textArea.setFont(new Font("Courier New",Font.PLAIN,14));
        textArea.setForeground(Color.BLACK);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(textArea);

        flabel =new Font("Arial", Font.PLAIN,16);
        fbutton=new Font("Arial",Font.BOLD,13);

        la = new JLabel("Loan Analysis: ");
        lbl=new JLabel("Loan Balance");
        irl=new JLabel("Interest Rate");
        nopl=new JLabel("Number of Payments");
        mpl=new JLabel("Monthly Payment");

        loanBalanceTf=new JTextField();
        interstRateTf=new JTextField();
        noOfPaymentTf=new JTextField();
        monthlyPaymentTf=new JTextField();

        b1=new JButton("Compute Monthly Payment");
        b2=new JButton("New Loan Analysis");
        monthlyPaymentTf.setBackground(Color.YELLOW);

        setLayout(null);
        setSize(800,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        la.setBounds(400,0,150,50);
        la.setFont(flabel);
        add(la);

        lbl.setBounds(20,0,100,50);
        lbl.setFont(flabel);
        add(lbl);

        irl.setBounds(20,30,150,50);
        irl.setFont(flabel);
        add(irl);

        nopl.setBounds(20,60,150,50);
        nopl.setFont(flabel);
        add(nopl);

        mpl.setBounds(20,90,150,50);
        mpl.setFont(flabel);
        add(mpl);

        b1.setBounds(50,140,250,30);
        b1.setFont(fbutton);
        add(b1);

        b2.setBounds(75,190,200,30);
        b2.setFont(fbutton);
        b2.setEnabled(false);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        loanBalanceTf.setBounds(170,15,100,20);
        loanBalanceTf.setFont(flabel);
        loanBalanceTf.setHorizontalAlignment(JTextField.RIGHT);
        add(loanBalanceTf);

        interstRateTf.setBounds(170,45,100,20);
        interstRateTf.setHorizontalAlignment(JTextField.RIGHT);
        interstRateTf.setFont(flabel);
        add(interstRateTf);

        noOfPaymentTf.setBounds(170,80,100,20);
        noOfPaymentTf.setHorizontalAlignment(JTextField.RIGHT);
        noOfPaymentTf.setFont(flabel);
        add(noOfPaymentTf);

        monthlyPaymentTf.setBounds(170,110,100,20);
        monthlyPaymentTf.setHorizontalAlignment(JTextField.RIGHT);
        monthlyPaymentTf.setFont(flabel);
        monthlyPaymentTf.setEditable(false);
        monthlyPaymentTf.setForeground(Color.BLACK);
        add(monthlyPaymentTf);

        x1=new JButton("X");
        x1.setBounds(300,70,50,25);
        x1.setFont(fbutton);
        add(x1);

        x2=new JButton("X");
        x2.setBounds(300,110,50,25);
        x2.setFont(fbutton);
        add(x2);
        x2.setVisible(false);

        x1.addActionListener(this);
        x2.addActionListener(this);

        exit= new JButton("Exit");
        exit.setBounds(500,220,100,30);
        exit.setFont(fbutton);
        add(exit);
        exit.addActionListener(this);


    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource()==b1) {
            try {
                if(interstRateTf.getText().equals("") || interstRateTf.getText().equals("0")) {
                    JOptionPane.showMessageDialog(null,"Interest Rate cannot be 0%");
                }
                if ((loanBalanceTf.getText().equals("") || interstRateTf.getText().equals("") || noOfPaymentTf.getText().equals("")) && (loanBalanceTf.getText().equals("") || interstRateTf.getText().equals("") || monthlyPaymentTf.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Fill All The Required Details");
                }
                if (monthlyPaymentTf.getText().equals("")) {
                    float a = Float.parseFloat(loanBalanceTf.getText());
                    float n = Float.parseFloat(noOfPaymentTf.getText());
                    float i = Float.parseFloat(interstRateTf.getText()) / 1200;
                    float p = (float) (i * a / (1 - Math.pow((1 + i), -n)));
                    monthlyPaymentTf.setText((int)p + "");
                    String str="Loan Amount : $"+a+"\n";
                    str+="Interest Rate : "+i*1200+"%\n\n";
                    str+=(int)(n-1)+" Payments of : $"+p + "\n";
                    str+="Total payments = $"+((float)(n)*p) + "\n";
                    str+="Intrest paid = $"+((float)((n)*p) - a) + "\n";
                    for (int i1 = 1;i1<=n-1;i1++) a+= a*i-p;
                    str+="Final payments = $"+a+"\n";
                    textArea.setText(str);
                }
                if (noOfPaymentTf.getText().equals("")) {
                    float A = Float.parseFloat(loanBalanceTf.getText());
                    float i = Float.parseFloat(interstRateTf.getText());
                    float P = Float.parseFloat(monthlyPaymentTf.getText());
                    float I = i / 1200;
                    float n = (float) (-(Math.log10(1 - I * A / P)) / Math.log10(1 + I));
                    noOfPaymentTf.setText((int) n+1 + "");
                    String str="Loan Amount : $"+A+"\n";
                    str+="Interest Rate : "+I*1200+"%\n\n";
                    str+=((int)n)+" Payments of : $"+P + "\n";
                    str+="Total payments = $"+((float)(n)*P) + "\n";
                    str+="Intrest paid = $"+((float)((n)*P) - A) + "\n";
                    for (int i1 = 1;i1<=n;i1++) A+= A*I-P;
                    str+="Final payments = $"+A+"\n";
                    textArea.setText(str);
                }
                b1.setEnabled(false);
                b2.setEnabled(true);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        if(event.getSource()==b2) {
            if (tf4enabled) {
                monthlyPaymentTf.setText(null);
            }
            if(tf3enabled){
                noOfPaymentTf.setText(null);
            }
            loanBalanceTf.setText(null);
            interstRateTf.setText(null);
            noOfPaymentTf.setText(null);
            monthlyPaymentTf.setText(null);
            textArea.setText(null);
            b1.setEnabled(true);
            b2.setEnabled(false);
        }
        if(event.getSource()==x1){
            x1.setVisible(false);
            x2.setVisible(true);
            monthlyPaymentTf.setEditable(true);
            noOfPaymentTf.setEditable(false);
            noOfPaymentTf.setBackground(Color.YELLOW);
            monthlyPaymentTf.setBackground(Color.white);
            tf3enabled=true;
            tf4enabled=false;
            noOfPaymentTf.setText(null);

            b1.setEnabled(true);
            b2.setEnabled(false);
        }
        if(event.getSource()==x2){
            x1.setVisible(true);
            x2.setVisible(false);
            monthlyPaymentTf.setEditable(false);
            noOfPaymentTf.setEditable(true);
            noOfPaymentTf.setBackground(Color.white);
            monthlyPaymentTf.setBackground(Color.yellow);
            tf3enabled=false;
            tf4enabled=true;
            monthlyPaymentTf.setText(null);

            b1.setEnabled(true);
            b2.setEnabled(false);
        }
        if(event.getSource()==exit){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new App();
    }
}
