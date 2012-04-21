package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class FileChooser extends JPanel {
  private JTextField p1 = new JTextField(), dir1 = new JTextField();
  private JTextField p2 = new JTextField(), dir2 = new JTextField();
  private JTextField p3 = new JTextField(), dir3 = new JTextField();
  File passage1 = null;
  File passage2= null;
  File passage3= null;
  private JButton open1 = new JButton("Browse"), gen = new JButton("Generate");
  private JButton open2 = new JButton("Browse");
  private JButton open3 = new JButton("Browse");
  public FileChooser() {
    JPanel p = new JPanel();
    JLabel L1 = new JLabel("First Passage");
    JLabel L2 = new JLabel("Second Passage");
    JLabel L3 = new JLabel("Third Passage");
    p.setLayout(new GridLayout(3, 3));
    p.add(L1);
    p.add(p1);
    p.add(open1);
    p.add(L2);
    p.add(p2);
    p.add(open2);
    p.add(L3);
    p.add(p3);
    p.add(open3);
    open1.addActionListener(new OpenL1());
    open2.addActionListener(new OpenL2());
    open3.addActionListener(new OpenL3());
    //this.add(open);
    gen.addActionListener(new GL());
    //this.add(gen);
    dir1.setEditable(false);
    dir2.setEditable(false);
    dir3.setEditable(false);
    p1.setEditable(false);
    p2.setEditable(false);
    p3.setEditable(false);
    this.setLayout (new BorderLayout());
 
    add(p,BorderLayout.CENTER);
    add(gen,BorderLayout.SOUTH);
    
    
  }

  class OpenL1 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Open" dialog:
      int rVal = c.showOpenDialog(FileChooser.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
    	passage1 = c.getSelectedFile();
        p1.setText(c.getSelectedFile().getName());
        dir1.setText(c.getCurrentDirectory().toString());
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        p1.setText("You pressed cancel");
        dir1.setText("");
      }
      p1.repaint();
      dir1.repaint();
    }
  }
  class OpenL2 implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      JFileChooser c = new JFileChooser();
	      // Demonstrate "Open" dialog:
	      int rVal = c.showOpenDialog(FileChooser.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	passage2 = c.getSelectedFile();
	        p2.setText(c.getSelectedFile().getName());
	        dir2.setText(c.getCurrentDirectory().toString());
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	        p2.setText("You pressed cancel");
	        dir2.setText("");
	      }
	      p2.repaint();
	      dir2.repaint();
	    }
	  }
  class OpenL3 implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      JFileChooser c = new JFileChooser();
	      // Demonstrate "Open" dialog:
	      int rVal = c.showOpenDialog(FileChooser.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	passage3 = c.getSelectedFile();
	        p3.setText(c.getSelectedFile().getName());
	        dir3.setText(c.getCurrentDirectory().toString());
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	        p3.setText("You pressed cancel");
	        dir3.setText("");
	      }
	      p3.repaint();
	      dir3.repaint();
	    }
	  }

  class GL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    	if ((passage1 != null) &&(passage2 != null) && (passage3 != null)){
    	File inputFile = passage1;
        File outputFile = new File("Passage 1.txt");

        FileReader in = null;
		try {
			in = new FileReader(inputFile);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        FileWriter out = null;
		try {
			out = new FileWriter(outputFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int c;

        try {
			while ((c = in.read()) != -1)
			  out.write(c);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	inputFile = passage2;
        outputFile = new File("Passage 2.txt");

		try {
			in = new FileReader(inputFile);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			out = new FileWriter(outputFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			while ((c = in.read()) != -1)
			  out.write(c);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        inputFile = passage3;
        outputFile = new File("Passage 3.txt");


		try {
			in = new FileReader(inputFile);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			out = new FileWriter(outputFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


        try {
			while ((c = in.read()) != -1)
			  out.write(c);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    	
    	}
  }

  public static void main(String[] args) {
	  JFrame f = new JFrame();
    run(f, 600, 400);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new FileChooser());
    frame.setSize(width, height);
    frame.setVisible(true);
  }
}
