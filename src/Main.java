/**
 *  Main class for the application
 *  
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class Main extends JPanel implements ActionListener{
	static private final String newline = "\n";
    JButton openButton1, openButton2, runButton;
	JTextArea log;
	JFileChooser fc;
	File file1;
	File file2;
	String original;
	String yours;
	public Main(){
		super(new BorderLayout());
		log = new JTextArea(9,32);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		//Create a file chooser
		fc = new JFileChooser();
		
		openButton1 = new JButton("Original File...");
		openButton1.addActionListener(this);
		openButton2 = new JButton("Asnwer File...");
		openButton2.addActionListener(this);
		runButton = new JButton("Run");
		runButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton1);
		buttonPanel.add(openButton2);
		buttonPanel.add(runButton);
		
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton1) {
            int returnVal = fc.showOpenDialog(Main.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file1 = fc.getSelectedFile();
                try {
					original = new Scanner(file1) .useDelimiter("\\A").next();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //This is where a real application would open the file.
                log.append("Original File: " + file1.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		if (e.getSource() == openButton2) {
            int returnVal = fc.showOpenDialog(Main.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file2 = fc.getSelectedFile();
                try {
					yours = new Scanner(file2) .useDelimiter("\\A").next();					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //This is where a real application would open the file.
                log.append("Your File: " + file2.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		if(e.getSource() == runButton){
			if(file1 == null || file2 == null){
				log.append("Select file(s)"+newline);
			}else{
				//implement Diff
				Diff diff = new Diff(original, yours);
				diff.createArr();		
				diff.getLcs();
				diff.findDiff();
				diff.getResultTxt();
				
				log.append("Inserted: "+diff.inserted+newline);
				log.append("Deleted: "+diff.deleted+newline);
				log.append("Changed: "+diff.changed+newline);
				log.append("Accuracy: "+diff.getAccuracy()+"%");
				System.out.println(diff.getDiffResult());
				System.out.println(diff.getAccuracy());
			}
			
		}
		
	}
	public File getFile1(){
		return file1;
	}
	public File getFile2(){
		return file2;
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Typer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add content to the window.
        frame.add(new Main());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) throws IOException{
		String t1 = "teacher hello";
		String t2 = "teacher hello";

		Main display = new Main();
		display.createAndShowGUI();
	} 
	
	
	
}
