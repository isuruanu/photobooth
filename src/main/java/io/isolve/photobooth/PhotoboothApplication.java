package io.isolve.photobooth;

import com.google.common.io.Resources;
import io.dropwizard.Application;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.isolve.photobooth.resources.CORSResponseFilter;
import io.isolve.photobooth.resources.PhotoClientResource;
import io.dropwizard.assets.AssetsBundle;

/**
 * Created by isuru on 4/9/16.
 */
public class PhotoboothApplication extends Application<PhotoboothConfiguration> {
    @Override
    public void run(PhotoboothConfiguration photoboothConfiguration, Environment environment) throws Exception {
        environment.jersey().register(CORSResponseFilter.class);
        environment.jersey().register(PhotoClientResource.class);
    }

    @Override
    public void initialize(Bootstrap<PhotoboothConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(new MultiPartBundle());
        bootstrap.addBundle(new AssetsBundle("/assets", "/take", "index.htm", "assets"));
    }

    public static void main(String[] args) throws Exception {
        PhotoboothApplication photoboothApplication = new PhotoboothApplication();
        photoboothApplication.run("server", Resources.getResource("photobooth-config.yml").getPath());
    }
}
