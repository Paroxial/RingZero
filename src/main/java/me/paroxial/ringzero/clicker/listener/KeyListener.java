package me.paroxial.ringzero.clicker.listener;

import lombok.RequiredArgsConstructor;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import me.paroxial.ringzero.clicker.Clicker;

@RequiredArgsConstructor
public class KeyListener implements NativeKeyListener {
	private final Clicker clicker;

	@Override
	public void nativeKeyTyped(NativeKeyEvent event) {
		// NO-OP
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent event) {
		// if the pressed key is GRAVE, we'll toggle the clicker
		if (event.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE) {
			clicker.setToggled(!clicker.isToggled());
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent event) {
		// NO-OP
	}
}
