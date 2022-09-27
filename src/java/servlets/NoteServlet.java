/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
import java.util.*;
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

        notePopulate();
        request.setAttribute("note", note);

        if (request.getParameter("edit") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        noteEdit(title, setNewLine(content));
        request.setAttribute("note", note);
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
    }

    private void notePopulate() {

        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String title = br.readLine();
            String content = br.readLine();
            note = new Note(title, content);
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void noteEdit(String title, String content) {

        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
            pw.println(title);
            pw.println(content);
            pw.close();
            note = new Note(title, content);
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static String setNewLine(String input) {
        Scanner scan = new Scanner(input);
        ArrayList newLine = new ArrayList<>();

        while (scan.hasNextLine()) {
            newLine.add(scan.nextLine());
        }
        String result = String.join("<br>", newLine);

        return result;
    }

}
