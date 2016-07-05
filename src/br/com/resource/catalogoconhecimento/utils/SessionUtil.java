package br.com.resource.catalogoconhecimento.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	      private static HttpSession instance;
	      
	      public static HttpSession getInstance(HttpServletRequest request){
	           if (instance == null){
	               instance = request.getSession();
	           }
	           
	           return instance;
	      }
	      
	
}
