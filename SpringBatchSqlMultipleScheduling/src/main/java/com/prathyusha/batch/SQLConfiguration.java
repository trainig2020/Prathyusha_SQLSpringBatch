package com.prathyusha.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.prathyusha.writer.DatabaseWriter;



@Configuration
@EnableBatchProcessing
public class SQLConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

    static Resource[] resources;

	
	

	public SQLConfiguration() {
		// TODO Auto-generated constructor stub
	}



	public Resource[] getResources() {
		
		
		return resources;
	}



	public void setResources(Resource[] resources) {
		this.resources = resources;
	}

	
	@Bean
	@StepScope
	@Qualifier
	public MultiResourceItemReader<String> reader1() throws Exception {
		MultiResourceItemReader<String> resourceItemReader = new MultiResourceItemReader<String>();
		ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

		
		Resource[] resources = {};
		String filePath = "file:";

		
		resources = patternResolver.getResources(filePath + "E:\\sqlfiles/*_1.sql");
		resourceItemReader.setResources(resources);
		resourceItemReader.setDelegate(reader());

		return resourceItemReader;

	}

	@Bean
	@StepScope
	@Qualifier
	public MultiResourceItemReader<String> reader2() throws Exception {

		MultiResourceItemReader<String> resourceItemReader = new MultiResourceItemReader<String>();
		
		SQLConfiguration spd = new SQLConfiguration();
		resources = spd.getResources();
		for (Resource resource : resources) {
			System.out.println("resource name : " + resource.getFilename());
		}

		resourceItemReader.setResources(resources);
		resourceItemReader.setDelegate(reader());

		return resourceItemReader;

	}
	
	@Bean
	public FlatFileItemReader<String> reader() throws Exception {

		FlatFileItemReader<String> reader = new FlatFileItemReader<String>();
		reader.setLineMapper(new PassThroughLineMapper());

		
		return reader;
	}
	
	@Bean
	public DatabaseWriter writer() {
		return new DatabaseWriter();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.afterPropertiesSet();
		taskExecutor.getActiveCount();

		return taskExecutor;
	}
	

	@Bean(name ="autoScheJob" )
	public Job autoSchJob() throws Exception {
		return jobBuilderFactory.get("autoScheJob").incrementer(new RunIdIncrementer()).start(step1()).build();
	}

	@Bean(name ="manualScheJob" )
	public Job job2() throws Exception {
		return jobBuilderFactory.get("manualScheJob").start(step2()).build();
	}
	

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<String, String>chunk(3).reader(reader1()).writer(writer()).taskExecutor(taskExecutor()).build();
	}
	
	@Bean
	public Step step2() throws Exception {
		return stepBuilderFactory.get("step2").<String, String>chunk(3).reader(reader2()).writer(writer()).taskExecutor(taskExecutor()).build();
	}
}
