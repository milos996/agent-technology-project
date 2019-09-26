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
	
	public static final char 
		MSG_REGISTER 	= 'r', 
		MSG_DEREGISTER 	= 'd', 
		MSG_GET_STATE	= 'g',
		MSG_STORE_STATE	= 's';
	
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
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		if (message == null || message.isEmpty())
			throw new IllegalArgumentException("Messages cannot be empty.");

//		char cmd = message.charAt(0);
//		String content = message.substring(1);
//		switch (cmd) {
//		case MSG_REGISTER:
//			doRegister(content, session);
//			break;
//		case MSG_DEREGISTER:
//			doUnregister(content, session);
//			break;
//		case MSG_GET_STATE:
//			//doGetState(content, session);
//			break;
//		case MSG_STORE_STATE:
//			//doStoreState(content);
//			break;
//		}
	}

	public void sendMessageToClient(@Observes @Default String msg) {
		System.out.println(msg);
		synchronized (this.connectedSession) {
			if (connectedSession != null) {
				connectedSession.getAsyncRemote().sendText(msg.toString());
			}
		}
	}

//	private void doRegister(String platformId, Session session) {
//		System.out.println("REGISTUJE SE SOCKET");
//		System.out.println(platformId);
//		sessions.put(platformId, session);
//	}
//
//	private void doUnregister(String platformId, Session session) {
//		System.out.println("UBIJA SE SESSIJA SA KLIJENTOM");
//		sessions.remove(platformId);
//	}
}
