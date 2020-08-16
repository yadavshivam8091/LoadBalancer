package io.ys.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.ys.config.TargetConfig;
import io.ys.scheduling.LoadBalance;
import io.ys.scheduling.RoundRobin;

@Service("loadbalancer.loadbalanceadapter")
public class LoadBalanceAdapter {

	private static TargetConfig hostConfig;
	private AlgorithmType LoadBalanceTechnique = null;

	@Autowired
	public LoadBalanceAdapter(@Qualifier("loadbalancer.hostconfig") TargetConfig hostConfig) {
		LoadBalanceAdapter.hostConfig = hostConfig;
		try {
		final String algoName = hostConfig.getLoadBalanceingAlgoritm() != null ? hostConfig.getLoadBalanceingAlgoritm() : "round-robin";
		this.LoadBalanceTechnique = AlgorithmType.valueOf(algoName);
		}catch (Exception e) {
			this.LoadBalanceTechnique =  null;
		}
	}

	public LoadBalance get() {
		return new RoundRobin(hostConfig.getHost());
//		switch (LoadBalanceTechnique) {
//
//		case Random: {
//			System.out.println("heree");
//		}
//		default: {
//			System.out.println("hereree>>>>>>>"+hostConfig.getHost());
//			return new RoundRobin(hostConfig.getHost());
//		}
//		}
	}
}
