package Main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import GUI.InGame;

public class Start extends JFrame implements ActionListener{
	Image mainImg = null;
	Image start = null;

	JButton startB = null;
	JPanel back = null;
	JPanel panelB = new JPanel();

	public Start() {
		
		imageLoad();

		this.setTitle("애니펑");
		this.setBounds(100, 100, 600, 450);
		this.setLayout(new BorderLayout());
		inti();

		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
	}

	private void imageLoad() {
		try {
			File sourceimage = new File("C:\\Users\\steam\\Desktop\\workspace\\SampleIMG\\anipung.jpg");
			mainImg = ImageIO.read(sourceimage);
		} catch (Exception e) {
			System.out.println("파일 접근 오류 : " + e);
		}
		try {
			File sourceimage = new File("C:\\Users\\steam\\Desktop\\workspace\\SampleIMG\\startB.png");
			start = ImageIO.read(sourceimage);
		} catch (Exception e) {
			System.out.println("파일 접근 오류 : " + e);
		}

	}

	private void inti() {

		
		back = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(mainImg, 0, 0, null);
			}
		};
		
		startB = new JButton(new ImageIcon(start));
		back.add(startB);

		this.add(back);
		
		startB.addActionListener(this);
	}

	public static void main(String[] args) {
		new Start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		new InGame();
		this.setVisible(false);
	}

}
