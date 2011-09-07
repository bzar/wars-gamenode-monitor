package fi.iki.bzar;

import java.io.IOException;

import fi.iki.bzar.gamenode.*;
import org.json.*;

public class WarsMonitor {
	public static void main(String[] args) {
		final GamenodeClient client = new GamenodeClientImpl(new GamenodeSkeletonImpl(new Object() {
			public String echo(Object params) {
				String s = (String) params;
				return s;
			}
		}));
		
		client.onConnect(new Callback() {
			@Override
			public void exec(Object params) {
				try {
					client.sendMessage("Hello World!");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
		client.onMessage(new Callback() {
			@Override
			public void exec(Object params) {
				System.out.println("Received message! " + params.toString());
			}
		});
		
		client.onError(new Callback() {
			@Override
			public void exec(Object params) {
				System.err.println("ERROR: " + params.toString());
			}
		});
		try {
			client.connect("ws://localhost:8888");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
