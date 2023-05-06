package FunStuff.Spotify;


import se.michaelthelin.spotify.model_objects.specification.Track;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TracksServlet", value = "/tracks")
public class TracksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String albumId = request.getParameter("albumId");
        String albumName = request.getParameter("albumName");
        if(albumId == null){
            albumId = "Fail";
        }

        request.setAttribute("albumName", albumName);
        request.setAttribute("albumId", albumId);
        Track[] tracks = MySpotify.getTracks(albumName);
        request.setAttribute("tracks", tracks);
        request.getRequestDispatcher("WEB-INF/Funstuff/tracks.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
