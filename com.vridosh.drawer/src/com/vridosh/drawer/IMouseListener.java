package com.vridosh.drawer;

import java.util.Set;

public interface IMouseListener {
	enum MouseButton {
		LEFT,
		RIGHT,
		MIDDLE
	};

	void onMouseButtonDown(MouseButton btn, int x, int y);
	void onMouseButtonUp(MouseButton btn, int x, int y);
	void onMouseButtonMove(Set<MouseButton> btns, int x, int y);
}
