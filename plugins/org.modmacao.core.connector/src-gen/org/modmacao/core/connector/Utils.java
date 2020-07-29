package org.modmacao.core.connector;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import metric.Metric;
import metric.MetricFactory;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class Utils {

	private static Logger LOGGER = LoggerFactory.getLogger(Utils.class);
	private Properties props;
	
	private Metric m;
	private String filename = "";
	private String currentMemory = "0";
	private String currentTime = "0";

	private String absoluteFilePath = "";
	
//	private String actualMemoryDeploy = "0";
//	private String actualMemoryUndeploy = "0";
//	private String actualMemoryStart = "0";
//	private String actualMemoryStop = "0";
//	private String actualMemoryConfigure = "0";
//	
//	private String predictedMemoryDeploy = "0";
//	private String predictedMemoryUndeploy = "0";
//	private String predictedMemoryStart = "0";
//	private String predictedMemoryStop = "0";
//	private String predictedMemoryConfigure = "0";
//	
//	private String actualTimeDeploy = "0";
//	private String actualTimeUndeploy = "0";
//	private String actualTimeStart = "0";
//	private String actualTimeStop = "0";
//	private String actualTimeConfigure = "0";
//	
//	private String predictedTimeDeploy = "0";
//	private String predictedTimeUndeploy = "0";
//	private String predictedTimeStart = "0";
//	private String predictedTimeStop = "0";
//	private String predictedTimeConfigure = "0";	
	

	public Utils(String filename) {
		this.filename = filename;
		this.m = MetricFactory.eINSTANCE.createMetric();
		createMetricFolderAndFile();
	}

	public Metric getMetric() {
		return m;
	}
	
	
	public void getPredictedValue() {
		readPredictedValues();
//		m.setPredictedMemoryDeploy(predictedMemoryDeploy);
//		m.setPredictedMemoryUndeploy(predictedMemoryUndeploy);
//		m.setPredictedMemoryStart(predictedMemoryStart);
//		m.setPredictedMemoryStop(predictedMemoryStop);
//		m.setPredictedMemoryConfigure(predictedMemoryConfigure);
//
//		m.setPredictedTimeDeploy(predictedTimeDeploy);
//		m.setPredictedTimeUndeploy(predictedTimeUndeploy);
//		m.setPredictedTimeStart(predictedTimeStart);
//		m.setPredictedTimeStop(predictedTimeStop);
//		m.setPredictedTimeConfigure(predictedTimeConfigure);
	}
	
	public void getActualValues(StatusType status){
		getActualMetricFromRemote();
		storeActualValuesInFile(status);
		switch (status) {
			case DEPLOY:
				m.setActualMemoryDeploy(currentMemory);
				m.setActualTimeDeploy(currentTime);
				break;
			case START:
				m.setActualMemoryStart(currentMemory);
				m.setActualTimeStart(currentTime);
				break;
			case UNDEPLOY:
				m.setActualMemoryUndeploy(currentMemory);
				m.setActualTimeUndeploy(currentTime);
				break;
			case STOP:
				m.setActualMemoryStop(currentMemory);
				m.setActualTimeStop(currentTime);
				break;
			case CONFIGURE:
				m.setActualMemoryConfigure(currentMemory);
				m.setActualTimeConfigure(currentTime);
				break;
			default:
				break;
		}	
	}

	private void getActualMetricFromRemote() {
		 String fileString;
		 try {
		 	fileString = new String(Files.readAllBytes(Paths.get("/home/ubuntu/" + filename + ".log")),
		 			StandardCharsets.UTF_8);
		 	String[] result = fileString.split(", ");
		 	currentMemory = result[0];
		 	currentTime = result[1];
		 } catch (IOException e) {
		 	LOGGER.error(e.toString());
		 }

	}

	private void createMetricFolderAndFile() {
		String role_path = this.getProperties().getProperty("ansible_rolespath");
		absoluteFilePath = role_path + "metric/"+filename+"/";
		File theDir = new File(absoluteFilePath);
		if (!theDir.exists())
			theDir.mkdirs();	
		try {
			new File(absoluteFilePath + "deploy" + ".log").createNewFile();
			new File(absoluteFilePath + "undeploy" + ".log").createNewFile();
			new File(absoluteFilePath + "stop" + ".log").createNewFile();
			new File(absoluteFilePath + "start" + ".log").createNewFile();
			new File(absoluteFilePath + "configure" + ".log").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void storeActualValuesInFile(StatusType status) {
		String textToAppend = currentMemory + ", "+currentTime+"\n";
		Path path = Paths.get(absoluteFilePath + status.getResponse()+".log");
		try {
			Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void readPredictedValues() {
		try {
			for (StatusType status: StatusType.values()) {
				List<String> allLines = Files.readAllLines(Paths.get(absoluteFilePath+status.getResponse()+".log"));
				predictMetrics(allLines, status);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void predictMetrics(List<String> allLines, StatusType status) {
		double memorySum = 0.0;
		double timeSum = 0.0;
		try {
			for (String line : allLines) {
				String[] metricLines = line.split(", ");
				memorySum += Double.valueOf(metricLines[0]);
				timeSum += Double.valueOf(metricLines[1]);
			}
		} catch(Exception ex) {
			memorySum = 0;
			timeSum = 0;
		}
		String averageMemorySum = String.valueOf(memorySum / allLines.size());
		String averageTimeSum = String.valueOf(timeSum/allLines.size());
		switch (status) {
			case DEPLOY:
				m.setPredictedMemoryDeploy(averageMemorySum);
				m.setPredictedTimeDeploy(averageTimeSum);
				break;
			case START:
				m.setPredictedMemoryStart(averageMemorySum);
				m.setPredictedTimeStart(averageTimeSum);
				break;
			case UNDEPLOY:
				m.setPredictedMemoryUndeploy(averageMemorySum);
				m.setPredictedTimeUndeploy(averageTimeSum);
				break;
			case STOP:
				m.setPredictedMemoryStop(averageMemorySum);
				m.setPredictedTimeStop(averageTimeSum);
				break;
			case CONFIGURE:
				m.setPredictedMemoryConfigure(averageMemorySum);
				m.setPredictedTimeConfigure(averageTimeSum);
				break;
			default:
				break;
		}	
	}
	
	private Properties getProperties() {
		if (props == null)
			loadProperties();
		return props;
	}
	
	private Properties loadProperties() {
		props = new Properties();
		InputStream input = null;
		try {
			String filename = "ansible.properties";
			Bundle bundle = FrameworkUtil.getBundle(this.getClass());
			if (bundle != null) {
				URL url = FrameworkUtil.getBundle(this.getClass()).getResource(filename);
				input = url.openConnection().getInputStream();
			}
			if (input == null) {
				input = this.getClass().getClassLoader().getResourceAsStream(filename);
			}
			props.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}


}
