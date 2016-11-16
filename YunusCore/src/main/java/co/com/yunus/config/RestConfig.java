package co.com.yunus.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import co.com.yunus.application.rest.MinistrosServices;
import co.com.yunus.application.rest.PartidasServices;
import co.com.yunus.application.rest.ReportesServices;

public class RestConfig extends Application
{
   @Override
   public Set<Class<?>> getClasses(){
      Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(PartidasServices.class);
      classes.add(MinistrosServices.class);
      classes.add(AppExceptionMapper.class);
      classes.add(ReportesServices.class);
      return classes;
   }
}
