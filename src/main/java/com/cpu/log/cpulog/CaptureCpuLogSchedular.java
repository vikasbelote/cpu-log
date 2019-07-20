package com.cpu.log.cpulog;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cpu.log.utils.CaptureCpuLog;

@Component
public class CaptureCpuLogSchedular {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		
		System.out.println("The time is now " + dateFormat.format(new Date()));
		CaptureCpuLog captureCpuLog = new CaptureCpuLog();
		captureCpuLog.createLog();
	}
}
