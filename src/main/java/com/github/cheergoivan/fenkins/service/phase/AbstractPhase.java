package com.github.cheergoivan.fenkins.service.phase;

public abstract class AbstractPhase implements Phase{
	protected Context context;

	public AbstractPhase(Context context) {
		this.context = context;
	}
}
