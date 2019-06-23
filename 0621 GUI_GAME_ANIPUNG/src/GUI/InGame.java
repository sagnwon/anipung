package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import Algorithme.Anipung;

public class InGame extends JFrame implements ActionListener {

	private Anipung anipung = Anipung.getInstance();
	private int[][] tailInfo = new int[5][5];

	int selIdx[] = new int[2];
	int selIdx2[] = new int[2];

	Image[] img = new Image[6];

	JButton[][] TileButton = new JButton[5][5];

	JPanel TailP = null;
	JPanel emptyP = new JPanel();

	public InGame() {

		setInitialVal();
		this.setBounds(100, 100, 250, 250);
		this.setLayout(new BorderLayout());
		loadIcon();
		setImgTile();
		inti();

		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

	}

	private void setInitialVal() {//1

		selIdx[0] = -1;
		selIdx[1] = -1;

		selIdx2[0] = -1;
		selIdx2[1] = -1;

	}

	private void loadIcon() {//2
		System.out.println("setImgTile");
		for (int i = 0; i < img.length; i++) {

			try {
				File sourceimage = new File("C:\\Users\\steam\\Desktop\\workspace\\SampleIMG\\" + i + ".png");
				img[i] = ImageIO.read(sourceimage);
			} catch (Exception e) {
				System.out.println("파일 접근 오류 : " + e);
			}

		}
	}

	private void setImgTile() {//3
		TailP = new JPanel();
		TailP.setLayout(new GridLayout(5, 5));
		
		System.out.println("setImgTile");

		tailInfo = anipung.tileSet(tailInfo);

		for (int i = 0; i < tailInfo.length; i++) {
			for (int j = 0; j < tailInfo[i].length; j++) {
				TileButton[i][j] = new JButton(new ImageIcon(img[tailInfo[i][j]]));
				TileButton[i][j].addActionListener(this);
			}
		}
	}
	
	private void inti() {//4
		
		System.out.println("init");
		setTile();
		this.add(TailP, "Center");
		System.out.println("conplite");

	}

	private void setTile() {
		System.out.println("setTile");
		for (int i = 0; i < tailInfo.length; i++) {
			for (int j = 0; j < tailInfo.length; j++) {
				TailP.add(TileButton[i][j]);
			}
		}
	}

	private void searchSelIdx(Object Obj) {

		for (int i = 0; i < TileButton.length; i++) {

			for (int j = 0; j < TileButton[i].length; j++) {

				if (Obj.equals(TileButton[i][j])) {

					selIdx[1] = j;
					break;
				}

			}

			if (selIdx[1] != -1) {

				selIdx[0] = i;
				break;
			}

		}
	}

	private void searchSelIdx2(Object Obj) {

		for (int i = 0; i < TileButton.length; i++) {

			for (int j = 0; j < TileButton[i].length; j++) {

				if (Obj.equals(TileButton[i][j])) {

					selIdx2[1] = j;
					break;
				}

			}

			if (selIdx2[1] != -1) {

				selIdx2[0] = i;
				break;
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (selIdx[0] == -1) {
			searchSelIdx(e.getSource());

		} else {
			
			int[][] saveTileInfo = clon(tailInfo);
			
			searchSelIdx2(e.getSource());
			
			if (Math.abs((selIdx[0]+selIdx[1])-(selIdx2[0]+selIdx2[1]))==1 & (selIdx[0]==selIdx2[0] | selIdx[1]==selIdx2[1])) {
				
				this.remove(TailP);
				
				tailInfo = anipung.tileMove(tailInfo, selIdx, selIdx2);
				setImgTile();
				inti();
				this.setVisible(true);
				
				int[][] ChktailInfo = anipung.checkTile(tailInfo);
				boolean chk = anipung.chkTile(ChktailInfo);
				
				if (chk) {
					System.out.println("바꿔");
					this.remove(TailP);
					
					tailInfo = ChktailInfo;
					
					
					setImgTile2();
					inti();
					this.setVisible(true);
					
				}else {
					
					this.remove(TailP);
					
					tailInfo = saveTileInfo;
					setImgTile();
					inti();
					this.setVisible(true);
				}
			}
			setInitialVal();
		}

	}
	
	private void setImgTile2() {//3
		TailP = new JPanel();
		TailP.setLayout(new GridLayout(5, 5));
		
		System.out.println("setImgTile2");

		for (int i = 0; i < tailInfo.length; i++) {
			for (int j = 0; j < tailInfo[i].length; j++) {
				TileButton[i][j] = new JButton(new ImageIcon(img[tailInfo[i][j]]));
				TileButton[i][j].addActionListener(this);
			}
		}
	}
	
	private int[][] clon(int[][] tile){
		int[][] tempTile = new int[5][5];
		for (int i = 0; i < tempTile.length; i++) {
			for (int j = 0; j < tempTile[i].length; j++) {
				tempTile[i][j] = tile[i][j];
			}
		}
		return tempTile;
	}
	
}
