
/*************************************************************************
	> File Name: StrategyImp.java
	> Author: 
	> Mail: 
	> Created Time: Thu 01 Sep 2016 06:17:03 PM CEST
 ************************************************************************/

package controllers.strategy;

public class StrategyImp implements StrategyInt
{
	public String show()
	{
		return "hello, im the strategy one";
	}

}
class StrategyImpThree implements StrategyInt
{
	public String show()
	{
		return "hello, Im the strategy three";
	}
}

class StrategyImpTwo implements StrategyInt
{
	public String show()
	{
		return "hello, Im the trategy two";
	}
}

class StrategyImpNull implements StrategyInt
{
	public String show()
	{
		return "Sorry, Im not a Strategy";
	}
}