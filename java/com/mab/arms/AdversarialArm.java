package com.mab.arms;

import com.mab.IArm;

public class AdversarialArm implements IArm {
	double m_t;
	double m_active_start;
	double m_active_end;
	
	public AdversarialArm(double t, double active_start, double active_end){
		m_t = t;
		m_active_start = active_start;
		m_active_end = active_end;
	}

	@Override
	public double draw() {
		m_t++;
		
		if ((m_active_start <= m_t) && (m_t <= m_active_end)){
			return 1.0;
		}else{
			return 0.0;
		}
	}

}
