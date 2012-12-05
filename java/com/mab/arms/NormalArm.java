package com.mab.arms;

import java.util.Random;

import com.mab.IArm;

public class NormalArm implements IArm {
	// mean
	private double m_mu;
	// standard deviation
	private double m_sigma;
	
	public NormalArm(double mu, double sigma){
		m_mu = mu;
		m_sigma = sigma;
	}
	
	@Override
	public double draw() {
		Random rand = new Random();
		//TODO: definitely not the way to do this but it works
		return (rand.nextGaussian() * m_sigma) + m_mu;
	}

}
