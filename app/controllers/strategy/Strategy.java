/*************************************************************************
	> File Name: Strategy.java
	> Author: 
	> Mail: 
	> Created Time: Thu 01 Sep 2016 05:26:18 PM CEST
 ************************************************************************/
package controllers.strategy;

import java.io.*;
import java.util.*;

import play.*;
import play.api.mvc.RequestHeader;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.filters.csrf.*;

import models.*;

import redis.clients.jedis.*;

import views.html.*;
import views.html.strategytemplate.*;

public class Strategy extends Controller
{
	StrategyInt strategy;
	@AddCSRFToken
	public static Result myresponse()
	{
		return ok(strategyhtml.render((List<Patterns>)Cache.get("patterns")));
		
	}
	public static Result submitStrategyGET(int num)
	{
		Strategy stra = new Strategy();
		stra.SetInterface(num);
		String str = stra.doit();
		return ok(strategyres.render(str.toString(), (List<Patterns>)Cache.get("patterns")));
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
    
    public String doit()
    {
    	return strategy.show();
    }
}


