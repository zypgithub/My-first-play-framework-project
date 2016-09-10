package models;
import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.*;
import play.db.ebean.Model.*;

@Entity
public class User extends Model{
	@Id
	public long id;
	@Required
	public String username;
	@Required
	public String password;
	@Email
	public String email;
	
	public static Finder<Integer, User> find = 
			new Finder<Integer, User>(Integer.class, User.class);
	public static List<User> findAll()
	{
		return find.all();
	}
}
