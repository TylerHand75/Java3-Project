package FunStuff.Spotify;

import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SongServlet", value = "/song")
public class SongServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String trackId = request.getParameter("trackId");
        String trackName = request.getParameter("trackName");
        if(trackId == null){
            trackId = "Fail";
        }

        request.setAttribute("trackName", trackName);
        request.setAttribute("trackId", trackId);

        request.getRequestDispatcher("WEB-INF/Funstuff/song.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
