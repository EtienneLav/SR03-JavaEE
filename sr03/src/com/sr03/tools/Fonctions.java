package com.sr03.tools;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sr03.beans.*;
import com.sr03.DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Fonctions {

	public Time timeDifferenceWithNow(Time _temps_debut){
		
		int secondes_debut = _temps_debut.getSeconds();
		int minutes_debut = _temps_debut.getMinutes();
		int heures_debut = _temps_debut.getHours();

		int total_second_debut = heures_debut *3600 + minutes_debut * 60 + secondes_debut;
		
		Date date_actuelle = new Date();
		Time temps_actuel = new Time(date_actuelle.getTime());
		int secondes_actuelles = temps_actuel.getSeconds();
		int minutes_actuelles = temps_actuel.getMinutes();
		int heures_actuelles = temps_actuel.getHours();

		int total_second_now = heures_actuelles *3600 + minutes_actuelles * 60 + secondes_actuelles;
		
		int intervalle_temps = total_second_now - total_second_debut;
		
		int heures_questionnaire = intervalle_temps/3600;
		intervalle_temps = intervalle_temps - heures_questionnaire*3600;
		
		int minutes_questionnaire = intervalle_temps/60;
		intervalle_temps = intervalle_temps - minutes_questionnaire*60;
		
		Time difference_temps = new Time(heures_questionnaire, minutes_questionnaire, intervalle_temps );
		
		return difference_temps;
	}
	
	public String parsedStringToBeSearched(String _chaine_a_parser){
		
		String chaine_parsee;
		
		chaine_parsee = _chaine_a_parser;
		String[] splited = chaine_parsee.split("\\s+");
		String chaine_rassemblee = "";
		
		for(int i = 0; i< (splited.length); i++)
			chaine_rassemblee = chaine_rassemblee+"%"+splited[i];
		
		chaine_parsee = "'"+chaine_rassemblee+"%'";
	
		return chaine_parsee;
	}
}
