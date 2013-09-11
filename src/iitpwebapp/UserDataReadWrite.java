package iitpwebapp;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/*import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;*/
import java.security.spec.InvalidKeySpecException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.sun.awt.AWTUtilities;
import java.util.prefs.Preferences;
class UserDataReadWrite
{
    String username,password,mailuser,mailpass;
    JRadioButton jrb1,jrb2,jrb3,jrb4;
    String intproxy,intport,domain;
    private Timer alphaChanger;
    Preferences prefs;
    public UserDataReadWrite()
    {
        prefs=Preferences.userNodeForPackage(iitpwebapp.UserDataReadWrite.class);
    }
    public String readProxyData() 
    {
        String userpass="",abc;
        String input,key;
        byte[] a;
        try
        {
            input=prefs.get("auth","");
            key=prefs.get("authkey","");
            intproxy=prefs.get("intproxy","");
            intport=prefs.get("intport","");
            if(input.equals("")||key.equals(""))
            {
                proxyPort();
                input=prefs.get("mail","");
                key=prefs.get("mailkey","");
                intproxy=prefs.get("intproxy","");
                intport=prefs.get("intport","");
            }
            int i=0;
            for(int j=0;j<key.length();j++)
            {
                if(key.charAt(j)=='\n')
                    i++;
            }
            a=new byte[i];
            i=0;
            abc="";
            for(int j=0;j<key.length();j++)
            {
                if(key.charAt(j)=='\n')
                {    
                    a[i]=Byte.parseByte(abc);
                    i++;
                    abc="";
                }
                else
                {
                    abc+=key.charAt(j);
                }
            }
            CryptoMessage cm=new CryptoMessage();
            cm.initialiseKey(a);
            userpass=cm.decrypt(input);    
        }
        catch(InvalidKeySpecException e){}
        catch(InvalidKeyException e){}
        catch(NoSuchAlgorithmException e){}
        catch(NoSuchPaddingException e){}
        catch(IllegalBlockSizeException e){}
        catch(BadPaddingException e){}
        catch(Exception e){}
        return userpass;
    }
    public String readUserPass() 
    {
        String userpass="",abc,input,key;
        byte[] a;
        try
        {
            input=prefs.get("mail","");
            key=prefs.get("mailkey","");
            if(input.equals("")||key.equals(""))
            {
                userPass();
                input=prefs.get("mail","");
                key=prefs.get("mailkey","");
            }
            int i=0;
            for(int j=0;j<key.length();j++)
            {
                if(key.charAt(j)=='\n')
                    i++;
            }
            a=new byte[i];
            i=0;
            abc="";
            for(int j=0;j<key.length();j++)
            {
                if(key.charAt(j)=='\n')
                {    
                    a[i]=Byte.parseByte(abc);
                    i++;
                    abc="";
                }
                else
                {
                    abc+=key.charAt(j);
                }
            }
            CryptoMessage cm=new CryptoMessage();
            cm.initialiseKey(a);
            userpass=cm.decrypt(input);    
        }
        catch(InvalidKeySpecException e){}
        catch(InvalidKeyException e){}
        catch(NoSuchAlgorithmException e){}
        catch(NoSuchPaddingException e){}
        catch(IllegalBlockSizeException e){}
        catch(BadPaddingException e){}
        catch(Exception e){}
        return userpass;
    }
    JTextField proxy,port,user,pass;
    JPasswordField jpf;
    JCheckBox auth;
    JDialog jd;
    float ii=0;
    void newFile()
    {
        proxyPort();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){}
        userPass();
    }
    void proxyPort()
    {
        try{
            SwingUtilities.invokeAndWait(new Runnable()
            {
                public void run()
                {
                    jd=new JDialog();
                    jd.setUndecorated(true);
                    jd.setAlwaysOnTop(true);
                    JPanel contentPane = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics grphcs) {
                            Graphics2D g2d = (Graphics2D) grphcs;
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                            GradientPaint gp = new GradientPaint(0, 0,getBackground().brighter().brighter(), 0, getHeight(),getBackground().darker().darker());
                            g2d.setPaint(gp);
                            g2d.fillRect(0, 0, getWidth(), getHeight());
                            super.paintComponent(grphcs);
                        }
                    };
                    contentPane.setOpaque(false);
                    jd.setContentPane(contentPane);
                    JLabel l1=new JLabel("    Proxy:"),l2=new JLabel("    Port"),l3=new JLabel("    Username:"),l4=new JLabel("    Password:"),l5=new JLabel("<html><h3>&nbsp;&nbsp;&nbsp;&nbsp;IIT-P WebApp:</h3>&nbsp;&nbsp;&nbsp;Please enter your Proxy settings:</html>");
                    jrb3=new JRadioButton("Intranet (Faster)",true);jrb4=new JRadioButton("Internet",false);
                    final ButtonGroup group2 = new ButtonGroup();group2.add(jrb3);group2.add(jrb4);
                    jrb3.setOpaque(false);jrb4.setOpaque(false);
                    jrb1=new JRadioButton("No Proxy",true);
                    jrb2=new JRadioButton("Proxy",false);
                    jrb2.setEnabled(false);
                    final ButtonGroup group = new ButtonGroup();
                    group.add(jrb1);group.add(jrb2);
                    jrb1.setOpaque(false);jrb2.setOpaque(false);
                    proxy=new JTextField("",20);port=new JTextField("",5);user=new JTextField("",10);
                    proxy.setEnabled(false);port.setEnabled(false);user.setEnabled(false);
                    jpf=new JPasswordField("",10);jpf.setEnabled(false);
                    auth=new JCheckBox("Authentication:",false);auth.setEnabled(false);
                    auth.setOpaque(false);
                    auth.addItemListener(new ItemListener()
                    {
                        public void itemStateChanged(ItemEvent e)
                        {
                            if(e.getStateChange()==ItemEvent.SELECTED)
                            {
                                user.setEnabled(true);
                                jpf.setEnabled(true);
                            }
                            else
                            {
                                user.setEnabled(false);
                                jpf.setEnabled(false);
                            }    
                        }
                    });
                    jrb2.addItemListener(new ItemListener()
                    {
                        public void itemStateChanged(ItemEvent e)
                        {
                            if(e.getStateChange()==ItemEvent.SELECTED)
                            {
                                proxy.setEnabled(true);
                                port.setEnabled(true);
                                auth.setEnabled(true);
                                if(auth.isSelected())
                                {
                                    user.setEnabled(true);
                                    jpf.setEnabled(true);
                                }
                            }
                            else
                            {
                                proxy.setEnabled(false);
                                port.setEnabled(false);
                                auth.setEnabled(false);
                                user.setEnabled(false);
                                jpf.setEnabled(false);
                            }
                        }
                    });
                    jrb3.addItemListener(new ItemListener()
                    {
                        public void itemStateChanged(ItemEvent e)
                        {
                            if(e.getStateChange()==ItemEvent.SELECTED)
                            {
                                jrb1.setSelected(true);
                                jrb2.setEnabled(false);
                            }
                            else
                            {
                                jrb2.setEnabled(true);
                            }
                        }
                    });
                    JButton ok=new JButton("Ok");
                    ok.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            intproxy="";
                            intport="";
                            username="";
                            password="";
                            if(!jrb3.isSelected())
                            {
                                if(jrb2.isSelected())
                                {
                                    intproxy=proxy.getText();
                                    intport=port.getText();
                                    if(auth.isSelected())
                                    {
                                        username=user.getText();
                                        password=new String(jpf.getPassword());
                                    }
                                }
                            }
                            jd.setVisible(false);
                        }
                    });
                    JPanel jp=new JPanel();
                    jp.setOpaque(false);
                    jp.setLayout(new GridBagLayout());
                    GridBagConstraints grid=new GridBagConstraints();
                    grid.fill=GridBagConstraints.BOTH;grid.gridwidth=2;grid.gridheight=1;
                    grid.gridx=0;grid.gridy=0;
                    jp.add(l5,grid);
                    grid.gridx=0;grid.gridy=1;grid.gridwidth=1;
                    jp.add(jrb3,grid);
                    grid.gridx=1;grid.gridy=1;
                    jp.add(jrb4,grid);
                    grid.gridx=0;grid.gridy=2;
                    jp.add(jrb1,grid);
                    grid.gridx=1;grid.gridy=2;
                    jp.add(jrb2,grid);
                    grid.gridx=0;grid.gridy=3;
                    jp.add(l1,grid);
                    grid.gridx=1;grid.gridy=3;
                    jp.add(proxy,grid);
                    grid.gridx=0;grid.gridy=4;
                    jp.add(l2,grid);
                    grid.gridx=1;grid.gridy=4;
                    jp.add(port,grid);
                    grid.gridx=0;grid.gridy=5;
                    jp.add(auth,grid);
                    grid.gridx=0;grid.gridy=6;
                    jp.add(l3,grid);
                    grid.gridx=1;grid.gridy=6;
                    jp.add(user,grid);
                    grid.gridx=0;grid.gridy=7;
                    jp.add(l4,grid);
                    grid.gridx=1;grid.gridy=7;
                    jp.add(jpf,grid);
                    grid.gridx=1;grid.gridy=8;grid.fill=GridBagConstraints.VERTICAL;
                    jp.add(ok,grid);
                    jp.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
                    jd.add(jp);
                    jd.pack();
                    //Dimension scrdim=Toolkit.getDefaultToolkit().getScreenSize();
                    Dimension dim=jd.getSize();
                    Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                    jd.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                    AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                    jd.setVisible(true);
                    ii=0;
                    alphaChanger = new Timer(70,new ActionListener() {

                        private float incrementer = .10f;

                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            ii=ii+incrementer;
                            if(ii>((float)0.9))
                                alphaChanger.stop();
                            AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                        }
                    });
                    alphaChanger.start();
                }
            });
        }catch(Exception e){}
        while(jd.isVisible()==true)
        {
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        String written;
        byte[] key;
        String keyy="";
        try 
        {
            CryptoMessage cm=new CryptoMessage();
            key=cm.generateKey();
            written=cm.encrypt(username+":"+password);
            for(int i=0;i<key.length;i++)
            {
                String ss=Byte.toString(key[i]);
                keyy=keyy+ss+"\n";
            }
            prefs.put("auth",written);
            prefs.put("authkey",keyy);
            prefs.put("intproxy",intproxy);
            prefs.put("intport",intport);
            prefs.flush();
        }
        catch(InvalidKeyException e){}
        catch(NoSuchAlgorithmException e){}
        catch(NoSuchPaddingException e){}
        catch(IllegalBlockSizeException e){}
        catch(BadPaddingException e){}
        catch(Exception e){}
    }
    void userPass()
    {
        try{
            SwingUtilities.invokeAndWait(new Runnable()
            {
                public void run()
                {
                    jd=new JDialog();
                    jd.setUndecorated(true);
                    jd.setAlwaysOnTop(true);
                    JPanel contentPane = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics grphcs) {
                            Graphics2D g2d = (Graphics2D) grphcs;
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                            GradientPaint gp = new GradientPaint(0, 0,getBackground().brighter().brighter(), 0, getHeight(),getBackground().darker().darker());
                            g2d.setPaint(gp);
                            g2d.fillRect(0, 0, getWidth(), getHeight());
                            super.paintComponent(grphcs);
                        }
                    };
                    contentPane.setOpaque(false);
                    jd.setContentPane(contentPane);
                    JLabel l1=new JLabel("    Username:"),l2=new JLabel("    Password:"),l3=new JLabel("<html><h3>&nbsp;&nbsp;&nbsp;&nbsp;IIT-P WebApp:</h3>&nbsp;&nbsp;&nbsp;Please enter your webmail username and password:&nbsp;&nbsp;&nbsp;<br>&nbsp;</html>"),l4=new JLabel("Enter Domain:"),l5=new JLabel("<html>Intranet: 172.16.1.11 or 172.16.1.32<br/>Internet: ashoka.iitp.ac.in, etc.<br/>&nbsp;</html>");
                    user=new JTextField("",10);
                    jpf=new JPasswordField("",10);
                    final JTextField dom=new JTextField("",20);
                    JButton ok=new JButton("Ok");
                    ok.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            domain=dom.getText();
                            mailuser=user.getText();
                            mailpass=new String(jpf.getPassword());
                            jd.setVisible(false);
                        }
                    });
                    Thread tth=new Thread(){
                        public void run()
                        {
                            final String hhhh=readDomainFile();
                            SwingUtilities.invokeLater(new Runnable(){
                                public void run()
                                {
                                    dom.setText(hhhh);
                                }
                            });
                        }
                    };
                    tth.start();
                    JPanel jp=new JPanel();
                    jp.setOpaque(false);
                    jp.setLayout(new GridBagLayout());
                    GridBagConstraints grid=new GridBagConstraints();
                    grid.fill=GridBagConstraints.BOTH;grid.gridwidth=2;grid.gridheight=1;
                    grid.gridx=0;grid.gridy=0;
                    jp.add(l3,grid);
                    grid.gridx=0;grid.gridy=1;grid.gridwidth=1;
                    jp.add(l4,grid);
                    grid.gridx=1;grid.gridy=1;
                    jp.add(dom,grid);
                    grid.gridx=1;grid.gridy=2;grid.gridwidth=1;
                    jp.add(l5,grid);
                    grid.gridx=0;grid.gridy=3;grid.gridwidth=1;
                    jp.add(l1,grid);
                    grid.gridx=1;grid.gridy=3;
                    jp.add(user,grid);
                    grid.gridx=0;grid.gridy=4;
                    jp.add(l2,grid);
                    grid.gridx=1;grid.gridy=4;
                    jp.add(jpf,grid);
                    grid.gridx=1;grid.gridy=5;grid.fill=GridBagConstraints.VERTICAL;
                    jp.add(ok,grid);
                    jp.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
                    jd.add(jp);
                    jd.pack();
                    //Dimension scrdim=Toolkit.getDefaultToolkit().getScreenSize();
                    Dimension dim=jd.getSize();
                    Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                    jd.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                    AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                    jd.setVisible(true);
                    ii=0;
                    alphaChanger = new Timer(70,new ActionListener() {

                        private float incrementer = .10f;

                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            ii=ii+incrementer;
                            if(ii>((float)0.9))
                                alphaChanger.stop();
                            AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                        }
                    });
                    alphaChanger.start();
                }
            });
        }catch(Exception e){}
        while(jd.isVisible()==true)
        {
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        String written;
        byte[] key;
        String keyy="";
        try 
        {
            CryptoMessage cm=new CryptoMessage();
            key=cm.generateKey();
            written=cm.encrypt(mailuser+":"+mailpass);
            for(int i=0;i<key.length;i++)
            {
                String ss=Byte.toString(key[i]);
                keyy=keyy+ss+"\n";
            }
            prefs.put("mail",written);
            prefs.put("mailkey",keyy);
            prefs.put("domain",domain);
            prefs.flush();
        }
        catch(InvalidKeyException e){}
        catch(NoSuchAlgorithmException e){}
        catch(NoSuchPaddingException e){}
        catch(IllegalBlockSizeException e){}
        catch(BadPaddingException e){}
        catch(Exception e){}
    }
    int readSplash()
    {
        int val=Integer.parseInt(prefs.get("splash","1"));
        prefs.put("splash",Integer.toString(val));
        return val;
    }
    void writeSplash(int i)
    {
        try
        {
            prefs.put("splash",Integer.toString(i));
            prefs.flush();
        }catch(Exception n){}
    }
    int readRefreshTime()
    {
        int val=Integer.parseInt(prefs.get("refresh","5"));
        prefs.put("refresh",Integer.toString(val));
        return val;
    }
    void writeRefreshTime(int i)
    {
        try
        {
            prefs.put("refresh",Integer.toString(i));
            prefs.flush();
        }catch(Exception n){}
    }
    String readDomainFile()
    {
        domain=prefs.get("domain","");
        return domain;
    }
    boolean readEnabled()
    {
        int val=Integer.parseInt(prefs.get("enabled","1"));
        prefs.put("enabled",Integer.toString(val));
        if(val==1)
            return true;
        else
            return false;
    }
    void writeEnabled(boolean b)
    {
        int i=(b==true)?1:0;
        try
        {
            prefs.put("enabled",Integer.toString(i));
            prefs.flush();
        }catch(Exception n){}
    }
    boolean readSound()
    {
        int val=Integer.parseInt(prefs.get("sound","1"));
        prefs.put("sound",Integer.toString(val));
        if(val==1)
            return true;
        else
            return false;
    }
    void writeSound(boolean b)
    {
        int i=(b==true)?1:0;
        try
        {
            prefs.put("sound",Integer.toString(i));
            prefs.flush();
        }catch(Exception n){}
    }
    String readDomain()
    {
        return domain;
    }
}    