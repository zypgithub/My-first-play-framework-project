package annotating;

import models.User;
import play.cache.Cache;
import play.libs.F.Promise;
import play.mvc.*;
import play.mvc.Http.Context;

public class MyAuth extends Action.Simple{

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		String id = ctx.session().get("ID");
		User user;
		if(id == null)
			user = null;
		else
			user = (User)Cache.get(id);
		ctx.args.put("user", user);
		return delegate.call(ctx);
	}

}
