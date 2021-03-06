package io.isolve.photobooth.resources;

import io.isolve.photobooth.api.*;
import io.isolve.photobooth.util.IdUtil;
import org.apache.commons.codec.binary.Base64InputStream;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by isuru on 4/9/16.
 */
@Path("/")
public class PhotoClientResource {

    IdUtil idUtil = new IdUtil();
    private final String location = "/projects/photobooth/uploads";

    @GET
    @Path("/admin")
    public Response adminPage() {
        try {
            return Response.temporaryRedirect(new URI("/take/admin.html")).build();
        } catch (URISyntaxException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/")
    public Response indexPage() {
        try {
            return Response.temporaryRedirect(new URI("/take/index.html")).build();
        } catch (URISyntaxException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/admin/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public PendingImages getPendingImages() {
        try {
            List<PendingImage> filesNameList = Files.list(Paths.get(location)).map(path -> new PendingImage("/client/images/" + path.getFileName().toString())).collect(Collectors.toList());
            PendingImages pendingImages = new PendingImages();
            pendingImages.setPendingImages(filesNameList);
            return pendingImages;
        } catch (IOException e) {
            throw new WebApplicationException("No images found", HttpStatus.NOT_FOUND_404);
        }
    }

    @POST
    @Path("/client/print")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response newPhotoRequest(@FormDataParam("fileData") InputStream data,
                                    @FormDataParam("fileData") FormDataContentDisposition fileMetaData) {
        try {
            for(int i = 0 ; i < "data:image/jpeg;base64".length(); i++) {
                data.read();
            }
            Files.copy(new Base64InputStream(data), Paths.get(location, idUtil.uniqeId() + ".jpg"));
        } catch (IOException e) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/client/images/{image}")
    public Response getImage(@PathParam("image") String image) {
        File file = Paths.get(location, image).toFile();
        String contentType = new MimetypesFileTypeMap().getContentType(file);
        return Response.ok(file, contentType).build();
    }

    @GET
    @Path("/client/print/status")
    @Produces(MediaType.APPLICATION_JSON)
    public PrintJobStatus currentStatus() {
        PrintJobStatus printJobStatus = new PrintJobStatus();
        printJobStatus.setCompleted(63);
        printJobStatus.setAllSubmitted(67);
        printJobStatus.setError(4);
        return printJobStatus;
    }

    @GET
    @Path("/client/print/status/history")
    @Produces(MediaType.APPLICATION_JSON)
    public PrintJobHistory statusHistory() {
        PrintJobHistory printJobHistory = new PrintJobHistory();
        printJobHistory.setPrintJobHistoryEntries(Arrays.asList(
                new PrintJobHistoryEntry(new PrintJobStatus(10, 10, 0), "12:10"),
                new PrintJobHistoryEntry(new PrintJobStatus(12, 14, 1), "12:20"),
                new PrintJobHistoryEntry(new PrintJobStatus(13, 14, 2), "12:30"),
                new PrintJobHistoryEntry(new PrintJobStatus(15, 15, 0), "12:40"),
                new PrintJobHistoryEntry(new PrintJobStatus(12, 14, 1), "12:50"),
                new PrintJobHistoryEntry(new PrintJobStatus(14, 14, 1), "13:00")
        ));
        return printJobHistory;
    }
}
