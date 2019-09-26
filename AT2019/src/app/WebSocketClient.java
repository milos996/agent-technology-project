package app;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/web-socket")
@Singleton
public class WebSocketClient {
	@SuppressWarnings("unused")
	private Session connectedSession = null;
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("cao");
		this.connectedSession = session;
		synchronized (this.connectedSession) {
			if (connectedSession != null) {
				connectedSession.getAsyncRemote().sendText("Pozz");
			}
		}
	}

	@OnClose
	public void onClose(CloseReason closeReason) {
		connectedSession = null;
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		if (message == null || message.isEmpty())
			throw new IllegalArgumentException("Messages cannot be empty.");

	}

	public void sendMessageToClient(@Observes @Default String msg) {
		System.out.println(msg);
		synchronized (this.connectedSession) {
			if (connectedSession != null) {
				connectedSession.getAsyncRemote().sendText(msg.toString());
			}
		}
	}
}
