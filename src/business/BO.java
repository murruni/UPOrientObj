package business;

import control.Handler;

public class BO {

	private Handler handler;

	public BO(Handler h) {
		this.handler = h;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
