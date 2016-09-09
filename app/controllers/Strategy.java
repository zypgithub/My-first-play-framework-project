/*************************************************************************
	> File Name: Strategy.java
	> Author: 
	> Mail: 
	> Created Time: Thu 01 Sep 2016 05:26:18 PM CEST
 ************************************************************************/
package controllers;

import java.io.*;
import java.util.*;

import play.*;
import play.api.mvc.RequestHeader;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.filters.csrf.*;

import models.*;

import redis.clients.jedis.*;

import views.html.*;

public class Strategy extends Controller
{
	StrategyInt strategy;
	@AddCSRFToken
	public static Result myresponse()
	{
		return ok(strategyhtml.render(Patterns.findAll()));
		
	}
	public static Result submitStrategyGET(int num)
	{
		MyStreamControler mystreamcontroler = new MyStreamControler();
		ByteArrayOutputStream os = mystreamcontroler.redirectOutPutToString();
		Strategy stra = new Strategy();
		stra.SetInterface(num);
		stra.doit();
		mystreamcontroler.recoverOutPut();		
		return ok(strategyres.render(os.toString(), Patterns.findAll()));
	}
	@RequireCSRFCheck
	public static Result submitStrategyPOST()
    {
        int num; 
        DynamicForm requestdata = Form.form().bindFromRequest(); 
        num = Integer.parseInt(requestdata.get("strategynum"));
        return submitStrategyGET(num);
        	
	}
    public void SetInterface(int num)
    {
    	this.strategy = new StrategyFactory(num).myinterface;
    }
    
    public void doit()
    {
    	strategy.show();
    }
}


