import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Ledger extends JFrame implements ActionListener
{
    
    private JLabel ledgerheading;
    private JTextArea output;
    private JButton display,back;
    public BufferedReader stud;
    public BufferedReader jour; 
    public BufferedWriter pw;
    public File temp;
    Container con=null;

    Ledger()
    {

        super("Ledger Record");
        con = getContentPane();
        con.setLayout(new FlowLayout());
        Color  lightBlue = new Color(164, 206, 209);
        con.setBackground(lightBlue);

        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(42, 135, 141);
        ledgerheading=new JLabel("The record history:");
        ledgerheading.setBounds(200, 2, 700,150);
        ledgerheading.setFont(font);
        ledgerheading.setForeground(Color.BLACK);

        output=new JTextArea();
        output.setBounds(200, 150, 900,400);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);
        //JScrollPane scroll = new JScrollPane(output);
        //JScrollPane sampleScrollPane = new JScrollPane (output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JScrollPane scroll = new JScrollPane(output);
        // scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // this.add(scroll);
        // //scroll.setBounds(200,150, 900, 400 );
        // scroll.setSize( 900, 400 );
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // //scroll(output, "bottom");
        // //scroll.setPreferredSize(new Dimension(450, 110));
        // setVisible(true);
    

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

        con.add(ledgerheading);
        con.add(output);
		//con.add(scroll);
        con.add(display);
        con.add(back);

    }

    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==display)
        {
			try
            {
                String b;
                int initial=0;
                stud = new BufferedReader(new FileReader("student.txt"));
				jour = new BufferedReader(new FileReader("journal.txt"));
				ledger(stud.readLine(),jour.readLine(),initial);
                stud.close();
                jour.close();  
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
    public void ledger (String s1, String s2,int initial) throws FileNotFoundException, IOException
	{
		String name = "", usn = "", sem = "", branch = "", cgpa= "", nob="", r, s, b, j="",sss,jjj;
        if(initial == 0)
        {
            temp = new File("temp.txt");
            Boolean createNewFile = temp.createNewFile();
            pw = new BufferedWriter(new FileWriter(temp));
            b = "NAME\t|USN\t|SEM\t|BRANCH\t|CGPA\t|NUMBER OF BACKS";
            pw.write(b); 
            pw.write("\n");
            initial=1;
        }

		//consequential matching and merging
		String[] ss = s1.split("\\|"); 
		String name_s=ss[0];
        String usn_s=ss[1];
		String sem_s= ss[2];
		String branch_s=ss[3];
		String cgpa_s=ss[4];
		String nob_s=ss[5];

		String[] jj = s2.split("\\|"); 
        String usn_j=jj[0];
		String sem_j= jj[1];
		String cgpa_j=jj[2];
		String nob_j=jj[3];
		
		int item1=0,item2=0;

		item1=Integer.parseInt(usn_s);
		item2=Integer.parseInt(usn_j);
		if(ss[1].equals("999") && jj[0].equals("999")) //ss=null && jj=null
		{
            pw.close();
            File file1= new File("temp.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            output.read(br1, null);
            br1.close();
            output.requestFocus(); 
            return;       
        }	
		else if(!ss[0].equals("999") && !jj[0].equals("999")) //ss!=null && jj!=null
		{
			if (item1 == item2)
			{
				b=name_s + "\t" + usn_s + "\t" + sem_j + "\t" + branch_s + "\t" + cgpa_j + "\t" + nob_j;
                pw.write(b);
                pw.write("\n");
                ledger(s1,jour.readLine(),initial);
			}                                                        
                                                            
			else if(item1 < item2)
			{
				b="END OF RECORD";
                pw.write(b);
                pw.write("\n");
                ledger(stud.readLine(),s2,initial);
			}
			else
			 {
                pw.write("Invalid Record Entry\n");
                ledger(s1,jour.readLine(),initial);
			 }
		}
		else //ss==null || jj==null
		{
			 if(jj[0].equals("999"))      
			{ 
				s1=stud.readLine();
				ss = s1.split("\\|"); 
				name_s=ss[0];
                usn_s=ss[1];
				sem_s= ss[2];
				branch_s=ss[3];
				cgpa_s=ss[4];                   
				nob_s=ss[5];
				while(!ss[0].equals("999"))
				{
					b=name_s + "\t" + usn_s + "\t" + sem_s + "\t" + branch_s + "\t" + cgpa_s + "\t" + nob_s;
                    pw.write(b);
                    pw.write("\n");
                    s1=stud.readLine();
					ss = s1.split("\\|"); 
					name_s=ss[0];
                    usn_s=ss[1];
					sem_s= ss[2];
					branch_s=ss[3];
					cgpa_s=ss[4];
					nob_s=ss[5];
					
				}
                pw.write("END OF RECORD\n");
				ledger(s1,s2,initial); 
			} 
            // else  //ss is reaching 999
            // {
            //     s2="999";
            //     ledger(s1,s2,initial);
            // }

		}
	}	

    public static void main(String args[])
    {
        Ledger led=new Ledger();
		led.setSize(1035,790);
		led.setVisible(true);
    }   




    
}
