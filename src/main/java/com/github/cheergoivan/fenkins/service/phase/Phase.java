package com.github.cheergoivan.fenkins.service.phase;

public interface Phase {
	void execute();

	default Phase next(Phase phase) {
		return () -> {
			execute();
			phase.execute();
		};
	}
}
