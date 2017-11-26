import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class pollingMachine {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JLabel countLabel;
   private JPanel controlPanel;
   private ButtonGroup group;
   private static int mushkoorVotes = 0;
   private static int abuBaqrVotes = 0;
   private static int atifVotes = 0;

   public pollingMachine(){
      prepareGUI();
   }
   public static void main(String[] args){
      pollingMachine newMachine = new pollingMachine();  
      newMachine.setup();       
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Digital EVM!");
      mainFrame.setSize(700,600);
      mainFrame.setLayout(new GridLayout(5, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        
      countLabel = new JLabel("",JLabel.CENTER);        
      statusLabel.setSize(350,100);
      countLabel.setSize(350,100);
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.add(countLabel);
      //mainFrame.pack();
	  mainFrame.setLocationRelativeTo(null);

      mainFrame.setVisible(true);  
   }
   private void setup(){
      headerLabel.setText("Cast your vote for AMUSU president!");
      statusLabel.setText("Status: Mushkor RadioButton checked");

      final JRadioButton radMushkoor = new JRadioButton("Mushkoor Usmani", true);
      final JRadioButton radAbuBaqr = new JRadioButton("Abu Baqr");
      final JRadioButton radAtif = new JRadioButton("Atif");

      final JButton okButton = new JButton("Submit"); 
      okButton.addActionListener(new ButtonClickListener());

      radMushkoor.setActionCommand("Mushkoor");
      radAbuBaqr.setActionCommand("AbuBaqr");
      radAtif.setActionCommand("Atif");

     radMushkoor.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            statusLabel.setText("Status: Mushkor RadioButton " 
               + (e.getStateChange()==1?"checked":"unchecked"));
         }           
      });
      radAbuBaqr.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            statusLabel.setText("Status: Abu Baqr RadioButton " 
               + (e.getStateChange()==1?"checked":"unchecked")); 
         }           
      });
      radAtif.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            statusLabel.setText("Status: Atif RadioButton " 
               + (e.getStateChange()==1?"checked":"unchecked"));
         }           
      });


      group = new ButtonGroup();
      
      group.add(radMushkoor);
      group.add(radAbuBaqr);
      group.add(radAtif);



      controlPanel.add(radMushkoor);
      controlPanel.add(radAbuBaqr);
      controlPanel.add(radAtif);

      controlPanel.add(okButton);

      refreshCount(); 


      mainFrame.setVisible(true);  
   }


   private class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
        JDialog.setDefaultLookAndFeelDecorated(true);
	    
	    int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

	    if (response == JOptionPane.NO_OPTION) {
	      statusLabel.setText("No Vote Casted!");
	    }
	     else if (response == JOptionPane.YES_OPTION) {
	      String selection = group.getSelection().getActionCommand(); 
	      if (selection == "Mushkoor"){
	      	mushkoorVotes+=1;
	      	statusLabel.setText("Status: Thanks for your vote!");
	      	refreshCount();
	      }
	      else if (selection == "AbuBaqr"){
	      	abuBaqrVotes+=1;
	      	statusLabel.setText("Status: Thanks for your vote!");
	      	refreshCount();
	      }
	      else{
	      	atifVotes+=1;
	      	statusLabel.setText("Status: Thanks for your vote!");
	      	refreshCount();
	      }

	    }
	     else if (response == JOptionPane.CLOSED_OPTION) {
	      statusLabel.setText("No Vote Casted!");
	    }

      }		
   }


   private void refreshCount(){

   	countLabel.setText("<html>Counting:<br><br>Mushkoor Votes: " + mushkoorVotes + "<br>Abu Baqr Votes:" + abuBaqrVotes + "<br>Atif Votes: " + atifVotes);

   }


}
