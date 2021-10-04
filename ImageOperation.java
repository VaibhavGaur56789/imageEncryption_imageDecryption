import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.Font;


public class ImageOperation {

    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser(); //to open the dialog for choosing file
        fileChooser.showOpenDialog(null); //will open the dialog in center
        File file = fileChooser.getSelectedFile(); //to get the selected file and store it in file variable

        //Now, To read the file we will use fileInputStream
        try {
            FileInputStream fis = new FileInputStream(file);

            byte []data = new byte[fis.available()]; //as we don't know how much byte is present in our array so we will use fis.available
            fis.read(data); //to use fis we will have to use byte key array and to use it we will have to create it above

            int i=0; //this is taken to encrypt every digit so 0 is used
            for(byte b:data) {
                System.out.println(b); //this will print the bytes of an image
                data[i] = (byte)(b^key);  //e.g. a=70; key=50, r=a^key => 116, 116^key(i.e. 50) => 70
                i++;  
            } //now we have received the changed value in data, to change this data and write into file....See below code....

            FileOutputStream fos = new FileOutputStream(file); //to write the data
            fos.write(data); //changed data will write now and old one will get replaced
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");
            

        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("This is testing");
        
        JFrame f = new JFrame();  //will import swing
        f.setTitle("Image Operation"); //to give title
        f.setSize(400, 400); //to set size
        f.setLocationRelativeTo(null); //so that it can be in center
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when we close the window it can get closed easily
        

        //*****************Above code is creation for blank window****************

        Font font = new Font("Robot", Font.BOLD, 25); //25 is size of font

        //Creating button
        JButton button = new JButton();
        button.setText("Open Image"); //to set text inside button
        button.setFont(font); //to set the font of text inside button

        //Creating text field
        JTextField textField = new JTextField(10);
        textField.setFont(font); //to create the text field
        
        button.addActionListener(e-> {
            System.out.println("Button clicked");
            String text = textField.getText(); //to store text in textField in text variable
            int temp = Integer.parseInt(text); //for conversion of string above
            operate(temp);
        }); /* by this we have added action listener in our button, now we will pass action listener object here, as we can't create
                                    object of action listener so we will create object of its child class */


        f.setLayout(new FlowLayout());
        f.add(button); //finally for adding the created button
        f.add(textField); ////finally for adding the created textField

        f.setVisible(true); //to check if window is visible

    }
}