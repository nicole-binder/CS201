/**
 * Nicole Binder and Margaret Anne Puzak 2019 Feb 19
 * SearchTimer.java
 * Creates a class to time Linear and Binary Searches
 * ....
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class SearchTimer {
	private LinearSearchableCollection linearCollection;
	private BinarySearchableCollection binaryCollection;
	private String searchWord;

	public SearchTimer(LinearSearchableCollection collectionList, String word){
		linearCollection = collectionList;
		searchWord = word;
	}

	public SearchTimer(BinarySearchableCollection collectionList, String word){
		binaryCollection = collectionList;
		searchWord = word;
	}

	public void linearSearchTime(){
		long startTime = System.currentTimeMillis();
		for ( int k = 0; k < 20000; k++) {
				linearCollection.find(searchWord);
		}
		long timeElapsed = System.currentTimeMillis() - startTime;
		System.out.format("Time for Linear Search: %.4f seconds\n", (double)timeElapsed / 1000.0);
	}

	public void binarySearchTime(){
		long startTime = System.currentTimeMillis();
		for (int k = 0; k < 20000; k++) {
				binaryCollection.find(searchWord);
		}
		long timeElapsed = System.currentTimeMillis() - startTime;
		System.out.format("Time for Binary Search: %.4f seconds\n", (double)timeElapsed / 1000.0);
	}
}
