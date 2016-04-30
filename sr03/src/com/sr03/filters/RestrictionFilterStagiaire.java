package com.sr03.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sr03.beans.Utilisateur;


public class RestrictionFilterStagiaire implements Filter {
    public static final String ATT_SESSION_USER_ID = "utilisateur_ID";
    public static final String ATT_SESSION_USER_TYPE = "utilisateur_Type";
    public static final String ACCES_CONNEXION  = "/index";


    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if ( session.getAttribute( ATT_SESSION_USER_ID ) == null || session.getAttribute( ATT_SESSION_USER_TYPE ) == null) {    	      
            /* Redirection vers la page publique */
            request.getRequestDispatcher( ACCES_CONNEXION ).forward( request, response );
        	
        } else {
        	String type = session.getAttribute( ATT_SESSION_USER_TYPE ).toString();
        	if (!type.equals("stagiaire")) {
                request.getRequestDispatcher( ACCES_CONNEXION ).forward( request, response );
        	}
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }
}