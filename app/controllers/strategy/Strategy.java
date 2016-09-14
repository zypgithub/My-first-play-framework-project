/*************************************************************************
	> File Name: Strategy.java
	> Author: 
	> Mail: 
	> Created Time: Thu 01 Sep 2016 05:26:18 PM CEST
 ************************************************************************/
package controllers.strategy;

import java.io.*;
import java.util.*;

import annotating.MyAuth;
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
	@With(MyAuth.class)
	public static Result myresponse()
	{
		User user = (User) ctx().args.get("user");
		return ok(strategyhtml.render((List<Patterns>)Cache.get("patterns"), user));
		
	}
	
	@With(MyAuth.class)
	public static Result submitStrategyGET(int num)
	{
		Strategy stra = new Strategy();
		stra.SetInterface(num);
		String str = stra.doit();
		User user = (User) ctx().args.get("user");
		return ok(strategyres.render(str.toString(), (List<Patterns>)Cache.get("patterns"), user));
	}
	
	@RequireCSRFCheck
	@With(MyAuth.class)
	public static Result submitStrategyPOST()
    {
        int num; 
		User user = (User) ctx().args.get("user");
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


