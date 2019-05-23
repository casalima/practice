package com.sandro.orion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class WordFinder 
{
	/**
	 * Iterate through each line of input.
	 */
	int letterIndex = 0;
	char[] lettersOfWord;  

	static char[][] board = {	{'A','D','F','A'},
								{'S','J','S','S'},
								{'A','D','E','E'}  };
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		WordFinder evaluator = new WordFinder();    
		String line;

		// Instructions: How to use the program 
		
		System.out.println("\n" + "The following board will be scanned to find a provided \"string\" \n"  );
		
		for (int x=0; x < board.length; x++) {
			for (int y=0; y < board[x].length; y++ ) {
				System.out.print(board[x][y] + " ");
			}
			System.out.println("");
		}

		System.out.println("\nSearches will evaluate adjacent boxes (North/South/East/West) when looking for the string ");
		System.out.println("E.g.: The strings \"JSFA\" \"ADESJD\" \\\"EEDJ\\\"will be found." );
		
		System.out.println("============================================================================================");
		System.out.println("\n" + "Enter string to search and hit <ENTER> (an empty string will finish the program).\n\n"  );
		
		// Start test
		while ((line = in.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			} else {
				if ( evaluator.checkWord(line) ) {
					System.out.println("String " + line + " was found. \n");				
				} else {
					System.out.println("String " + line + " was not found. \n");
				}				
			}
		}
	}

	public void printBoard() {
	}
	
	public boolean checkWord(String word) {

		//initialize board
		char[][] board = {	{'A','D','F','A'},
							{'S','J','S','S'},
							{'A','D','E','E'}  };

		lettersOfWord = word.toCharArray();
		letterIndex = 0;
		
		// Scan  board for first letter of word  
		for (int x=0; x<3; x++ ){
			for (int y=0; y<4; y++ ){ 
				
				//System.out.println(board[x][y]);
				if ( recursiveCheck(clone2DArray(board), x, y, letterIndex) ) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean recursiveCheck(char[][] board, int x, int y, int letterIndex ){

		boolean found = false; 
				
		if (board[x][y] == lettersOfWord[letterIndex]){

			// clean matched box 
			board[x][y] = ' ';
			
			if (lettersOfWord.length == letterIndex + 1) {	// Have we reached the end of the word 
				return (true);
			}

			char[][] boardCopy = clone2DArray(board);
			
			// if coordinates still in limits, check square above
			if ( x-1 >= 0 && board[x-1][y] != ' ' ){ 
				//found = recursiveCheck(board.clone(), x-1, y, letterIndex + 1);
				found = recursiveCheck(boardCopy, x-1, y, letterIndex + 1);
			}
		
			// Check square below		
			if ( (! found) && (x+1 <= 2) && board[x+1][y] != ' ' ) {
				found = recursiveCheck(boardCopy, x+1, y, letterIndex + 1);
			} 
			
			// check square left
			if ( (! found) && (y-1 >= 0) && board[x][y-1] != ' ' ) {
				found = recursiveCheck(boardCopy, x, y-1 , letterIndex + 1);
			}

			// check square right
			if ( (! found) && (y+1 <= 3) && board[x][y+1] != ' ' ) {
				found = recursiveCheck(boardCopy, x, y+1 , letterIndex + 1);
			}

		}
		
		return (found);

	} 
	
	
	public char[][] clone2DArray(char[][] originalArr){
		
		char[][] copyArr = new char[originalArr.length][];
		
		for (int i=0; i<originalArr.length; i++) {
			copyArr[i] = originalArr[i].clone();
		}
		
		return copyArr;
		
	}	
	
}
