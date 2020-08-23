package demo;

import java.io.IOException;
import java.nio.MappedByteBuffer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public  class mapper extends Mapper<LongWritable, Text, Text, LongWritable>
{

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		String line=value.toString();
		String words[]=line.split(" ");
		for(String word:words)
		{
			
			context.write(new Text(word), new LongWritable(1));
			
			
		}
		
		
		
	}

	
}
