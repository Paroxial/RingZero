package me.paroxial.ringzero.clicker.thread;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ShutdownThread extends Thread {

	@Override
	public void run() {
		System.out.println("Shutting down...");

		try {
			File file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());

			Closeable closeable = (Closeable) getClass().getClassLoader(),
					parentCloseable = (Closeable) getClass().getClassLoader().getParent();

			closeable.close();
			parentCloseable.close();

			file.delete();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		// TODO: handle shutdown (self-destruct, etc.)
	}
}
