// contributed by ms884
package chatting_application;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;


public class Client  implements ActionListener{
   
   //global variable 
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static JFrame f1  = new JFrame();

    static Box vertical = Box.createVerticalBox();
    
   
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    Boolean typing;
    
    Client()
    {f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      //Navigatio0n_panel_setting
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94 , 84));
        p1.setBounds(0,0,450,70);
        f1.add(p1);
        
        //previous_page_button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting_application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel (i3); 
        l1.setBounds(5 , 17, 30 , 30);
        p1.add(l1);
        
        //mouse_listner_previous_page_button
        l1.addMouseListener( new MouseAdapter(){
                 public void mouseClicked(MouseEvent ae){
                     System.exit(0);
                 } 
    });
        //User1_picture
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting_application/icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel (i6); 
        l2.setBounds(40 , 5, 60 , 60);
        p1.add(l2);
        
        //video_button_Icon
          ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting_application/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel (i9); 
        l5.setBounds(290 , 20, 30 , 30);
        p1.add(l5);
        
        //phone_Icon
          ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting_application/icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel (i13); 
        l6.setBounds(350 , 20, 35 , 35);
        p1.add(l6);
        
        //burger dot icon
          ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting_application/icons/3icon.png"));
        Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel (i16); 
        l7.setBounds(415 , 20, 13 , 25);
        p1.add(l7);
        
        //user1 gaitonde
        JLabel l3 = new JLabel("Bunty");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 18);
        p1.add(l3);
        
        //status value(Active)
        JLabel l4 = new JLabel("Active_Now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 35, 100, 20);
        p1.add(l4);
        
        Timer t = new Timer(1 , new ActionListener(){
           public void  actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
                   
               }
           } 
        });
        
        t.setInitialDelay(1000);
        //new
        //message_history_JPanel
        a1 = new  JPanel();
        //a1.setBounds(5, 75 , 440 , 570 );
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN , 16));
        //f1.add(a1);
        
        //JScroll_Pane
        JScrollPane sp = new JScrollPane(a1);
        a1.setBounds(5, 75 , 440 , 570 );
        sp.setBorder(BorderFactory.createEmptyBorder());
        
        
        ScrollBarUI ui = new BasicScrollBarUI(){
          protected JButton createDecreaseButton(int orientation){
              JButton button = super.createDecreaseButton(orientation);
              button.setBackground(new Color(7 , 94 , 84));
              button.setForeground(Color.WHITE);
              this.thumbColor = new Color(7 , 94 , 84);
              return button;
          }  
          
           protected JButton createIncreaseButton(int orientation){
              JButton button = super.createDecreaseButton(orientation);
              button.setBackground(new Color(7 , 94 , 84));
              button.setForeground(Color.WHITE);
              this.thumbColor= new Color(7 , 94 , 84);
              return button;
          }  
        };
          
        sp.getVerticalScrollBar().setUI(ui);
        f1.add(sp);
        //new
        // Text_Field(chat_box)
        t1 = new JTextField();
        t1.setBounds(5, 655, 310, 40);
        t1.setFont(new Font("SAN_SERIF" , Font.PLAIN, 16));
        f1.add(t1);
        
         t1.addKeyListener(new KeyAdapter (){
        public void keyPressed(KeyEvent ke){
          l4.setText("typing ..");
          
          t.stop();
          
          typing = true;
        }
        public void keyReleased(KeyEvent ke){
            typing = false;
            
            if(!t.isRunning()){
                t.start();
            }
        }
    });
        
        //Send_button
        b1 = new JButton("Send");
        b1.setBounds(320, 655 , 123 , 40);
        b1.setBackground(new Color(7 , 94 , 84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF" , Font.PLAIN , 16));
        b1.addActionListener(this);
        f1.add(b1);
        
   // PANEL STRUCTURE 
   f1.getContentPane ().setBackground(Color.WHITE);
    f1.setLayout(null);    
    f1.setSize(450 , 700);
    f1.setLocation(1100 , 200);
    f1.setUndecorated(true);
    f1.setVisible(true);
    }
    
     public void sendTextToFile(String message) throws FileNotFoundException{
        try{
            FileWriter f = new FileWriter("chat.txt"); 
            PrintWriter p = new PrintWriter(new BufferedWriter(f));{
            p.println("bunty" + message);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {try{
     String out = t1.getText();
     
     sendTextToFile(out); 
    
     JPanel p2 = formatLabel(out); 

    a1.setLayout(new BorderLayout());
    
    JPanel right = new JPanel(new BorderLayout());
     right.add(p2 , BorderLayout.LINE_END);
     vertical.add(right);  
     vertical.add(Box.createVerticalStrut(15));
             
             a1.add(vertical , BorderLayout.PAGE_START);
             
    // a1.add(p2);
     dout.writeUTF(out);
     t1.setText("");}
    catch(Exception e){
         System.out.println(e);
     }
    }

public static JPanel formatLabel(String out){
    JPanel p3 = new JPanel();
    p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
    
    
    JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</html>");
    l1.setFont(new Font("Tahoma",Font.PLAIN,16));
    l1.setBackground(new Color(37 , 211 , 102));
    l1.setOpaque(true);
    l1.setBorder(new EmptyBorder(15,15,15,50));
    
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    JLabel l2 = new JLabel();
    l2.setText(sdf.format(cal.getTime()));
    
    p3.add(l1);
    p3.add(l2);
    return p3;
}

    
    public static void main(String args [])
    {
        new Client().f1.setVisible(true);
          try{
              s = new Socket("127.0.0.1" , 6001);
              din = new DataInputStream(s.getInputStream());
              dout = new DataOutputStream(s.getOutputStream());
              
              String msgInput = "";
              
              while(true){
                  a1.setLayout(new BorderLayout());
                  msgInput = din.readUTF();
                  JPanel p2 = formatLabel(msgInput);
                  JPanel left = new JPanel(new BorderLayout());
                left.add(p2 , BorderLayout.LINE_START);
                  
                
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical , BorderLayout.PAGE_START);
                f1.validate();
              }
              
          }catch(Exception e){
              
          }
    }
}
