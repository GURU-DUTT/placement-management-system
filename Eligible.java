import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Eligible extends JFrame implements ActionListener
{
    
    private JLabel eligibleheading,eligibleheading1,eligibleheading2,eligibleheading3;
    private JTextArea output;
    private JButton display,back;
    
    Container con=null;

    Eligible()
    {
        super("Placement Eligibility");
        con = getContentPane();
        con.setLayout(null);
        Color  lightBlue = new Color(164, 206, 209);
        con.setBackground(lightBlue);

        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(42, 135, 141);
        eligibleheading=new JLabel("PLACEMENT ELIGIBILITY");
        eligibleheading.setBounds(200, 5, 700,20);
        eligibleheading.setFont(font);
        eligibleheading.setForeground(Color.BLACK);

        eligibleheading1=new JLabel("PLATINUM: CGPA>=8 AND NO BACKLOGS ALLOWED");
        eligibleheading1.setBounds(200, 50, 700,20);
        eligibleheading1.setFont(font);
        eligibleheading1.setForeground(Color.BLACK);

        eligibleheading2=new JLabel("GOLD: CGPA>=7 AND NO BACKLOGS ALLOWED");
        eligibleheading2.setBounds(200, 75, 700,20);
        eligibleheading2.setFont(font);
        eligibleheading2.setForeground(Color.BLACK);

        eligibleheading3=new JLabel("SILVER: CGPA>=6");
        eligibleheading3.setBounds(200, 100, 700,20);
        eligibleheading3.setFont(font);
        eligibleheading3.setForeground(Color.BLACK);


        output=new JTextArea();
        output.setBounds(100, 150, 1150,400);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);

        display = new JButton("Display");
		display.setBounds(400,600,150,40);
		display.addActionListener(this);
        display.setFont(font);
        display.setForeground(Color.WHITE);
        display.setBackground(blue);

        back = new JButton("Go Back");
		back.setBounds(600,600,150,40);
		back.addActionListener(this);
        back.setFont(font);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(eligibleheading);
        con.add(eligibleheading1);
        con.add(eligibleheading2);
        con.add(eligibleheading3);
		con.add(output);
        con.add(display);
        con.add(back);

    }

    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==display)
        {
			try
            {
                //platinum
		        //cgpa >=8.0 && nob=0
		        //gold
		        //cgpa >=7.0 && nob=0
		        //silver
		        //cgpa >=6.0

                //platinum: cloudera 26, sap labs 24,amazon,19, anz 11, capgemini 8, cognizant 6, wipro 4
                //silver: anz 11, capgemini 8, cognizant 6, wipro 4
                //gold: cognizant 6, wipro 4

                System.out.println("hi");
		        String name, cgpa1, nob1, r,b,e="";
		        double cgpa, nob;
		        
                File temp = new File("temp.txt");
				Boolean createNewFile1 = temp.createNewFile();
                BufferedWriter pw = new BufferedWriter(new FileWriter(temp));

                BufferedReader br = new BufferedReader(new FileReader("student.txt"));
		        while((r = br.readLine()) != null )
		        {
		        	String[] result = r.split("\\|"); 
		        	name=result[0];
		        	cgpa1=result[4];
		        	nob1=result[5];
		        	cgpa = Double.parseDouble(cgpa1);
		        	nob = Double.parseDouble(nob1);
		        	if(cgpa!=999)
		        	{
		        		if(cgpa >= 8.0 && nob == 0)
		        	    {
                            b = name + " : " + "Platinum,Gold, Silver";
                            e="Eligible companies: SAP Labs (24LPA), Amazon (19LPA), ANZ (11LPA), Capgemini (8LPA), Cognizant (6LPA), Wipro (4LPA)";
                            pw.write(b+"\n");
                            pw.write(e+"\n\n");

		        	    }
		        	    else if(cgpa >= 7.0 && nob == 0)
		        	    {
		        	    	b = name + " : " + "Gold, Silver";
                            e="Eligible companies: ANZ (11LPA), Capgemini (8LPA), Cognizant (6LPA), Wipro (4LPA)";
                            pw.write(b+"\n");
                            pw.write(e+"\n\n");
		        	    }
		        	    else if(cgpa >= 6.0)
		        	    {
		        	    	b = name + " : " + "Silver";
                            e="Eligible companies: Cognizant (6LPA), Wipro (4LPA)";
                            pw.write(b+"\n");
                            pw.write(e+"\n\n");
		        	    }
		        	    else
		        	    {
		        	    	b = name + " : " + "Not Eligible";
                            pw.write(b+"\n\n");
		        	    }
		        	}	
                }
                br.close();
                pw.close();
                File file = new File("temp.txt");
                BufferedReader br1 = new BufferedReader(new FileReader(file));
                output.read(br1,null);
                br1.close(); 
                output.requestFocus();
                file.delete();  
            }
            catch(Exception e)
            {
                e.printStackTrace();
                //System.out.println("error is" +e);
            }
        }

        if(ae.getSource()==back)
        {
            try
            {
                this.dispose();
                Home h=new Home();
                h.setSize(1035,790);
                h.setVisible(true);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[])
    {
        Eligible eli=new Eligible();
		eli.setSize(1035,790);
		eli.setVisible(true);
    }   

}