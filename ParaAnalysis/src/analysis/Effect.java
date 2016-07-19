package analysis;

public class Effect {
	// 对应的幂次
	private static final int POW_DRSRP = 2;
	private static final int POW_ERSRP = 3;

	private static final int POW_DRSRQ = 2;
	private static final int POW_ERSRQ = 3;

	private static final int POW_EPRB = 50;

	private static final int POW_EULSINR = 3;

	private static final int PRB_NUM_ALL = 100;// 总连接数
	private UeData ueData;
	private MrData mrData;

	private double dRsrp;
	private double rRsrp;
	private double eRsrp;

	private double dRsrq;
	private double rRsrq;
	private double eRsrq;

	private double rPrbNum;
	private double ePrb;

	private double eDlPlr;

	private double eUlPlr;

	private double eUlSinr;

	private double e;
	private double eUl;
	private double eDl;

	public Effect(UeData ue, MrData mr) {
		this.ueData = ue;
		this.mrData = mr;
		// RSRP影响程度计算
		double curDrsrp1 = (mr.getIndexExpRsrp() - ue.getIndexRsrp())
				+ Math.abs(mr.getIndexExpRsrp() - ue.getIndexRsrp());
		double curDrsrp2 = Math.pow((47.0 - mr.getIndexExpRsrp()) / 48, POW_DRSRP);
		dRsrp = 0.5 * curDrsrp1 / 48 * curDrsrp2;
		rRsrp = mr.getMrRsrp()[ue.getIndexRsrp()] / mr.getSumMrRsrp();
		eRsrp = Math.pow(1 - rRsrp, POW_ERSRP) * dRsrp;
		// RSRP影响程度计算
		double curDrsrq1 = (mr.getIndexExpRsrq() - ue.getIndexRsrq())
				+ Math.abs(mr.getIndexExpRsrq() - ue.getIndexRsrq());
		double curDrsrq2 = Math.pow((17.0 - mr.getIndexExpRsrp()) / 18, POW_DRSRQ);
		dRsrq = 0.5 * curDrsrq1 / 18 * curDrsrq2;
		rRsrq = mr.getMrRsrq()[ue.getIndexRsrq()] / mr.getSumMrRsrq();
		eRsrq = Math.pow(1 - rRsrq, POW_ERSRQ) * dRsrq;
		// PRB影响程度计算
		rPrbNum = mr.getExpMrPrbNum() / PRB_NUM_ALL;
		ePrb = Math.pow(1 - rPrbNum, POW_EPRB);
		// PLR影响程度
		eDlPlr = mr.getExpMrPlrDl();
		eUlPlr = mr.getExpMrPlrUl();
		// SInr影响程度
		double curSinr=1.0 - mr.getIndexExpSinrUl() / 37.0;
		eUlSinr = Math.pow(curSinr, POW_EULSINR);

		eDl = eRsrp * 0.3 + eRsrq * 0.3 + ePrb * 0.2 + eDlPlr * 0.2;
		eUl = eRsrp * 0.3 + eRsrq * 0.3 + eUlSinr * 0.2 + eUlPlr * 0.2;

		e = eUl * 0.3 + eDl * 0.7;
	}

	public double getrPrbNum() {
		return rPrbNum;
	}

	public void setrPrbNum(double rPrbNum) {
		this.rPrbNum = rPrbNum;
	}

	public static int getPrbNumAll() {
		return PRB_NUM_ALL;
	}

	public double getdRsrp() {
		return dRsrp;
	}

	public void setdRsrp(double dRsrp) {
		this.dRsrp = dRsrp;
	}

	public double getrRsrp() {
		return rRsrp;
	}

	public void setrRsrp(double rRsrp) {
		this.rRsrp = rRsrp;
	}

	public double getdRsrq() {
		return dRsrq;
	}

	public void setdRsrq(double dRsrq) {
		this.dRsrq = dRsrq;
	}

	public double getrRsrq() {
		return rRsrq;
	}

	public void setrRsrq(double rRsrq) {
		this.rRsrq = rRsrq;
	}

	public UeData getUeData() {
		return ueData;
	}

	public void setUeData(UeData ueData) {
		this.ueData = ueData;
	}

	public MrData getMrData() {
		return mrData;
	}

	public void setMrData(MrData mrData) {
		this.mrData = mrData;
	}

	public double getE() {
		return e;
	}

	public void setE(double e) {
		this.e = e;
	}

	public double geteUl() {
		return eUl;
	}

	public void seteUl(double eUl) {
		this.eUl = eUl;
	}

	public double geteDl() {
		return eDl;
	}

	public void seteDl(double eDl) {
		this.eDl = eDl;
	}

	public double geteRsrp() {
		return eRsrp;
	}

	public void seteRsrp(double eRsrp) {
		this.eRsrp = eRsrp;
	}

	public double geteRsrq() {
		return eRsrq;
	}

	public void seteRsrq(double eRsrq) {
		this.eRsrq = eRsrq;
	}

	public double getePrb() {
		return ePrb;
	}

	public void setePrb(double ePrb) {
		this.ePrb = ePrb;
	}

	public double geteDlPlr() {
		return eDlPlr;
	}

	public void seteDlPlr(double eDlPlr) {
		this.eDlPlr = eDlPlr;
	}

	public double geteUlPlr() {
		return eUlPlr;
	}

	public void seteUlPlr(double eUlPlr) {
		this.eUlPlr = eUlPlr;
	}

	public double geteUlSinr() {
		return eUlSinr;
	}

	public void seteUlSinr(double eUlSinr) {
		this.eUlSinr = eUlSinr;
	}

	public static int getPowDrsrp() {
		return POW_DRSRP;
	}

	public static int getPowErsrp() {
		return POW_ERSRP;
	}

	public static int getPowDrsrq() {
		return POW_DRSRQ;
	}

	public static int getPowErsrq() {
		return POW_ERSRQ;
	}

	public static int getPowEprb() {
		return POW_EPRB;
	}

	public static int getPowEulsinr() {
		return POW_EULSINR;
	}

	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("RSRP影响程度:" + eRsrp + "\n");
		sBuilder.append("RSRQ影响程度:" + eRsrq + "\n");
		sBuilder.append("UE PDSCH信道占用PRB数影响程度:" + ePrb + "\n");
		sBuilder.append("下行丢包率影响程度:" + eDlPlr + "\n");
		sBuilder.append("上行丢包率影响程度:" + eUlPlr + "\n");
		sBuilder.append("上行信干噪比影响程度:" + eUlSinr + "\n");
		sBuilder.append("上行信道影响程度:" + eUl + "\n");
		sBuilder.append("下行信道影响程度:" + eDl + "\n");
		sBuilder.append("综合无线环境影响程度:" + e + "\n");
		return sBuilder.toString();
	}

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		String[] rsrp = {"-76 -76 -74 -74 -74 -74 -74 -74 -74 -74 -75 -74 -74 -77 -77 -75 -75 -75 -75 -75 -75 -75 -75 -75 -81 -81 -81 -81 -81 -77 -77 -77 -77 -76 -76 -76 -76 -76 -76 -76 -76 -76 -74 -74 -74 -74 -74 -74 -74 -75 -74 -74 -74 -77 -77 -75 -75 -75 -75 -75 -75 -75 -75 -75 -81 -81 -81 -81 -81 -77 -77 -77 -77 -76 -76 -76 -76 -76 -76 -76 -74 -74 -74 -77 -77 -75 -75 -75 -75 -75 -75 -75 -75 -75 -81 -81 -81 -81 -81 -77"
						,"-107 -107 -109 -109 -109 -109 -108 -108 -108 -108"
						,"-111 -111 -110 -110 -110 -113 -113 -113 -111 -111"
				
		};
		String[] rsrq = {"-9 -9 -7 -7 -8 -8 -8 -8 -8 -10 -8 -10 -10 -8 -8 -8 -8 -8 -8 -8 -8 -8 -7 -7 -8 -8 -8 -8 -8 -8 -8 -8 -8 -9 -9 -9 -9 -9 -9 -9 -9 -9 -7 -7 -8 -8 -8 -8 -8 -8 -10 -10 -10 -8 -8 -8 -8 -8 -8 -8 -8 -8 -7 -7 -8 -8 -8 -8 -8 -8 -8 -8 -8 -9 -9 -9 -9 -9 -9 -9 -10 -10 -10 -8 -8 -8 -8 -8 -8 -8 -8 -8 -7 -7 -8 -8 -8 -8 -8 -8"
						,"-11 -11 -12 -12 -12 -12 -13 -13 -12 -12 -12 -12"
						,"-12 -12 -13 -13 -13 -15 -15 -15 -15 -13"
				
		};
		UeData ueData = new UeData(rsrp[2], rsrq[2]);
		MrData mrData = new MrData();
		Effect effect = new Effect(ueData, mrData);
		System.out.println(ueData.toString());
		System.out.println(mrData.toString());
		System.out.println(effect.toString());

	}
}
