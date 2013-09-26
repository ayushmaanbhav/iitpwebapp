package iitpwebapp; 
import javax.swing.JDialog;
import com.sun.awt.AWTUtilities;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URL;*/
class Main
{
    UserDataReadWrite ur;
    SysTray st;
    private String temp1,temp2;
    float ii;
    Timer alphaChanger;
    JDialog jd;
    public void main()
    {
        LookAndFeel.set();
        ur=new UserDataReadWrite();
        if(ur.readSplash()==1)
            SwingUtilities.invokeLater(new Splash());
        try{
            Thread.sleep(3000);
        }catch(InterruptedException ex){}
        ur.readDomainFile();
        st=new SysTray(ur);
        final boolean fg=ur.readEnabled();
        final int gg=ur.readSplash();
        try
        {   
            SwingUtilities.invokeAndWait(new Runnable()
            {    
                public void run()
                {
                    st.initialise(fg,gg);
                }
            });
        }
        catch(InterruptedException e){}
        catch(java.lang.reflect.InvocationTargetException e){}
        String input="";
        input=ur.prefs.get("firststart","1");
        ur.prefs.put("firststart","0");
        try{
            ur.prefs.flush();
        }catch(Exception e){}
        if(Integer.parseInt(input)==1)
        {
            display("<html><h3>IIT-P WebApp:</h3>Welcome to IIT-P WebApp where you will experience <br>an easy way to access your WebMail. It automatically <br>connects to your mail service and get the updates... <br>With many additional options. <br><br>Please complete the following formalities...<br>  </html>",10);
            ur.newFile();
            //display("<html><h3>IIT-P WebApp:</h3>Congratulations... !! You have been registered.<br>Connecting to ashoka.iitp.ac.in...</html>",4);
            SwingUtilities.invokeLater(new Runnable()
            {    
                public void run()
                {
                    st.showMessage("IITPWebApp","Welcome !!");
                }
            });
            try{
                Thread.sleep(2000);
            }catch(InterruptedException ex){}
        }
        IITPWebMail mail=new IITPWebMail(st,ur);
        mail.main();
    }
    int f=0;
    void display(final String str,final int sec)
    {
         try{
                SwingUtilities.invokeAndWait(new Runnable()
                {
                    public void run()
                    {
                        jd=new JDialog();
                        jd.setUndecorated(true);
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
                        JLabel l1=new JLabel(str);
                        JPanel jp=new JPanel();
                        jp.setOpaque(false);
                        jp.add(l1);
                        jp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        jd.add(jp);
                        jd.pack();
                        jd.setAlwaysOnTop(true);
                        Dimension dim=jd.getSize();
                        Rectangle scrdim=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                        jd.setLocation((scrdim.width-dim.width),(scrdim.height-dim.height));
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
                                    f=1;
                                }
                                com.sun.awt.AWTUtilities.setWindowOpacity(jd,Float.valueOf(ii));
                            }
                        });
                        alphaChanger.start();
                    }
                });
                while(f!=1)
                {
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ex){}
                }
                try{
                    Thread.sleep(sec*1000);
                }catch(InterruptedException ex){}
                jd.setVisible(false);
         }
         catch(InterruptedException e){}
         catch(java.lang.reflect.InvocationTargetException e){}
    }
}