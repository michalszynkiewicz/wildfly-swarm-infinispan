package org.wildfly.swarm.cachebooster;

import org.jboss.logging.Logger;
import org.wildfly.swarm.cachebooster.dto.CacheCheck;
import org.wildfly.swarm.cachebooster.dto.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * mstodo: Header
 *
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 1/31/18
 */
@Path("/")
@Produces("application/json")
public class GreetingResource {

    private static final Logger log = Logger.getLogger(GreetingResource.class);

    @Inject
    private NameCache cache;

    @GET
    @Path("greeting")
    public Message greeting() {
        log.info("getting the greeting");
        String value = cache.get();
        if (value == null) {
            value = takeFromSlowService();
            cache.put(value);
        }
        return new Message(value);
    }

    @GET
    @Path("cached")
    public CacheCheck isCached() {
        log.debug("checking if the value is cached");
        return new CacheCheck(cache.hasValue());
    }

    @DELETE
    @Path("cached")
    public Response deleteFromCache() {
        log.info("removing cached value");
        cache.remove();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private String takeFromSlowService() {
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "foo";
    }
}
