package com.codingapi.deeplearning.demo05.learn;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * 特征缩放
 * @author lorne
 * @date 2019-10-31
 * @description
 *  至于为什么将以构造的方式去记录 max,min 为了统一全部特征缩放比例
 */
public class DataSetScalingHelper {

    /**
     * x 的极值
     */
    private double Xmax;
    private double Xmin;

    /**
     * y 的极值
     */
    private double Ymax;
    private double Ymin;

    /**
     * 数据源
     */
    private DataSet data;


    /**
     * 记录极值
     */
    public DataSetScalingHelper(DataSet data) {
        this.data = data;
        this.Xmax = data.getX().maxNumber().doubleValue();
        this.Xmin = data.getX().minNumber().doubleValue();

        this.Ymax = data.getY().maxNumber().doubleValue();
        this.Ymin = data.getY().minNumber().doubleValue();
    }

    /**
     * 缩放数据
     */
    public INDArray scaling(INDArray data,boolean isLabel){
       if(isLabel){
           return  data.sub(Ymin).div((Ymax-Ymin));
       }else{
           return data.sub(Xmin).div((Xmax-Xmin));
       }
    }

    /**
     * 缩放数据
     */
    public INDArray scaling(INDArray data){
       return scaling(data,false);
    }

    public void scalingSelf(){
       data.setX(scaling( data.getX(),false));
       data.setY(scaling(data.getY(),true));
    }

}
