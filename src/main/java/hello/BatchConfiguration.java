package hello;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	//tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<Student> reader() {
		return new FlatFileItemReaderBuilder<Student>()
				.name("studentItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.studentDetails(new String[] {"classroomId", "studentId", "studentFirstName", "studentMidInit", "studentLastName"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {{
					setTargetType(Student.class);
				}})
				.build();
	}
	
	@Bean
	public StudentItemProcessor processor() {
		return new StudentItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Student> writer(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Student>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO students (classroom_id, student_id, student_first_name, student_mid_init, student_last_name) VALUES(:classroomId, :studentId, :studentFirstName, :studentMidInit, :studentLastName)")
				.dataSource(dataSource)
				.build();
	}
	
	// end::readerwriterprocessor[]
	
	// tag::jobstep[]
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<Student> writer) {
		return stepBuilderFactory.get("step1")
				.<Student, Student> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}
	// end::jobstep[]
}