package com.styleTransfer.dao;

import com.styleTransfer.domain.Img;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgMapper {
    @Select("select id, path, owner from img where owner = #{ownerName}")
    public List<Img> getImgByOwner(String ownerName);

    @Insert("insert into img(path, owner) values(#{path}, #{owner})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertImg(Img img);
}
