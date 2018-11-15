import javax.swing.*;         // Used for GUI
import java.awt.*;            // for gridlayout and fancy colors
import java.io.*;             // Used for standard IO
import java.awt.event.*;      // For some reason just importing java.awt.* doesn't get the action listner stuff so this needs to be here for that

public class GridLayoutAuto extends JFrame implements ActionListener, Runnable 
{
   private static final JLabel instructionsText = new JLabel("Enter the Text you wish to Spam!");
   private static final JLabel instructionsNum = new JLabel("Enter The number of times you wish to spam said text");
   
   private JLabel status = new JLabel("Status: Awaiting Input");
   
   private JTextField textToSpam = new JTextField("", 50);
   private JTextField timesToSpam = new JTextField("",50);
   
   private JButton beginSpamButton = new JButton("Click to Begin Spam");
   
   public static final int x = 700;
   public static final int y = 170;
   
   public spamObject spammer = new spamObject(5, "dummyString");
   
   public GridLayoutAuto()
   {
      super();
      
      setSize(x,y);
      setLayout(new GridLayout(6,1));
      setTitle("AutoTyper - SageTheWizard @ GitHub"); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      beginSpamButton.addActionListener(this);
      
      add(instructionsText);
      add(textToSpam);
      add(instructionsNum);
      add(timesToSpam);
      add(beginSpamButton);
      add(status);
      
   }
   public GridLayoutAuto(int num, String spamText)
   {
      spammer.setTime(num);
      spammer.setSpam(spamText);
   }
   public void run()
   {
      beginSpam(spammer);
   }
   
   public void actionPerformed(ActionEvent buttonClicked)
   {
      
      if ((buttonClicked.getActionCommand()).equals("Click to Begin Spam"))
      {
         if ((textToSpam.getText()).equals(""))
         {
            status.setText("Status: ERROR! Must input a STRING (TEXT) into the TEXT TO SPAM field!");
            status.setForeground(Color.RED);
         }
         else if ((timesToSpam.getText()).equals(""))
         {
            status.setText("Status: ERROR! Must input INTEGER into the TIMES TO SPAM field!");
            status.setForeground(Color.RED);
         }
         else
         {
            try
            {
               spammer.setTime(Integer.parseInt(timesToSpam.getText()));
               spammer.setSpam(textToSpam.getText());
               
               status.setForeground(Color.MAGENTA);
               status.setText("SPAMMING IN 5 SECONDS...");
               
               GridLayoutAuto para = new GridLayoutAuto(spammer.getTime(), spammer.getSpam());
               Thread spamThread = new Thread(para);
               spamThread.start();
               status.setText("Status: Awaiting Input");
               status.setForeground(Color.BLACK);   
            }
            catch(NumberFormatException e)
            {
               status.setText("Status: ERROR! input in IIMES TO SPAM must be an INTEGER");
               status.setForeground(Color.RED); 
            }
            catch(InterruptedException ex)
            {
               status.setText("Status: ERROR! Something went horribly wrong");
               status.setForeground(Color.RED);
            }
         }
      }
   }
   
   public void beginSpam(spamObject spamData)
   {
      try
      {
         Robot spamBoi = new Robot();
         int keycode;
         System.out.println(spamData.getTime());
         spamBoi.delay(5000);
         for (int i = 0; i < spamData.getTime(); i++)
         {  
            for (int j = 0; j < (spamData.getSpam()).length(); j++)
            {
               keycode = KeyEvent.getExtendedKeyCodeForChar((spamData.getSpam()).charAt(j));
               
               if ((Character.isLetter((spamData.getSpam()).charAt(j)) && (Character.isUpperCase((spamData.getSpam()).charAt(j)))))
                  spamBoi.keyPress(KeyEvent.VK_SHIFT);
                  
               spamBoi.keyPress(keycode);
               spamBoi.keyRelease(KeyEvent.VK_SHIFT);
               spamBoi.keyRelease(keycode);
            }
            spamBoi.keyPress(KeyEvent.VK_ENTER);
            spamBoi.keyRelease(KeyEvent.VK_ENTER);
            spamBoi.delay(500);
         }
      }
      catch(Exception e)
      {
         status.setText("Status: ERROR! Something broke! Check for invalid characters {!@#$%^&*()}");
         status.setForeground(Color.RED);
      }
   }
   
}