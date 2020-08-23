package demo;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

class reduce extends Reducer<Text,LongWritable,Text,LongWritable>
{

	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		

		long sum=0;
		for(LongWritable value:values)
		{
			sum=sum+value.get();
			
			
			
			
		}
		
		context.write(key, new LongWritable(sum));
		
	}
	

}
