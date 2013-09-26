package iitpwebapp;
import java.net.URL;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import com.sun.awt.AWTUtilities;
import java.awt.GraphicsEnvironment;
import java.awt.GradientPaint;
//import java.sql.*;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.io.IOException;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
//import java.io.FileReader;
//import java.io.BufferedReader;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
//import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
//import java.io.*;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
//import javax.swing.text.html.HTMLEditorKit;
//import javax.swing.text.html.StyleSheet;
//import javax.swing.text.StyledEditorKit;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
//import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
//import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
//import java.util.ArrayList;
public class IITPWebMail
{
    int err,scrollvalue,rt,fstrt,msgno,i,step,steps,height,stepf,i2,i3,i4,mailbox,fstrt1,vis1,vis2,vis3,vis4;    
    Timer alphaChanger,slide,drop1,drop2;
    Thread thread,thread2,refthread;
    HtmlSelect priority;
    WebWindow win1,win2,win3;
    HtmlPage mainpage,mainpage2,mainpage3,mainpage4;
    HtmlPage pg,comp;
    ActionListener mouse;
    Rectangle bounds;
    HtmlForm form;
    JComboBox jcb;
    JSplitPane jspp;
    HtmlCheckBoxInput hcbi;
    float ii;
    JDialog jd,jd2,jd3,jd4,jd5,de;
    List<HtmlTableRow> htr,htr2,htr3;
    HtmlTableRow row;
    String message,domain,filedown,str,gg,aa,bb;
    JTextArea txt,txt2;
    JTextField sub,to,attach;
    JLabel l1,jll,subject,mes,jlabel;
    JButton j1,j2,j3,j4,back,back2,j5,j6,jj1,jj2,b1,b2,b3,b4,b5,add,more,forward;
    JScrollPane jsp,jsp2,jsp3,jsp4,jsp5;
    JPanel jp,jp1,jp2,jp3,jp4,panel,panel2,gp,jpl2,jlab;
    JCheckBox receipt;
    boolean enabled,downloaded,refreshing,sld;
    WebClient webClient;
    KeyEventDispatcher ked,ked2;
    KeyboardFocusManager manager;
    DefaultCredentialsProvider credentialsProvider;
    String proxy,port,puser,ppass,user,pass,comptxt,subtxt,totxt;
    SysTray st;
    UserDataReadWrite ur;
    public IITPWebMail(SysTray s,UserDataReadWrite ur)
    {
        err=0;step=0;steps=20;pg=null;i2=0;i3=0;mailbox=0;fstrt=0;
        de=null;drop1=drop2=null;
        j5=null;mainpage=null;mainpage2=null;mainpage3=null;mainpage4=null;
        this.ur=ur;more=null;
        refreshing=false;
        jd5=null;
        slide=null;
        jd3=null;
        rt=ur.readRefreshTime();
        thread=null;
        refthread=null;
        jd4=null;
        fstrt=0;
        j1=j2=j3=j4=forward=null;
        jd=jd2=null;
        enabled=ur.readEnabled();
        downloaded=false;
        str="";
        totxt=subtxt=comptxt="";
        st=s;
        filedown=null;
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        proxy="";port="";puser="";ppass="";user="";pass="";
        puser=ur.readProxyData().substring(0,ur.readProxyData().indexOf(':'));
        ppass=ur.readProxyData().substring(ur.readProxyData().indexOf(':')+1);
        user=ur.readUserPass().substring(0,ur.readUserPass().indexOf(':'));
        pass=ur.readUserPass().substring(ur.readUserPass().indexOf(':')+1);
        domain=ur.readDomain();
        mainpage=null;
        proxy=ur.intproxy;port=ur.intport;
        if(!(proxy.equals("")||port.equals("")))
        {
            try{
                webClient=new WebClient(BrowserVersion.FIREFOX_3_6,proxy,Integer.parseInt(port));
            }catch(Exception d)
            {
                webClient=new WebClient(BrowserVersion.FIREFOX_3_6);
                JOptionPane.showMessageDialog(null,"Error loading proxy and port.\nPls go to settings and verify.","Error:",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            webClient=new WebClient(BrowserVersion.FIREFOX_3_6);
        }
        if(puser!=""&&ppass!="")
        {
            credentialsProvider=(DefaultCredentialsProvider)webClient.getCredentialsProvider();
            credentialsProvider.addCredentials(puser,ppass);
        }
        try
        {
            webClient.setUseInsecureSSL(true);
        }
        catch(java.security.GeneralSecurityException gse){}
        webClient.setCssEnabled(false);
        webClient.setJavaScriptEnabled(false);
        webClient.setThrowExceptionOnScriptError(true);
        webClient.setPrintContentOnFailingStatusCode(false);
        win1=webClient.getCurrentWindow();
        /*webClient.addWebWindowListener(new WebWindowListener()
        {
            public void webWindowOpened(WebWindowEvent event)
            {
                System.out.println("added");
                //windows.add(event.getWebWindow());
            }
            public void webWindowClosed(WebWindowEvent event)
            {
                System.out.println("closed");
                //windows.add(event.getWebWindow());
            }
            public void webWindowChanged(WebWindowEvent event)
            {
                System.out.println("window changed");
                //windows.add(event.getWebWindow());
            }
            public void webWindowContentChanged(WebWindowEvent event)
            {
                System.out.println("changed content");
                //windows.add(event.getWebWindow());
            }
        });*/
        webClient.setRefreshHandler(new RefreshHandler(){
            public void handleRefresh(Page page, URL url, int arg) throws IOException 
            {}
        });
        st.initialiseApp(this);
    }
    BufferedImage hill=null;
    int jjjjj=0;
    synchronized public HtmlPage login()
    {
        HtmlPage page=null;
        HtmlForm form;
        try
        {
            page=(HtmlPage)webClient.getPage(win1,new WebRequest(new URL("https://"+domain+"/src/login.php?secure_login=yes")));        //122.252.251.251
            //System.out.println(page.asXml());
            form = page.getFormByName("login_form");
            form.getInputByName("login_username").setValueAttribute(user);
            form.getInputByName("secretkey").setValueAttribute(pass);
            HtmlElement kkk=null;
            try
            {
                hill=((HtmlImage)(kkk=(HtmlElement)form.getByXPath("//img").get(1))).getImageReader().read(0);
                //System.out.println(kkk.asXml());
            }catch(Exception mm){}//System.out.println(mm.getMessage());mm.printStackTrace();}
            //final File temp = File.createTempFile("iitpwebappimg",".img");
            //temp.deleteOnExit();
            //hi.saveAs(temp);
            
            final JTextField gg=new JTextField("",20);
            jjjjj=0;
            try{
                try{
                    while(st.jd.isVisible())
                    {
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException ex){}
                    }
                }catch(Exception hj){}
                SwingUtilities.invokeAndWait(new Runnable()
                {
                    public void run()
                    {
                        jd5=new JDialog(){
                            @Override
                            public void setVisible(boolean b)
                            {
                                try{
                                    if(b==false)
                                        manager.removeKeyEventDispatcher(ked2);
                                    else
                                        manager.addKeyEventDispatcher(ked2);
                                }catch(Exception e){}
                                super.setVisible(b);
                            }
                        };
                        ked2=new KeyEventDispatcher(){
                            @Override
                            public boolean dispatchKeyEvent(KeyEvent e)
                            {
                                if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
                                {
                                    jd5.setVisible(false); 
                                    enabled=false;
                                    st.item3.setState(false);
                                    st.trayIcon.setToolTip("IITPWebApp\nDisabled");
                                    jjjjj=1;
                                }
                                return false;
                            }
                        };
                        jd5.setUndecorated(true);
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
                        gg.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ee)
                            {
                                jd5.setVisible(false);
                                jjjjj=1;
                                if(vis1==1)
                                {
                                    jd.setVisible(true);
                                }
                                else
                                if(vis2==1)
                                {
                                    jd2.setVisible(true);
                                }
                                else
                                if(vis3==1)
                                {
                                    jd3.setVisible(true);
                                }
                                else
                                if(vis4==1)
                                {
                                    jd4.setVisible(true);
                                }
                            }
                        });
                        contentPane.setOpaque(false);
                        jd5.setContentPane(contentPane);
                        JLabel l1=new JLabel("<html>Please enter the following captcha.<br/>Press Esc to switch in standby mode.</html>",new ImageIcon(hill),JLabel.CENTER);
                        l1.setVerticalTextPosition(JLabel.TOP);
                        l1.setHorizontalTextPosition(JLabel.CENTER);
                        JPanel jp=new JPanel(new BorderLayout());
                        jp.setOpaque(false);
                        jp.add(l1,BorderLayout.CENTER);
                        jp.add(gg,BorderLayout.PAGE_END);
                        jp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        jd5.add(jp);
                        jd5.pack();
                        jd5.setAlwaysOnTop(true);
                        Dimension dim=jd5.getSize();
                        Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                                
                        if(scrdim.x == 0 && scrdim.y == 0)
                            jd5.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                        else if(scrdim.x == 0)
                            jd5.setLocation((scrdim.width-dim.width),(scrdim.y));
                        else //if(scrdim.y == 0)
                            jd5.setLocation((scrdim.x),(scrdim.height-dim.height));
                        
                        com.sun.awt.AWTUtilities.setWindowOpacity(jd5,Float.valueOf(0));
                        vis1=vis2=vis3=vis4=0;
                        try{
                            if(jd.isVisible())
                            {
                                jd.setVisible(false);
                                vis1++;
                            }
                        }catch(Exception hj){}
                        try{
                            if(jd2.isVisible())
                            {
                                jd2.setVisible(false);
                                vis2++;
                            }
                        }catch(Exception hj){}
                        try{
                            if(jd3.isVisible())
                            {
                                jd3.setVisible(false);
                                vis3++;
                            }
                        }catch(Exception hj){}
                        try{
                            if(jd4.isVisible())
                            {
                                jd4.setVisible(false);
                                vis4++;
                            }
                        }catch(Exception hj){}
                        jd5.setVisible(true);
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
                                com.sun.awt.AWTUtilities.setWindowOpacity(jd5,Float.valueOf(ii));
                            }
                        });
                        alphaChanger.start();
                    }
                });
            }
            catch(InterruptedException e){}
            catch(java.lang.reflect.InvocationTargetException e){}
            while(jjjjj==0)
            {
                try{
                    Thread.sleep(500);
                }catch(InterruptedException ex){}
            }
            //System.out.println("here5");
            try{
                form.getInputByName("captcha").setValueAttribute(gg.getText());
            }catch(Exception e)
            {
                try{
                    form.getInputByName("captcha_response").setValueAttribute(gg.getText());
                }catch(Exception ef){}
            }
            //System.out.println("here6");
            page=(HtmlPage)form.getInputByValue("Login").click();
            try{
                page=(HtmlPage)page.getFrames().get(1).getEnclosedPage();
            }catch(java.lang.IndexOutOfBoundsException e)
            {
            }
            //System.out.println("here7");
            //System.out.println(page.asXml());
        }
        catch(Exception e)
        {
            err++;
            if(err==5)
            {
                st.displayMessage("","Connection Lost.");
                st.trayIcon.setToolTip("IITPWebApp\nNo connection");
            }
            else if(err>30)
            {
                try{
                    Thread.sleep(40000);
                }catch(InterruptedException ee){}
            }
        }
        //enabled=true;
        form=null;
        return page;
    }
    void logout()
    {
        webClient.closeAllWindows();
        enabled=false;
    }
    HtmlElement getMessageInfo(int i)
    {
        return (HtmlElement)mainpage.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/label/../..").get(i);
    }
    HtmlElement getMessageInfo(int i,HtmlPage hp)
    {
        return (HtmlElement)hp.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/label/../..").get(i);
    }
    HtmlElement getUnreadMessageInfo(int i)
    {
        return (HtmlElement)mainpage.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/label/b/../../..").get(i);
    }
    synchronized HtmlPage getRightSide()
    {
        err=0;
        HtmlPage page=null;
        do
        {
            try
            {
                page=webClient.getPage(win1,new WebRequest(new URL("https://"+domain+"/src/right_main.php")));
                //page=webClient.getPage("https://172.16.1.11/src/right_main.php?mailbox=INBOX.Sent");
                //page=webClient.getPage("https://172.16.1.11/src/right_main.php?mailbox=INBOX.Drafts");
                if((page!=null)&&page.getTitleText().indexOf("You must be logged in to access this page")!=-1)
                {
                    //st.showMessage("","You were logged out.\nRe-loging in...");
                    if(!busy)
                    {
                        busy = true;
                        page=loginuser();
                        mainpage = page;
                        busy = false;
                    }
                    else
                    {
                        while(busy)
                        {
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                        }
                        page = mainpage;
                    }
                    try{
                        Thread.sleep(500);
                    }catch(InterruptedException e){}
                }
            }
            catch(Exception e)
            {
                err++;
                if(err==5)
                {
                    st.displayMessage("","Connection Lost.");
                    st.trayIcon.setToolTip("IITPWebApp\nNo connection");
                }
                else if(err>30)
                {
                    try{
                        Thread.sleep(40000);
                    }catch(InterruptedException ee){}
                }
            }
        }
        while(page==null);
        st.trayIcon.setToolTip("IITPWebApp");
        return page;
    }
    synchronized void setProxy()
    {
        if(!(proxy.equals("")||port.equals("")))
        {
            ProxyConfig pgg=new ProxyConfig(proxy,Integer.parseInt(port));
            webClient.setProxyConfig(pgg);
            pgg=null;
        }
        if(puser!=""&&ppass!="")
        {
            credentialsProvider.addCredentials(puser,ppass);
        }
    }
    synchronized void checkUpdates()
    {
        st.trayIcon.setToolTip("IITPWebApp\nUpdating");
        try{
            String updatemsg=getMessageInfo(0).asText();
            str=ur.prefs.get("update",updatemsg);
            updatemsg=null;
        }catch(Exception e)
        {
            return;
        }
        int j=0,k=0,p=0;
        i=0;
        String ss="",mm="";
        do
        {
            try
            {
                ss=getMessageInfo(i++).asText();
                if(ss.indexOf(str)!=-1)
                    p=1;
            }
            catch(Exception e)
            {
                if(i<=1)
                    return;
                break;
            }
            try
            {
                mm=getUnreadMessageInfo(j).asText();
            }
            catch(Exception e)
            {}
            if(ss.equals(mm)&&p==0)
            {
                j++;
            }
            if(i==j&&p==0)
            {
                k++;
            }
            else
            {
                break;
            }
        }
        while(true);
        ss=mm=null;
        Thread noofmsgs=new Thread(){
            public void run()
            {
                do
                {
                    try 
                    {
                        getMessageInfo(i++);
                        if(i>300)
                            break;
                    }
                    catch(Exception e)
                    {
                        break;
                    }
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException ie){}
                }
                while(true);
                
                if(fstrt1==0)
                {
                    fstrt1++;
                    win2=webClient.openWindow(null,"Sent");
                    win3=webClient.openWindow(null,"Drafts");
                    try
                    {
                        mainpage2=webClient.getPage(win3,new WebRequest(new URL("https://"+domain+"/src/right_main.php?mailbox=INBOX.Drafts")));
                    }catch(Exception ee){}
                    st.item10.setEnabled(true);
                    i2=0;
                    do
                    {
                        try 
                        {
                            mainpage2.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/label/../..").get(i2++);
                            if(i2>100)
                                break;
                        }
                        catch(Exception e)
                        {
                            break;
                        }
                    }
                    while(true);
                    try
                    {
                        mainpage3=webClient.getPage(win2,new WebRequest(new URL("https://"+domain+"/src/right_main.php?mailbox=INBOX.Sent")));
                    }catch(Exception ff){}
                    st.item9.setEnabled(true);
                    i3=0;
                    do
                    {
                        try 
                        {
                            mainpage3.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/label/../..").get(i3++);
                            if(i3>100)
                                break;
                        }
                        catch(Exception e)
                        {
                            break;
                        }
                    }
                    while(true);
                }
            }
        };
        noofmsgs.start();
        if(fstrt==0)
        {
            SwingUtilities.invokeLater(new Runnable(){
                public void run()
                {
                    if(j5==null||j6==null||forward==null)
                    {
                        j5=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/Email-Reply-icon.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Email-Reply-icon-pressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Email-Reply-icon-rollover.png")));
                        j5.setActionCommand("Reply");
                        j5.setToolTipText("Reply");
                        //j5.setEnabled(false);
                        forward=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/forward.png")),new ImageIcon(ClassLoader.getSystemResource("Data/forwardpressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/forwardrollover.png")));
                        forward.setActionCommand("Forward");
                        forward.setToolTipText("Forward");
                        //forward.setEnabled(false);
                        j6=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/Email-Delete-icon.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Email-Delete-icon-pressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Email-Delete-icon-rollover.png")));
                        j6.setActionCommand("Delete");
                        j6.setToolTipText("Delete");
                        //j6.setEnabled(false);
                    }
                    ActionListener actionLis=new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            step=0;
                            sld=false;
                            if(jsp!=null)
                                jspp.setDividerLocation(0);
                            if(slide!=null)
                            {
                                slide.stop();
                            }
                            if(downloaded)
                            {
                                try{
                                    filedown.trim();
                                    Desktop.getDesktop().open(new File(filedown));
                                    //Process p = new ProcessBuilder("explorer.exe", "Explorer /e,/root,"+filedown).start();
                                }catch(IOException gg)
                                {
                                    try{
                                        Desktop.getDesktop().open(new File(System.getenv("USERPROFILE")+"\\Downloads\\IITPWebApp\\"));
                                    }catch(IOException ggf){}
                                }
                                downloaded=false;
                            }
                            else
                            {
                            int go1=0,go2=0,go3=0,go4=0,go5=0;
                            if(jd5==null)
                            {
                                go5=1;
                            }
                            else if(!jd5.isVisible())
                            {
                                go5=1;
                            }
                            if(jd4==null)
                            {
                                go4=1;
                            }
                            else if(!jd4.isVisible())
                            {
                                go4=1;
                            }
                            else if(jd4.isVisible())
                            {
                                jd4.setVisible(false);
                                go4=1;
                            }
                            if(jd3==null)
                            {
                                go3=1;
                            }
                            else if(!jd3.isVisible())
                            {
                                go3=1;
                            }
                            else if(jd3.isVisible())
                            {
                                jd3.setVisible(false);
                                go3=1;
                            }
                            if(jd2==null)
                            {
                                go1=1;
                            }
                            else if(!jd2.isVisible())
                            {
                                go1=1;
                            }
                            if(st.jd==null)
                            {
                                go2=1;
                            }
                            else if(!st.jd.isVisible())
                            {
                                go2=1;
                            }
                            if(go1==1&&go2==1&&go3==1&&go4==1&&go5==1)
                            {
                            if(j1==null||j2==null)
                            {
                                j1=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon-pressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon-rollover.png")));
                                j1.setActionCommand("Prev");
                                j1.setToolTipText("Previous");
                                j1.addActionListener(this);
                                j2=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/Button-Next-icon.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Next-icon-pressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Next-icon-rollover.png")));
                                j2.setActionCommand("Next");
                                j2.setToolTipText("Next");
                                j2.addActionListener(this);
                            }
                            if(thread!=null)
                            {
                                thread.interrupt();
                            }
                            try
                            {
                                String cmmd=e.getActionCommand();
                                if(cmmd.equals("Prev"))                            //((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[2]//a[1]").get(0)).click();
                                {
                                    msgno--;
                                }
                                else if(cmmd.equals("Next"))                            //((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[2]//a[2]").get(0)).click();
                                {
                                    msgno++;
                                }
                                else if(cmmd.equals("Sent"))
                                {
                                    webClient.setCurrentWindow(win2);
                                    mainpage4=mainpage3;msgno=0;
                                    i4=i3;
                                }
                                else if(cmmd.equals("Drafts"))
                                {
                                    webClient.setCurrentWindow(win3);
                                    mainpage4=mainpage2;msgno=0;
                                    i4=i2;
                                }
                                else if(cmmd.equals("Inbox"))
                                {
                                    webClient.setCurrentWindow(win1);
                                    mainpage4=mainpage;msgno=0;
                                    i4=i;
                                }
                            }catch(Exception hh){}
                            if(msgno>0)
                            {
                                j1.setEnabled(true);
                            }
                            else
                            {
                                j1.setEnabled(false);
                            }
                            if(msgno<(i4-2))
                            {
                                j2.setEnabled(true);
                            }
                            else
                            {
                                j2.setEnabled(false);
                            }
                            if(j3!=null)
                            {
                                j3.setEnabled(false);
                                for(ActionListener al:j3.getActionListeners())
                                {
                                    j3.removeActionListener(al);
                                    al=null;
                                }
                            }
                            if(more!=null)
                            {
                                more.removeActionListener(mouse);
                            }
                            if(j6!=null)
                            {
                                j6.setEnabled(false);
                            }
                            if(j5!=null)
                            {
                                j5.setEnabled(false);
                            }
                            if(forward!=null)
                            {
                                forward.setEnabled(false);
                            }
                            if(thread!=null)
                                thread.interrupt();
                            thread=null;
                            thread=new Thread(){
                                String mm;
                                public void run()
                                {
                                    try
                                    {
                                        try{
                                            pg=((HtmlElement)mainpage4.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/label/../../td[5]//a").get(msgno)).click();                       
                                            if(interrupted())
                                                return;
                                            if((pg!=null)&&pg.getTitleText().indexOf("You must be logged in to access this page")!=-1)
                                            {
                                                //st.showMessage("","You were logged out.\nRe-loging in...");
                                                if(!busy)
                                                {
                                                    busy = true;
                                                    mainpage = loginuser();
                                                    busy = false;
                                                }
                                                else
                                                {
                                                    while(busy)
                                                    {
                                                        try{
                                                            Thread.sleep(300);
                                                        }catch(InterruptedException e){}
                                                    }
                                                }
                                                //return;
                                            }
                                        }catch(Exception r)
                                        {
                                            if(i4<=2)
                                            {
                                                SwingUtilities.invokeLater(new Runnable(){
                                                    public void run()
                                                    {
                                                        jp1.setVisible(false);
                                                    }
                                                });
                                                JOptionPane.showMessageDialog(jd,"No Mails.","IITPWebApp",JOptionPane.PLAIN_MESSAGE);
                                                ii=0;
                                                alphaChanger = null;
                                                alphaChanger = new Timer(50,new ActionListener() {
                                                    private float incrementer = .10f;
                                                    @Override
                                                    public void actionPerformed(ActionEvent e)
                                                    {
                                                        ii=ii+incrementer;
                                                        if(ii>((float)0.9))
                                                        {   
                                                            alphaChanger.stop();
                                                            jd.setVisible(false);
                                                        }
                                                        try{
                                                            AWTUtilities.setWindowOpacity(jd,Float.valueOf((float)(1-ii)));
                                                        }catch(Exception m){}
                                                    }
                                                });
                                                alphaChanger.start();
                                            }
                                            /*SwingUtilities.invokeLater(new Runnable(){
                                                public void run()
                                                {
                                                    txt.setText("\n\n                        *****CONNECTION PROBLEM*****");
                                                    jp1.setVisible(false);
                                                }
                                            });*/
                                            return;
                                        }
                                        if(interrupted())
                                            return;
                                        try{
                                            message=((HtmlElement)pg.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td").get(0)).asText();
                                            //System.out.println(((HtmlElement)pg.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td").get(0)).asText());
                                            //System.out.println(((HtmlElement)pg.getByXPath("//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td").get(0)).asXml());
                                        }catch(Exception gggg){}
                                        if(interrupted())
                                            return;
                                        SwingUtilities.invokeLater(new Runnable(){
                                            public void run()
                                            {
                                                txt.setText(message);
                                                scrollvalue=0;
                                                try{
                                                    jsp.getViewport().setViewPosition(new Point(0,0));
                                                    jsp.getVerticalScrollBar().setValue(scrollvalue);
                                                    txt.setCaretPosition(0);
                                                    jsp.scrollRectToVisible(new Rectangle());
                                                }catch(Exception r){}
                                                jp1.setVisible(false);
                                                j6.setEnabled(true);
                                                j5.setEnabled(true);
                                                forward.setEnabled(true);
                                                bounds=jsp.getBounds();
                                            }
                                        });
                                        if(interrupted())
                                            return;
                                        htr=null;
                                        htr=(List<HtmlTableRow>)pg.getByXPath("//body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/b/../../../tr[2]/td/table/tbody/tr");
                                        if((htr!=null)&&htr.size()>1)
                                        {
                                            if(interrupted())
                                                return;
                                            j3.addActionListener(new ActionListener(){
                                                public void actionPerformed(ActionEvent ae)
                                                {
                                                    jd2=null;
                                                    panel=null;
                                                    back=null;
                                                    j3.setEnabled(false);
                                                    JLabel j[]=new JLabel[htr.size()-1];
                                                    JButton jb[]=new JButton[htr.size()-1];
                                                    jd2=new JDialog();
                                                    jd2.setUndecorated(true);
                                                    jd2.setSize(376,250);
                                                    jd2.setAlwaysOnTop(true);
                                                    JPanel contentPane2 = new JPanel() {
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
                                                    contentPane2.setOpaque(false);
                                                    jd2.setContentPane(contentPane2);
                                                    panel=new JPanel(new GridBagLayout()){
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
                                                    panel.setOpaque(false);
                                                    back=new JButton("Back");
                                                    back.setEnabled(false);
                                                    back.addActionListener(new ActionListener(){
                                                        public void actionPerformed(ActionEvent aej)
                                                        {
                                                            back.setEnabled(false);
                                                            jd.setVisible(true);
                                                            ii=0;
                                                            alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                                                private float incrementer = .10f;
                                                                @Override
                                                                public void actionPerformed(ActionEvent e)
                                                                {
                                                                    ii=ii+incrementer;
                                                                    if(ii>((float)0.9))
                                                                    {   
                                                                        alphaChanger.stop();
                                                                        AWTUtilities.setWindowOpacity(jd2,Float.valueOf(0));
                                                                        jd2.setVisible(false);
                                                                        j3.setEnabled(true);
                                                                    }
                                                                    AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                                                                    AWTUtilities.setWindowOpacity(jd2,Float.valueOf((float)(1-ii)));
                                                                }
                                                            });
                                                            alphaChanger.start();
                                                        }
                                                    });
                                                    GridBagConstraints gbc=new GridBagConstraints();
                                                    gbc.fill=GridBagConstraints.BOTH;
                                                    gbc.gridheight=1;
                                                    int g=-1;
                                                    for(HtmlTableRow r:htr)
                                                    {
                                                        if(g==-1)
                                                        {
                                                            g++;
                                                            continue;
                                                        }
                                                        row=r;
                                                        j[g]=new JLabel("<html><p>"+row.getCell(0).asText()+"\t"+row.getCell(1).asText()+"\t"+row.getCell(2).asText()+"</p><html>");
                                                        j[g].setPreferredSize(new Dimension(250,50));
                                                        jb[g]=new JButton("Download");
                                                        jb[g].setActionCommand(""+g);
                                                        jb[g].addActionListener(new ActionListener(){
                                                            public void actionPerformed(final ActionEvent ae)
                                                            {
                                                                st.displayMessage("","Downloading...");
                                                                final JButton jbb=(JButton)ae.getSource();
                                                                jbb.setEnabled(false);
                                                                Thread thread2=new Thread()
                                                                { 
                                                                    public void run()
                                                                    {
                                                                        HtmlTableRow crow=null;
                                                                        try
                                                                        {
                                                                            crow=htr.get(Integer.parseInt(ae.getActionCommand())+1);
                                                                            InputStream is=((HtmlElement)pg.getByXPath("//body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/b/../../../tr[2]/td/table/tbody/tr["+(Integer.parseInt(ae.getActionCommand())+2)+"]/td[5]//a").get(0)).click().getWebResponse().getContentAsStream(); 
                                                                            try 
                                                                            {
                                                                                if(!(new File(System.getenv("USERPROFILE")+"\\Downloads\\IITPWebApp")).exists())
                                                                                    (new File(System.getenv("USERPROFILE")+"\\Downloads\\IITPWebApp")).mkdirs();
                                                                                OutputStream out = new FileOutputStream(new File(System.getenv("USERPROFILE")+"\\Downloads\\IITPWebApp\\"+crow.getCell(0).asText()));
                                                                                int read=0; 
                                                                                byte[] bytes = new byte[1024];
                                                                                while((read = is.read(bytes)) != -1) 
                                                                                {
                                                                                    out.write(bytes, 0, read);
                                                                                }
                                                                                is.close();
                                                                                out.flush();
                                                                                out.close();
                                                                                SwingUtilities.invokeLater(new Runnable(){
                                                                                    public void run()
                                                                                    {
                                                                                        jbb.setEnabled(true);
                                                                                    }
                                                                                });
                                                                                filedown=System.getenv("USERPROFILE")+"\\Downloads\\IITPWebApp\\"+crow.getCell(0).asText();
                                                                                st.showMessage("Downloaded:",""+System.getenv("USERPROFILE")+"\\Downloads\\IITPWebApp\\"+crow.getCell(0).asText());
                                                                                is=null;
                                                                                out=null;crow=null;
                                                                                downloaded=true;
                                                                                try{
                                                                                    Thread.sleep(7000);
                                                                                }catch(InterruptedException ie){}
                                                                                downloaded=false;
                                                                            }
                                                                            catch (IOException e)
                                                                            {
                                                                                st.showMessage("","Error in downloading");
                                                                                SwingUtilities.invokeLater(new Runnable(){
                                                                                    public void run()
                                                                                    {
                                                                                        jbb.setEnabled(true);
                                                                                    }
                                                                                });
                                                                            }
                                                                        }
                                                                        catch(Exception io)
                                                                        {
                                                                            st.showMessage("","Error in downloading");
                                                                            SwingUtilities.invokeLater(new Runnable(){
                                                                                public void run()
                                                                                {
                                                                                    jbb.setEnabled(true);
                                                                                }
                                                                            });
                                                                        }
                                                                    }
                                                                };
                                                                thread2.start();
                                                            }
                                                        });
                                                        gbc.gridx=0;gbc.gridy=g;gbc.gridwidth=5;
                                                        panel.add(j[g],gbc);
                                                        gbc.gridx=5;gbc.gridy=g;gbc.gridwidth=1;
                                                        panel.add(jb[g],gbc);
                                                        g++;
                                                    }
                                                    
                                                    gbc.gridx=2;gbc.gridy=g;gbc.gridwidth=1;
                                                    JPanel panel2=new JPanel();
                                                    panel2.add(back);
                                                    panel2.setOpaque(false);
                                                    
                                                    jsp2=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                                    jsp2.setPreferredSize(panel.getPreferredSize());
                                                    jsp2.setOpaque(false);
                                                    
                                                    JPanel panel3=(JPanel)jd2.getContentPane();
                                                    panel3.setOpaque(false);
                                                    panel3.setLayout(new BorderLayout());
                                                    panel3.add(jsp2);
                                                    panel3.add(back,BorderLayout.PAGE_END);
                                                    panel3.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
                                                    
                                                    Dimension dim=jd2.getSize();
                                                    Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                                
                                                    if(scrdim.x == 0 && scrdim.y == 0)
                                                        jd2.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                                                    else if(scrdim.x == 0)
                                                        jd2.setLocation((scrdim.width-dim.width),(scrdim.y));
                                                    else //if(scrdim.y == 0)
                                                        jd2.setLocation((scrdim.x),(scrdim.height-dim.height));
                                                      
                                                    AWTUtilities.setWindowOpacity(jd2,Float.valueOf(0));
                                                    jd2.setVisible(true);
                                                    ii=0;
                                                    alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                                        private float incrementer = .10f;
                                                        @Override
                                                        public void actionPerformed(ActionEvent e)
                                                        {
                                                            ii=ii+incrementer;
                                                            if(ii>((float)0.9))
                                                            {   
                                                                alphaChanger.stop();
                                                                AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                                                jd.setVisible(false);
                                                                back.setEnabled(true);
                                                            }
                                                            AWTUtilities.setWindowOpacity(jd2,Float.valueOf(ii));
                                                            AWTUtilities.setWindowOpacity(jd,Float.valueOf((float)(1-ii)));
                                                        }
                                                    });
                                                    alphaChanger.start();
                                                }
                                            });
                                            SwingUtilities.invokeLater(new Runnable(){
                                                public void run()
                                                {
                                                    j3.setEnabled(true);
                                                }
                                            });
                                        }
                                        if(interrupted())
                                            return;
                                        try{
                                            pg=((HtmlElement)pg.getByXPath("//body/table[3]/tbody/tr/td/table/tbody//td[a=\"more\"]//a").get(0)).click();
                                            if(interrupted())
                                                return;
                                            pg=((HtmlElement)pg.getByXPath("//body/table[3]/tbody/tr/td/table/tbody//td[a=\"more\"]//a").get(1)).click();
                                            if(interrupted())
                                                return;
                                            pg=((HtmlElement)pg.getByXPath("//body/table[3]/tbody/tr/td/table/tbody//td[a=\"more\"]//a").get(2)).click();
                                        }catch(Exception e){}
                                        if(interrupted())
                                            return;
                                        try{
                                            htr3=(List<HtmlTableRow>)pg.getByXPath("//body/table[3]/tbody/tr/td/table/tbody/tr");
                                            mm="<html><pre>";
                                            for(HtmlTableRow r:htr3)
                                            {
                                                if(interrupted())
                                                    return;
                                                String kk=r.getCell(0).asText().trim();
                                                if(kk.indexOf("Subject")!=-1)
                                                {
                                                    gg=r.getCell(1).asText()+"        ";
                                                }
                                                else if(kk.indexOf("From")!=-1)
                                                {
                                                    String input=r.getCell(1).asXml();
                                                    input=refine(input);
                                                    if(input.length()>43)
                                                    {
                                                        input+="        ";
                                                        if(!slide.isRunning())
                                                        {
                                                            aa=input;
                                                            slide.start();
                                                        }
                                                    }
                                                    aa=input;
                                                }
                                                else if(kk.indexOf("Date")!=-1)
                                                {
                                                    bb=refine(r.getCell(1).asText());
                                                    SwingUtilities.invokeLater(new Runnable(){
                                                        public void run(){
                                                            if(aa.length()<=43)
                                                                l1.setText("<html><pre>&nbsp;From: "+aa+"<br>&nbsp;Received: "+bb+"</pre></html>");
                                                        }
                                                    });
                                                }
                                                else if(kk.indexOf("To")!=-1)
                                                {
                                                    mm=mm+"&nbsp;"+kk+"&nbsp;"+refine(r.getCell(1).asXml())+"<br>";
                                                }
                                                else if(kk.indexOf("Cc")!=-1)
                                                {
                                                    mm=mm+"&nbsp;"+kk+"&nbsp;"+refine(r.getCell(1).asXml())+"<br>";
                                                }
                                                else if(kk.indexOf("Bcc")!=-1)
                                                {
                                                    mm=mm+"&nbsp;"+kk+"&nbsp;"+refine(r.getCell(1).asXml())+"<br>";
                                                }
                                                else if(kk.indexOf("Priority")!=-1)
                                                {
                                                    if(r.getCell(1).asText().indexOf("High")!=-1)
                                                        mes.setText("<html><pre><font size=2 color=\"red\">Mail : "+(msgno+1)+"</font>&nbsp;</pre></html>");
                                                }
                                            }
                                            mm+="</pre></html>";
                                            SwingUtilities.invokeLater(new Runnable(){
                                                public void run(){
                                                    jlabel.setText(mm);
                                                    jsp5.setPreferredSize(jlabel.getPreferredSize());
                                                    stepf=0;
                                                }
                                            });
                                            more.addActionListener(mouse);
                                        }catch(Exception g){}
                                    }
                                    catch(Exception e){}
                                }
                                String refine(String input)
                                {
                                    input=input.replace("<td align=\"left\" valign=\"top\" width=\"80%\">","");
                                    input=input.replace("</td>","");
                                    input=input.trim();
                                    input=input.replace((char)32+"","+12345+");
                                    input=input.replace('\u0085',(char)32);
                                    input=input.replace('\u2028',(char)32);
                                    input=input.replace('\u2029',(char)32);
                                    input=input.replaceAll("\\s+","");
                                    input=input.replace("(less)","");
                                    try{
                                        input=input.substring(0,input.indexOf("("))+input.substring(input.indexOf(")")+1);
                                    }catch(Exception j){}
                                    input=input.replace("<br/>","<br/>&nbsp;&nbsp;&nbsp;");
                                    input=input.replace("+12345+",(char)32+"");
                                    input=input.replace("&lt;","(");
                                    input=input.replace("&gt;",")");
                                    input=input.replace("&amp;","&");
                                    input=input.replace("&quot;","\"");
                                    input=input.replace("\"","");
                                    return input;
                                }
                            };
                            thread.start();
                            if(jd==null)
                            {
                                jd=new JDialog(){
                                    @Override
                                    public void setVisible(boolean b)
                                    {
                                        try{
                                            if(b==false)
                                                manager.removeKeyEventDispatcher(ked);
                                            else
                                                manager.addKeyEventDispatcher(ked);
                                        }catch(Exception e){}
                                        super.setVisible(b);
                                    }
                                };
                                jd.setUndecorated(true);
                                jd.setSize(376,250);
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
                                
                                /*HTMLEditorKit kit = new HTMLEditorKit();
                                StyleSheet styleSheet = kit.getStyleSheet();
                                styleSheet.addRule("A {color:red}"); //change links to red*/
                                
                                txt=new JTextArea(5,20);
                                txt.setLineWrap(true);
                                txt.setWrapStyleWord(true);
                                //txt.setFont(new Font("Jokerman",Font.PLAIN,28));
                                //txt.setForeground(Color.ORANGE);
                                txt.setEditable(false);
                                jsp=new JScrollPane(txt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                jsp.setPreferredSize(txt.getPreferredSize());
                                
                                jlab=new JPanel(new BorderLayout(0,0));
                                jlab.setOpaque(false);
                                jlabel=new JLabel();
                                jlabel.setOpaque(false);
                                jlabel.setHorizontalTextPosition(JLabel.LEFT);
                                jsp5=new JScrollPane(jlabel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                jsp5.setOpaque(false);
                                jsp5.getViewport().setOpaque(false);
                                //jsp5.setBorder(BorderFactory.createEmptyBorder(0,0,0,4));
                                jsp5.setBorder(BorderFactory.createEmptyBorder(0,0,0,4));
                                jsp5.setViewportBorder(BorderFactory.createEmptyBorder());    
                                                        
                                jlab.add(jsp5,BorderLayout.PAGE_END);
                                jspp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jlab,jsp);
                                jspp.setOpaque(false);
                                jspp.setDividerLocation(0);
                                jspp.setDividerSize(0);
                                jspp.setContinuousLayout(true);
                                
                                jp1=(JPanel)jd.getGlassPane();
                                jp1.setLayout(new BorderLayout());
                                jp1.add(new Waiting(),BorderLayout.CENTER);
                    
                                jp2=new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));
                                jp2.setOpaque(false);
                                j3=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/attachment.png")),new ImageIcon(ClassLoader.getSystemResource("Data/attachmentpressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/attachmentrollover.png")));
                                j3.setActionCommand("Attachments");
                                j3.setToolTipText("Attachments");
                                j3.setEnabled(false);
                                j4=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/close.png")),new ImageIcon(ClassLoader.getSystemResource("Data/closepressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/closerollover.png")));
                                j4.setActionCommand("Close");
                                j4.setToolTipText("Close");
                                j4.addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent gg)
                                    {
                                        //manager.removeKeyEventDispatcher(ked);
                                        try{
                                            thread.interrupt();
                                            slide.stop();
                                        }catch(Exception e){}
                                        jp1.setVisible(false);
                                        ii=0;
                                        alphaChanger = null;
                                        alphaChanger = new Timer(50,new ActionListener() {
                                            private float incrementer = .10f;
                                            @Override
                                            public void actionPerformed(ActionEvent e)
                                            {
                                                ii=ii+incrementer;
                                                if(ii>((float)0.9))
                                                {   
                                                    alphaChanger.stop();
                                                    jd.setVisible(false);
                                                }
                                                try{
                                                    AWTUtilities.setWindowOpacity(jd,Float.valueOf((float)(1-ii)));
                                                }catch(Exception m){}
                                            }
                                        });
                                        alphaChanger.start();
                                    }
                                });
                                jp2.add(j1);
                                jp2.add(j2);
                                jp2.add(j3);
                                jp2.add(forward);
                                jp2.add(j5);
                                jp2.add(j6);
                                jp2.add(j4);
               
                                l1=new JLabel();
                                l1.setHorizontalTextPosition(JLabel.LEFT);
                                subject=new JLabel();
                                subject.setHorizontalTextPosition(JLabel.LEFT);
                                mes=new JLabel();
                                mes.setHorizontalTextPosition(JLabel.RIGHT);
                                
                                more=new JButton(new ImageIcon(ClassLoader.getSystemResource("Data/more.png")));
                                more.setFocusPainted(false);
                                more.setRolloverEnabled(true);
                                more.setRolloverIcon(new ImageIcon(ClassLoader.getSystemResource("Data/morerollover.png")));
                                //setPressedIcon(pressed);
                                more.setBorderPainted(false);
                                more.setContentAreaFilled(false);
                                //more.setEnabled(false);
                                bounds=jsp.getBounds();
                                mouse=new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        if(sld)
                                        {
                                            sld=!sld;
                                            mouseExited();
                                        }
                                        else
                                        {
                                            sld=!sld;
                                            mouseEntered();
                                        }
                                    }
                                    public void mouseEntered()
                                    {
                                        if(drop2!=null)
                                            drop2.stop();
                                        if(drop1==null)
                                        {
                                            drop1=new Timer(20,new ActionListener(){
                                                Rectangle b=jsp.getBounds();
                                                public void actionPerformed(ActionEvent e) {
                                                    bounds=jsp.getBounds();
                                                    int shift=bounds.height/steps;
                                                    step++;
                                                    if(step>=steps)
                                                    {
                                                        drop1.stop();stepf=1;
                                                        jspp.setDividerLocation(bounds.y+1);
                                                        jsp5.setPreferredSize(new Dimension(jsp5.getWidth()-6,jspp.getDividerLocation()));
                                                    }
                                                    else
                                                        jspp.setDividerLocation(bounds.y+shift);
                                                    if((bounds.y-b.y+shift*2.5)>=jsp5.getHeight()&&stepf==0)
                                                    {
                                                        drop1.stop();
                                                    }
                                                }
                                            });
                                        }
                                        drop1.start();
                                    }
                                    public void mouseExited()
                                    {
                                        if(drop1!=null)
                                            drop1.stop();
                                        if(drop2==null)
                                        {
                                            drop2=new Timer(20,new ActionListener(){
                                                public void actionPerformed(ActionEvent e) {
                                                    bounds=jsp.getBounds();
                                                    int shift=bounds.height/steps;
                                                    jspp.setDividerLocation(bounds.y-shift);
                                                    if(jspp.getDividerLocation()<=shift)
                                                    {
                                                        jspp.setDividerLocation(0);
                                                        //jsp5.setPreferredSize(jlabel.getPreferredSize());
                                                        drop2.stop();
                                                        step=0;
                                                    }   
                                                }
                                            });
                                        }
                                        drop2.start();
                                    }
                                };
                                
                                JPanel jpl=new JPanel(new BorderLayout(0,0));
                                jpl.setOpaque(false);
                                jpl.add(l1,BorderLayout.LINE_START);
                                jpl.add(mes,BorderLayout.LINE_END);
                                jpl2=new JPanel(new BorderLayout(0,0));
                                jpl2.setOpaque(false);
                                jpl2.add(jpl,BorderLayout.PAGE_START);
                                jpl2.add(subject,BorderLayout.LINE_START);
                                jpl2.add(more,BorderLayout.LINE_END);
                                
                                jp=(JPanel)jd.getContentPane();
                                jp.setLayout(new BorderLayout());
                                jp.add(jpl2,BorderLayout.PAGE_START);
                                jp.add(jspp,BorderLayout.CENTER);
                                jp.add(jp2,BorderLayout.PAGE_END);
                                jp.setBorder(new CompoundBorder(new LineBorder(Color.black,1,true),BorderFactory.createEmptyBorder(0,5,0,5)));
                                //jp.setBorder(new CompoundBorder(new CurvedBorder(10,Color.black),BorderFactory.createEmptyBorder(0,5,0,5)));
                                    
                                ked=new KeyEventDispatcher(){
                                    @Override
                                    public boolean dispatchKeyEvent(KeyEvent e)
                                    {
                                        if (e.getID() == KeyEvent.KEY_RELEASED&&jd.isVisible()&&((de==null)||de.isVisible()==false)) 
                                        {
                                            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                                            {
                                                j2.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                                            {
                                                j1.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_DELETE)
                                            {
                                                if(j6.isEnabled())
                                                    j6.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
                                            {
                                                j4.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_A)
                                            {
                                                j3.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_R)
                                            {
                                                j5.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_F)
                                            {
                                                forward.doClick();
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_M)
                                            {
                                                more.doClick();
                                            }
                                        }
                                        else if (e.getID() == KeyEvent.KEY_PRESSED&&jd.isVisible()&&((de==null)||de.isVisible()==false)) 
                                        {
                                            if(e.getKeyCode()==KeyEvent.VK_UP)
                                            {
                                                if(scrollvalue>0)
                                                {
                                                    scrollvalue-=5;
                                                    try{
                                                        jsp.getVerticalScrollBar().setValue(scrollvalue);
                                                    }catch(Exception g)
                                                    {
                                                        scrollvalue+=5;
                                                    }
                                                }
                                            }
                                            else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                                            {
                                                if(scrollvalue<jsp.getVerticalScrollBar().getMaximum())
                                                {
                                                    scrollvalue+=5;
                                                    try{
                                                        jsp.getVerticalScrollBar().setValue(scrollvalue);
                                                    }catch(Exception g)
                                                    {
                                                        scrollvalue-=5;
                                                    }
                                                }
                                            }
                                        }
                                        return false;
                                    }
                                };
                            }
                            if(jd.isVisible()==false)
                            {
                                Dimension dim=jd.getSize();
                                Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                                
                                if(scrdim.x == 0 && scrdim.y == 0)
                                    jd.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                                else if(scrdim.x == 0)
                                    jd.setLocation((scrdim.width-dim.width),(scrdim.y));
                                else //if(scrdim.y == 0)
                                    jd.setLocation((scrdim.x),(scrdim.height-dim.height));
                                    
                                AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                jd.setVisible(true);
                                ii=0;
                                alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                    private float incrementer = .10f;
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        ii=ii+incrementer;
                                        if(ii>((float)0.9))
                                        {
                                            alphaChanger.stop();
                                            jd.requestFocus();
                                        }
                                        AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                                    }
                                });
                                alphaChanger.start();
                            }
                            try
                            {
                                final HtmlTableRow tr=(HtmlTableRow)getMessageInfo(msgno,mainpage4);
                                mes.setText("<html><pre><font size=2>Mail : "+(msgno+1)+"</font>&nbsp;</pre></html>");
                                if(mainpage4==mainpage)
                                    aa=tr.getCell(1).asText();
                                else
                                    aa="";
                                bb=tr.getCell(2).asText();
                                if(aa.length()<=43)
                                    l1.setText("<html><pre>&nbsp;From: "+aa+"<br>&nbsp;Received: "+bb+"</pre></html>");
                                gg=tr.getCell(4).asText();
                                if(gg.length()<=32)    
                                    subject.setText("<html><pre>&nbsp;Subject: "+gg+"</pre></html>");
                                if(gg.length()>32||aa.length()>43)
                                {
                                    aa+="        ";
                                    gg+="        ";
                                }
                                if(slide==null)
                                {
                                    slide=new Timer(140,new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            try{
                                                if(gg.length()>40)
                                                {
                                                    subject.setText("<html><pre>&nbsp;Subject: "+gg.substring(0,32)+"</pre></html>");
                                                    gg=gg.substring(1)+gg.charAt(0);
                                                }
                                                if(aa.length()>51)
                                                {
                                                    l1.setText("<html><pre>&nbsp;From: "+aa.substring(0,43)+"<br>&nbsp;Received: "+bb+"</pre></html>");
                                                    aa=aa.substring(1)+aa.charAt(0);
                                                }
                                            }catch(Exception jj)
                                            {slide.stop();}
                                        }
                                    });
                                    slide.setInitialDelay(0);
                                }
                                if(gg.length()>32||aa.length()>43)
                                {    
                                    slide.start();
                                }
                            }
                            catch(Exception fff){}
                            jp1.setVisible(true);
                            txt.setText("");
                            bounds=jsp.getBounds();
                            }
                            }
                        }
                    };
                    st.trayIcon.addActionListener(actionLis);
                    st.item8.addActionListener(actionLis);
                    st.item9.addActionListener(actionLis);
                    st.item10.addActionListener(actionLis);
                    st.item8.setEnabled(true);
                    j6.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae)
                        {
                            jll=new JLabel("Confirm deletion ?");
                            de=new JDialog();
                            de.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                            de.setUndecorated(true);
                            de.setAlwaysOnTop(true);
                            jj1=new JButton("Yes");jj2=new JButton("No");
                            jj1.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent hh)
                                {
                                    de.setVisible(false);
                                    de.remove(jj1);
                                    de.remove(jj2);
                                    jll.setText("  Deleting...");
                                    ImageIcon iic=new ImageIcon(ClassLoader.getSystemResource("Data/loader.gif"));
                                    jll.setIcon(iic);
                                    de.pack();
                                    de.setLocationRelativeTo(jd);
                                    Thread del=new Thread(){
                                        public void run()
                                        {
                                            try
                                            {
                                                mainpage4=((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[1]//a[3]").get(0)).click();
                                                if((mainpage4!=null)&&mainpage4.getTitleText().indexOf("You must be logged in to access this page")!=-1)
                                                {
                                                    //st.showMessage("","You were logged out.\nRe-loging in...");
                                                    if(!busy)
                                                    {
                                                        busy = true;
                                                        mainpage = loginuser();
                                                        busy = false;
                                                    }
                                                    else
                                                    {
                                                        while(busy)
                                                        {
                                                            try{
                                                                Thread.sleep(300);
                                                            }catch(InterruptedException e){}
                                                        }
                                                    }
                                                    //mainpage=getRightSide();
                                                }
                                            }catch(Exception dd)
                                            {
                                                SwingUtilities.invokeLater(new Runnable(){
                                                    public void run()
                                                    {
                                                        de.setVisible(false);
                                                        st.showMessage("","Error in deletion.\nPlease try later.");
                                                    }
                                                });
                                            }
                                            if(msgno>=(i4-2))
                                            {
                                                msgno--;
                                            }
                                            i4--;
                                            SwingUtilities.invokeLater(new Runnable(){
                                                public void run()
                                                {
                                                    ActionEvent ae=new ActionEvent((Object)st.trayIcon,ActionEvent.ACTION_PERFORMED,"Delete");
                                                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(ae);
                                                    //st.trayIcon.dispatchEvent(ae);  //can be used too.
                                                    ii=0;
                                                    alphaChanger = null;alphaChanger = new Timer(35,new ActionListener() {
                                                        private float incrementer = .10f;
                                                        @Override
                                                        public void actionPerformed(ActionEvent e)
                                                        {
                                                            ii=ii+incrementer;
                                                            if(ii>((float)0.9))
                                                            {   
                                                                alphaChanger.stop();
                                                                de.setVisible(false);
                                                                //st.showMessage("","Deleted");
                                                            }
                                                            AWTUtilities.setWindowOpacity(de,Float.valueOf((float)(1-ii)));
                                                        }
                                                    });
                                                    alphaChanger.start();
                                                }
                                            });
                                        }
                                    };
                                    del.start();
                                    de.setVisible(true);
                                }
                            });
                            jj2.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent hh)
                                {
                                    de.setVisible(false);
                                }
                            });
                            JPanel contentPane = new JPanel(new BorderLayout()) {
                                @Override
                                protected void paintComponent(Graphics grphcs) {
                                    Graphics2D g2d = (Graphics2D) grphcs;
                                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                                    GradientPaint gp = new GradientPaint(getWidth()/4, 0,Color.white, getWidth(), 0,getBackground().darker());
                                    g2d.setPaint(gp);
                                    g2d.fillRect(0, 0, getWidth(), getHeight());
                                    super.paintComponent(grphcs);
                                }
                            };
                            contentPane.setOpaque(false);
                            contentPane.add(jll,BorderLayout.PAGE_START);
                            contentPane.add(jj1,BorderLayout.WEST);
                            contentPane.add(jj2,BorderLayout.EAST);
                            contentPane.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
                            de.setContentPane(contentPane);
                            de.pack();
                            de.setLocationRelativeTo(jd);
                            de.setVisible(true);
                        }
                    });
                    ActionListener actionListener=new ActionListener(){
                        public void actionPerformed(final ActionEvent ae)
                        {
                            int go1=0,go2=0,go3=0,go4=0,go5=0;
                            if(ae.getActionCommand().equals("Compose"))
                            {
                                if(jd==null)
                                {
                                    go3=1;
                                }
                                else if(!jd.isVisible())
                                {
                                    go3=1;
                                }
                                else if(jd.isVisible())
                                {
                                    jd.setVisible(false);
                                    go3=1;
                                }
                            }
                            else
                            {
                                go3=1;
                            }
                            if(jd2==null)
                            {
                                go1=1;
                            }
                            else if(!jd2.isVisible())
                            {
                                go1=1;
                            }
                            else if(jd2.isVisible())
                            {
                                jd2.setVisible(false);
                                go1=1;
                            }
                            if(st.jd==null)
                            {
                                go2=1;
                            }
                            else if(!st.jd.isVisible())
                            {
                                go2=1;
                            }
                            if(jd4==null)
                            {
                                go4=1;
                            }
                            else if(!jd4.isVisible())
                            {
                                go4=1;
                            }
                            if(jd5==null)
                            {
                                go5=1;
                            }
                            else if(!jd5.isVisible())
                            {
                                go5=1;
                            }
                            if(go1==1&&go2==1&&go3==1&&go4==1&&go5==1)
                            {
                            thread2=new Thread(){
                                public void run()
                                {
                                    comp=null;
                                    //do
                                    //{
                                        try
                                        {
                                            if(ae.getActionCommand().equals("Reply"))
                                                comp=((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[3]//a[3]").get(0)).click();
                                            else if(ae.getActionCommand().equals("Forward"))
                                                comp=((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[3]//a[1]").get(0)).click();
                                            else
                                                comp=((HtmlElement)mainpage.getByXPath("//body/table[1]/tbody/tr[2]/td[1]//a[1]").get(0)).click();
                                            if((comp!=null)&&comp.getTitleText().indexOf("You must be logged in to access this page")!=-1)
                                            {
                                                //st.showMessage("","You were logged out.\nRe-loging in...");
                                                if(!busy)
                                                {
                                                    busy = true;
                                                    mainpage = loginuser();
                                                    busy = false;
                                                }
                                                else
                                                {
                                                    while(busy)
                                                    {
                                                        try{
                                                            Thread.sleep(300);
                                                        }catch(InterruptedException e){}
                                                    }
                                                }
                                                //mainpage=getRightSide();
                                                comp=null;
                                                if(ae.getActionCommand().equals("Reply"))
                                                    comp=((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[3]//a[3]").get(0)).click();
                                                else if(ae.getActionCommand().equals("Forward"))
                                                    comp=((HtmlElement)pg.getByXPath("//body/table[2]/tbody/tr/td[3]//a[1]").get(0)).click();
                                                else
                                                    comp=((HtmlElement)mainpage.getByXPath("//body/table[1]/tbody/tr[2]/td[1]//a[1]").get(0)).click();
                                            }
                                        }catch(Exception dd)
                                        {
                                            return;
                                            //try{
                                                //Thread.sleep(3000);
                                            //}catch(InterruptedException ee){}
                                        }
                                    //}while(comp==null);
                                    form = comp.getFormByName("compose");
                                    SwingUtilities.invokeLater(new Runnable(){
                                        public void run()
                                        {
                                            try
                                            {
                                                //if(ae.getActionCommand().equals("Reply"))
                                                {
                                                    subtxt=form.getInputByName("subject").getValueAttribute();
                                                    totxt=form.getInputByName("send_to").getValueAttribute();
                                                    comptxt=form.getTextAreaByName("body").getText();
                                                }
                                                to.setText(totxt);
                                                sub.setText(subtxt);
                                                txt2.setText(comptxt);
                                                checkUploaded();
                                                receipt.setSelected(false);
                                            }catch(Exception j){}
                                            to.setEditable(true);
                                            sub.setEditable(true);
                                            txt2.setEditable(true);
                                            b1.setEnabled(true);
                                            b5.setEnabled(true);
                                            b2.setEnabled(true);
                                            gp.setVisible(false);
                                            receipt.setEnabled(true);
                                            jcb.setEnabled(true);
                                            to.requestFocus();
                                        }
                                    });
                                }
                            };
                            if(true)
                            {
                                jd3=new JDialog();
                                jd3.setUndecorated(true);
                                jd3.setSize(376,250);
                                jd3.setAlwaysOnTop(true);
                            
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
                                jd3.setContentPane(contentPane);
                                gp=(JPanel)jd3.getGlassPane();
                                gp.setLayout(new BorderLayout());
                                JLabel jlabel=new JLabel(new ImageIcon(ClassLoader.getSystemResource("Data/loading.gif")));
                                gp.add(jlabel,BorderLayout.CENTER);
                                
                                txt2=new JTextArea(5,20);
                                txt2.setLineWrap(true);
                                txt2.setWrapStyleWord(true);
                                //txt2.setFont(new Font("Jokerman",Font.PLAIN,28));
                                //txt2.setForeground(Color.ORANGE);
                                txt2.setEditable(false);
                                jsp3=new JScrollPane(txt2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                jsp3.setPreferredSize(txt2.getPreferredSize());
                            
                                JLabel totext=new JLabel("<html><pre>&nbsp;To  : </pre></html>"),subtext=new JLabel("<html><pre>&nbsp;Sub : </pre></html>");
                                totext.setHorizontalTextPosition(JLabel.LEFT);
                                subtext.setHorizontalTextPosition(JLabel.LEFT);
                                to=new JTextField("");
                                to.setColumns(10);
                                to.setEditable(false);
                                sub=new JTextField("");
                                sub.setColumns(10);
                                sub.setEditable(false);
                                JPanel to1=new JPanel();
                                to1.setOpaque(false);
                                to1.setLayout(new BorderLayout());
                                to1.add(totext,BorderLayout.WEST);
                                to1.add(to,BorderLayout.CENTER);
                                JPanel sub1=new JPanel();
                                sub1.setOpaque(false);
                                sub1.setLayout(new BorderLayout());
                                sub1.add(subtext,BorderLayout.WEST);
                                sub1.add(sub,BorderLayout.CENTER);
                            
                                JPanel head=new JPanel();
                                head.setOpaque(false);
                                head.setLayout(new BorderLayout());
                                head.add(to1,BorderLayout.NORTH);
                                head.add(sub1,BorderLayout.SOUTH);
                            
                                JPanel jp44=new JPanel(new FlowLayout(FlowLayout.CENTER,2,5));
                                jp44.setOpaque(false);
                                jcb=new JComboBox(new String[]{"High","Normal","Low"});
                                jcb.setSelectedIndex(1);
                                jcb.setEnabled(false);
                                receipt=new JCheckBox("Receipt",false);
                                receipt.setEnabled(false);
                                b1=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/attachment.png")),new ImageIcon(ClassLoader.getSystemResource("Data/attachmentpressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/attachmentrollover.png")));
                                b1.setActionCommand("Attach Files");
                                b1.setToolTipText("Attach Files");
                                b1.setEnabled(false);
                                b1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae)
                                    {
                                        b1.setEnabled(false);
                                        jd4=new JDialog();
                                        jd4.setUndecorated(true);
                                        jd4.setSize(376,250);
                                        jd4.setAlwaysOnTop(true);
                                        JPanel contentPane2 = new JPanel() {
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
                                        contentPane2.setOpaque(false);  
                                        jd4.setContentPane(contentPane2);       
                                        panel2=new JPanel(new GridBagLayout()){
                                            @Override
                                            protected void paintComponent(Graphics grphcs) {
                                                Graphics2D g2d = (Graphics2D) grphcs;
                                                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                                                GradientPaint gp = new GradientPaint(0, 0,getBackground().brighter().brighter(), getWidth(), getHeight(),getBackground().darker().darker());
                                                g2d.setPaint(gp);
                                                g2d.fillRect(0, 0, getWidth(), getHeight());
                                                super.paintComponent(grphcs);
                                            }
                                        };
                                        panel2.setOpaque(false);
                                        checkUploaded();
                                        back2=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon-pressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon-rollover.png")));
                                        back2.setEnabled(false);
                                        back2.addActionListener(new ActionListener(){
                                            public void actionPerformed(ActionEvent aej)
                                            {
                                                back2.setEnabled(false);
                                                jd3.setVisible(true);
                                                ii=0;
                                                alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {  
                                                    private float incrementer = .10f;   
                                                    @Override
                                                    public void actionPerformed(ActionEvent e)
                                                    {
                                                        ii=ii+incrementer;
                                                        if(ii>((float)0.9))
                                                        {   
                                                            alphaChanger.stop();
                                                            AWTUtilities.setWindowOpacity(jd4,Float.valueOf(0));
                                                            jd4.setVisible(false);
                                                            b1.setEnabled(true);
                                                            if(b3!=null)
                                                                b3.setEnabled(true);
                                                        }
                                                        AWTUtilities.setWindowOpacity(jd3,Float.valueOf(ii));
                                                        AWTUtilities.setWindowOpacity(jd4,Float.valueOf((float)(1-ii)));
                                                    }
                                                });
                                                alphaChanger.start();
                                            }
                                        });
                                        final JPanel panel3=new JPanel(new FlowLayout());
                                        add=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/add.png")),new ImageIcon(ClassLoader.getSystemResource("Data/addpressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/addrollover.png")));
                                        add.setToolTipText("Attach a file");
                                        add.addActionListener(new ActionListener(){
                                            public void actionPerformed(final ActionEvent ae)
                                            {
                                                Thread thread2=new Thread()
                                                { 
                                                    public void run()
                                                    {
                                                        final JFileChooser fc = new JFileChooser();
                                                        int returnVal = fc.showOpenDialog(jd4);
                                                        if (returnVal == JFileChooser.APPROVE_OPTION) 
                                                        {
                                                            if ((long)(20*1024*1024)>=(long)(fc.getSelectedFile().length())) 
                                                            {
                                                                final JLabel label=new JLabel(new ImageIcon(ClassLoader.getSystemResource("Data/upload.gif")));
                                                                try{
                                                                    SwingUtilities.invokeLater(new Runnable(){
                                                                        public void run()
                                                                        {
                                                                            attach.setText(fc.getSelectedFile().getAbsolutePath());
                                                                            add.setEnabled(false);
                                                                            panel3.add(label);
                                                                            panel3.updateUI();
                                                                        }
                                                                    });
                                                                    form=comp.getFormByName("compose");
                                                                    HtmlFileInput fileInput = form.getInputByName("attachfile");
                                                                    fileInput.setValueAttribute(fc.getSelectedFile().getAbsolutePath());
                                                                    comp=(HtmlPage)form.getInputByName("attach").click();
                                                                    form=comp.getFormByName("compose");
                                                                }catch(Exception l)
                                                                {
                                                                    st.showMessage("","Error occured while uploading file.\nPls try again.");
                                                                }
                                                                SwingUtilities.invokeLater(new Runnable(){
                                                                    public void run()
                                                                    {
                                                                        add.setEnabled(true);
                                                                        panel3.remove(label);
                                                                        panel3.updateUI();
                                                                        checkUploaded();
                                                                        panel2.updateUI();
                                                                    }
                                                                });
                                                            }
                                                            else 
                                                            {
                                                                // Exceeds 20Mb size limit.
                                                                JOptionPane.showMessageDialog(jd4,"The selected file exceeds the 20Mb size limit.","Error:",JOptionPane.PLAIN_MESSAGE);
                                                                return;
                                                            }
                                                        } 
                                                        else 
                                                        {
                                                            return;
                                                        }
                                                    }
                                                };
                                                thread2.start();
                                            }
                                        });
                                        add.setEnabled(false);
                                        attach=new JTextField(18);
                                        attach.setEditable(false);
                                        panel3.add(back2);
                                        panel3.add(attach);
                                        panel3.add(add);
                                        panel3.setOpaque(false);
    
                                        jsp4=new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                        jsp4.setPreferredSize(panel2.getPreferredSize());
                                        jsp4.setOpaque(false);
    
                                        JPanel panel4=(JPanel)jd4.getContentPane();
                                        panel4.setOpaque(false);
                                        panel4.setLayout(new BorderLayout());
                                        panel4.add(jsp4);
                                        panel4.add(panel3,BorderLayout.PAGE_END);
                                        panel4.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(5,5,0,5)));
    
                                        Dimension dim=jd4.getSize();
                                        Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                                
                                        if(scrdim.x == 0 && scrdim.y == 0)
                                            jd4.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                                        else if(scrdim.x == 0)
                                            jd4.setLocation((scrdim.width-dim.width),(scrdim.y));
                                        else //if(scrdim.y == 0)
                                            jd4.setLocation((scrdim.x),(scrdim.height-dim.height));
                                    
                                        AWTUtilities.setWindowOpacity(jd4,Float.valueOf(0));
                                        jd4.setVisible(true);
                                        if(b3!=null)
                                            b3.setEnabled(false);
                                        ii=0;
                                        alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                            private float incrementer = .10f;
                                            @Override
                                            public void actionPerformed(ActionEvent e)
                                            {
                                                ii=ii+incrementer;
                                                if(ii>((float)0.9))
                                                {   
                                                    alphaChanger.stop();
                                                    AWTUtilities.setWindowOpacity(jd3,Float.valueOf(0));
                                                    jd3.setVisible(false);
                                                    back2.setEnabled(true);
                                                    add.setEnabled(true);
                                                }
                                                AWTUtilities.setWindowOpacity(jd4,Float.valueOf(ii));
                                                AWTUtilities.setWindowOpacity(jd3,Float.valueOf((float)(1-ii)));
                                            }
                                        });
                                        alphaChanger.start();
                                    }
                                });
                                b2=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/send.png")),new ImageIcon(ClassLoader.getSystemResource("Data/sendpressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/sendrollover.png")));
                                b2.setActionCommand("Send");
                                b2.setToolTipText("Send");
                                b2.setEnabled(false);
                                b2.addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent gg)
                                    {
                                        if(to.getText().equals(""))
                                        {   
                                            JOptionPane.showMessageDialog(jd3,"Please enter the \'To\' field.","Error:",JOptionPane.PLAIN_MESSAGE);
                                            return;
                                        }
                                        to.setEditable(false);
                                        sub.setEditable(false);
                                        txt2.setEditable(false);
                                        b1.setEnabled(false);
                                        b2.setEnabled(false);
                                        b5.setEnabled(false);
                                        receipt.setEnabled(false);
                                        jcb.setEnabled(false);
                                        form.getInputByName("send_to").setValueAttribute(to.getText());
                                        form.getInputByName("subject").setValueAttribute(sub.getText());
                                        form.getTextAreaByName("body").setText(txt2.getText());
                                        hcbi=(HtmlCheckBoxInput)form.getInputByName("request_mdn");
                                        hcbi.setChecked(receipt.isSelected());
                                        priority=(HtmlSelect)form.getSelectByName("mailprio");
                                        priority.setSelectedAttribute(priority.getOptions().get(jcb.getSelectedIndex()),true);
                                        st.displayMessage("","Sending...");
                                        if(ae.getActionCommand().equals("Reply")||ae.getActionCommand().equals("Forward"))
                                        {
                                            AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                            jd.setVisible(true);
                                            ii=0;
                                            alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                                private float incrementer = .10f;
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    ii=ii+incrementer;
                                                    if(ii>((float)0.9))
                                                    {   
                                                        alphaChanger.stop();
                                                        AWTUtilities.setWindowOpacity(jd3,Float.valueOf(0));
                                                        jd3.setVisible(false);
                                                        j5.setEnabled(true);
                                                        forward.setEnabled(true);
                                                    }
                                                    AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                                                    AWTUtilities.setWindowOpacity(jd3,Float.valueOf((float)(1-ii)));
                                                }
                                            });
                                            alphaChanger.start();
                                        }
                                        else
                                        {
                                            ii=0;
                                            alphaChanger = null;alphaChanger = new Timer(50,new ActionListener() {
                                                private float incrementer = .10f;
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    ii=ii+incrementer;
                                                    if(ii>((float)0.9))
                                                    {   
                                                        alphaChanger.stop();
                                                        AWTUtilities.setWindowOpacity(jd3,Float.valueOf((float)0));
                                                        jd3.setVisible(false);
                                                    }
                                                    AWTUtilities.setWindowOpacity(jd3,Float.valueOf((float)(1-ii)));
                                                }
                                            });
                                            alphaChanger.start();
                                        }
                                        Thread s=new Thread(){
                                            public void run(){
                                                try
                                                {
                                                    HtmlPage page=(HtmlPage)form.getInputByValue("Send").click();
                                                    st.showMessage("","Mail sent.");
                                                }catch(Exception e)
                                                {
                                                    st.showMessage("","The mail could not be sent.\nPlease try later.");
                                                }
                                            }
                                        };
                                        s.start();
                                    }
                                });
                                if(ae.getActionCommand().equals("Compose"))
                                {
                                    b4=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/close.png")),new ImageIcon(ClassLoader.getSystemResource("Data/closepressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/closerollover.png")));
                                    b4.setActionCommand("Close");
                                    b4.setToolTipText("Close");
                                    b4.addActionListener(new ActionListener()
                                    {
                                        public void actionPerformed(ActionEvent gg)
                                        {
                                            totxt=to.getText();
                                            subtxt=sub.getText();
                                            comptxt=txt2.getText();
                                            thread2.interrupt();
                                            gp.setVisible(false);
                                            ii=0;
                                            alphaChanger = null;
                                            alphaChanger = new Timer(50,new ActionListener() {
                                                private float incrementer = .10f;
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    ii=ii+incrementer;
                                                    if(ii>((float)0.9))
                                                    {   
                                                        alphaChanger.stop();
                                                        jd3.setVisible(false);
                                                    }
                                                    try{
                                                        AWTUtilities.setWindowOpacity(jd3,Float.valueOf((float)(1-ii)));
                                                    }catch(Exception m){}
                                                }
                                            });
                                            alphaChanger.start();
                                        }
                                    });
                                }
                                b5=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/clear.png")),new ImageIcon(ClassLoader.getSystemResource("Data/clearpressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/clearrollover.png")));
                                b5.setToolTipText("Clear Text");
                                b5.addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent gg)
                                    {
                                        subtxt="";
                                        totxt="";
                                        comptxt="";
                                        to.setText(totxt);
                                        sub.setText(subtxt);
                                        txt2.setText(comptxt);
                                    }
                                });
                                b5.setEnabled(false);
                                jp44.add(b5);
                                jp44.add(jcb);
                                jp44.add(receipt);
                                jp44.add(b1);
                                jp44.add(b2);
                                if(ae.getActionCommand().equals("Compose"))
                                    jp44.add(b4);
                                if(ae.getActionCommand().equals("Reply")||ae.getActionCommand().equals("Forward"))
                                {
                                    b3=new FancyButton(new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon-pressed.png")),new ImageIcon(ClassLoader.getSystemResource("Data/Button-Previous-icon-rollover.png")));
                                    b3.setActionCommand("Back");
                                    b3.setToolTipText("Back");
                                    b3.setEnabled(false);
                                    b3.addActionListener(new ActionListener()
                                    {
                                        public void actionPerformed(ActionEvent gg)
                                        {
                                            totxt=to.getText();
                                            subtxt=sub.getText();
                                            comptxt=txt2.getText();
                                            thread2.interrupt();
                                            b3.setEnabled(false);
                                            AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                            jd.setVisible(true);
                                            ii=0;
                                            alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                                private float incrementer = .10f;
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    ii=ii+incrementer;
                                                    if(ii>((float)0.9))
                                                    {   
                                                        alphaChanger.stop();
                                                        AWTUtilities.setWindowOpacity(jd3,Float.valueOf(0));
                                                        jd3.setVisible(false);
                                                        j5.setEnabled(true);
                                                        forward.setEnabled(true);
                                                    }
                                                    AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                                                    AWTUtilities.setWindowOpacity(jd3,Float.valueOf((float)(1-ii)));
                                                }
                                            });
                                            alphaChanger.start();
                                        }
                                    });
                                }
                                jp4=new JPanel(new BorderLayout());
                                jp4.setOpaque(false);
                                if(ae.getActionCommand().equals("Reply")||ae.getActionCommand().equals("Forward"))
                                    jp4.add(b3,BorderLayout.WEST);
                                jp4.add(jp44,BorderLayout.EAST);
                                
                                jp3=(JPanel)jd3.getContentPane();
                                jp3.setLayout(new BorderLayout());
                                jp3.add(head,BorderLayout.PAGE_START);
                                jp3.add(jsp3,BorderLayout.CENTER);
                                jp3.add(jp4,BorderLayout.PAGE_END);
                                jp3.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),BorderFactory.createEmptyBorder(8,5,0,5)));
                            
                                Dimension dim=jd3.getSize();
                                Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                                
                                if(scrdim.x == 0 && scrdim.y == 0)
                                    jd3.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
                                else if(scrdim.x == 0)
                                    jd3.setLocation((scrdim.width-dim.width),(scrdim.y));
                                else //if(scrdim.y == 0)
                                    jd3.setLocation((scrdim.x),(scrdim.height-dim.height));
                            }
                            thread2.start();
                            if(ae.getActionCommand().equals("Reply")||ae.getActionCommand().equals("Forward"))
                            {
                                j5.setEnabled(false);
                                forward.setEnabled(false);
                                AWTUtilities.setWindowOpacity(jd3,Float.valueOf(0));
                                jd3.setVisible(true);
                                ii=0;
                                alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                    private float incrementer = .10f;
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                         ii=ii+incrementer;
                                         if(ii>((float)0.9))
                                         {   
                                             alphaChanger.stop();
                                             AWTUtilities.setWindowOpacity(jd,Float.valueOf(0));
                                             jd.setVisible(false);
                                             b3.setEnabled(true);
                                         }
                                         AWTUtilities.setWindowOpacity(jd3,Float.valueOf(ii));
                                         AWTUtilities.setWindowOpacity(jd,Float.valueOf((float)(1-ii)));
                                    }
                                });
                                alphaChanger.start();
                            }
                            if(ae.getActionCommand().equals("Compose"))
                            {
                                AWTUtilities.setWindowOpacity(jd3,Float.valueOf(0));
                                jd3.setVisible(true);
                                ii=0;
                                alphaChanger = null;alphaChanger = new Timer(70,new ActionListener() {
                                    private float incrementer = .10f;
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                         ii=ii+incrementer;
                                         if(ii>((float)0.9))
                                         {   
                                             alphaChanger.stop();
                                         }
                                         AWTUtilities.setWindowOpacity(jd3,Float.valueOf(ii));
                                    }
                                });
                                alphaChanger.start();
                            }
                            gp.setVisible(true);
                            }
                        }
                        void checkUploaded()
                        {
                            panel2.removeAll();
                            htr2=null;
                            try{
                               htr2=(List<HtmlTableRow>)comp.getByXPath("//body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr");
                            }catch(Exception g){}
                            if((htr2!=null)&&htr2.size()>0)
                            {
                                JLabel j[]=new JLabel[htr2.size()];
                                JButton jb[]=new JButton[htr2.size()];
                                GridBagConstraints gbc=new GridBagConstraints();
                                gbc.fill=GridBagConstraints.BOTH;
                                gbc.gridheight=1;
                                int g=0;
                                for(HtmlTableRow r:htr2)
                                {
                                    row=r;
                                    j[g]=new JLabel("<html><p>"+row.getCell(1).asText()+"\t"+row.getCell(2).asText()+"\t"+row.getCell(3).asText()+"\t"+row.getCell(4).asText()+"</p><html>");
                                    j[g].setPreferredSize(new Dimension(250,50));
                                    jb[g]=new JButton("Delete");
                                    jb[g].setActionCommand(""+g);
                                    jb[g].addActionListener(new ActionListener(){
                                        public void actionPerformed(final ActionEvent ae)
                                        {
                                            final JButton jbb=(JButton)ae.getSource();
                                            jbb.setEnabled(false);
                                            Thread thread2=new Thread()
                                            { 
                                                public void run()
                                                {
                                                    try{
                                                        form=comp.getFormByName("compose");
                                                        ((HtmlCheckBoxInput)(form.getInputsByName("delete[]").get(Integer.parseInt(ae.getActionCommand())))).setChecked(true);
                                                        comp=(HtmlPage)form.getInputByName("do_delete").click();
                                                        form=comp.getFormByName("compose");
                                                    }catch(Exception l)
                                                    {
                                                        st.showMessage("","Error occured while deleting file.\nPls try again.");
                                                    }
                                                    SwingUtilities.invokeLater(new Runnable(){
                                                        public void run()
                                                        {
                                                            checkUploaded();
                                                            panel2.updateUI();
                                                        }
                                                    });
                                                }
                                            };
                                            thread2.start();
                                        }
                                    });
                                    gbc.gridx=0;gbc.gridy=g;gbc.gridwidth=5;
                                    panel2.add(j[g],gbc);
                                    gbc.gridx=5;gbc.gridy=g;gbc.gridwidth=1;
                                    panel2.add(jb[g],gbc);
                                    g++;
                                }
                            }
                        }
                    };
                    j5.addActionListener(actionListener);
                    forward.addActionListener(actionListener);
                    st.item7.addActionListener(actionListener);
                    st.item7.setEnabled(true);
                }
            });
            fstrt++;
        }
        if(k==0&&refreshing)
        {
            st.showMessage("","Refreshed.\nNo new mails.");
            msgno=0;
        }
        int y=0;
        if(k==1)
        {
            msgno=0;y=1;
            HtmlTableRow tr=(HtmlTableRow)getMessageInfo(0);
            st.showMessage("","Got a new mail !!"+"\nFrom: "+tr.getCell(1).asText()+"\nReceived: "+tr.getCell(2).asText()+"\nSubject: "+tr.getCell(4).asText()+"\nClick to view.");
        }
        else if(k>1)
        {
            msgno=0;y=1;
            st.showMessage("","Got "+k+" new mails !!\nClick to view.");
            try{
                Thread.sleep(4000);
            }catch(InterruptedException ex){}
            int l=0;
            while(l<k)
            {
                HtmlTableRow tr=(HtmlTableRow)getMessageInfo(l);
                st.displayMessage("","Mail "+(l+1)+" :"+"\nFrom: "+tr.getCell(1).asText()+"\nReceived: "+tr.getCell(2).asText()+"\nSubject: "+tr.getCell(4).asText()+"\nClick to view.");
                try{
                    Thread.sleep(3500);
                }catch(InterruptedException ex){}
                l++;
                if(l>1)
                {
                    if(k>2)
                        st.displayMessage("","To view more double click the tray icon.");
                    break;
                }
            }
        }
        if(j!=0)
        {
            st.trayIcon.setToolTip("IITPWebApp\n"+j+" Unread Mails");
        }
        else
        {
            st.trayIcon.setToolTip("IITPWebApp");
        }
        if(fstrt==1)
        {
            if(y==0)
                st.displayMessage("IITPWebApp","Double click the Tray icon\nto view your inbox.");
            fstrt++;
        }
        try
        {
            ur.prefs.put("update",getMessageInfo(0).asText());
            str=getMessageInfo(0).asText();
        }
        catch(Exception ef){}
    }
    boolean busy = false;
    synchronized HtmlPage loginuser()
    {
        HtmlPage hp=null;
        err=0;
        do
        {
            if(enabled)
            {
                hp=login();
                if((hp!=null)&&hp.getTitleText().indexOf("Unknown user or password incorrect")!=-1)
                {
                    st.showMessage("","Got: Unknown user or password incorrect.\nPlease re-enter your username and pass.");
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException ex){}
                    ur.userPass();
                    domain=ur.domain;
                    user=ur.mailuser;
                    pass=ur.mailpass;
                    hp=null;
                }
                else if((hp!=null)&&hp.getTitleText().indexOf("you did not provide the correct challenge response")!=-1)
                {
                    st.showMessage("","You entered wrong captcha.\nPlease enter again.");
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException ex){}
                    hp=null;
                }
            }
            else
            {
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException ex){}
            }
        }
        while(hp==null);
        //st.displayMessage("","Logged In...");
        st.trayIcon.setToolTip("IITPWebApp");
        return hp;
    }
    /*void sendMails()
    {
        String pp = null;
        BufferedReader buf = null;
        BufferedWriter buw = null;
        BufferedWriter buw2 = null;
        try{
            //st.showMessage("","Sending Mails.");
            try{
                buf=new BufferedReader(new FileReader("data.txt"));
                pp=buf.readLine();
                buf.close();
            }catch(Exception nn){}
            SqlDD sqll=new SqlDD();
            ResultSet res = sqll.getData(pp);
            String strr="";
            int nextt=0;
            int hh=0;
            while(res.next())
            {
                if(nextt == 0)
                {
                    buw = new BufferedWriter(new FileWriter("data.txt"));
                    buw2 = new BufferedWriter(new FileWriter("errr.txt", true));
                    nextt=1;
                }
                HtmlPage com =((HtmlElement)mainpage.getByXPath("//body/table[1]/tbody/tr[2]/td[1]//a[1]").get(0)).click();
                HtmlForm form = com.getFormByName("compose");
                form.getInputByName("send_to").setValueAttribute(res.getString(2));
                form.getInputByName("subject").setValueAttribute("Confirmation Link for Best Professor Poll");
                form.getTextAreaByName("body").setText("Plese click the following link to confirm your registration:\r\n\r\n\r\nhttp://172.16.1.16/polling/confirm.php?user="+res.getString(2)+"&confirmcode="+res.getString(3)+"\r\n\r\nThanks\r\nAyush Jain");
                try{
                    System.out.print("Sending mail to ID="+res.getString(1)+", User="+res.getString(2)+"\t");
                    HtmlPage page=(HtmlPage)form.getInputByValue("Send").click();
                    //st.showMessage("","Mail sent: "+res.getString(2));
                    strr = res.getString(1)+"\n"+strr;
                    buw2.write("ID="+res.getString(1)+", User="+res.getString(2)+":   Success\n");
                    System.out.println("Success");
                    hh++;
                    if(hh >= 15)
                        break;
                }catch(Exception mm)
                {
                    //st.showMessage("","Mail error: "+res.getString(2));
                    buw2.write("ID="+res.getString(1)+", User="+res.getString(2)+":   Fail"+mm.getMessage()+"\n");
                    System.out.println("Failed"+mm.getMessage());
                }
            }
            if(nextt == 1)
            {
                buw.write(strr);
                buw.flush();
                buw2.flush();
                buw.close();
                buw2.close();
                st.showMessage("",hh+" Mails Sent.");
            }
            sqll.close();
        }catch(Exception mm){
            mm.printStackTrace();
        }
    }*/
    void main()
    {
        st.trayIcon.setToolTip("IITPWebApp\nConnecting");
        busy = true;
        HtmlPage log=loginuser();int start=1;
        busy = false;
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                st.item5.setEnabled(true);
            }
        });
        do
        {
            if(enabled)
            {
                if(start==1)
                {
                    mainpage=log;
                    start=0;
                }
                else
                {
                    st.trayIcon.setToolTip("IITPWebApp\nRefreshing");
                    mainpage=getRightSide();
                    st.trayIcon.setToolTip("IITPWebApp");
                }
                checkUpdates();
                //sendMails();                                                         //website
            }
            try{
                Thread.sleep(rt*60*1000);
            }catch(InterruptedException ex){}
        }
        while(true);
    }
    void relogin()
    {
        st.displayMessage("","User credentials changed.\nRelogging in.");
        if(!busy)
        {
            busy = true;
            mainpage = loginuser();
            busy = false;
        }
        else
        {
            while(busy)
            {
                try{
                    Thread.sleep(300);
                }catch(InterruptedException e){}
            }
        }
        //st.displayMessage("","Logged In...");
        //HtmlPage p=getRightSide();
        
        //mainpage=p;
        checkUpdates();
        st.displayMessage("","Click to view your messages.");
    }
    void refresh()
    {
        if(refthread!=null)
        {
            refthread.interrupt();
        }
        refthread=new Thread(){
            public void run()
            {
                HtmlPage p=getRightSide();
                
                mainpage=p;
                refreshing=true;
                checkUpdates();
                refreshing=false;
            }
        };
        st.trayIcon.setToolTip("IITPWebApp\nRefreshing");
        refthread.start();
        st.trayIcon.setToolTip("IITPWebApp");
    }
}