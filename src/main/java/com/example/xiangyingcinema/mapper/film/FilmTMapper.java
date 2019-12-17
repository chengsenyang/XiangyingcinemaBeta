package com.example.xiangyingcinema.mapper.film;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.xiangyingcinema.model.film.FilmT;
import com.example.xiangyingcinema.vo.film.FilmDetailVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 */
public interface FilmTMapper extends BaseMapper<FilmT> {

    FilmDetailVO getFilmDetailByName(@Param("filmName") String filmName);

    FilmDetailVO getFilmDetailById(@Param("uuid") String uuid);

}