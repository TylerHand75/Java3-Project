package FunStuff.Spotify;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlbumServlet", value = "/albums")
public class AlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String artistId = request.getParameter("artistId");
        String artistName = request.getParameter("artistName");
        if(artistId == null){
            artistId = "Fail";
        }
        request.setAttribute("artistName", artistName);
        request.setAttribute("artistId", artistId);
        AlbumSimplified[] albums = MySpotify.getAlbum(artistName);
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("WEB-INF/Funstuff/album.jsp").forward(request,response);

    }

}
