package com.kkpa.datasctructures.arrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class JavaArrayList {

    private static Map<Integer, List<Integer>> dataLinesMap = new HashMap<>();
    
    private static List<Query> queries = new ArrayList<Query>();

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

	int totalLines = scan.nextInt();
	fillData(totalLines);
	
	int totalQueries = scan.nextInt();
	fillQueries(totalQueries);
	
	applyQueriesAndShowResult();
	
    }

    

    private static void fillQueries(int totalQueries) {
	IntStream.rangeClosed(1, totalQueries).
		forEach( q -> {
		    int x = scan.nextInt();
		    int y = scan.nextInt();
		    Query query = new JavaArrayList.Query(x,y); 
		    queries.add(query);
		});
    }

    private static void fillData(int totalLines) {
	IntStream.rangeClosed(1, totalLines).forEach(n -> {
	    int d = scan.nextInt();
	    List<Integer> lineData = fillLineData(d);
	    dataLinesMap.put(n, lineData);
	});
    }

    private static List<Integer> fillLineData(int lenghtCollection) {
	List<Integer> lineData = new ArrayList<>();
	if (lenghtCollection >= 0) {
	    IntStream.rangeClosed(1, lenghtCollection).forEach(l -> lineData.add(scan.nextInt()));
	}
	return lineData;
    }
    
    private static void applyQueriesAndShowResult() {
	final String ERROR = "ERROR!";
	for (Query querie : queries) {
	    int line = querie.getxPosition();
	    List<Integer> dataLine = dataLinesMap.get(line);
	    if (dataLine != null) {
		int positionOfLine = querie.getyPosition();
		if (dataLine.size() >= positionOfLine) {
		    print(dataLine.get(positionOfLine - 1));
		}
		else {
		    print(ERROR);
		}
	    }
	    else {
		print(ERROR);
	    }
	}
    }
    
    private static void print(Object result) {
	System.out.println(result);
    }
    
    private static class Query {
	
	private int xPosition;
	private int yPosition;
	
	public Query (int x , int y) {
	    this.xPosition = x;
	    this.yPosition = y;
	}
	
	public int getxPosition() {
	    return xPosition;
	}
	public void setxPosition(int xPosition) {
	    this.xPosition = xPosition;
	}
	public int getyPosition() {
	    return yPosition;
	}
	public void setyPosition(int yPosition) {
	    this.yPosition = yPosition;
	}
    }
}
