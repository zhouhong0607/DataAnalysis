package analysis;

public class Util
{
	
	/**
	 * 计算UE 期望
	 * @param value
	 * @return
	 */
	public static double calExp(double[] value)
	{
		double exp = 0;
		for (int i = 0; i < value.length; i++)
		{
			exp += value[i];
		}
		exp = exp / value.length;
		return exp;
	}
	/**
	 * 计算RSRP对应区间
	 * @param value
	 * @return
	 */
	public static int getRsrpIndex(double value)
	{
		if (value < -120)
		{
			return 0;
		} else if (value < -115)
		{
			return 1;
		} else if (value < -80)
		{
			return 2 + (int)( value + 115);
		} else if (value < -60)
		{
			return 37 + (int)(( value + 80) / 2);
		} else
		{
			return 47;
		}
	}

	/**
	 * 计算RSRQ对应区间
	 * @param value
	 * @return
	 */
	public static int getRsrqIndex(double value)
	{
		if(value<-19.5)
		{
			return 0;
		}else if (value>-3.5)
		{
			return 17;
		}else
		{
			return (int)(19.5+value)+1;
		}
	}
	/**
	 * 计算上行信噪比对应区间
	 * @param value
	 * @return
	 */
	public static int getSinrUlIndex(double value)
	{
		if(value<-10)
		{
			return 0;
		}else if (value>25)
		{
			return 36;
		}else
		{
			return (int)(10.0+value)+1;
		}
	}
	/**
	 * 计算PRB对应区间
	 * @param value
	 * @return
	 */
	public static int  getPrbNumIndex(double value)
	{
		if(value<=0) return 0;
		else {
			return (int)(value-1)/10+1;
		}
	}
	
	
	
	
	/**
	 * 计算Rsrp加权期望
	 * @param value
	 * @return
	 */
	public static double calRsrpExp(double[] value)
	{
		double sum=0;//分母
		double wSum=0;//分子
		wSum+=value[0]*120.0;
		sum+=value[0];
		wSum+=value[1]*117.5;
		sum+=value[1];
		
		double cur=114.5;
		for(int i=2;i<=36;i++)
		{
			wSum+=value[i]*cur;
			sum+=value[i];
			cur-=1;
		}
		cur=79.0;
		for(int i=37;i<=46;i++)
		{
			wSum+=value[i]*cur;
			sum+=value[i];
			cur-=2;
		}
		wSum+=value[47]*60;
		sum+=value[47];
		double result;
		result=-(wSum/sum);
		return result;
	}
	/**
	 * 计算Rsrq加权期望
	 * @param value
	 * @return
	 */
	public static double calRsrqExp(double[] value)
	{
		double sum=0;//分母
		double wSum=0;//分子
		wSum+=value[0]*19.5;
		sum+=value[0];
		double cur=18.75;
		for(int i=1;i<=16;i++)
		{
			wSum+=value[i]*cur;
			sum+=value[i];
			cur-=1;
		}
		wSum+=value[17]*3;
		sum+=value[17];
		double result;
		result=-(wSum/sum);
		return result;
	}
	/**
	 * 计算PRB加权期望
	 * @param value
	 * @return
	 */
	public static double calPrbExp(double[] value)
	{
		double sum=0;//分母
		double wSum=0;//分子
		wSum+=0;
		sum+=value[0];
		
		double cur=5;
		for(int i=1;i<=10;i++)
		{
			wSum+=value[i]*cur;
			sum+=value[i];
			cur+=10;
		}
		
		double result;
		result=wSum/sum;
		return result;
	}
	/**
	 * 计算PLR加权期望（上行、下行）
	 * @param value6
	 * @param value8
	 * @param value9
	 * @return
	 */
	public static double calPlrExp(double[] value6,double[] value8,double[] value9)
	{
		double sum=0;//分母
		double wSum=0;//分子
		double curSum=0;
		curSum=value6[0]+value8[0]+value9[0];
		sum+=curSum;
		wSum+=curSum*0.001;
		
		curSum=value6[1]+value8[1]+value9[1];
		sum+=curSum;
		wSum+=curSum*0.0035;
		
		curSum=value6[2]+value8[2]+value9[2];
		sum+=curSum;
		wSum+=curSum*0.0075;
		
		double cur=0.015;
		for(int i=3;i<=11;i++)
		{
			curSum=value6[i]+value8[i]+value9[i];
			sum+=curSum;
			wSum+=curSum*cur;
			
			cur+=0.01;
		}
		cur=0.11;
		for(int i=12;i<=16;i++)
		{
			curSum=value6[i]+value8[i]+value9[i];
			sum+=curSum;
			wSum+=curSum*cur;
			
			cur+=0.02;
		}
		
		cur=0.225;
		for(int i=17;i<=22;i++)
		{
			curSum=value6[i]+value8[i]+value9[i];
			sum+=curSum;
			wSum+=curSum*cur;
			
			cur+=0.05;
		}
		
		cur=0.55;
		for(int i=23;i<=27;i++)
		{
			curSum=value6[i]+value8[i]+value9[i];
			sum+=curSum;
			wSum+=curSum*cur;
			
			cur+=0.1;
		}
		double result;
		result=wSum/sum;
		return result;
	}
	/**
	 * 计算上行信噪比加权期望
	 * @param value
	 * @return
	 */
	public static double calUlSinrExp(double[] value)
	{
		double sum=0;//分母
		double wSum=0;//分子
		wSum+=value[0]*(-10.0);
		sum+=value[0];
		double cur=-9.5;
		for(int i=1;i<=35;i++)
		{
			wSum+=value[i]*cur;
			sum+=value[i];
			cur+=1;
		}
		wSum+=value[36]*25;
		sum+=value[36];
		double result;
		result=wSum/sum;
		return result;
	}
}
