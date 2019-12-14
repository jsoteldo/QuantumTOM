/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quantum.servlet;

import com.google.gson.Gson;
import com.quantum.bean.AsesoresviewBean;
import com.quantum.bean.SubeprospectosBean;
import com.quantum.dao.ArchivoDAO;
import com.quantum.modelos.Archivo;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Selectequivalencias;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RIO CASMA
 */
@WebServlet(name = "Equivalencias", urlPatterns = {"/Equivalencias"})
public class Equivalencias extends HttpServlet {

    private org.slf4j.Logger log = LoggerFactory.getLogger(Equivalencias.class);      
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet equivalencias</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet equivalencias at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<Selectequivalencias> Lstselectequivalencia = null;
        ArchivoDAO daoarchivo;
        Mensaje msj;
        Mensaje msjprocesado;
        try {
            String inputarray = request.getParameter("json");
            Gson gson = new Gson();
            daoarchivo = new ArchivoDAO();

            Selectequivalencias[] seleccionados = gson.fromJson(inputarray,
                    Selectequivalencias[].class);

            Lstselectequivalencia = Arrays.asList(seleccionados);
           

            Archivo arch = daoarchivo.consultaarchivo();
            SubeprospectosBean enviarchivo = new SubeprospectosBean();
            msj=enviarchivo.procesaArchivo(arch.getDir(), Lstselectequivalencia);
            msjprocesado=daoarchivo.procesar();
            msj.setMensaje(msj.getMensaje()+"</br>"+msjprocesado.getMensaje());
            response(resp, gson.toJson(msj));

        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
    
    
    private void response(HttpServletResponse resp, Object msg)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.print(msg);
                out.flush();
                out.close();
		/*out.print("<body>");
		out.print("<t1>" + msg + "</t1>");
		out.print("</body>");
		out.print("</html>");*/
	}

}
