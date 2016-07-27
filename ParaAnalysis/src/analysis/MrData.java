package analysis;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MrData
{
	private	double[] mrRsrp;
	private	double[] mrRsrq;
//	private	double[] mrPrbNum;
	private	double[] mrPlrUl6;
	private	double[] mrPlrUl8;
	private	double[] mrPlrUl9;
	private	double[] mrPlrDl6;
	private	double[] mrPlrDl8;
	private	double[] mrPlrDl9;
	private	double[] mrSinrUl;
	private	double expMrRsrp;
	private	double expMrRsrq;
//	private	double expMrPrbNum;
	private	double expMrPlrUl;
	private	double expMrPlrDl;
	private	double expMrSinrUl;
	private	int indexExpRsrp;
	private	int indexExpRsrq;
	private	int indexExpSinrUl;
//	private int indexExpPrbNum;
	private double sumMrRsrp;//Rsrp的样本数量和
	private double sumMrRsrq;//Rsrq同上
	
	
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("MR_RSRP加权期望:"+expMrRsrp+"\n");
		sBuilder.append("MR_RSRP加权期望区间:"+indexExpRsrp+"\n");
		sBuilder.append("MR_RSRQ加权期望:"+expMrRsrq+"\n");
		sBuilder.append("MR_RSRQ加权期望区间:"+indexExpRsrq+"\n");
//		sBuilder.append("MR_PRB加权期望:"+expMrPrbNum+"\n");
//		sBuilder.append("MR_PRB加权期望区间:"+indexExpPrbNum+"\n");
		
		
		sBuilder.append("上行MR_PLR加权期望:"+expMrPlrUl+"\n");
		sBuilder.append("下行MR_PLR加权期望:"+expMrPlrDl+"\n");
		
		sBuilder.append("MR_SinrUl加权期望:"+expMrSinrUl+"\n");
		sBuilder.append("MR_SinrUl加权期望区间:"+indexExpSinrUl+"\n");
		
		return sBuilder.toString();
		
	}
	
	public MrData()
	{
		/**
		 * 数据初始化
		 */
		//TODO
		Workbook workbook = null;
		
		String mrNameColumnName = "mrName";//真实值所在列的列名
		int mrNameColumnOrdinal = -1;//真实值所在列的序数，初始值设置为
		
		String mrValueColumnName = "v";//真实值所在列的列名
		int mrValueColumnOrdinal = -1;//真实值所在列的序数，初始值设置为-1
		
		try{
			InputStream in = new FileInputStream("./excel/已选取数据.xls");
			workbook = Workbook.getWorkbook(in);
			Sheet mrSheet = workbook.getSheet(0);
			int mrColumns = mrSheet.getColumns();
			int mrRows = mrSheet.getRows();
			
			for(int i = 0; i < mrColumns; i++){
				Cell cell = mrSheet.getCell(i,0);
				if(mrNameColumnName.equals("" + cell.getContents())){
					mrNameColumnOrdinal = i;
				}
			}
			for(int i = 0; i < mrColumns; i++){
				Cell cell = mrSheet.getCell(i,0);
				if(mrValueColumnName.equals("" + cell.getContents())){
					mrValueColumnOrdinal = i;
				}
			}
			if(mrNameColumnOrdinal != -1 && mrValueColumnOrdinal != -1){
				for(int i = 1; i < mrRows; i++){
					Cell cellOut = mrSheet.getCell(mrNameColumnOrdinal, i);
					String valStrOut = cellOut.getContents() + "";
					
					if(valStrOut != null && !valStrOut.equals("")){
//						System.out.println(i + "--" + valStrOut);
						Cell cellIn = mrSheet.getCell(mrValueColumnOrdinal, i);
						String valStrIn = cellIn.getContents() + "";
						
						if(valStrIn != null && !valStrIn.equals("")){
//							System.out.println(i + "--" + valStrIn);
							String[] strToArray = valStrIn.split(" ");
							double[] strToIntArr = new double[strToArray.length];
							for(int j = 0; j < strToIntArr.length; j++){
								strToIntArr[j] = Double.parseDouble(strToArray[j]);
							}
							switch(valStrOut){
							case "MR.RSRP":
								this.setMrRsrp(strToIntArr);
								break;
							case "MR.RSRQ":
								this.setMrRsrq(strToIntArr);
								break;
//							case "MR.PDSCHPRBNum":
//								this.setMrPrbNum(strToIntArr);
//								break;
							case "MR.PacketLossRateULQci6":
								this.setMrPlrUl6(strToIntArr);
								break;
							case "MR.PacketLossRateULQci8":
								this.setMrPlrUl8(strToIntArr);
								break;
							case "MR.PacketLossRateULQci9":
								this.setMrPlrUl9(strToIntArr);
								break;
							case "MR.PacketLossRateDLQci6":
								this.setMrPlrDl6(strToIntArr);
								break;
							case "MR.PacketLossRateDLQci8":
								this.setMrPlrDl8(strToIntArr);
								break;
							case "MR.PacketLossRateDLQci9":
								this.setMrPlrDl9(strToIntArr);
								break;
							case "MR.SinrUL":
								this.setMrSinrUl(strToIntArr);
								break;
							}
						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			workbook.close();
		}
		
		
		
		
		
		/**
		 * 数据初始化
		 */
		
		
		for(Double d:mrRsrp)
		{
			sumMrRsrp+=d;
		}
		for(Double d:mrRsrq)
		{
			sumMrRsrq+=d;
		}
		
		
		
		expMrRsrp=Util.calRsrpExp(mrRsrp);
		indexExpRsrp=Util.getRsrpIndex(expMrRsrp);
		expMrRsrq=Util.calRsrqExp(mrRsrq);
		indexExpRsrq=Util.getRsrqIndex(expMrRsrq);
//		expMrPrbNum=Util.calPrbExp(mrPrbNum);
//		indexExpPrbNum=Util.getPrbNumIndex(expMrPrbNum);
		expMrPlrUl=Util.calPlrExp(mrPlrUl6, mrPlrUl8, mrPlrUl9);
		expMrPlrDl=Util.calPlrExp(mrPlrDl6, mrPlrDl8, mrPlrDl9);
		expMrSinrUl=Util.calUlSinrExp(mrSinrUl);
		indexExpSinrUl=Util.getSinrUlIndex(expMrSinrUl);
		
	}
	
	
	
	public double getExpMrRsrp() {
		return expMrRsrp;
	}



	public void setExpMrRsrp(double expMrRsrp) {
		this.expMrRsrp = expMrRsrp;
	}



	public double getExpMrRsrq() {
		return expMrRsrq;
	}



	public void setExpMrRsrq(double expMrRsrq) {
		this.expMrRsrq = expMrRsrq;
	}



//	public double getExpMrPrbNum() {
//		return expMrPrbNum;
//	}
//
//
//
//	public void setExpMrPrbNum(double expMrPrbNum) {
//		this.expMrPrbNum = expMrPrbNum;
//	}



	public double getExpMrPlrUl() {
		return expMrPlrUl;
	}



	public void setExpMrPlrUl(double expMrPlrUl) {
		this.expMrPlrUl = expMrPlrUl;
	}



	public double getExpMrPlrDl() {
		return expMrPlrDl;
	}



	public void setExpMrPlrDl(double expMrPlrDl) {
		this.expMrPlrDl = expMrPlrDl;
	}



	public double getExpMrSinrUl() {
		return expMrSinrUl;
	}



	public void setExpMrSinrUl(double expMrSinrUl) {
		this.expMrSinrUl = expMrSinrUl;
	}



	
	
	
	
	public double[] getMrPlrUl6() {
		return mrPlrUl6;
	}


	public void setMrPlrUl6(double[] mrPlrUl6) {
		this.mrPlrUl6 = mrPlrUl6;
	}


	public double[] getMrPlrUl8() {
		return mrPlrUl8;
	}


	public void setMrPlrUl8(double[] mrPlrUl8) {
		this.mrPlrUl8 = mrPlrUl8;
	}


	public double[] getMrPlrUl9() {
		return mrPlrUl9;
	}


	public void setMrPlrUl9(double[] mrPlrUl9) {
		this.mrPlrUl9 = mrPlrUl9;
	}


	public double[] getMrPlrDl6() {
		return mrPlrDl6;
	}


	public void setMrPlrDl6(double[] mrPlrDl6) {
		this.mrPlrDl6 = mrPlrDl6;
	}


	public double[] getMrPlrDl8() {
		return mrPlrDl8;
	}


	public void setMrPlrDl8(double[] mrPlrDl8) {
		this.mrPlrDl8 = mrPlrDl8;
	}


	public double[] getMrPlrDl9() {
		return mrPlrDl9;
	}


	public void setMrPlrDl9(double[] mrPlrDl9) {
		this.mrPlrDl9 = mrPlrDl9;
	}
	
	
	
	public double[] getMrRsrp() {
		return mrRsrp;
	}
	public void setMrRsrp(double[] mrRsrp) {
		this.mrRsrp = mrRsrp;
	}
	public double[] getMrRsrq() {
		return mrRsrq;
	}
	public void setMrRsrq(double[] mrRsrq) {
		this.mrRsrq = mrRsrq;
	}
//	public double[] getMrPrbNum() {
//		return mrPrbNum;
//	}
//	public void setMrPrbNum(double[] mrPrbNum) {
//		this.mrPrbNum = mrPrbNum;
//	}

	public double[] getMrSinrUl() {
		return mrSinrUl;
	}
	public void setMrSinrUl(double[] mrSinrUl) {
		this.mrSinrUl = mrSinrUl;
	}



	public int getIndexExpRsrp()
	{
		return indexExpRsrp;
	}



	public void setIndexExpRsrp(int indexExpRsrp)
	{
		this.indexExpRsrp = indexExpRsrp;
	}



	public int getIndexExpRsrq()
	{
		return indexExpRsrq;
	}



	public void setIndexExpRsrq(int indexExpRsrq)
	{
		this.indexExpRsrq = indexExpRsrq;
	}



	public int getIndexExpSinrUl()
	{
		return indexExpSinrUl;
	}



//	public int getIndexExpPrbNum()
//	{
//		return indexExpPrbNum;
//	}
//
//	public void setIndexExpPrbNum(int indexExpPrbNum)
//	{
//		this.indexExpPrbNum = indexExpPrbNum;
//	}

	public void setIndexExpSinrUl(int indexExpSinrUl)
	{
		this.indexExpSinrUl = indexExpSinrUl;
	}



	public double getSumMrRsrp()
	{
		return sumMrRsrp;
	}



	public void setSumMrRsrp(double sumMrRsrp)
	{
		this.sumMrRsrp = sumMrRsrp;
	}



	public double getSumMrRsrq()
	{
		return sumMrRsrq;
	}



	public void setSumMrRsrq(double sumMrRsrq)
	{
		this.sumMrRsrq = sumMrRsrq;
	}
	
	
}
