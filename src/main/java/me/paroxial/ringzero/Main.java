package me.paroxial.ringzero;

import me.paroxial.ringzero.clicker.Clicker;

/**
 * RingZero - an auto-clicker for Minecraft cheating
 * <p>
 * The goal of this clicker is to stay as undetectable as possible from anti-cheats and screen shares yet still be
 * unusable with client-side anti-cheats such as CheatBreaker, as they can distinguish from non-human clicks.
 * <p>
 * TODO: use our own modified version of the JNativeHook library.
 *
 * @author Paroxial
 */
public class Main {
	public static void main(String[] args) {
		// initializing our Clicker instance
		new Clicker();
	}
}
