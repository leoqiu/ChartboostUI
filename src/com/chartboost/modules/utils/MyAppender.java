package com.chartboost.modules.utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * log4j appender
 * 
 * No applicable yet
 * 
 * 
 * @author Leo
 *
 */

public class MyAppender extends AppenderSkeleton {

	private final ConcurrentHashMap<Long, BufferedWriter> tid2file = new ConcurrentHashMap<Long, BufferedWriter>();

	private final String outputDir = System.getProperty("outputdir");
	private final String outputFile = outputDir + "output.log.grouped.txt";
	private final String ext = ".threadlog.txt";
	
	
	public MyAppender() {
		try {
			if (outputDir != null) {
				Files.deleteIfExists(FileSystems.getDefault().getPath(
						outputFile));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void close(){
		
	}

	@Override
	public boolean requiresLayout() {
		
		return false;
	}

	@Override
	public void append(LoggingEvent event) {
//		if (outputDir == null)
//			return; // by default nothing appended, see comments on top
		try {
			long tid = Thread.currentThread().getId();
System.out.println(tid);
			BufferedWriter fw = tid2file.get(tid);
			if (fw == null) {
				fw = new BufferedWriter(new FileWriter(
						getFileNameFromThreadID(tid)));
				tid2file.put(tid, fw);
			}
			fw.write(event.getMessage().toString());
			fw.write("\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String getFileNameFromThreadID(long tid) {
		return String.format("%sthread_output_%04d%s", outputDir, tid, ext);
	}

}
