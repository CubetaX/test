package cn.weather.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.weather.servlet.*;;

public class Search extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		String id = request.getParameter("id");
		System.out.println("id:" + id);
		
		try {
			DBConnection db = new DBConnection();
			ResultSet rs = db.executeQuery("select * from question where id ="+id+"");
			String question = "";
			String optA = "";
			String optB = "";
			String optC = "";
			String optD = "";
			
			while(rs.next()){
				question=rs.getString(2);
				optA=rs.getString(3);
				optB=rs.getString(4);
				optC=rs.getString(5);
				optD=rs.getString(6);
			}
			db.close();
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
			obj.put("result",question);
			obj.put("optA", optA);
			obj.put("optB", optB);
			obj.put("optC", optC);
			obj.put("opt", optD);
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
