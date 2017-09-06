import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Window extends JFrame implements ActionListener
{
    Display display;  //where the numbers display moving across the screen
    JTextField entry; //where the user enters their answer
    JButton submit;  //the button to press to submit your answer
    JPanel topPanel;  //the panel to hold the button
    JPanel entryPanel; //the panel to hold the textfield for entry
    Timer t;            //the Timer that allows for motion across the screen
    int timerDelay = 300;   //number of milliseconds
    int WIDTH = 400;
    int HEIGHT = 400;
    /*
     * CONSTRUCTOR
     */
    public Window()
    {
        display = new Display();  ///CREATE THE DISPLAY WINDOW

        //create submit button and put in top JPanel
        submit = new JButton("Submit");
        submit.addActionListener(this);
        topPanel = new JPanel();
        topPanel.add(submit);

        //create entry field, then put in entry panel:
        entry = new JTextField(30);  //30 is the width of the text field
        entryPanel = new JPanel();
        entryPanel.add(entry);

        //PUT IT ALL IN THE FRAME
        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);  //the button goes on top
        add(display, BorderLayout.CENTER);  //the display in the middle
        add(entryPanel, BorderLayout.SOUTH);  //the entry at bottom

        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));  

        //STUFF YOU JUST HAVE TO DO WITH A JFRAME:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        //YOU'VE PRESSED THE SUBMIT BUTTON!!!

        display.shift += 10;  //when you press the button, you are at the next level, so increase the speed

        //get rid of the button and entry while displaying the new number:
        entryPanel.remove(entry);
        entryPanel.revalidate();
        entryPanel.repaint();
        topPanel.remove(submit);
        topPanel.revalidate();
        topPanel.repaint();
        //reset the number text to the left side:
        display.textxLoc = 20;
        display.textyLoc = 200;

        //make the delay shorter each time (do this with or without shift increase)
        timerDelay -= 10;

        /*
         * REPLACE THIS LINE - IT IS JUST ADDING RANDOM NUMBERS, NOT ADDING DIGITS:
         */
        display.x += (int)(Math.random() * 1000);
        //DEFINE THE TIMER THAT ALLOWS FOR THE ANIMATION OF THE NUMBER ACROSS THE SCREEN:
        t = new Timer(timerDelay , new ActionListener() 
            {  //define the actionListener...
                public void actionPerformed(ActionEvent e) //this is called every timerDelay millisecs
                {  
                    //move the text to the right each time:
                    display.textxLoc+= display.shift;
                    //it's off the screen, so let the user enter their number:
                    if(display.textxLoc > WIDTH + 200) {  
                        //TIME TO STOP THE TIMER AND ADD BACK THE BUTTON AND THE ENTRY BOX:
                        stop();
                        entry.setText("");
                        entryPanel.add(entry);
                        entryPanel.revalidate();
                        entryPanel.repaint();

                        topPanel.add(submit);
                        topPanel.revalidate();
                        topPanel.repaint();
                    }
                    //not off the screen yet so keep painting:
                    display.repaint(); 

                }
            }
        );
        //START THE TIMER
        t.start();
    }

    public void stop(){
        t.stop();
    }

}
