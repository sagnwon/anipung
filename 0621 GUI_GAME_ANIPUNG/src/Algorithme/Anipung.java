package Algorithme;

import java.util.ArrayList;
import java.util.Random;

public class Anipung {

	private static Anipung getAnipung = null;

	public static Anipung getInstance() {
		if (getAnipung == null) {
			getAnipung = new Anipung();
		}
		return getAnipung;
	}

	private Anipung() {}

	private Random r = new Random();

	public int[][] tileSet(int[][] tile) {

		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j] == 0) {
					tile[i][j] = r.nextInt(5)+1;
				}
			}
		}
		return tile;
	}

	public int[][] tileMove(int[][] tile, int[] idxF, int[] idxS) {

		int temp = tile[idxF[0]][idxF[1]];

		tile[idxF[0]][idxF[1]] = tile[idxS[0]][idxS[1]]; // 첫 셀렉 자리에 두번째 셀렉타일 넣고
		tile[idxS[0]][idxS[1]] = temp; // 두번째 셀렉 자리에 첫 셀렉타일 넣고

		return tile;
	}

	public int[][] checkTile(int[][] tile) {

		ArrayList<int[]> temp1 = checkRow(tile);
		ArrayList<int[]> temp2 = checkCol(tile);
		
		if (temp1.size()!=0) {
			tile = removeTileRow(temp1,tile);
		}
		if (temp2.size()!=0) {
			tile = removeTileCol(temp2,tile);
		}

		return tile;
	}

	public ArrayList<int[]> checkRow(int[][] tile) {

		ArrayList<int[]> Idx = new ArrayList<>();

		for (int i = 0; i < tile.length; i++) {//row

			int cnt = 0;
			int continued = -1;
			int[] rowIdx = new int[4];

			for (int j = 0; j < tile[i].length; j++) {//col

				if (continued == -1) {

					rowIdx[0] = i;
					rowIdx[1] = j;
					continued = tile[i][j];

				} else if (continued == tile[i][j]) {

					cnt++;
					if (cnt >= 2) {
						rowIdx[2] = i;
						rowIdx[3] = j;
					}

				} else if (cnt < 2) {

					rowIdx[0] = i;
					rowIdx[1] = j;
					continued = tile[i][j];
					cnt = 0;
				}
			}
			if (rowIdx[3] != 0) {
				Idx.add(rowIdx);
			}

		}

		return Idx;

	}

	// 0,1번째 좌표 하나, 2,3번째 좌표 둘
	public ArrayList<int[]> checkCol(int[][] tile) {

		ArrayList<int[]> Idx = new ArrayList<>();

		for (int i = 0; i < tile.length; i++) {//col

			int cnt = 0;
			int continued = -1;
			int[] colIdx = new int[4];

			for (int j = 0; j < tile[i].length; j++) {//row

				if (continued == -1) {

					colIdx[0] = j;
					colIdx[1] = i;
					continued = tile[j][i];

				} else if (continued == tile[j][i]) {

					cnt++;
					if (cnt >= 2) {
						colIdx[2] = j;
						colIdx[3] = i;
					}

				} else if (cnt < 2) {

					colIdx[0] = j;
					colIdx[1] = i;
					continued = tile[j][i];
					cnt = 0;
				}
			}

			if (colIdx[2] != 0) {
				Idx.add(colIdx);
			}

		}

		return Idx;

	}

	public int[][] removeTileRow(ArrayList<int[]> idxList, int[][] tile) {

		for (int j = 0; j < idxList.size(); j++) {
			int[] idxinfo = idxList.get(j);
			for (int j2 = idxinfo[1]; j2 <= idxinfo[3]; j2++) {
				tile[idxinfo[0]][j2] = 0;
			}
		}

		
		return tile;
	}
	
	public int[][] removeTileCol(ArrayList<int[]> idxList, int[][] tile) {


		for (int j = 0; j < idxList.size(); j++) {
			int[] idxinfo = idxList.get(j);
			System.out.println(idxinfo[0]+"/"+idxinfo[1]+"/"+idxinfo[2]+"/"+idxinfo[3]);
			for (int j2 = idxinfo[0]; j2 <= idxinfo[2]; j2++) {
				tile[j2][idxinfo[1]] = 0;
			}
		}
		
		return tile;
	}
	
	public boolean chkTile(int[][] ChktailInfo) {
		
		boolean chk = false;
		
		for (int i = 0; i < ChktailInfo.length; i++) {
			for (int j = 0; j < ChktailInfo[i].length; j++) {
				if (ChktailInfo[i][j]==0) {
					chk = true;
					return chk;
				}
			}
		}
		return chk;
		
	}
}
