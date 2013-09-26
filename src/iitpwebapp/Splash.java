package iitpwebapp;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.sun.awt.AWTUtilities;
public class Splash implements Runnable
{
    JWindow splash;
    float ii;
    Timer alphaChanger;
    public void run()
    {
        splash = new JWindow();
        JPanel content = new JPanel();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        URL url=ClassLoader.getSystemResource("Data/splash.jpg");
        JLabel label = new JLabel(new ImageIcon(url));
        content.add(label,BorderLayout.CENTER);
        content.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        splash.add(content);
        splash.pack();
        Dimension s=splash.getSize();
        int x = (screen.width-s.width)/2;
        int y = (screen.height-s.height)/2;
        splash.setBounds(x,y,s.width,s.height);
        AWTUtilities.setWindowOpacity(splash,Float.valueOf(0));
        ii=0;
        splash.setVisible(true);
        alphaChanger = new Timer(60,new ActionListener() {
            private float incrementer = .1f;
            @Override 
            public void actionPerformed(ActionEvent e)
            {
               ii=ii+incrementer;
               if(ii>((float)0.9))
               {   
                   alphaChanger.stop();
                   AWTUtilities.setWindowOpacity(splash,Float.valueOf(1));
                   try{
                       Thread.sleep(1500);
                    }catch(InterruptedException ex){}
                    splash.setVisible(false);
                }
               AWTUtilities.setWindowOpacity(splash,Float.valueOf(ii));
            }
        });
        alphaChanger.start();
    }
}