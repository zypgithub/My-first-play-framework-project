
/*************************************************************************
	> File Name: StrategyImp.java
	> Author: 
	> Mail: 
	> Created Time: Thu 01 Sep 2016 06:17:03 PM CEST
 ************************************************************************/

package controllers;

public class StrategyImp implements StrategyInt
{
	public void show()
	{
		System.out.println("hello, im the strategy one");
	}

}
class StrategyImpThree implements StrategyInt
{
	public void show()
	{
		System.out.println("hello, Im the strategy three");
	}
}

class StrategyImpTwo implements StrategyInt
{
	public void show()
	{
		System.out.println("hello, Im the trategy two");
	}
}

class StrategyImpNull implements StrategyInt
{
	public void show()
	{
		System.out.println("Sorry, Im not a Strategy");
	}
}