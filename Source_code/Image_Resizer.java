import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*; 
import javax.imageio.*;
public class Image_Resizer implements ActionListener
{
	JFrame fr;
	JButton b1,b2,b3;
	JTextField t1,t2;
	JLabel l1,l2,l3,l4;
	JFileChooser fc;
	ImageIcon i1;
	public Image_Resizer() 
	{
		JFrame fr=new JFrame("Image Resizer");
		fr.setSize(1000,640);
		fr.setLayout(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l1=new JLabel("Height :");
		l1.setBounds(50,560,80,40);
		fr.add(l1);
		
		t1=new JTextField();
		t1.setBounds(150,560,80,40);
		fr.add(t1);
		
		l2=new JLabel("Width :");
		l2.setBounds(250,560,80,40);
		fr.add(l2);
		
		t2=new JTextField();
		t2.setBounds(350,560,80,40);
		fr.add(t2);
		
		
		
		b2=new JButton("Browse");
		b2.setBounds(450,560,80,40);
		fr.add(b2);
		
		b1=new JButton("Resize");
		b1.setBounds(550,560,80,40);
		fr.add(b1);
		b1.setEnabled(false);
		
		b3=new JButton("Download");
		b3.setBounds(650,560,80,40);
		fr.add(b3);
		b3.setEnabled(false);

		l3=new JLabel();
		l3.setBounds(10,10,800,450);
		fr.add(l3); 	 	
				
		l4=new JLabel("You have to save the output image in same formate or extension.");
		l4.setBounds(100,480,500,40);
		fr.add(l4);
		
		
		fr.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	String str;
	public void actionPerformed(ActionEvent e1) 
		{
			if(e1.getSource()==b2)
			{
				fc= new JFileChooser();				
				fc.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = fc.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					 str=selectedFile.getAbsolutePath();
					 
					 BufferedImage img = null;
						try {
							img = ImageIO.read(new File(str));
						} catch (IOException e) {
							e.printStackTrace();
						}
						Image dimg = img.getScaledInstance(l3.getWidth(), l3.getHeight(),Image.SCALE_SMOOTH);
						ImageIcon i1 = new ImageIcon(dimg);
						
					//i1=new ImageIcon(selectedFile+"");
					l3.setIcon(i1);
						b1.setEnabled(true);
						b3.setEnabled(true);
				}
				
					
			}
			
			if(e1.getSource()==b1)
			{
				Icon ii=l3.getIcon();
				ImageIcon icon=new ImageIcon(str);
				Image image=icon.getImage();
				image=image.getScaledInstance(Integer.parseInt(t2.getText()),Integer.parseInt(t1.getText()),0);
				icon=new ImageIcon(image);
				l3.setIcon(icon);
			}
			if(e1.getSource()==b3)
			{try{
				fc=new JFileChooser();
				fc.showSaveDialog(fr);
				
				String outputImagePath=fc.getSelectedFile()+"";
				System.out.println(outputImagePath);
				int scaledWidth = Integer.parseInt(t2.getText());
				int scaledHeight = Integer.parseInt(t1.getText());
				File inputFile = new File(str);
				FileInputStream fis=new FileInputStream(inputFile);
				BufferedInputStream bis=new BufferedInputStream(fis);
				BufferedImage inputImage = ImageIO.read(bis);
				BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
				Graphics2D g2d = outputImage.createGraphics();
				g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
				g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
  
				
				
			}
			catch(Exception eee)
			{System.out.println(eee);}
			}
		}
		
	public static void main(String[] s) throws Exception
	{
		new Image_Resizer();
	}
}