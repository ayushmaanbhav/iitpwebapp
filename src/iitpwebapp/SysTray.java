package iitpwebapp;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.TrayIcon;
import java.awt.Toolkit;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Desktop;
import javax.swing.Timer;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.CheckboxMenuItem;
import javax.swing.SwingUtilities;
import javax.sound.sampled.*;
//import java.io.File;
import java.net.URL;

public class SysTray {
    Image image;
    JTextField numberField;
    Timer alphaChanger;
    float ii;
    Clip clip;
    AudioInputStream audioInputStream;
    FloatControl gainControl;
    boolean jcb1state;
    int vis1,vis2,vis3,vis4;
    boolean sound;
    SystemTray sysTray;
    String value;
    JButton jb1,jb2,ok;
    JCheckBox jcb1,jcb2;
    PopupMenu menu;
    JDialog jd;
    JTextField jtf;
    MenuItem item1,item2,item4,item5,item7,item8,item9,item10;
    CheckboxMenuItem item3,item6;
    TrayIcon trayIcon;
    IITPWebMail app;
    UserDataReadWrite ur;
    public SysTray(UserDataReadWrite u)
    {
        ur=u;
        sound=ur.readSound();
        value="";
        trayIcon=null;
        jd=null;
    }
    void initialiseApp(IITPWebMail ap)
    {
        app=ap;
    }
    boolean splashEnabled;
    void initialise(boolean enabled,int splashEnab)
    {
        splashEnabled=((splashEnab==1)?true:false);
        if (SystemTray.isSupported()) {
            sysTray = SystemTray.getSystemTray();
            URL url=ClassLoader.getSystemResource("Data/systray.png");
            image  = Toolkit.getDefaultToolkit().getImage(url);
            menu = new PopupMenu();
            item1 = new MenuItem("Exit");
            item2 = new MenuItem("Settings");
            item3 = new CheckboxMenuItem("Enable App",enabled);
            item4 = new MenuItem("About");
            item5 = new MenuItem("Refresh Now");
            item5.setEnabled(false);
            item6 = new CheckboxMenuItem("Enable Sound",sound);
            item7 = new MenuItem("Compose");
            item7.setEnabled(false);
            item8 = new MenuItem("Inbox");
            item8.setEnabled(false);
            item9 = new MenuItem("Sent");
            item9.setEnabled(false);
            item10 = new MenuItem("Drafts");
            item10.setEnabled(false);
            menu.add(item8);
            menu.add(item7);
            menu.add(item9);
            menu.add(item10);
            menu.add(item2);
            menu.add(item3);
            menu.add(item6);
            menu.add(item4);
            menu.add(item5);
            menu.add(item1);
            item1.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   System.exit(0);
               }
            });
            item2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) 
                {
                    vis1=0;vis2=0;vis3=0;vis4=0;
                    try{
                        if(app.jd.isVisible())
                        {
                            app.jd.setVisible(false);
                            vis1++;
                        }
                    }catch(Exception hj){}
                    try{
                        if(app.jd2.isVisible())
                        {
                            app.jd2.setVisible(false);
                            vis2++;
                        }
                    }catch(Exception hj){}
                    try{
                        if(app.jd3.isVisible())
                        {
                            app.jd3.setVisible(false);
                            vis3++;
                        }
                    }catch(Exception hj){}
                    try{
                        if(app.jd4.isVisible())
                        {
                            app.jd4.setVisible(false);
                            vis4++;
                        }
                    }catch(Exception hj){}
                    jd=new JDialog();
                    jd.setUndecorated(true);
                    jd.setSize(300,190);
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
                    JPanel jp=(JPanel)jd.getContentPane();
                    jp.setLayout(new GridBagLayout());
                    JLabel l1=new JLabel("<html><h3>IIT-P WebApp Settings:</h3></html>");
                    JLabel l2=new JLabel("Refresh Time(mins):");
                    Thread mm=new Thread(){
                        public void run()
                        {
                            try{
                                value="";
                                value=WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Run","IITP-WebApp");
                            }catch(Exception efg)
                            {value="";}
                            SwingUtilities.invokeLater(new Runnable(){
                                public void run()
                                {
                                    try
                                    {
                                        if(value.indexOf("IITPWebApp")!=-1)
                                            jcb1.setSelected(true);
                                        else
                                            jcb1.setSelected(false);
                                    }catch(Exception e)
                                    {
                                        jcb1.setSelected(false);
                                    }
                                    jcb1state=jcb1.isSelected();
                                }
                            });
                        }
                    };
                    mm.start();
                    jcb1=new JCheckBox("Run at Startup");
                    jcb1.setOpaque(false);
                    jcb2=new JCheckBox("Show Splash",splashEnabled);
                    jcb2.setOpaque(false);
                    jcb2.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e) 
                        {
                            Thread tt=new Thread(){
                                public void run()
                                {
                                    splashEnabled=jcb2.isSelected();
                                    if(jcb2.isSelected())
                                        ur.writeSplash(1);
                                    else    
                                        ur.writeSplash(0);
                                }
                            };
                            tt.start();
                        }
                    });
                    jb1=new JButton("Change Username or Password");
                    jb1.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            jd.setVisible(false);
                            Thread tt=new Thread(){
                                public void run()
                                {
                                    ur.userPass();
                                    if(!(app.user.equals(ur.mailuser)&&app.pass.equals(ur.mailpass)&&app.domain.equals(ur.domain)))
                                    {
                                        app.user=ur.mailuser;
                                        app.pass=ur.mailpass;
                                        app.domain=ur.domain;
                                        app.relogin();
                                    }
                                    SwingUtilities.invokeLater(new Runnable(){
                                        public void run()
                                        {
                                            com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                            jd.setVisible(true);
                                            ii=0;
                                            alphaChanger = new Timer(70,new ActionListener() {
                                                private float incrementer = .10f;
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    ii=ii+incrementer;
                                                    if(ii>((float)0.9))
                                                    {
                                                        alphaChanger.stop();
                                                    }
                                                    com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                                                }
                                            });
                                            alphaChanger.start();
                                        }
                                    });
                                }
                            };
                            tt.start();
                        }
                    });
                    jb2=new JButton("Change Proxy Settings.");
                    jb2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            jd.setVisible(false);
                            Thread tt=new Thread(){
                                public void run()
                                {
                                    ur.proxyPort();
                                    app.proxy=ur.intproxy;
                                    app.port=ur.intport;
                                    app.puser=ur.username;
                                    app.ppass=ur.password;
                                    app.domain=ur.domain;
                                    Thread kl=new Thread(){
                                        void main()
                                        {
                                            app.setProxy();
                                        }
                                    };
                                    kl.start();
                                    SwingUtilities.invokeLater(new Runnable(){
                                        public void run()
                                        {
                                            com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                            jd.setVisible(true);
                                            ii=0;
                                            alphaChanger = new Timer(70,new ActionListener() {
                                                private float incrementer = .10f;
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    ii=ii+incrementer;
                                                    if(ii>((float)0.9))
                                                    {
                                                        alphaChanger.stop();
                                                    }
                                                    com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                                                }
                                            });
                                            alphaChanger.start();
                                        }
                                    });
                                }
                            };
                            tt.start();
                        }
                    });
                    ok=new JButton("Ok");
                    ok.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            if(jcb1.isSelected()!=jcb1state)
                            {
                                Thread j=new Thread(){
                                    public void run(){
                                        try{
                                            if(jcb1.isSelected())
                                            {
                                                String io=WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\IITPWebApp","IITP-WebApp");
                                                WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Run","IITP-WebApp",io);
                                            }
                                            else
                                            {
                                                WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Run","IITP-WebApp");
                                            }
                                        }catch(Exception d){}
                                    }
                                };
                                j.start();
                            }
                            try
                            {
                                final int h=Integer.parseInt(numberField.getText());
                                if(h>0&&h<=60)
                                {
                                    Thread hhk=new Thread(){
                                        public void run()
                                        {
                                            ur.writeRefreshTime(h);
                                        }
                                    };
                                    hhk.start();
                                    app.rt=h;
                                    jd.setVisible(false);
                                    if(vis1==1)
                                    {
                                        app.jd.setVisible(true);
                                    }
                                    else
                                    if(vis2==1)
                                    {
                                        app.jd2.setVisible(true);
                                    }
                                    else
                                    if(vis3==1)
                                    {
                                        app.jd3.setVisible(true);
                                    }
                                    else
                                    if(vis4==1)
                                    {
                                        app.jd4.setVisible(true);
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(jd,"Please enter an integer between 1-60","Error:",JOptionPane.PLAIN_MESSAGE);
                                }
                            }catch(Exception ef)
                            {
                                JOptionPane.showMessageDialog(jd,"Please enter an integer between 1-60","Error:",JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    });
                    numberField = new JTextField();
                    Thread t3=new Thread()
                    {
                        public void run()
                        {
                            final String strtt=""+ur.readRefreshTime();
                            SwingUtilities.invokeLater(new Runnable(){
                                public void run()
                                {
                                    numberField.setText(strtt);
                                }
                            });
                        }
                    };
                    t3.start();
                    numberField.setColumns(2);
                    GridBagConstraints gbc=new GridBagConstraints();
                    gbc.fill=GridBagConstraints.BOTH;
                    gbc.gridwidth=2;gbc.gridheight=2;gbc.gridx=0;gbc.gridy=0;
                    jp.add(l1,gbc);
                    gbc.gridx=0;gbc.gridy=2;gbc.gridheight=1;gbc.gridwidth=2;
                    jp.add(jb1,gbc);
                    gbc.gridx=0;gbc.gridy=3;
                    jp.add(jb2,gbc);
                    gbc.gridx=0;gbc.gridy=4;
                    jp.add(jcb1,gbc);
                    gbc.gridx=0;gbc.gridy=5;
                    jp.add(jcb2,gbc);
                    gbc.gridx=0;gbc.gridy=6;gbc.gridwidth=1;
                    jp.add(l2,gbc);
                    gbc.gridx=1;gbc.gridy=6;
                    jp.add(numberField,gbc);
                    gbc.gridx=0;gbc.gridy=7;gbc.gridwidth=2;
                    jp.add(ok,gbc);
                    
                    jp.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
                    Dimension dim=jd.getSize();
                    Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                                
                    if(scrdim.x == 0 && scrdim.y == 0)
                        jd.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                    else if(scrdim.x == 0)
                        jd.setLocation((scrdim.width-dim.width),(scrdim.y));
                    else //if(scrdim.y == 0)
                        jd.setLocation((scrdim.x),(scrdim.height-dim.height));
                    
                    com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                    jd.setVisible(true);
                    ii=0;
                    alphaChanger = new Timer(70,new ActionListener() {
                        private float incrementer = .10f;
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            ii=ii+incrementer;
                            if(ii>((float)0.9))
                            {
                                alphaChanger.stop();
                            }
                            com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                        }
                    });
                    alphaChanger.start();
                }
            });
            item3.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e)
                {
                    app.enabled=item3.getState();
                    if(app.enabled==false)
                    {
                        trayIcon.setToolTip("IITPWebApp\nDisabled");
                    }
                    else
                    {
                        trayIcon.setToolTip("IITPWebApp");
                    }
                    Thread hh=new Thread(){
                        public void run()
                        {
                             ur.writeEnabled(app.enabled);
                        }
                    };
                    hh.start();
                }
            });
            item4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"All Credits : Ayush Jain , 1101CS09 , IIT - Patna","About:",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            item5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    app.refresh();
                }
            });
            item6.addItemListener(new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                   sound=item6.getState();
                   Thread hh=new Thread(){
                      public void run()
                      {
                          ur.writeSound(item6.getState());
                      }
                   };
                   hh.start();
               }
            });
            item7.setActionCommand("Compose");
            item8.setActionCommand("Inbox");
            item9.setActionCommand("Sent");
            item10.setActionCommand("Drafts");
            /*item8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(trayIcon!=null)
                    {
                        int ll=0;
                        for(ActionListener al:trayIcon.getActionListeners())
                        {
                            ll++;
                        }
                        if(ll>0)
                        {
                            ActionEvent ae=new ActionEvent((Object)trayIcon,ActionEvent.ACTION_PERFORMED,"");
                            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(ae);
                        }
                    }
                }
            });
            item9.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(trayIcon!=null)
                    {
                        int ll=0;
                        for(ActionListener al:trayIcon.getActionListeners())
                        {
                            ll++;
                        }
                        if(ll>0)
                        {
                            ActionEvent ae=new ActionEvent((Object)trayIcon,ActionEvent.ACTION_PERFORMED,"Sent");
                            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(ae);
                        }
                    }
                }
            });
            item10.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(trayIcon!=null)
                    {
                        int ll=0;
                        for(ActionListener al:trayIcon.getActionListeners())
                        {
                            ll++;
                        }
                        if(ll>0)
                        {
                            ActionEvent ae=new ActionEvent((Object)trayIcon,ActionEvent.ACTION_PERFORMED,"Drafts");
                            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(ae);
                        }
                    }
                }
            });*/
            trayIcon = new TrayIcon(image, "IITPWebApp", menu);
            trayIcon.setActionCommand("Inbox");
            //trayIcon.setImageAutoSize(true);
            try 
            {
                sysTray.add(trayIcon);
            }
            catch(AWTException e) 
            {
               JOptionPane.showMessageDialog(null,"System Tray Unsupported !","Error:",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    void showMessage(final String title,final String message)
    {
        SwingUtilities.invokeLater(new Runnable()
        {    
            public void run()
            {
                trayIcon.displayMessage(title,message,TrayIcon.MessageType.NONE);
                Thread t3=new Thread()
                {
                    public void run()
                    {
                        if(sound)
                            playSound();
                    }
                };
                t3.start();
            }
        });
    }
    void displayMessage(final String title,final String message)
    {
        SwingUtilities.invokeLater(new Runnable()
        {    
            public void run()
            {
                trayIcon.displayMessage(title,message,TrayIcon.MessageType.NONE);
            }
        });
    }
    void playSound()
    {
        try
        {
            URL url=ClassLoader.getSystemResource("Data/notification.wav");
            if (url==null)
            {
                return;
            }
            audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float)(Math.log(25.0/100)/Math.log(10.0)*20.0);
            gainControl.setValue(dB);
            clip.start();
        }
        catch (Exception e1){}
    }
}