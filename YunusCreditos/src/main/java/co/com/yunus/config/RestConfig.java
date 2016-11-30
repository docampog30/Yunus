package co.com.yunus.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestConfig extends Application
{
   @Override
   public Set<Class<?>> getClasses(){
      Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(AppExceptionMapper.class);
      return classes;
   }
}
