package com.lastation.exercise.bookSrore.in.business.factory;

import com.lastation.exercise.bookSrore.in.business.ebi.InEbi;
import com.lastation.exercise.bookSrore.in.business.ebo.InEbo;

public class InEbiFactory {
	private InEbiFactory(){
	}
	
	public static InEbi getEbi() {
		return new InEbo();
	}
}
