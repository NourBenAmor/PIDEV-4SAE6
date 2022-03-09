package tn.Pi.Service;

import org.springframework.stereotype.Service;

@Service
public class ServiceConstants {
	  public  String n = "/";
      public  String n2 = "\\";
      public  String projectPath = System.getProperty("user.dir").replace(n2, n);
}
