package models;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.*;

@Entity
public class Patterns extends Model{
	
	@Id
	@Required
	@Column(unique = true)
	public String name;
	
	public String URL;
	
	public static Finder<Integer, Patterns> find = 
			new Finder<Integer, Patterns>(Integer.class, Patterns.class);
	public static List<Patterns> findAll()
	{
		return find.all();
	}

}
