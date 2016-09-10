import play.*;
import play.cache.Cache;
import models.Patterns;

public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app)
	{
		Cache.set("patterns", Patterns.findAll());
	}
}
