import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Sudoku {
	
	public static int[][] Sudoku = new int[9][9];
	public static int[][] storeSudoku = new int[9][9];
	public static int[][][] possible = new int[9][9][9];
	public static int[][][] storePossible = new int[9][9][9];
	public static int[][] storeDoubles = new int[81][2];
	public static boolean change = true;
	
	public static void loadSudoku() throws IOException {
		String path = System.getProperty("user.dir") + "/Sudoku.txt";
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		String[] textData = new String[9];

		for (int j=0; j < 9; j++) {
			String[] temp = textReader.readLine().split("");
			
			for (int i=0;i<9;i++){
				Sudoku[i][j] = Integer.parseInt(temp[i]);
			}
		}
		
		textReader.close( );
		
	}
	//takes number from actual sudoku, uses it to fill space in "possible" array
	public static void transToPos(){
		for (int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				if (Sudoku[i][j] > 0){
					for (int k = 0;k<9;k++){
						possible[i][j][k] = Sudoku[i][j];
					}
				} 
			}
		}
	}
	public static void initialTransToPos(){
		for (int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				if (Sudoku[i][j] > 0){
					for (int k = 0;k<9;k++){
						possible[i][j][k] = Sudoku[i][j];
					}
				} else {
					for (int k = 0;k<9;k++){
						if(checkColumn(i,k+1) == true){
							possible[i][j][k] = 0;
						}
						if(checkRow(j,k+1) == true){
							possible[i][j][k] = 0;
						}
					}
				}
			}
		}
		
		for (int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(0,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
			for(int j = 3;j<6;j++){
				for (int k = 3;k<9;k++){
					if(checkBox(3,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
			for(int j = 6;j<9;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(6,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
		}
		for (int i = 3;i<6;i++){
			for(int j = 0;j<3;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(1,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
			for(int j = 3;j<6;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(4,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
			for(int j = 6;j<9;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(7,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
		}
		for (int i = 6;i<9;i++){
			for(int j = 0;j<3;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(2,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
			for(int j = 3;j<6;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(5,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
			for(int j = 6;j<9;j++){
				for (int k = 0;k<9;k++){
					if(checkBox(8,k+1) == true){
						possible[i][j][k] = 0;
					}	
				}
			}
		}
		for (int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				if (Sudoku[i][j] > 0){
					for (int k = 0;k<9;k++){
						possible[i][j][k] = Sudoku[i][j];
					}
				}
			}
		}
		
	}
	public static void loadPossible() {
		for (int i = 0;i<9;i++){
			for (int j = 0;j<9;j++){
				for (int k = 0;k<9;k++){
					possible[i][j][k] = k+1;
				}
			}
		}
	}
	public static void displaySudoku() {
		for (int j=0;j<3;j++){
			String line = "";
			for (int i=0;i<3;i++){
				line = line + Sudoku[i][j];
			}
			line = line + "x";
			for (int k=3;k<6;k++){
				line = line + Sudoku[k][j];
			}
			line = line + "x";
			for (int p=6;p<9;p++){
				line = line + Sudoku[p][j];
			}
			System.out.println(line);
		}
		System.out.println("xxxxxxxxxxx");
		for (int j=3;j<6;j++){
			String line = "";
			for (int i=0;i<3;i++){
				line = line + Sudoku[i][j];
			}
			line = line + "x";
			for (int k=3;k<6;k++){
				line = line + Sudoku[k][j];
			}
			line = line + "x";
			for (int p=6;p<9;p++){
				line = line + Sudoku[p][j];
			}
			System.out.println(line);
		}
		System.out.println("xxxxxxxxxxx");
		for (int j=6;j<9;j++){
			String line = "";
			for (int i=0;i<3;i++){
				line = line + Sudoku[i][j];
			}
			line = line + "x";
			for (int k=3;k<6;k++){
				line = line + Sudoku[k][j];
			}
			line = line + "x";
			for (int p=6;p<9;p++){
				line = line + Sudoku[p][j];
			}
			System.out.println(line);
		}
	}
	public static void displayPossible() {
	
		System.out.println("---------------------------------------------------");
		for (int j =0;j<3;j++){
			String line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			
			line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			
			line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			System.out.println("---------------------------------------------------");
			}
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("---------------------------------------------------");
			for (int j =3;j<6;j++){
			String line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			
			line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			
			line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			System.out.println("---------------------------------------------------");
			}
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("---------------------------------------------------");
			for (int j =6;j<9;j++){
			String line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 0;k<3;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			
			line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 3;k<6;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			
			line = "";
			for (int i = 0;i<3;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 3;i<6;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			line = line + "xxx";
			for (int i = 6;i<9;i++){
				line = line + "|";
				for (int k = 6;k<9;k++){
					line = line + possible[i][j][k];
				}
				line = line + "|";
			}
			System.out.println(line);
			System.out.println("---------------------------------------------------");
			}
	}
	public static boolean checkColumn(int col,int val){
			boolean exists = false;
			for (int j =0;j<9;j++){
				if (Sudoku[col][j] == val){
					exists = true;
				}
			}
		
		return exists;
	}
	public static boolean checkRow(int row,int val){
			boolean exists = false;
			for (int i =0;i<9;i++){
				if (Sudoku[i][row] == val){
					exists = true;
				}
			}
		
		return exists;
	}
	public static boolean checkBox (int box,int val){
		double p = box;
		p = 10*((p/3) - (int)(box/3));
		
		int iLow = (int)p;
		int iUp = iLow + 2;
		
		int jLow = ((int)(box/3)) * 3;
		int jUp = jLow + 2;
		
		boolean exists = false;
		for (int i = iLow;i<iUp + 1;i++){
			for (int j = jLow;j<jUp+1;j++){
				if (Sudoku[i][j] == val){
					exists = true;
				}
			}
		}
		return exists;
	}
	public static boolean checkComplete(){
		boolean complete = true;
		for (int i =0;i<9;i++){
			for (int j=0;j<9;j++){
				if (Sudoku[i][j] == 0){
					complete = false;
				}
			}
		}
		return complete;
	}
	//if there is only one possible number in a square it fills it in
	public static void fillSquareFromOnlyPossible(){
		for (int i = 0;i<9;i++){
			for (int j=0;j<9;j++){
				int possibleNumber = 0;
				int chosenNumber = 0;
				for (int k=0;k<9;k++){
					for (int val = 1;val < 10;val++){
						if (possible[i][j][k] == val){
							chosenNumber = val;
							possibleNumber++;
						}
					}
				}
				if (possibleNumber == 1 && Sudoku[i][j] == 0){
					Sudoku[i][j] = chosenNumber;
					change = true;
					System.out.println("FillSquarePoss " + change);
				}
			}
		}
	}
	//checks to see if square has X as a possible number
	public static boolean checkXPossible (int i, int j, int X){
		for (int k =0;k<9;k++){
			if (possible[i][j][k] == X){
				return true;
			}
		}
		
		return false;
	}
	//if for a number there is only one possible place in the 3x3 grid
	//it fills it in
	public static void onlyPossiblePlace(){
		for (int val = 1;val<10;val++){	
			int valCount = 0;
			int singleIPos = 0;
			int singleJPos = 0;
			for(int i = 0;i<3;i++){
				for(int j =0;j<3;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;		
					}
				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				//System.out.println("Entered " + val + " at " + singleIPos + "," + singleJPos); 
				change = true;
			}
			
			
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 0;i<3;i++){
				for(int j =3;j<6;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}												
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
			
			
			valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 0;i<3;i++){
				for(int j =6;j<9;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
			
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 3;i<6;i++){
				for(int j =0;j<3;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
			
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 3;i<6;i++){
				for(int j =3;j<6;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
					
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 3;i<6;i++){
				for(int j =6;j<9;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}	
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
					
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 6;i<9;i++){
				for(int j =0;j<3;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
			
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 6;i<9;i++){
				for(int j =3;j<6;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}
				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}
			
		    valCount = 0;
			singleIPos = 0;
			singleJPos = 0;
			for(int i = 6;i<9;i++){
				for(int j =6;j<9;j++){
					if (checkXPossible(i,j,val) == true){
						valCount++;
						singleIPos = i;
						singleJPos = j;
					}

				}
			}
			if (valCount == 1 && (Sudoku[singleIPos][singleJPos] == val) == false){
				Sudoku[singleIPos][singleJPos] = val;
				change = true;
			}		
		}
	
	}
	//takes a square and checks the number of possible values in that
	//square and the 2 to the right
	public static int rightThreeCheck(int iPos, int jPos, int val){
		int possibleNumber = 0;
		for(int i = 0;i<3;i++){
			if(possible[i+iPos][jPos][val-1] == val){
				possibleNumber++;
			}
		}
		return possibleNumber;
	}
	//takes a square and checks the number of possible values in that
	//square and the 2 below
	public static int downThreeCheck(int iPos, int jPos, int val){
		int possibleNumber = 0;
		for(int j = 0;j<3;j++){
			if(possible[iPos][j+jPos][val-1] == val){
				possibleNumber++;
			}
		}
		return possibleNumber;		
	}
	//removes possibles from line if in the square it is the only line
	//with that possible
	public static void removeFromLine(){
		for(int j=0;j<3;j++){
			for (int k = 0;k<9;k++){
				if(rightThreeCheck(0,3*j,k+1)>0 && rightThreeCheck(0,3*j+1,k+1)==0 && rightThreeCheck(0,3*j+2,k+1) == 0){
					possible[3][3*j][k] = 0;
					possible[4][3*j][k] = 0;
					possible[5][3*j][k] = 0;
					possible[6][3*j][k] = 0;
					possible[7][3*j][k] = 0;
					possible[8][3*j][k] = 0;
					//System.out.println("Box " + (j+1) + " line 1 " + (k+1));
				}	
				if(rightThreeCheck(0,3*j,k+1)==0 && rightThreeCheck(0,3*j+1,k+1)>0 && rightThreeCheck(0,3*j+2,k+1) == 0){
					possible[3][3*j + 1][k] = 0;
					possible[4][3*j + 1][k] = 0;
					possible[5][3*j + 1][k] = 0;
					possible[6][3*j + 1][k] = 0;
					possible[7][3*j + 1][k] = 0;
					possible[8][3*j + 1][k] = 0;
					//System.out.println("Box " + (j+1) + " line 2 " + (k+1));
				}	
				if(rightThreeCheck(0,3*j,k+1)==0 && rightThreeCheck(0,3*j+1,k+1)==0 && rightThreeCheck(0,3*j+2,k+1) > 0){
					possible[3][3*j + 2][k] = 0;
					possible[4][3*j + 2][k] = 0;
					possible[5][3*j + 2][k] = 0;
					possible[6][3*j + 2][k] = 0;
					possible[7][3*j + 2][k] = 0;
					possible[8][3*j + 2][k] = 0;
					//System.out.println("Box " + (j+1) + " line 3 " + (k+1));
				}	
				
				if(rightThreeCheck(3,3*j,k+1)>0 && rightThreeCheck(3,3*j+1,k+1)==0 && rightThreeCheck(3,3*j+2,k+1) == 0){
					possible[0][3*j][k] = 0;
					possible[1][3*j][k] = 0;
					possible[2][3*j][k] = 0;
					possible[6][3*j][k] = 0;
					possible[7][3*j][k] = 0;
					possible[8][3*j][k] = 0;
					//System.out.println("Box " + (j+2) + " line 1 " + (k+1));
				}	
				if(rightThreeCheck(3,3*j,k+1)==0 && rightThreeCheck(3,3*j+1,k+1)>0 && rightThreeCheck(3,3*j+2,k+1) == 0){
					possible[0][3*j + 1][k] = 0;
					possible[1][3*j + 1][k] = 0;
					possible[2][3*j + 1][k] = 0;
					possible[6][3*j + 1][k] = 0;
					possible[7][3*j + 1][k] = 0;
					possible[8][3*j + 1][k] = 0;
					//System.out.println("Box " + (j+2) + " line 2 " + (k+1));
				}	
				if(rightThreeCheck(3,3*j,k+1)==0 && rightThreeCheck(3,3*j+1,k+1)==0 && rightThreeCheck(3,3*j+2,k+1) > 0){
					possible[0][3*j + 2][k] = 0;
					possible[1][3*j + 2][k] = 0;
					possible[2][3*j + 2][k] = 0;
					possible[6][3*j + 2][k] = 0;
					possible[7][3*j + 2][k] = 0;
					possible[8][3*j + 2][k] = 0;
					//System.out.println("Box " + (j+2) + " line 3 " + (k+1));
				}	

				if(rightThreeCheck(6,3*j,k+1)>0 && rightThreeCheck(6,3*j+1,k+1)==0 && rightThreeCheck(6,3*j+2,k+1) == 0){
					possible[0][3*j][k] = 0;
					possible[1][3*j][k] = 0;
					possible[2][3*j][k] = 0;
					possible[3][3*j][k] = 0;
					possible[4][3*j][k] = 0;
					possible[5][3*j][k] = 0;
					//System.out.println("Box " + (j+3) + " line 1 " + (k+1));
				}	
				if(rightThreeCheck(6,3*j,k+1)==0 && rightThreeCheck(6,3*j+1,k+1)>0 && rightThreeCheck(6,3*j+2,k+1) == 0){
					possible[0][3*j + 1][k] = 0;
					possible[1][3*j + 1][k] = 0;
					possible[2][3*j + 1][k] = 0;
					possible[3][3*j + 1][k] = 0;
					possible[4][3*j + 1][k] = 0;
					possible[5][3*j + 1][k] = 0;
					//System.out.println("Box " + (j+3) + " line 2 " + (k+1));
				}	
				if(rightThreeCheck(6,3*j,k+1)==0 && rightThreeCheck(6,3*j+1,k+1)==0 && rightThreeCheck(6,3*j+2,k+1) > 0){
					possible[0][3*j + 2][k] = 0;
					possible[1][3*j + 2][k] = 0;
					possible[2][3*j + 2][k] = 0;
					possible[3][3*j + 2][k] = 0;
					possible[4][3*j + 2][k] = 0;
					possible[5][3*j + 2][k] = 0;
					//System.out.println("Box " + (j+3) + " line 3 " + (k+1));
				}				
			}
		}
		
		for(int i=0;i<3;i++){
			for (int k = 0;k<9;k++){
				if(downThreeCheck(3*i,0,k+1)>0 && downThreeCheck(3*i+1,0,k+1)==0 && downThreeCheck(3*i+2,0,k+1) == 0){
					possible[3*i][3][k] = 0;
					possible[3*i][4][k] = 0;
					possible[3*i][5][k] = 0;
					possible[3*i][6][k] = 0;
					possible[3*i][7][k] = 0;
					possible[3*i][8][k] = 0;
					//System.out.println("Box " + (j+1) + " line 1 " + (k+1));
				}	
				if(downThreeCheck(3*i,0,k+1)==0 && downThreeCheck(3*i+1,0,k+1)>0 && downThreeCheck(3*i+2,0,k+1) == 0){
					possible[3*i+1][3][k] = 0;
					possible[3*i+1][4][k] = 0;
					possible[3*i+1][5][k] = 0;
					possible[3*i+1][6][k] = 0;
					possible[3*i+1][7][k] = 0;
					possible[3*i+1][8][k] = 0;
					//System.out.println("Box " + (j+1) + " line 2 " + (k+1));
				}	
				if(downThreeCheck(3*i,0,k+1)==0 && downThreeCheck(3*i+1,0,k+1)==0 && downThreeCheck(3*i+2,0,k+1) > 0){
					possible[3*i+2][3][k] = 0;
					possible[3*i+2][4][k] = 0;
					possible[3*i+2][5][k] = 0;
					possible[3*i+2][6][k] = 0;
					possible[3*i+2][7][k] = 0;
					possible[3*i+2][8][k] = 0;
					//System.out.println("Box " + (j+1) + " line 3 " + (k+1));
				}	
				
				if(downThreeCheck(3*i,3,k+1)>0 && downThreeCheck(3*i+1,3,k+1)==0 && downThreeCheck(3*i+2,3,k+1) == 0){
					possible[3*i][0][k] = 0;
					possible[3*i][1][k] = 0;
					possible[3*i][2][k] = 0;
					possible[3*i][6][k] = 0;
					possible[3*i][7][k] = 0;
					possible[3*i][8][k] = 0;
					//System.out.println("Box " + (j+2) + " line 1 " + (k+1));
				}	
				if(downThreeCheck(3*i,3,k+1)==0 && downThreeCheck(3*i+1,3,k+1)>0 && downThreeCheck(3*i+2,3,k+1) == 0){
					possible[3*i+1][0][k] = 0;
					possible[3*i+1][1][k] = 0;
					possible[3*i+1][2][k] = 0;
					possible[3*i+1][6][k] = 0;
					possible[3*i+1][7][k] = 0;
					possible[3*i+1][8][k] = 0;
					//System.out.println("Box " + (j+2) + " line 2 " + (k+1));
				}	
				if(downThreeCheck(3*i,3,k+1)==0 && downThreeCheck(3*i+1,3,k+1)==0 && downThreeCheck(3*i+2,3,k+1) > 0){
					possible[3*i+2][0][k] = 0;
					possible[3*i+2][1][k] = 0;
					possible[3*i+2][2][k] = 0;
					possible[3*i+2][6][k] = 0;
					possible[3*i+2][7][k] = 0;
					possible[3*i+2][8][k] = 0;
					//System.out.println("Box " + (j+2) + " line 3 " + (k+1));
				}	

				if(downThreeCheck(3*i,6,k+1)>0 && downThreeCheck(3*i+1,6,k+1)==0 && downThreeCheck(3*i+2,6,k+1) == 0){
					possible[3*i][0][k] = 0;
					possible[3*i][1][k] = 0;
					possible[3*i][2][k] = 0;
					possible[3*i][3][k] = 0;
					possible[3*i][4][k] = 0;
					possible[3*i][5][k] = 0;
					//.out.println("Box " + (j+3) + " line 1 " + (k+1));
				}	
				if(downThreeCheck(3*i,6,k+1)==0 && downThreeCheck(3*i+1,6,k+1)>0 && downThreeCheck(3*i+2,6,k+1) == 0){
					possible[3*i+1][0][k] = 0;
					possible[3*i+1][1][k] = 0;
					possible[3*i+1][2][k] = 0;
					possible[3*i+1][3][k] = 0;
					possible[3*i+1][4][k] = 0;
					possible[3*i+1][5][k] = 0;
					//System.out.println("Box " + (j+3) + " line 2 " + (k+1));
				}	
				if(downThreeCheck(3*i,6,k+1)==0 && downThreeCheck(3*i+1,6,k+1)==0 && downThreeCheck(3*i+2,6,k+1) > 0){
					possible[3*i+2][0][k] = 0;
					possible[3*i+2][1][k] = 0;
					possible[3*i+2][2][k] = 0;
					possible[3*i+2][3][k] = 0;
					possible[3*i+2][4][k] = 0;
					possible[3*i+2][5][k] = 0;
					//System.out.println("Box " + (j+3) + " line 3 " + (k+1));
				}				
			}
		}		
	}
	public static boolean trySolve(){
		change = true;
		while(checkComplete() == false && change == true){	
			change = false;
			initialTransToPos();
			
			fillSquareFromOnlyPossible();
			onlyPossiblePlace();
			removeFromLine();
			onlyPossiblePlace();
			fillSquareFromOnlyPossible();
			
			System.out.println("!!!" + change);
		}
		
		System.out.println(" ");
		transToPos();
		displayPossible();
		System.out.println(" ");
		displaySudoku();
		
		if(checkComplete() == true){
			return true;
		}
		return false;
	}
	
	public static void assumeTwoChoice(){
		
		boolean found = false;
		int count = 0;
		int tick = 0;
		
		for (int y = 0;y<81;y++){
			storeDoubles[y][0] = -1;
			storeDoubles[y][1] = -1;
		}
		
		System.out.println("assumedTwoChoice");
		for (int i =0;i<9;i++){
			for (int j = 0; j < 9;j++){
				found = false;
				count = 0;
				for (int k = 0;k<9;k++){
					if (possible[i][j][k] == k+1){
						count++;
					}
				}
				if (count == 2){
					System.out.println("done");
					storeDoubles[tick][0] = i;
					storeDoubles[tick++][1] = j;
				}
			}
		}
		for (int s =0;s<81;s++){
			System.out.println(storeDoubles[s][0] + "," + storeDoubles[s][1]);
		}
		int x = 0;
		while (trySolve() == false && storeDoubles[x][0] > -1){
			storePossible = possible;
			storeSudoku = Sudoku;
			int a = 0;
			int b = 0;
			for (int k =0;k<9;k++){
				if(possible[storeDoubles[x][0]][storeDoubles[x][1]][k] == k+1){
					if(a == 0){
						a = k+1;
						
					}else{
						b = k+1;
					}
				}
			}
			for(int p = 0;p<9;p++){
				possible[storeDoubles[x][0]][storeDoubles[x][1]][p] = a;
			}
			Sudoku[storeDoubles[x][0]][storeDoubles[x][1]] = a;
			x++;
			if(trySolve() == false){
				possible = storePossible;
				Sudoku = storeSudoku;
			}
		}
			

	}
	
	
	public static void main(String args[]) {
		try{
			loadSudoku();
		} catch (IOException e){
			System.out.println("Failed to load Sudoku");
		}
		
		loadPossible();
		initialTransToPos();
		displaySudoku();

		

		
/* 		if (trySolve() == false){
			assumeTwoChoice();
			
		} */
		System.out.println("TRY SOLVE");
		System.out.println(trySolve());

		//System.out.println("At position 0, 8 there are " + rightThreeCheck(0,8,2) + " 2's to the right");
		//System.out.println("At position 0, 8 there are " + rightThreeCheck(0,8,1) + " 1's to the right");
		//System.out.println("At position 0, 8 there are " + rightThreeCheck(0,8,7) + " 7's to the right");

	}
	
}
