/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author Khanh Nguyen
 */
public class NoteServlet extends HttpServlet {

    Note note = new Note();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getQueryString();

        if (query != null && query.contains("edit")) {
            // Display the edit form
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
        } else {
            notePopulate();
            request.setAttribute("note", note);
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String contents = request.getParameter("content");

        String path = getServletContext().getRealPath("/WEB-INF/note.txt");

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        pw.println(title);
        pw.println(contents);

        pw.close();

        note = new Note(title, contents);

        request.setAttribute("note", note);

        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);

    }

    private void notePopulate() {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
 
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String title = br.readLine();
            String contents = br.readLine();

            note = new Note(title, contents);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
