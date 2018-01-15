package com.github.cheergoivan.fenkins;

import com.github.cheergoivan.fenkins.service.phase.Phase;

public class Phase2 implements Phase{

	@Override
	public void execute() {
		System.out.println(this.getClass());
	}

}
