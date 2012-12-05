package com.mab.arms;

import java.util.Random;

import com.mab.IArm;

public class BernoulliArm implements IArm {
	private double m_p;
	
	public BernoulliArm(double p){
		m_p = p;
	}
	
	@Override
	public double draw() {
		Random rand = new Random();
		if(rand.nextDouble() > m_p){
			return 0.0;
		}else{
			return 1.0;
		}
	}

}
