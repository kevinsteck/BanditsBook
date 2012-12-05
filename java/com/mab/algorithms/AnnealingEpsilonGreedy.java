package com.mab.algorithms;

import java.util.Random;
import com.mab.IBandit;

public class AnnealingEpsilonGreedy implements IBandit {
	private int[] m_counts;
	private double[] m_values;
	
	private double m_maxVal;
	private int m_maxValIndex = 0;
	
	public AnnealingEpsilonGreedy(int num_arms){
		m_counts = new int[num_arms];
		m_values = new double[num_arms];
	}

	@Override
	public int select_arm() {
		Random rand = new Random();
		int countsSum = 1;
		for(int i : m_counts)
			countsSum += i;
		
		// TODO: make these all doubles? may be safer...
		double epsilon = 1 / Math.log(countsSum + 0.0000001);
		if(rand.nextDouble() > epsilon){
			return m_maxValIndex;
		}else{
			return rand.nextInt(m_counts.length);
		}
	}

	// TODO: put this in a base abstract
	@Override
	public void update(int arm_index, double reward) {
		int curCount = ++m_counts[arm_index];
		double curValue = m_values[arm_index];
		
		double newValue = ((curCount -1) / curCount) * curValue + (1 / curCount) * reward;
		m_values[arm_index] = newValue;
		
		if(newValue > m_maxVal){ 
			m_maxValIndex = arm_index;
			m_maxVal = newValue;
		}
	}
}
