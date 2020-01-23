package me.paroxial.ringzero.clicker;

import lombok.Getter;
import lombok.Setter;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import me.paroxial.ringzero.clicker.gui.Gui;
import me.paroxial.ringzero.clicker.listener.KeyListener;
import me.paroxial.ringzero.clicker.listener.MouseListener;
import me.paroxial.ringzero.clicker.thread.ShutdownThread;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clicker {
	// NOTE: values of 16 or above aren't guaranteed to be undetectable due to the nature of Minecraft's tick loop
	private static final int CPS = 15;

	private Robot robot;
	private Gui gui;

	@Getter
	@Setter
	private boolean toggled, pressed, firstClick, skippingNextPress, skippingNextRelease;
	private long lastClickTime;

	public Clicker() {
		// adding a shutdown hook for close operations
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());

		// disabling JNativeHook's logger
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);

		// initializing our Robot and setting up our native listeners
		try {
			robot = new Robot();

			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(new KeyListener(this));
			GlobalScreen.addNativeMouseListener(new MouseListener(this));
		} catch (AWTException | NativeHookException e) {
			e.printStackTrace();
		}

		// initializing our Gui; we're doing this afterwards in case the above operations throw an exception
		gui = new Gui();

		// handling the clicker loop
		while (gui.isVisible()) {
			try {
				int sleepTime = 1000 / CPS / 2;

				/*
				 * we don't want the first click handled as a Robot click because we haven't released the mouse yet, so
				 * we just sleep and then release so we can move on to the Robot clicks
				 */
				if (toggled && firstClick) {
					firstClick = false;
					robot.delay(250);
					mouseRelease();
				} else if (toggled && pressed && System.currentTimeMillis() - lastClickTime > sleepTime) {
					mousePress();
					robot.delay(sleepTime);
					mouseRelease();
					lastClickTime = System.currentTimeMillis();
				} else {
					Thread.sleep(1L);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Two methods representing mouse presses and releases.
	 *
	 * We want to skip the next press and release operations so that our native listeners don't handle them as a human
	 * click, since Java cannot distinguish between human and Robot clicks.
	 */
	private void mousePress() {
		skippingNextPress = true;
		robot.mousePress(16);
	}

	private void mouseRelease() {
		skippingNextRelease = true;
		robot.mouseRelease(16);
	}
}
