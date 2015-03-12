import java.util.ArrayList;
import java.util.List;

public class KPM
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String str = "AbhishekisthbestboytobeAbhishekisgoingto be amazing good but nkoiAbuishek of it but once again gringotypeAbhishekisthbestbostTo";

		char[] charArray = str.toCharArray();
		int pointer = 0;
		List<String> list = new ArrayList<>();
		List<String> dummy = new ArrayList<>();

		if (charArray.length > 1)
		{
			for (int i = 1; i < charArray.length; i++)
			{
					if(charArray[i] == charArray[pointer]){
						dummy.add(""+charArray[i]);
						pointer++;
					}else{
						pointer = 0;
						if(list.size() < dummy.size()){
							list.clear();
							list.addAll(dummy);
						}
						dummy.clear();
					}
			}
		}
		else
		{
			System.err.println(str);
		}
		
		if(dummy.size() > list.size()){
			list.clear();list.addAll(dummy);
		}
		System.out.println(list);
	}

}
