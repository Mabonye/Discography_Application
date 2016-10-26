package com.mycompany.discography.plainTextResponse;

import com.mycompany.discography.model.Artist;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author mima
 */
@Path("/artistInfo")
public class ArtistResource {
    
    private List<Artist> getArtistList(String artistName){
       
        String track_list = String.format("  Innuendo%n  I'm Going Slightly Mad%n  Headlong%n  I Can't Live With You%n  Ride The Wild Wind%n  All God's People" +
                                 "%n  These Are The Days Of Our Lives%n  Delilah%n  Don't Try So Hard%n  The Hitman%n  Bijou%n  The Show Must Go On");
        List<Artist> artistList = new ArrayList<>();
        
        if(artistName.equals("Queen")){
            //Create an artist 
            Artist new_artist = new Artist();
            new_artist.setArtistName("Queen");
            new_artist.setAlbumName("Innuendo");
            new_artist.setYear(1991);
            new_artist.setTracklist(track_list);
            artistList.add(new_artist);
        }
        return artistList;
    }
    
    // Retuns the deteils of an artist in plain text
    // Use http://localhost:8084/Discography/artistInfo/plain/Queen
    @Path("/plain/{artistName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN) 
    public Response getArtistNamePlain(@PathParam("artistName") String artistName) {
       
        String result = ""; 
        List<Artist> artistList = getArtistList(artistName);
        for(Artist artist1:artistList){
            String artist_name = "Artist: " + artist1.getArtistName();
            String artist_album = "\n" + "Album: " + artist1.getAlbumName();
            String album_year = "\n" + "Year: " + artist1.getYear();
            String track_list = "\n" + "Tracklist: " + "\n" + artist1.getTracklist();           
            result = result + artist_name + artist_album + album_year + track_list;
        
        }
        return Response.status(200).entity(result).build();
    }
    
    @DELETE 
    @Path("/{artistName}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response deleteArtistByName(@PathParam("artistName") String artistName) {
        
        List<Artist> artistList = getArtistList(artistName);
        for(Artist artist1:artistList){
            String artist_name = artist1.getArtistName();
	    if("Queen".equals(artist_name)){
		return Response.status(204).build();
	    } 
        }
        return Response.status(200).build();
    }
}
