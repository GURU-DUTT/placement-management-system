import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Search extends JFrame implements ActionListener
{
    
    private JLabel usnL;
    private JTextField usnT; 
    private JTextArea output;
    private JButton search,back;
    
    Container con=null;
    String usn="";

    Search()
    {
        super("Search Record");
        con = getContentPane();
        con.setLayout(null);
        Color  lightBlue = new Color(164, 206, 209);
        con.setBackground(lightBlue);

        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(42, 135, 141);
        usnL=new JLabel("Enter usn of record to be searched");
        usnL.setBounds(400, 50, 700,150);
        usnL.setFont(font);
        usnL.setForeground(Color.BLACK);
        
        usnT=new JTextField(200);
		usnT.setBounds(725,100,250,50);
        usnT.setFont(font);
        usnT.setForeground(Color.BLACK);

        output=new JTextArea();
        output.setBounds(200,200,900,100);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);

        search = new JButton("Search");
		search.setBounds(400,600,150,40);
		search.addActionListener(this);
        search.setFont(font);
        search.setForeground(Color.WHITE);
        search.setBackground(blue);

        back = new JButton("Go Back");
		back.setBounds(600,600,150,40);
		back.addActionListener(this);
        back.setFont(font);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(usnL);
		con.add(usnT);
        con.add(output);
        con.add(search);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==search)
        {
            String usn = usnT.getText();
			try
            {
                String name = "", usn1 ="", sem = "", branch = "", cgpa= "", nob="", r;
                BufferedReader br = new BufferedReader(new FileReader("student.txt"));
		        while((r= br.readLine()) !=null)
		        {
		        	String[] result = r.split("\\|");
		        	name=result[0];
		        	usn1=result[1];
		        	sem= result[2];
		        	branch=result[3];
		        	cgpa=result[4];
		        	nob=result[5];
		        	if(usn1.equals(usn))
		        	{
                        File temp = new File("temp.txt");
					    Boolean createNewFile1 = temp.createNewFile();
					    BufferedWriter pw = new BufferedWriter(new FileWriter(temp));
                        String b = "NAME" + "\t|" + "USN" + "\t|" + "SEM" + "\t|" + "BRANCH" + "\t|" + "CGPA" + "\t|" + "NUMBER OF BACKS";
                        String bb = name  + "\t|" + usn1 + "\t|" + sem + "\t|" + branch + "\t|" + cgpa + "\t|" + nob;
                        pw.write(b);
                        pw.write("\n");
                        pw.write(bb);
                        pw.write("\n");
                        pw.close();
                        br.close();
                        File file = new File("temp.txt");
                        BufferedReader br1 = new BufferedReader(new FileReader(file));
                        output.read(br1,null);
                        br1.close();  
                        output.requestFocus();
                        file.delete();
		        		return ;
		        	}
		        }
		        showMessageDialog(null , "Record not found");
		        br.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
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
        Search ser=new Search();
		ser.setSize(1035,790);
		ser.setVisible(true);
    }
}

