package maven.com.linkedin.learning;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

/**
 * JAX-RS server side events.
 * Sending event to client from server, a kind of ping.
 * @author Vineesh.Chauhan
 *
 */
@Path("sse")
public class MySSEResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@Path("guest/{guest}/salute")
	@GET
	public void saluteSSE(@Context SseEventSink sseEventSink, @Context Sse sseUtil, @PathParam("guest") String guest) {
		buildAndPublishSSE(sseEventSink, sseUtil, guest);
	}

	private void buildAndPublishSSE(SseEventSink sseEventSink, Sse sseUtil, String guest) {

		new Thread(() -> {
			for (int count = 0; count < 10; count++) {
				OutboundSseEvent sseEvent = sseUtil.newEvent("Hello " + guest);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sseEventSink.send(sseEvent);
			}
		}).start();
	}
}
