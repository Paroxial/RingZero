package me.paroxial.ringzero.clicker.listener;

import lombok.RequiredArgsConstructor;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import me.paroxial.ringzero.clicker.Clicker;

@RequiredArgsConstructor
public class MouseListener implements NativeMouseListener {
	private final Clicker clicker;

	@Override
	public void nativeMouseClicked(NativeMouseEvent event) {
		// NO-OP
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent event) {
		// if the button is a left press, we'll skip it if it's from a Robot, otherwise we'll set the respective fields
		if (event.getButton() == NativeMouseEvent.BUTTON1) {
			if (clicker.isSkippingNextPress()) {
				clicker.setSkippingNextPress(false);
			} else {
				clicker.setFirstClick(true);
				clicker.setPressed(true);
			}
		}
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent event) {
		// if the button is a left release, we'll skip it if it's from a Robot, otherwise we'll set the respective fields
		if (event.getButton() == NativeMouseEvent.BUTTON1) {
			if (clicker.isSkippingNextRelease()) {
				clicker.setSkippingNextRelease(false);
			} else {
				clicker.setPressed(false);
			}
		}
	}
}
