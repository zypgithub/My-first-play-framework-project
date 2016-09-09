package controllers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MyStreamControler {

	PrintStream old;
	public ByteArrayOutputStream redirectOutPutToString(){
		ByteArrayOutputStream mystream = new ByteArrayOutputStream();

		//get a print stream
	    PrintStream ps = new PrintStream(mystream);
	    
	    //record old stream.
	    old = System.out;
	    
	    //set new out put stream
	    System.setOut(ps);
	    return mystream;
	}
	public void recoverOutPut()
	{
		System.setOut(old);
	}
}
