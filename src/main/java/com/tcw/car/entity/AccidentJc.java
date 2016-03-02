package com.tcw.car.entity;

import java.util.List;

/**
 * @desc 事故检查
 * Created by zhaoyc on 2015/1/27 0027.
 */
public class AccidentJc {

    private List<Photos> photos;

    /**
     * 发动机
     */
    private String engineType;

    /**
     * 车身覆盖件是否更换(0否，1是)
     */
    private String is_chengeSurface;

    /**
     * 此车是否有需要修复部件(0无，1有)
     */
    private String is_repair;
    /**
     * 车身覆盖件照片
     */
    private List<BaseImg> surfacePhoto;

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getIs_chengeSurface() {
        return is_chengeSurface;
    }

    public void setIs_chengeSurface(String is_chengeSurface) {
        this.is_chengeSurface = is_chengeSurface;
    }

    public String getIs_repair() {
        return is_repair;
    }

    public void setIs_repair(String is_repair) {
        this.is_repair = is_repair;
    }

	public List<BaseImg> getSurfacePhoto() {
		return surfacePhoto;
	}

	public void setSurfacePhoto(List<BaseImg> surfacePhoto) {
		this.surfacePhoto = surfacePhoto;
	}
    
}
