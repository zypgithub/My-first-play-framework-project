package controllers;

public class StrategyFactory {
	StrategyInt myinterface;
	public StrategyFactory(int num)
	{
		switch(num)
		{
		case 1:
			myinterface =  new StrategyImp();
			break;
		case 2:
			myinterface =  new StrategyImpTwo();
			break;
		case 3:
			myinterface =  new StrategyImpThree();
			break;
		default:
			myinterface = new StrategyImpNull();
				
		}
	}

}
