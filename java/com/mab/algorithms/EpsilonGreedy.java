package com.mab.algorithms;

import java.util.Random;

import com.mab.IBandit;

public class EpsilonGreedy implements IBandit {
	private double m_epsilon;
	private int[] m_counts;
	private double[] m_values;
	
	private double m_maxVal;
	private int m_maxValIndex = 0;
	
	public EpsilonGreedy(float epsilon, int num_arms){
		m_counts = new int[num_arms];
		m_values = new double[num_arms];
		m_epsilon = epsilon;
	}

	@Override
	public int select_arm() {
		Random rand = new Random();
		if(rand.nextFloat() > m_epsilon){
			// max of array
			return m_maxValIndex;
		}else{
			return rand.nextInt(m_counts.length);
		}
	}

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
