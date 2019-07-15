package com.cpu.log.cpulog;

import java.util.Date;
import java.util.Random;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CaptureCpuLog {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		System.out.println("The time is now " + dateFormat.format(new Date()));
		this.calculateTime();
	}

	private void calculateTime() {

		for (int i = 0; i < 30; i++) {
			long start = System.nanoTime();
			int cpuCount = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
			Random random = new Random(start);
			int seed = Math.abs(random.nextInt());
			log("Starting Test with " + cpuCount + " CPUs and random number:" + seed);
			int primes = 10000;
			long startCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			start = System.nanoTime();
			while (primes != 0) {
				if (isPrime(seed)) {
					primes--;
				}
				seed++;

			}
			float cpuPercent = calcCPU(startCPUTime, start, cpuCount);
			log("CPU Util:" + cpuPercent);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}

	}

	public void log(Object message) {
		System.out.println(message);
	}

	public int calcCPU(long cpuStartTime, long elapsedStartTime, int cpuCount) {
		long end = System.nanoTime();
		long totalAvailCPUTime = cpuCount * (end - elapsedStartTime);
		long totalUsedCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - cpuStartTime;
		System.out.println("Total CPU Time:" + totalUsedCPUTime + " ns.");
		System.out.println("Total Avail CPU Time:" + totalAvailCPUTime + " ns.");
		float per = ((float) totalUsedCPUTime * 100) / (float) totalAvailCPUTime;
		System.out.println("%:" + per);
		return (int) per;
	}

	private boolean isPrime(int n) {
		// 2 is the smallest prime
		if (n <= 2) {
			return n == 2;
		}
		// even numbers other than 2 are not prime
		if (n % 2 == 0) {
			return false;
		}
		// check odd divisors from 3
		// to the square root of n
		for (int i = 3, end = (int) Math.sqrt(n); i <= end; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
